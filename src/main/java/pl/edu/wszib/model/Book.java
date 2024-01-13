package pl.edu.wszib.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Accessors(fluent = true)
//@Builder
public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;
    private String borrowerName;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false; // Domyślnie książka nie jest wypożyczona
        this.borrowerName = null; // Początkowo brak wypożyczającego
        this.borrowDate = null; // Początkowo brak daty wypożyczenia
        this.returnDate = null; // Początkowo brak daty zwrotu
    }

    public Book(String title, String author, String isbn, boolean isBorrowed,
                String borrowerName, LocalDate borrowDate, LocalDate returnDate) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = isBorrowed;
        this.borrowerName = borrowerName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String getBorrowerName() {
        return this.borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}

