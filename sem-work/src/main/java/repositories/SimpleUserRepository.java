package repositories;

import models.Operator;
import models.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SimpleUserRepository implements UserRepository {
    JdbcTemplate jdbcTemplate;

    public static final RowMapper<User> userRowMapper = (rs, i) -> {
        if (rs.getBoolean("isOperator")) {
            return new Operator(
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("email"));
        }
        return new User(
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("email"));
    };

    public SimpleUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO \"user\" values (?, ?, ?)",
                user.getLogin(),
                user.getPassword(),
                user.getEmail()
                );
    }
}
