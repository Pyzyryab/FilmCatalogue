package films.data_handling;

import java.util.List;

import films.exceptions.*;
import films.domain.Film;


public interface IDataAccess {

    boolean exists(String resourceName) throws DataAccessException;
    
    List<Film> listFilms(String resourceName) throws ReadDataException;
   
    String find(String resourceName, String desiredFilm) throws ReadDataException;

    void write(Film film, String resourceName, boolean overrideContent) throws WriteDataException;

    void create(String resourceName) throws DataAccessException;

    void delete(String resourceName) throws DataAccessException, FileDontExistsException;

}
