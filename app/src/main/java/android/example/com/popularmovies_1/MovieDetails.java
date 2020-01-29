package android.example.com.popularmovies_1;

public class MovieDetails {

    private String originalTitle;
    private String plotSynopsis;
    private String userRating;
    private String releaseDate;
    private  String thumbnail;
    private int movieId;


    public  MovieDetails(){

    }

public MovieDetails (String originalTitle, String plotSynopsis, String userRating, String releaseDate, String thumbnail, int movieId) {

    //Setters for movie details variables

    this.originalTitle = originalTitle;
    this.plotSynopsis = plotSynopsis;
    this.userRating = userRating;
    this.releaseDate = releaseDate;
    this.thumbnail = thumbnail;
    this.movieId = movieId;

}


    public void setoriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.plotSynopsis = plotSynopsis;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail;}

    public void setMovieId(int movieId) { this.movieId = movieId;}

    //Getters for movie details variables
    
    public String getoriginalTitle() {
        return originalTitle;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public String  getUserRating() {
        return userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getMovieId(){ return movieId;}


}

