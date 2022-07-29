package springbook.user.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springbook.user.domain.Grade;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userMapper = new RowMapper<User>(){

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setGrade(Grade.valueOf(rs.getInt("grade")));
            user.setLogin(rs.getInt("login"));
            user.setRecommend(rs.getInt("recommend"));
            return user;
        }
    };

    public void add(final User user){
        this.jdbcTemplate.update("insert into users(id, name, password, grade, login, recommend) values(?, ?, ?, ?, ?, ?)",
                user.getId(), user.getName(), user.getPassword(), user.getGrade().intValue(), user.getLogin(), user.getRecommend());
    }

    public User get(String id){
        return this.jdbcTemplate.queryForObject("select * from users where id = ?",
                new Object[]{id}, this.userMapper);
    }

    public void deleteAll(){
        this.jdbcTemplate.update("delete from users");
    }

    public int getCount(){
        return this.jdbcTemplate.queryForInt("select count(*) from users");
    }

    public List<User> getAll(){
        return this.jdbcTemplate.query("select * from users order by id",
                this.userMapper);
    }

    public void update(User user){
        this.jdbcTemplate.update("update users set name = ?, password = ?, grade = ?, login = ?, recommend = ? where id = ?",
                user.getName(), user.getPassword(), user.getGrade().intValue(), user.getLogin(), user.getRecommend(), user.getId());
    }
}