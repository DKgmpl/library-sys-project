package pl.edu.wszib.repository;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.model.Book;

import java.time.LocalDate;
@Getter @Setter
public class BookRepository {
    private final Book[] books = new Book[5];
    public BookRepository() {
        Book a = new Book(
                "Ogniem i Mieczem", "Henryk Sienkiewicz", "978-83-732-7157-9", "Aleksander",
                LocalDate.of(2024, 1, 6), LocalDate.of(2024, 2, 21));
        Book b = new Book(
                "Krzyżacy", "Henryk Sienkiewicz", "978-83-774-0824-7", "Zbigniew",
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 13));
    }
//    public boolean isBorrowed(String isbn) {
//
//    }
}
