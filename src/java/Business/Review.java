package Business;

import java.sql.Date;
import java.util.Objects;

public class Review {

    //Declare Variables
    private int reviewID;
    private int movieID;
    private int userID;
    private String reviewText;
    private double reviewRating;
    private Date reviewDate;

    /**
     * Parameterized Constructor
     *
     * @param reviewID Review ID
     * @param movieID Movie ID
     * @param userID User ID
     * @param reviewText Review Text
     * @param reviewRating Review Rating
     * @param reviewDate Review Date
     */
    public Review(int reviewID, int movieID, int userID, String reviewText, double reviewRating, Date reviewDate) {
        this.reviewID = reviewID;
        this.movieID = movieID;
        this.userID = userID;
        this.reviewText = reviewText;
        this.reviewRating = reviewRating;
        this.reviewDate = reviewDate;
    }

    /**
     * Default Constructor
     */
    public Review() {
        this.reviewID = 1;
        this.movieID = 1;
        this.userID = 1;
        this.reviewText = "hello";
        this.reviewRating = 1;
        this.reviewDate = new Date(1998, 10, 10);

    }

    /**
     * Getter Method
     *
     * @return Review ID
     */
    public int getReviewID() {
        return reviewID;
    }

    /**
     * Setter Method
     *
     * @param reviewID Review ID
     */
    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    /**
     * Getter Method
     *
     * @return Movie ID
     */
    public int getMovieID() {
        return movieID;
    }

    /**
     * Setter Method
     *
     * @param movieID Movie ID
     */
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    /**
     * Getter Method
     *
     * @return User ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Setter Method
     *
     * @param userID User ID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Getter Method
     *
     * @return Review Text
     */
    public String getReviewText() {
        return reviewText;
    }

    /**
     * Setter Method
     *
     * @param reviewText Review Text
     */
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    /**
     * Getter Method
     *
     * @return Review Rating
     */
    public double getReviewRating() {
        return reviewRating;
    }

    /**
     * Setter Method
     *
     * @param reviewRating review rating
     */
    public void setReviewRating(double reviewRating) {
        this.reviewRating = reviewRating;
    }

    /**
     * Getter Method
     *
     * @return Review Date
     */
    public Date getReviewDate() {
        return reviewDate;
    }

    /**
     * Setter Method
     *
     * @param reviewDate Review Date
     */
    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    /**
     * Calculate the hashcode of this object, based only on its reviewID,
     * movieID, userID
     *
     * @return the hashcode for the Review object
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.reviewID;
        hash = 97 * hash + this.movieID;
        hash = 97 * hash + this.userID;
        return hash;
    }

    /**
     *
     * @param obj other Object, usually a Review, to compare with.
     * @return true if the objects are identical
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Review other = (Review) obj;
        if (this.reviewID != other.reviewID) {
            return false;
        }
        if (this.movieID != other.movieID) {
            return false;
        }
        if (this.userID != other.userID) {
            return false;
        }
        if (Double.doubleToLongBits(this.reviewRating) != Double.doubleToLongBits(other.reviewRating)) {
            return false;
        }
        if (!Objects.equals(this.reviewText, other.reviewText)) {
            return false;
        }
        return Objects.equals(this.reviewDate, other.reviewDate);
    }

    /**
     *
     * @return a string representation of Review object
     */
    @Override
    public String toString() {
        return "Review{" + "reviewID=" + reviewID + ", movieID=" + movieID + ", userID=" + userID + ", reviewText=" + reviewText + ", reviewRating=" + reviewRating + ", reviewDate=" + reviewDate + '}';
    }

}
