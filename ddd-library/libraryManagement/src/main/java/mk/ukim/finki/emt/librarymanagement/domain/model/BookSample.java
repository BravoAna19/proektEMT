package mk.ukim.finki.emt.librarymanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.librarymanagement.domain.valueobjects.BookId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bookSample")
@Getter
public class BookSample extends AbstractEntity<BookSampleId> {

    private Money bookPrice;

    @Column(name = "qty", nullable = false)
    private int quantity;

    // Vrska kon entitetot Book koj se noaga vo drug ogranicen kontekst
    @AttributeOverride(name = "id", column = @Column(name = "book_id", nullable = false))
    private BookId bookId;

    public BookSample() {
        super(DomainObjectId.randomId(BookSampleId.class));
    }

    public BookSample(@NonNull BookId bookId, @NonNull Money bookPrice, int qty) {
        super(DomainObjectId.randomId(BookSampleId.class));
        this.bookId = bookId;
        this.bookPrice = bookPrice;
        this.quantity = qty;
    }

    public Money subtotal(){
        return bookPrice.multiply(quantity);
    }
}
