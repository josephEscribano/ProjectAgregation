package dao.springJDBC;

import dao.DAOReviews;
import model.Review;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SpringDAOReviews implements DAOReviews {
    @Override
    public Review get(int id) {
        return null;
    }

    @Override
    public List<Review> getAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        return null;
    }

    @Override
    public void save(Review t) {

    }

    @Override
    public void update(Review t) {

    }

    @Override
    public void delete(Review t) {

    }
}
