package pl.edu.wszib;

import pl.edu.wszib.model.Book;

import java.util.List;
import java.util.Scanner;

// TODO Sprawdzić czy wszystko działa
// TODO Logowanie użytkownika od APK
// TODO Hashowanie hasła użytkownika
// TODO Najpierw login i hasło potem wyświetlenie MENU

public class LibraryApp {
    private final LibraryLogic libraryLogic;
    private final Scanner scanner;

    private LibraryApp() {
        this.libraryLogic = new LibraryLogic();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean exitFlipper = true;
        while (exitFlipper) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    listAllBooks();
                    break;
                case 2:
                    searchBooks();
                    break;
                case 3:
                    listBorrowedBooks();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    listOverdueBooks();
                    break;
                case 6:
                    addBook();
                    break;
                case 7:
                    removeBook();
                    break;
                case 0:
                    saveBooksToFile();
                    System.out.println("Wychodzenie z programu.");
                    exitFlipper = false;
                    break;
                default:
                    System.out.println("Zły wybór! Podaj poprawną wartość.");
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

    private void searchBooks() {
        System.out.print("Wprowadź szukaną książkę: ");
        String query = scanner.nextLine();

        List<Book> searchResults = libraryLogic.searchBooks(query);
        displayBooks(searchResults);
    }

    private void borrowBook() {
        System.out.print("Wprowadź ISBN książki którą pożyczasz: ");
        String isbn = scanner.nextLine();
        System.out.print("Wprowadź swoje imię: ");
        String borrowerName = scanner.nextLine();
//        System.out.println("Wprowadź datę zwrotu (RRRR-MM-DD): ");
//        String returnDate = scanner.nextLine();

        libraryLogic.borrowBook(isbn, borrowerName);
        System.out.println("Książka wypożyczona pomyślnie!");
    }

    private void listBorrowedBooks() {
        List<Book> borrowedBooks = libraryLogic.getBorrowedBooks();
        displayBooks(borrowedBooks);
    }

    private void listOverdueBooks() {
        List<Book> overdueBooks = libraryLogic.getOverdueBooks();
        displayBooks(overdueBooks);
    }

    private void listAllBooks() {
        List<Book> allBooks = libraryLogic.getAllBooks();
        displayBooks(allBooks);
    }

    private void removeBook() {
        System.out.print("Wprowadź ISBN książki do usunięcia: ");
        String isbn = scanner.nextLine();

        libraryLogic.removeBook(isbn);
        System.out.println("Książka usunięta pomyślnie!");
    }

    public void displayMenu() {
        System.out.println("\n   Witaj w systemie do zarządzania biblioteką!");
        System.out.println("   MENU:");
        System.out.println("1. Lista wszystkich książek // Działa");
        System.out.println("2. Szukaj książek // Działa");
        System.out.println("3. Lista pożyczonych książek // Działa");
        System.out.println("4. Pożycz książkę // Działa");
        System.out.println("5. Lista nieoddanych książek // Nie działa");
        System.out.println("6. Dodaj książkę // Działa");
        System.out.println("7. Usuń książkę // Działa");
        System.out.println("0. Wyjście");
        System.out.print("   Wybrana opcja: ");
    }

    private void displayBooks(List<Book> books) {
        System.out.println("\nLista książek:");
        books.forEach(book -> {
            System.out.println("Tytuł: " + book.getTitle());
            System.out.println("Autor: " + book.getAuthor());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Wypożyczony: " + (book.isBorrowed() ? "Tak" : "Nie"));
            if (book.isBorrowed()) {
                System.out.println("Wypożyczający: " + book.getBorrowerName());
                System.out.println("Data wypożyczenia: " + book.getBorrowDate());
                System.out.println("Data zwrotu: " + book.getReturnDate());
            }
            System.out.println("-------------------------------");
        });
    }

    private void saveBooksToFile() {
        libraryLogic.saveBooksToFile();
    }

    public static void main(String[] args) {
        LibraryApp libraryApp = new LibraryApp();
        libraryApp.run();
    }
}
