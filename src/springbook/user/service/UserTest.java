package springbook.user.service;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import springbook.user.domain.Grade;
import springbook.user.domain.User;

public class UserTest {
    User user;

    @Before
    public void setUp(){
        user = new User();
    }

    @Test
    public void upgradeGrade(){
        Grade[] grades = Grade.values();
        for (Grade grade : grades) {
            if (grade.nextGrade() == null) {
                continue;
            }
            user.setGrade(grade);
            user.upgradeGrade();
            assertThat(user.getGrade(), is(grade.nextGrade()));
        }
    }

    @Test(expected = IllegalStateException.class)
    public void cannotUpgradeGrade(){
        Grade[] grades = Grade.values();
        for (Grade grade :
                grades) {
            if (grade.nextGrade() != null) continue;
            user.setGrade(grade);
            user.upgradeGrade();
        }
    }
}