package mk.ukim.finki.emt.librarymanagement.domain.repository;

import mk.ukim.finki.emt.librarymanagement.domain.model.RentBook;
import mk.ukim.finki.emt.librarymanagement.domain.model.RentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<RentBook, RentId> {
}
