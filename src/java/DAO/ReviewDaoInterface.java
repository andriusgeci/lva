package DAO;

import Business.Review;
import Exceptions.DaoException;
import java.util.ArrayList;

/**
 * This method is interface of ReviewDao class.
 */
public interface ReviewDaoInterface {

    /**
     * This method to display all existing reviews in the database.
     *
     * @return an ArrayList of review objects.
     * @throws DaoException if message: "SQLException occurred in the
     * findAllReviews()" seen, error occurred by searching all reviews details
     * stage. If message: "SQLException occurred in the findAllReviews() final"
     * seen, database is not accessible.
     */
    public ArrayList<Review> findAllReviews() throws DaoException;

    /**
     * This method will add a review that user have left about the movie
     *
     * @param rev The review to be added to the database.
     * @return true if the review information was added successfully, false
     * otherwise.
     * @throws DaoException if message: "SQLException occurred in the
     * addRieview()" seen, error occurred by adding review information stage. If
     * message: "SQLException occurred in the addReview() final" seen, database
     * is not accessible.
     */
    public boolean addReview(Review rev) throws DaoException;

    /**
     * This method will delete a review left by the user. Mostly for admin to
     * deal with bad language.
     *
     * @param rev The review id to be deleted from database.
     * @return true if the review information deleted successfully, false
     * otherwise.
     * @throws DaoException If message: "Exception occurred in the ROLLBACK
     * deleteReviewById" or "SQLException deleteReviewById()" seen, error
     * occurred by deleting review information stage. If message: "SQLException
     * occurred in the deleteReview() final" seen, database is not accessible.
     */
    public boolean deleteReviewById(int rev) throws DaoException;

    /**
     * This method to search review information by the review id.
     *
     * @param revieID The review id to search for review information in the
     * database.
     * @return review information by review id.
     * @throws DaoException If message: "SQLException occurred in the
     * findReviewById()" seen, error occurred by searching review information
     * stage. If message: "SQLException occurred in the findReviewById() final"
     * seen, database is not accessible.
     */
    public Review findReviewById(int revieID) throws DaoException;

    /**
     * This method to update review information in the database.
     *
     * @param rev The review information to be updated.
     * @return true if the review information was updated successfully, false
     * otherwise.
     * @throws DaoException If message: "SQLException occurred in the
     * updateReview() method" seen, error occurred by updating review
     * information stage. If message: "SQLException occurred in the
     * updateReview() method final" seen, database is not accessible.
     */
    public boolean updateReview(Review rev) throws DaoException;

    /**
     * This method takes in user id and will return user name if id exists in
     * the database.
     *
     * @param usrID The user id to search for first user name in the database.
     * @return First user name by user id.
     * @throws DaoException If message: "SQLException occurred in the
     * findFirstNameByUserId()" seen, error occurred by searching user name
     * stage. If message: "SQLException occurred in the findFirstNameByUserId()
     * final" seen, database is not accessible.
     */
    public String findFirstNameByUserId(int usrID) throws DaoException;
}
