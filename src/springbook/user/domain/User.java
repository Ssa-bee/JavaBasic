package springbook.user.domain;

public class User {
    String id;
    String name;
    String password;
    Grade grade;
    int login;
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    int recommend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String id, String name, String password, Grade grade, int login, int recommend, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.grade = grade;
        this.login = login;
        this.recommend = recommend;
        this.email = email;
    }

    public User() {

    }

    public void upgradeGrade(){
        Grade nextGrade = this.grade.nextGrade();
        if (nextGrade == null) {
            throw new IllegalStateException(this.grade + "은 업그레이드가 불가능합니다.");
        }else{
            this.grade = nextGrade;
        }
    }
}
