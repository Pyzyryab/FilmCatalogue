package films.services;

import java.util.List;

import films.data_handling.DataAccessImpl;
import films.data_handling.IDataAccess;
import films.domain.Film;
import films.exceptions.DataAccessException;
import films.exceptions.FileDontExistsException;
import films.exceptions.ReadDataException;
import films.exceptions.WriteDataException;

public class FilmCatalogImpl implements IFilmCatalog {

    private final IDataAccess data;

    public FilmCatalogImpl() {
        this.data = new DataAccessImpl();
    }

    @Override
    public void addFilm(String filmName) {
        // Convert the film name to our custom TYPO "Film"
        Film film = new Film(filmName);

        // Checks if file is already created
        boolean overrideContent = false;
        try {
            overrideContent = this.data.exists(RESOURCE_NAME);
            this.data.write(film, RESOURCE_NAME, overrideContent);

        } catch (DataAccessException | WriteDataException ex) {
            System.out.println("Error while accesing data.");
            ex.printStackTrace(System.out);
        } 
    }

    @Override
    public void findFilm(String desiredFilm) {
        String result = "";
        try {
            result = this.data.find(RESOURCE_NAME, desiredFilm);
        } catch (ReadDataException ex) {
            ex.printStackTrace();
            System.out.println("Error while finding the desired film");
        }
        System.out.println("Result -> " + result);
    }

    @Override
    public void listFilms() {
        try {
            List<Film> filmsList = this.data.listFilms(RESOURCE_NAME);
            for (Film film : filmsList) {
                System.out.println(film);
            }
        } catch (ReadDataException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Data access error");
        }
    }

    @Override
    public void startFilmCatalog() {
        try {
            if (this.data.exists(RESOURCE_NAME)) {
                this.data.delete(RESOURCE_NAME);
                this.data.create(RESOURCE_NAME);
            } else {
                this.data.create(RESOURCE_NAME);
            }
        } catch (DataAccessException | FileDontExistsException ex) {
            ex.printStackTrace(System.out);
            System.out.print("Error while initializating a new film catalog.");
        }
        
    }
}
