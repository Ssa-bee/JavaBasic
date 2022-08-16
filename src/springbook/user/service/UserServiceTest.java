package springbook.user.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import springbook.user.dao.UserDao;
import springbook.user.domain.Grade;
import springbook.user.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static springbook.user.service.UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER;
import static springbook.user.service.UserServiceImpl.MIN_RECOMMEND_FOR_GOLD;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "applicationContext.xml")
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    UserDao userDao;
    @Autowired
    PlatformTransactionManager transactionManager;
    @Autowired
    MailSender mailSender;
    @Autowired
    ApplicationContext context;
    List<User> users;

    @Before
    public void setUp() {
        users = Arrays.asList(
                new User("bumjin", "박범진", "p1", Grade.BASIC, MIN_LOGCOUNT_FOR_SILVER - 1, 0, "test@test.com"),
                new User("joytouch", "강명성", "p2", Grade.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0, "test1@test.com"),
                new User("erwins", "신승한", "p3", Grade.SILVER, 60, MIN_RECOMMEND_FOR_GOLD - 1, "test2@test.com"),
                new User("madnite1", "이상호", "p4", Grade.SILVER, 60, MIN_RECOMMEND_FOR_GOLD, "test3@test.com"),
                new User("green", "오민규", "p5", Grade.GOLD, 100, Integer.MAX_VALUE, "test4@test.com"));
    }

    @Test
    public void upgradeGrades() throws Exception {
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        MockUserDao mockUserDao = new MockUserDao(this.users);
        userServiceImpl.setUserDao(mockUserDao);

        MockMailSender mockMailSender = new MockMailSender();
        userServiceImpl.setMailSender(mockMailSender);

        userServiceImpl.upgradeGrades();

        List<User> updated = mockUserDao.getUpdated();
        assertThat(updated.size(), is(2));
        checkUserAndGrade(updated.get(0), "joytouch", Grade.SILVER);
        checkUserAndGrade(updated.get(1), "madnite1", Grade.GOLD);

        List<String> request = mockMailSender.getRequests();
        assertThat(request.size(), is(2));
        assertThat(request.get(0), is(users.get(1).getEmail()));
        assertThat(request.get(1), is(users.get(3).getEmail()));
    }

    @Test
    public void add() {
        userDao.deleteAll();

        User userWithGrade = users.get(4);
        User userWithoutGrade = users.get(0);
        userWithoutGrade.setGrade(null);

        userService.add(userWithGrade);
        userService.add(userWithoutGrade);

        User userWithGradeRead = userDao.get(userWithGrade.getId());
        User userWithoutGradeRead = userDao.get(userWithoutGrade.getId());

        assertThat(userWithGradeRead.getGrade(), is(userWithGrade.getGrade()));
        assertThat(userWithoutGradeRead.getGrade(), is(userWithoutGrade.getGrade()));
    }

    @Test
    @DirtiesContext
    public void upgradeAllOrNothing() throws Exception {
        TestUserService testUserService = new TestUserService(users.get(3).getId());
        testUserService.setUserDao(this.userDao);;
        testUserService.setMailSender(mailSender);

        ProxyFactoryBean txProxyFactoryBean = context.getBean("&userService", ProxyFactoryBean.class);
        txProxyFactoryBean.setTarget(testUserService);
        UserService txUserService = (UserService) txProxyFactoryBean.getObject();
        userDao.deleteAll();
        for (User user : users) userDao.add(user);

        try {
            txUserService.upgradeGrades();
            fail("TestUserServiceException expected");
        } catch (TestUserServiceException e) {
        }

        checkGradeUpgraded(users.get(1), false);
    }

    private void checkUserAndGrade(User updated, String expectedId, Grade expectedGrade) {
        assertThat(updated.getId(), is(expectedId));
        assertThat(updated.getGrade(), is(expectedGrade));
    }
    private void checkGradeUpgraded(User user, boolean upgraded) {
        User userUpdate = userDao.get(user.getId());
        if (upgraded) {
            assertThat(userUpdate.getGrade(), is(user.getGrade().nextGrade()));
        } else {
            assertThat(userUpdate.getGrade(), is(user.getGrade()));
        }
    }

    static class TestUserService extends UserServiceImpl {
        private String id;

        private TestUserService(String id) {
            this.id = id;
        }

        public void upgradeGrade(User user) {
            if (user.getId().equals(this.id)) throw new TestUserServiceException();
            super.upgradeGrade(user);
        }
    }

    static class MockUserDao implements UserDao {
        private List<User> users;
        private List<User> updated = new ArrayList();

        public MockUserDao(List<User> users) {
            this.users = users;
        }

        public List<User> getUpdated() {
            return updated;
        }

        @Override
        public void add(User user) {
            throw new UnsupportedOperationException();
        }

        @Override
        public User get(String id) {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<User> getAll() {
            return this.users;
        }

        @Override
        public void deleteAll() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int getCount() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void update(User user) {
            updated.add(user);
        }
    }

    static class TestUserServiceException extends RuntimeException {

    }
}