package films.domain;

import java.io.Serializable;

// This class is implemented as a JavaBean
public class Film implements Serializable{
    
    private String filmName;

    // Empty constructor :: JavaBean requeriment
    public Film() {
    }

    public Film(String filmName) {
        this.filmName = filmName;
    }

    // Getters and setters
    public String getFilmName() {
        return this.filmName;
    }
    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    // String representation ->
    @Override
    public String toString() {
        return this.filmName;
    }
    public String formattedFilmData() {
        StringBuilder sb = new StringBuilder();
        sb.append("Film -> {FilmName: ").append(this.filmName);
        sb.append("}");
        return sb.toString();
    }

}
