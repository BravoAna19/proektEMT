package mk.ukim.finki.emt.bookscatalog.services;

import mk.ukim.finki.emt.bookscatalog.domain.models.Book;
import mk.ukim.finki.emt.bookscatalog.domain.models.BookId;
import mk.ukim.finki.emt.bookscatalog.services.form.BookForm;

import java.util.List;

public interface BookService {
    Book findById(BookId id);
    Book createBook(BookForm form);
    Book rentItemCreated(BookId productId, int quantity);
    Book rentItemRemoved(BookId productId, int quantity);
    List<Book> getAll();

}
