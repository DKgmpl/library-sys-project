package pl.edu.wszib;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.model.Book;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class LibraryLogic {
    private List<Book> books;

    public LibraryLogic() {
        this.books = new ArrayList<>();
        loadBooksFromFile();
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooksToFile();
    }

    public List<Book> searchBooks(String query) {
        return books.stream().filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase())
                || book.getAuthor().toLowerCase().contains(query.toLowerCase())
                || book.getIsbn().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
    }

    public void borrowBook(String isbn, String borrowerName) {
        books.stream().filter(book -> book.getIsbn().equals(isbn)
                && !book.isBorrowed()).findFirst().ifPresent(book -> {
            book.setBorrowed(true);
            book.setBorrowerName(borrowerName);
            book.setBorrowDate(LocalDate.now());                            // Ustawienie aktualnej daty
            book.setReturnDate(LocalDate.now().plusWeeks(2));   // Wypożyczenie na 2 tyg
        });
        saveBooksToFile();
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        saveBooksToFile();
    }

    public List<Book> getBorrowedBooks() {
        return books.stream().filter(Book::isBorrowed).collect(Collectors.toList());
    }

    public List<Book> getOverdueBooks() {
        return books.stream().filter(book -> book.isBorrowed()
                && LocalDate.now().isAfter(book.getReturnDate())).collect(Collectors.toList());
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    //    private void loadBooksFromFile() {
//        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/books.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts.length == 3) {
//                    books.add(new Book(parts[0], parts[1], parts[2]));
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("Błąd czytania książek z pliku: " + e.getMessage());
//        }
//    }
    void loadBooksFromFile() {
        File file = new File("src/main/resources/books.txt");
        if (!file.exists()) {
            System.out.println("Plik z książkami nie istnieje. Tworzenie nowego pliku.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1); // Użyj limitu -1, aby zachować puste stringi dla pustych wartości

                if (parts.length < 7) {
                    System.err.println("Nieprawidłowy format danych w pliku książek: " + line);
                    continue;
                }

                String title = parts[0];
                String author = parts[1];
                String isbn = parts[2];
                boolean isBorrowed = Boolean.parseBoolean(parts[3]);
                String borrowerName = parts[4].isEmpty() || parts[4].equals("null") ? null : parts[4];
                LocalDate borrowDate = parts[5].isEmpty() || parts[5].equals("null") ? null : LocalDate.parse(parts[5]);
                LocalDate returnDate = parts[6].isEmpty() || parts[6].equals("null") ? null : LocalDate.parse(parts[6]);

                // Tworzenie nowego obiektu Book z wczytanych danych
                Book book = new Book(title, author, isbn, isBorrowed, borrowerName, borrowDate, returnDate);

                books.add(book);
            }
        } catch (IOException e) {
            System.err.println("Błąd czytania książek z pliku: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("Błąd parsowania daty: " + e.getMessage());
        }
    }

    public void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/books.txt"))) {
            for (Book book : books) {
                String line = book.getTitle() + "," + book.getAuthor() + "," + book.getIsbn() + ","
                        + book.isBorrowed() + "," + book.getBorrowerName() + ","
                        + (book.getBorrowDate() == null ? "null" : book.getBorrowDate().toString()) + ","
                        + (book.getReturnDate() == null ? "null" : book.getReturnDate().toString());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Błąd zapisu książek do pliku: " + e.getMessage());
        }
    }
}
