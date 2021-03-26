package films.CLI;

import static java.lang.Integer.parseInt;

import java.util.Scanner;

import films.services.FilmCatalogImpl;
import films.services.IFilmCatalog;

public class UserInterface {

    public static void main(String[] args) {
        int option = -1;
        Scanner scanner = new Scanner(System.in);

        IFilmCatalog catalog = new FilmCatalogImpl();

        while (option != 0) {
            System.out.println("\n--> Welcome to FILM CATALOGUE APP. In order to continue, pleasw, choose an option: \n"
                + "\t 1. Start a new film catalogue\n"
                + "\t 2. Add a new film\n"
                + "\t 3. Show films on catalogue\n"
                + "\t 4. Find a film\n"
                + "\t 0. Close session and leave\n"
            );
            
            try {
                option = parseInt(scanner.nextLine());
            } catch (Exception ex) {
                System.out.println("Introduce a valid value!\n");
            }

            switch(option) {
                case 1:
                    catalog.startFilmCatalog();
                    break;
                case 2:
                    System.out.println("Introduce a Film name: \n");
                    String filmName = scanner.nextLine();
                    catalog.addFilm(filmName);
                    break;
                case 3:
                    catalog.listFilms();
                    break;
                case 4:
                    System.out.println("Introduce a name to find a Film: \n");
                    String desiredFilm = scanner.nextLine();
                    catalog.findFilm(desiredFilm);
                    break;
                case 0:
                    System.out.println("Thanks for use our software! See you next time! :)");
                    break;
                default:
                    System.out.println("Not a valid option");
                    break;
            }
        }
    }
    
}
