package Business;

import java.util.Objects;

public class Movie implements Comparable<Movie> {

    private int movieId;
    private String title;
    private String description;
    private int releaseYear;
    private String ageRating;
    private int movieLegth;
    private String imgUrl;
    private double price;
    private int quantityInStock;
    private double ratingAvg;
    private int ratingNum;
    private long ratingTotal;

    /**
     * default constructor
     */
    public Movie() {
    }

    /**
     * Parameterized full constructor
     *
     * @param movieId new Movie ID
     * @param title new Movie Title
     * @param description new Movie Description
     * @param releaseYear new Movie Release Year
     * @param ageRating new Movie Age Rating
     * @param movieLegth new Movie Length
     * @param imgUrl new Movie Picture URL
     * @param price new Movie Price
     * @param quantityInStock new Movie Quantity
     * @param ratingAvg new Rating Average
     * @param ratingNum new Total User Count For rating
     * @param ratingTotal new Total User Rating For rating
     */
    public Movie(int movieId, String title, String description, int releaseYear, String ageRating, int movieLegth, String imgUrl, double price, int quantityInStock, double ratingAvg, int ratingNum, long ratingTotal) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.ageRating = ageRating;
        this.movieLegth = movieLegth;
        this.imgUrl = imgUrl;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.ratingAvg = ratingAvg;
        this.ratingNum = ratingNum;
        this.ratingTotal = ratingTotal;
    }

    /**
     * Parameterized constructor without movieId; Movie id in SQL has auto
     * increment property;
     *
     * @param title new Movie Title
     * @param description new Movie Description
     * @param releaseYear new Movie Release Year
     * @param ageRating new Movie Age Rating
     * @param movieLegth new Movie Length
     * @param imgUrl new Movie Picture URL
     * @param price new Movie Price
     * @param quantityInStock new Movie Quantity
     */
    public Movie(String title, String description, int releaseYear, int movieLegth, String ageRating, String imgUrl, double price, int quantityInStock) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.movieLegth = movieLegth;
        this.ageRating = ageRating;
        this.imgUrl = imgUrl;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    /**
     * Getter Method
     *
     * @return Movie ID
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * Getter Method
     *
     * @return Movie Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter Method
     *
     * @return Movie Release Year
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Getter Method
     *
     * @return Movie Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter Method
     *
     * @return Movie Length
     */
    public int getMovieLegth() {
        return movieLegth;
    }

    /**
     * Getter Method
     *
     * @return Movie Age Rating
     */
    public String getAgeRating() {
        return ageRating;
    }

    /**
     * Getter Method
     *
     * @return Movie Picture URL
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * Getter Method
     *
     * @return Movie Price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Getter Method
     *
     * @return Movie Quantity
     */
    public int getQuantityInStock() {
        return quantityInStock;
    }

    /**
     * Setter Method
     *
     * @param movieId Movie ID
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    /**
     * Getter Method
     *
     * @return Movie Rating Average
     */
    public double getRatingAvg() {
        return ratingAvg;
    }

    /**
     * Getter Method
     *
     * @return Total User Count For rating
     */
    public int getRatingNum() {
        return ratingNum;
    }

    /**
     * Getter Method
     *
     * @return Total User Rating
     */
    public long getRatingTotal() {
        return ratingTotal;
    }

    /**
     * Setter Method
     *
     * @param title Movie Title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter Method
     *
     * @param description Movie Description text
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter Method
     *
     * @param releaseYear Movie Release Year
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Setter Method
     *
     * @param movieLegth Movie Length
     */
    public void setMovieLegth(int movieLegth) {
        this.movieLegth = movieLegth;
    }

    /**
     * Setter Method
     *
     * @param ageRating Movie age Rating
     */
    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    /**
     * Setter Method
     *
     * @param imgUrl Movie Picture URL
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * Setter Method
     *
     * @param price Movie Price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Setter Method
     *
     * @param quantityInStock Quantity in Stock
     */
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    /**
     * Setter Method
     *
     * @param ratingAvg Average rating of Movie
     */
    public void setRatingAvg(double ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    /**
     * Setter Method
     *
     * @param ratingNum Total number count of users left review
     */
    public void setRatingNum(int ratingNum) {
        this.ratingNum = ratingNum;
    }

    /**
     * Setter Method
     *
     * @param ratingTotal Movie rating Total
     */
    public void setRatingTotal(long ratingTotal) {
        this.ratingTotal = ratingTotal;
    }

    /**
     * Calculate the hashcode of this object, based only on its movieId and
     * movieLegth
     *
     * @return the hashcode for the Movie object
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.movieId;
        hash = 37 * hash + Objects.hashCode(this.title);
        hash = 37 * hash + this.movieLegth;
        return hash;
    }

    /**
     *
     * @param obj other Object, usually a Movie, to compare with.
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
        final Movie other = (Movie) obj;
        if (this.movieId != other.movieId) {
            return false;
        }
        if (this.movieLegth != other.movieLegth) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return a string representation of Movie object
     */
    @Override
    public String toString() {
        return "Movie{" + "movieId=" + movieId + ", title=" + title + ", description=" + description + ", releaseYear=" + releaseYear + ", ageRating=" + ageRating + ", movieLegth=" + movieLegth + ", imgUrl=" + imgUrl + ", price=" + price + ", quantityInStock=" + quantityInStock + ", ratingAvg=" + ratingAvg + ", ratingNum=" + ratingNum + ", ratingTotal=" + ratingTotal + '}';
    }

    /**
     * compareTo method to compare Movie objects, to sort them in alphabetical
     * order by title
     *
     * @param m Movie object to compare
     * @return 1,-1,0 depending on movie Object
     */
    public int compareTo(Movie m) {
        return (this.title.compareTo(m.getTitle()));
    }
}
