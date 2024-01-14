package pl.edu.wszib.model;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;
    private String borrowerName;
    private String borrowerSurname;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false; // Domyślnie książka nie jest wypożyczona
        this.borrowerName = null; // Początkowo brak wypożyczającego
        this.borrowerSurname = null; // Początkowo brak wypożyczającego
        this.borrowDate = null; // Początkowo brak daty wypożyczenia
        this.returnDate = null; // Początkowo brak daty zwrotu
    }

    public Book(String title, String author, String isbn, boolean isBorrowed,
                String borrowerName, String borrowerSurname, LocalDate borrowDate, LocalDate returnDate) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = isBorrowed;
        this.borrowerName = borrowerName;
        this.borrowerSurname = borrowerSurname;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public String getBorrowerSurname() {
        return borrowerSurname;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    // Setters

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public void setBorrowerSurname(String borrowerSurname) {
        this.borrowerSurname = borrowerSurname;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}