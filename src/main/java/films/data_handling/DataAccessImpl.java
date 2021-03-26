package films.data_handling;

import java.io.*;
import java.util.*;

import films.domain.Film;
import films.exceptions.DataAccessException;
import films.exceptions.ReadDataException;
import films.exceptions.WriteDataException;
import films.exceptions.FileDontExistsException;

public class DataAccessImpl implements IDataAccess {

    @Override
    public boolean exists(String resourceName) throws DataAccessException {
        File file = new File(resourceName);
        return file.exists();
    }

    @Override
    public List<Film> listFilms(String resourceName) throws ReadDataException {
        File file = new File(resourceName);
        List<Film> filmsList = new ArrayList<>();

        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            
            String line;
            line = input.readLine();
            
            while (line != null) {
                Film film = new Film(line);
                filmsList.add(film);
                line = input.readLine();
            }
            input.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new ReadDataException("Failed when tried to list Films: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new ReadDataException("Failed when tried to read lines on file: " + ex.getMessage());
        } 
        return filmsList;
    }

    @Override
    public void write(Film film, String resourceName, boolean overrideContent) throws WriteDataException {
        File file = new File(resourceName);

        try {
            PrintWriter output = new PrintWriter(new FileWriter(file, overrideContent));
            output.println(film.toString());
            output.close();
            System.out.println("Added new film to the registry. Film: " + film);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new WriteDataException("Something went wrong when trying to add a new film: " + ex.getMessage());
        }
        
    }
   
    @Override
    public String find(String resourceName, String desiredFilm) throws ReadDataException {
        File file = new File(resourceName);
        String result = "";

        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String line = input.readLine();
            int index = 1;

            while (line != null) {
                if(desiredFilm != null && desiredFilm.equalsIgnoreCase(line)) {
                    result = "Film " + "line + founded on line " + index;
                    break;
                }
                line = input.readLine();
                index++;
            }
            input.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new ReadDataException("Error while trying to read the provided file: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }    

    @Override
    public void create(String resourceName) throws DataAccessException {
        File file = new File(resourceName);
        try {
            PrintWriter output = new PrintWriter(new FileWriter(file));
            output.close();
            System.out.println("New file created: " + resourceName);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new DataAccessException("Error while creating the file: " + ex.getMessage());
        }
    }

    @Override
    public void delete(String resourceName) throws DataAccessException, FileDontExistsException {
        File file = new File(resourceName);
        if (file.exists()) {
            file.delete();
            System.out.println("File deleted succesfully!");
        } else {
            throw new FileDontExistsException("Not founded the given file or path to file.");
        }
    }

}
