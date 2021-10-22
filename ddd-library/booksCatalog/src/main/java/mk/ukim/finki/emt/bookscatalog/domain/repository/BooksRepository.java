package mk.ukim.finki.emt.bookscatalog.domain.repository;

import mk.ukim.finki.emt.bookscatalog.domain.models.Book;
import mk.ukim.finki.emt.bookscatalog.domain.models.BookId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, BookId> {
}
