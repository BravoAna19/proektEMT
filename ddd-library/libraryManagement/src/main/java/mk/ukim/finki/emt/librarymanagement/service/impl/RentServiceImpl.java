package mk.ukim.finki.emt.librarymanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.librarymanagement.domain.exceptions.RentIdNotExistException;
import mk.ukim.finki.emt.librarymanagement.domain.exceptions.RentItemIdNotExistException;
import mk.ukim.finki.emt.librarymanagement.domain.model.BookSampleId;
import mk.ukim.finki.emt.librarymanagement.domain.model.RentBook;
import mk.ukim.finki.emt.librarymanagement.domain.model.RentId;
import mk.ukim.finki.emt.librarymanagement.domain.repository.RentRepository;
import mk.ukim.finki.emt.librarymanagement.service.RentService;
import mk.ukim.finki.emt.librarymanagement.service.forms.RentForm;
import mk.ukim.finki.emt.librarymanagement.service.forms.RentItemForm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;
    private final Validator validator;
    //private final DomainEventPublisher domainEventPublisher;

    @Override
    public RentId placeRent(RentForm rentForm) {
        Objects.requireNonNull(rentForm, "rent must not be null.");
        var constraintViolations = validator.validate(rentForm);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The rent form is not valid", constraintViolations);
        }
        var newRent = rentRepository.saveAndFlush(toDomainObject(rentForm));
        //newRent.getOrderItemList().forEach(item -> domainEventPublisher.publish(new OrderItemCreated(item.getProductId().getId(), item.getQuantity())));
        return newRent.getId();

    }

    @Override
    public List<RentBook> findAll() {
        return rentRepository.findAll();
    }

    @Override
    public Optional<RentBook> findById(RentId id) {
        return rentRepository.findById(id);
    }

    @Override
    public void addItem(RentId rentId, RentItemForm rentItemForm) throws RentIdNotExistException {
        RentBook rent = rentRepository.findById(rentId).orElseThrow(RentIdNotExistException::new);
        rent.addItem(rentItemForm.getBook(), rentItemForm.getQuantity());
        rentRepository.saveAndFlush(rent);
        //domainEventPublisher.publish(new RentItemCreated(rentItemForm.getBook().getId().getId(), rentItemForm.getQuantity()));
    }

    @Override
    public void deleteItem(RentId rentId, BookSampleId bookSampleId) throws RentIdNotExistException, RentItemIdNotExistException {
        RentBook rent = rentRepository.findById(rentId).orElseThrow(RentIdNotExistException::new);
        rent.removeItem(bookSampleId);
        rentRepository.saveAndFlush(rent);
        //domainEventPublisher.publish(new RentItemCreated(rentItemForm.getBook().getId().getId(), rentItemForm.getQuantity()));
    }
    private RentBook toDomainObject(RentForm rentForm) {
        var order = new RentBook(Instant.now(),rentForm.getCurrency());
        rentForm.getItems().forEach(item->order.addItem(item.getBook(),item.getQuantity()));
        return order;
    }

}
