package pl.edu.wszib.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.wszib.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BookRepository {
    private final List<Book> books = new ArrayList<>();

    public Optional<Book> findByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    public List<Book> findByTitleOrAuthor(String query) {
        return books.stream()
                .filter(book -> book.getTitle().contains(query) || book.getAuthor().contains(query))
                .collect(Collectors.toList());
    }

    public void add(Book book) {
        books.add(book);
    }

    public void remove(Book book) {
        books.remove(book);
    }

    public List<Book> findAll() {
        return new ArrayList<>(books);
    }
}
