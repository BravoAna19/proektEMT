package mk.ukim.finki.emt.librarymanagement.service;

import mk.ukim.finki.emt.librarymanagement.domain.exceptions.RentIdNotExistException;
import mk.ukim.finki.emt.librarymanagement.domain.exceptions.RentItemIdNotExistException;
import mk.ukim.finki.emt.librarymanagement.domain.model.BookSampleId;
import mk.ukim.finki.emt.librarymanagement.domain.model.RentBook;
import mk.ukim.finki.emt.librarymanagement.domain.model.RentId;
import mk.ukim.finki.emt.librarymanagement.service.forms.RentForm;
import mk.ukim.finki.emt.librarymanagement.service.forms.RentItemForm;

import java.util.List;
import java.util.Optional;

public interface RentService {
    RentId placeRent(RentForm rentForm);

    List<RentBook> findAll();

    Optional<RentBook> findById(RentId id);

    void addItem(RentId rentId, RentItemForm rentItemForm) throws RentIdNotExistException;

    void deleteItem(RentId rentId, BookSampleId bookSampleId) throws RentIdNotExistException, RentItemIdNotExistException;

}
