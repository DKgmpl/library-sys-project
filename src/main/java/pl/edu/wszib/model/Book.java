package pl.edu.wszib.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter @Setter
public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;
    private String borrowerName;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Book(String title, String author, String isbn, String borrowerName, LocalDate borrowDate, LocalDate returnDate) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
        this.borrowerName = borrowerName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

}

