package springbook.user.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import springbook.user.dao.UserDao;
import springbook.user.domain.Grade;
import springbook.user.domain.User;

import java.util.List;

public class UserServiceImpl implements UserService{
    UserDao userDao;
    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;
    private MailSender mailSender;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void upgradeGrades(){
        List<User> users = userDao.getAll();
        
        for (User user : users) {
            if (canUpgradeGrade(user)) {
                upgradeGrade(user);
            }
        }
    }

    public boolean canUpgradeGrade(User user) {
        Grade currentGrade = user.getGrade();
        switch (currentGrade) {
            case BASIC:
                return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
            case SILVER:
                return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
            case GOLD:
                return false;
            default:
                throw new IllegalArgumentException("Unknown Grade : " + currentGrade);
        }
    }

    public void upgradeGrade(User user) {
        user.upgradeGrade();
        userDao.update(user);
        sendUpgradeEMail(user);
    }

    private void sendUpgradeEMail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("useradmin@ksug.org");
        mailMessage.setSubject("Upgrade 안내");
        mailMessage.setText("사용자의 등급이 " + user.getGrade().name());
        System.out.println(this.mailSender + "");
        this.mailSender.send(mailMessage);
    }

    public void add(User user) {
        if (user.getGrade() == null) user.setGrade(Grade.BASIC);
        userDao.add(user);
    }
}
