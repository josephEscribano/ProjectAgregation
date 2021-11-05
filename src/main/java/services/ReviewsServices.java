/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.List;

import dao.DAOFactory;
import model.Customer;
import model.Review;

/**
 *
 * @author Laura
 */
public class ReviewsServices {


    private DAOFactory dao;

    public ReviewsServices(DAOFactory dao) {
        this.dao = dao;
    }

    public List<Review> getAllReviews() {

        return dao.getDAOReviews().getAll();
    }

    public void deleteReview(Customer customer, Review review) {
    }

    public ArrayList<String> searchByItem(int id) {
        ArrayList<String> st =  null;
        return st;
    }

    public Review createReview() {
        Review rev = null;
        return rev;    }

    public void addReview(Customer customer, Review review) {

    }
}
