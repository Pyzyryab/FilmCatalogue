package films.services;

public interface IFilmCatalog {

    String RESOURCE_NAME = "films.txt";

    void addFilm(String filmName);

    void listFilms();

    void findFilm(String desiredFilm);

    void startFilmCatalog();

}