package pl.edu.wszib;

import pl.edu.wszib.model.Book;
import pl.edu.wszib.model.User;
import pl.edu.wszib.repository.UserRepository;
import pl.edu.wszib.services.AuthService;

import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private final LibraryLogic libraryLogic;
    private AuthService authService;
    private final Scanner scanner;

    private LibraryApp() {
        this.authService = new AuthService(new UserRepository());
        this.libraryLogic = new LibraryLogic();
        userRepository.initializeData();
        this.authService = new AuthService(userRepository);
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        User loggedUser = login();
        while (loggedUser == null) {
            try {
                loggedUser = login();  // Proces logowania użytkownika
            } catch (SecurityException e) {
                System.out.println(e.getMessage());
                // Możesz dodać tutaj logikę ponownego logowania
                return;  // Zakończ, jeśli logowanie nie powiedzie się ponownie
            }
        }
        boolean exitFlag = false;
        while (!exitFlag) {
            displayMenu(loggedUser);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    listAllBooks();
                    break;
                case 2:
                    listBorrowedBooks();
                    break;
                case 3:
                    searchBooks();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    if (isUserAdmin(loggedUser)) {
                        listOverdueBooks();
                    } else {
                        System.out.println("Ta operacja dostępna jest tylko dla administratora.");
                    }
                    break;
                case 6:
                    if (isUserAdmin(loggedUser)) {
                        addBook();
                    } else {
                        System.out.println("Ta operacja dostępna jest tylko dla administratora.");
                    }
                    break;
                case 7:
                    if (isUserAdmin(loggedUser)) {
                        removeBook();
                    } else {
                        System.out.println("Ta operacja dostępna jest tylko dla administratora.");
                    }
                    break;
                case 0:
                    saveBooksToFile();
                    System.out.println("Wychodzenie z programu.");
                    exitFlag = true;
                    break;
                default:
                    System.out.println("Nieznana opcja! Wybierz poprawną opcję.");
            }
        }
    }

    private void addBook() {
        System.out.print("Wprowadź tytuł: ");
        String title = scanner.nextLine();
        System.out.print("Wprowadź autora: ");
        String author = scanner.nextLine();
        System.out.print("Wprowadź ISBN: ");
        String isbn = scanner.nextLine();

        libraryLogic.addBook(new Book(title, author, isbn));

        System.out.println("Książka dodana pomyślnie!");
    }

    private void removeBook() {
        System.out.print("Wprowadź ISBN książki do usunięcia: ");
        String isbn = scanner.nextLine();

        libraryLogic.removeBook(isbn);
        System.out.println("Książka usunięta pomyślnie!");
    }

    private void searchBooks() {
        System.out.print("Wprowadź szukaną książkę: ");
        String query = scanner.nextLine();

        List<Book> searchResults = libraryLogic.searchBooks(query);
        displayBooks(searchResults);
        System.out.println("Naciśnij klawisz aby kontynuować..");
        scanner.nextLine();
    }

    private void borrowBook() {
        System.out.print("Wprowadź ISBN książki którą pożyczasz: ");
        String isbn = scanner.nextLine();
        System.out.print("Wprowadź swoje imię: ");
        String borrowerName = scanner.nextLine();
        System.out.print("Wprowadź swoje nazwisko: ");
        String borrowerSurname = scanner.nextLine();

        libraryLogic.borrowBook(isbn, borrowerName, borrowerSurname);
        System.out.println("Książka wypożyczona pomyślnie!");
    }

    private void displayBooks(List<Book> books) {
        System.out.printf("%-30s %-20s %-15s %-10s %-20s %-20s %-15s %-15s\n",
                "Tytuł", "Autor", "ISBN", "Wydana", "Imię wyopoży."
                ,"Nazwisko wypoży.", "Data wypoży.", "Data zwrotu");
        for (Book book : books) {
            System.out.printf("%-30s %-20s %-15s %-10s %-20s %-20s %-15s %-15s\n",
                    book.getTitle(),
                    book.getAuthor(),
                    book.getIsbn(),
                    book.isBorrowed() ? "Tak" : "Nie",
                    book.isBorrowed() ? book.getBorrowerName() : "",
                    book.isBorrowed() ? book.getBorrowerSurname() : "",
                    book.getBorrowDate() != null ? book.getBorrowDate().toString() : "",
                    book.getReturnDate() != null ? book.getReturnDate().toString() : "");
        }
    }

    private void listAllBooks() {
        List<Book> allBooks = libraryLogic.getAllBooks();
        displayBooks(allBooks);
        pause(); // Przerwa do czasu, aż użytkownik naciśnie Enter
    }

    private void listBorrowedBooks() {
        List<Book> borrowedBooks = libraryLogic.getBorrowedBooks();
        displayBooks(borrowedBooks);
        pause(); // Przerwa do czasu, aż użytkownik naciśnie Enter
    }

    private void listOverdueBooks() {
        List<Book> overdueBooks = libraryLogic.getOverdueBooks();
        displayBooks(overdueBooks);
        pause(); // Przerwa do czasu, aż użytkownik naciśnie Enter
    }

    private void pause() {
        System.out.println("Naciśnij Enter, aby kontynuować...");
        scanner.nextLine();
    }

    public void displayMenu(User loggedUser) {
        System.out.println("\n   Witaj w systemie do zarządzania biblioteką!");
        System.out.println("   MENU:");
        System.out.println("1. Lista wszystkich książek");
        System.out.println("2. Lista pożyczonych książek");
        System.out.println("3. Szukaj książkę");
        System.out.println("4. Pożycz książkę");
        if (isUserAdmin(loggedUser)){
            System.out.println("5. Lista książek z przekroczoną datą zwrotu");
            System.out.println("6. Dodaj książkę");
            System.out.println("7. Usuń książkę");
        }
        System.out.println("0. Wyjście");
        System.out.print("   Wybrana opcja: ");
    }

    private void saveBooksToFile() {
        libraryLogic.saveBooksToFile();
    }

    private User login() {
        User loggedUser = null;
        while (loggedUser == null) {
            System.out.print("Podaj nazwę użytkownika: ");
            String username = scanner.nextLine();

            System.out.print("Podaj hasło: ");
            String password = scanner.nextLine();

            try {
                loggedUser = authService.login(username, password);
                System.out.println("Logowanie powiodło się.");
            } catch (SecurityException e) {
                System.out.println("Nieprawidłowa nazwa użytkownika lub hasło.");
                System.out.print("Czy chcesz spróbować ponownie? (tak/nie): ");
                String response = scanner.nextLine().trim();
                if (!response.equalsIgnoreCase("tak")) {
                    break; // Wyjście z pętli i zwrócenie null
                }
            }
        }
        return loggedUser; // Może być null, jeśli użytkownik zrezygnuje z kolejnych prób
    }

    private boolean isUserAdmin(User user) {
        return "ADMIN".equals(user.getRole());
    }

    private static final UserRepository userRepository = new UserRepository();
    public static void main(String[] args) {
        userRepository.initializeData();
        LibraryApp libraryApp = new LibraryApp();
        libraryApp.run();
    }
}
