package mk.ukim.finki.emt.librarymanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.emt.librarymanagement.domain.valueobjects.Book;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rentbook")
public class RentBook extends AbstractEntity<RentId> {

    private Instant rentOn;

    @Column(name = "rent_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    //Vrska pomegu BookSample i RentBook
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<BookSample> bookSampleList = new HashSet<>();


    protected RentBook(){
        super(RentId.randomId(RentId.class));
    }

    public RentBook(Instant now, mk.ukim.finki.emt.sharedkernel.domain.financial.Currency currency) {
        super(RentId.randomId(RentId.class));
        this.rentOn = now;
        this.currency = currency;
    }


    // Metod koj presmetuva vkupna suma za iznajmuvanje na kniga
    public Money total(){
        return bookSampleList.stream().map(BookSample::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    public BookSample addItem(@NonNull Book book, int qty) {
        Objects.requireNonNull(book,"book must not be null");
        var item  = new BookSample(book.getId(), book.getPrice(),  qty);
        bookSampleList.add(item);
        return item;
    }

    public void removeItem(@NonNull BookSampleId orderItemId) {
        Objects.requireNonNull(orderItemId,"Book Sample must not be null");
        bookSampleList.removeIf(v->v.getId().equals(orderItemId));
    }

}
