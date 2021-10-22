package mk.ukim.finki.emt.bookscatalog.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.bookscatalog.domain.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.bookscatalog.domain.models.Book;
import mk.ukim.finki.emt.bookscatalog.domain.models.BookId;
import mk.ukim.finki.emt.bookscatalog.domain.repository.BooksRepository;
import mk.ukim.finki.emt.bookscatalog.services.BookService;
import mk.ukim.finki.emt.bookscatalog.services.form.BookForm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BooksRepository booksRepository;

    @Override
    public Book findById(BookId id) {
        return booksRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public Book createBook(BookForm form) {
        Book b = Book.build(form.getBookName(), form.getBookAuthor(), form.getPrice(),form.getSales());
        booksRepository.save(b);
        return b;
    }

    @Override
    public Book rentItemCreated(BookId bookId, int quantity) {
        Book b = booksRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        b.addSales(quantity);
        booksRepository.saveAndFlush(b);
        return b;
    }

    @Override
    public Book rentItemRemoved(BookId bookId, int quantity) {
        Book b = booksRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        b.removeSales(quantity);
        booksRepository.saveAndFlush(b);
        return b;
    }

    @Override
    public List<Book> getAll() {
        return booksRepository.findAll();
    }

}
