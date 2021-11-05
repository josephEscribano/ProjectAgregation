package dao.springJDBC;

import dao.DAOReviews;
import dao.DBConPool;
import dao.springJDBC.mappers.ReviewsMapper;
import model.Review;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Querys;

import java.util.List;

public class SpringDAOReviews implements DAOReviews {
    @Override
    public Review get(int id) {
        return null;
    }

    @Override
    public List<Review> getAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConPool.getInstance().getDataSource());


        return jdbcTemplate.query(Querys.SELECT_REVIEW_QUERY,new ReviewsMapper());
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
