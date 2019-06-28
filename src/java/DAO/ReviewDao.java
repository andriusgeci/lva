package DAO;

import Business.Review;
import Exceptions.DaoException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class ReviewDao to work with user reviews about the movie. Class ReviewDao
 * implements interface of name of ReviewDaoInterface.
 */
public class ReviewDao extends Dao implements ReviewDaoInterface {

    /**
     * This method to display all existing reviews in the database.
     *
     * @return an ArrayList of review objects.
     * @throws DaoException if message: "SQLException occurred in the
     * findAllReviews()" seen, error occurred by searching all reviews details
     * stage. If message: "SQLException occurred in the findAllReviews() final"
     * seen, database is not accessible.
     */
    @Override
    public ArrayList<Review> findAllReviews() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Review> reviews = new ArrayList<Review>();

        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM Reviews";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next()) {
                int reviewID = rs.getInt("review_id");
                int movieID = rs.getInt("movie_id");
                int userID = rs.getInt("user_id");
                String reviewText = rs.getString("review_text");
                double reviewRating = rs.getDouble("review_rating");
                java.sql.Date reviewDate = rs.getDate("reviews_date");

                Review rev = new Review(reviewID, movieID, userID, reviewText, reviewRating, reviewDate);
                reviews.add(rev);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException occurred in the findAllReviews() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("SQLException occurred in the findAllReviews() final" + e.getMessage());
            }
        }
        return reviews;
    }

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
    @Override
    public boolean addReview(Review rev) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        if (rev != null) {
            if (findReviewById(rev.getReviewID()) == null) {
                auditDao censoredTextFinish = new auditDao();
                try {
                    con = this.getConnection();
                    String query = "INSERT INTO reviews(movie_id, user_id, review_text, review_rating ) VALUES(?,?,?,?)";
                    ps = con.prepareStatement(query);
                    ps.setInt(1, rev.getMovieID());
                    ps.setInt(2, rev.getUserID());
                    ps.setString(3, censoredTextFinish.censoredText(rev.getReviewText()));
                    ps.setDouble(4, rev.getReviewRating());
                    ps.execute();
                } catch (SQLException e) {
                    throw new DaoException("SQLException occurred in the addRieview():" + e.getMessage());
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                        if (con != null) {
                            freeConnection(con);
                        }
                    } catch (SQLException e) {
                        throw new DaoException("SQLException occurred in the addReview() final:" + e.getMessage());
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

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
    @Override
    public boolean deleteReviewById(int rev) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;

        if (rev != 0) {
            try {
                con = this.getConnection();
                con.setAutoCommit(false);

                con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

                CallableStatement cs = null;
                String query = "{call remove_review_update_movieRating_procedure( ? )}";
                cs = con.prepareCall(query);
                cs.setInt(1, rev);
                cs.execute();

                query = "DELETE FROM reviews WHERE review_id = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, rev);

                ps.execute();
                //Commiting transaction
                con.commit();
            } catch (SQLException e) {
                try {
                    //start rollbacck
                    con.rollback();
                } catch (SQLException ex1) {
                    System.out.println("Exception occurred in the ROLLBACK deleteReviewById" + ex1.getMessage());
                }
                throw new DaoException(" SQLException deleteReviewById(): " + e.getMessage());
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        freeConnection(con);
                    }
                } catch (SQLException e) {
                    throw new DaoException("SQLException occurred in the deleteReview() final:" + e.getMessage());
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method to search review information by the review id.
     *
     * @param inpReviewID The review id to search for review information in the
     * database.
     * @return review information by review id.
     * @throws DaoException If message: "SQLException occurred in the
     * findReviewById()" seen, error occurred by searching review information
     * stage. If message: "SQLException occurred in the findReviewById() final"
     * seen, database is not accessible.
     */
    @Override
    public Review findReviewById(int inpReviewID) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Review rev = null;
        try {
            con = this.getConnection();

            String query = "SELECT * FROM REVIEWS WHERE review_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, inpReviewID);

            rs = ps.executeQuery();
            if (rs.next()) {
                int reviewID = rs.getInt("review_id");
                int movieID = rs.getInt("movie_id");
                int userID = rs.getInt("user_id");
                String reviewText = rs.getString("review_text");
                double reviewRating = rs.getDouble("review_rating");
                java.sql.Date reviewDate = rs.getDate("reviews_date");

                rev = new Review(reviewID, movieID, userID, reviewText, reviewRating, reviewDate);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException occurred in the findReviewById():" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("SQLException occurred in the findReviewById() final" + e.getMessage());
            }
        }
        return rev;
    }

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
    @Override
    public boolean updateReview(Review rev) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean updated = false;

        if (rev != null) {
            try {
                con = this.getConnection();

                String query = "UPDATE reviews SET  = ? , review_text = ? , review_rating = ? WHERE review_id = ?";
                ps = con.prepareStatement(query);

                ps.setString(1, rev.getReviewText());
                ps.setDouble(2, rev.getReviewRating());
                ps.setInt(3, rev.getReviewID());
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    updated = true;
                }

            } catch (SQLException e) {
                throw new DaoException("SQLException occurred in the updateReview() method:" + e.getMessage());
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        freeConnection(con);
                    }
                } catch (SQLException e) {
                    throw new DaoException("SQLException occurred in the updateReview() method final:" + e.getMessage());
                }
            }
        }
        return updated;
    }

    /**
     * This method takes in user id and will return user name if id exists in
     * the database.
     *
     * @param usrId The user id to search for first user name in the database.
     * @return First user name by user id.
     * @throws DaoException If message: "SQLException occurred in the
     * findFirstNameByUserId()" seen, error occurred by searching user name
     * stage. If message: "SQLException occurred in the findFirstNameByUserId()
     * final" seen, database is not accessible.
     */
    @Override
    public String findFirstNameByUserId(int usrId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String userName = null;
        try {
            con = this.getConnection();

            String query = "SELECT DISTINCT firstName FROM reviews r join users u on (r.user_id = u.userId) WHERE user_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, usrId);

            rs = ps.executeQuery();
            while (rs.next()) {
                userName = rs.getString("firstName");
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException occurred in the findFirstNameByUserId():" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("SQLException occurred in the findFirstNameByUserId() final" + e.getMessage());
            }
        }
        return userName;
    }

    /**
     * This method to search review information by the review id.
     *
     * @param userId
     * @return review information by user id.
     * @throws DaoException If message: "SQLException occurred in the
     * findReviewByUserId()" seen, error occurred by searching review
     * information stage. If message: "SQLException occurred in the
     * findReviewByUserId() final" seen, database is not accessible.
     */
    public ArrayList<Review> findReviewByUserId(int userId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Review> rev = new ArrayList();
        try {
            con = this.getConnection();

            String query = "SELECT * FROM REVIEWS WHERE user_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);

            rs = ps.executeQuery();
            if (rs.next()) {
                int reviewID = rs.getInt("review_id");
                int movieID = rs.getInt("movie_id");
                int userID = rs.getInt("user_id");
                String reviewText = rs.getString("review_text");
                double reviewRating = rs.getDouble("review_rating");
                java.sql.Date reviewDate = rs.getDate("reviews_date");

                Review revList = new Review(reviewID, movieID, userID, reviewText, reviewRating, reviewDate);
                rev.add(revList);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException occurred in the findReviewById():" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("SQLException occurred in the findReviewById() final" + e.getMessage());
            }
        }
        return rev;
    }
}
