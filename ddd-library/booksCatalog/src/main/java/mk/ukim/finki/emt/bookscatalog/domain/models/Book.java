package mk.ukim.finki.emt.bookscatalog.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;

@Entity
@Table (name = "book")
public class Book extends AbstractEntity<BookId> {

    private String bookName;
    private String bookAuthor;
    private int rent = 0;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="price_amount")),
            @AttributeOverride(name="currency", column = @Column(name="price_currency"))
    })
    private Money price;

    protected Book() {
        super(BookId.randomId(BookId.class));
    }

    public static Book build(String bookName, String bookAuthor, Money price, int sales) {
        Book b = new Book();
        b.price = price;
        b.bookName = bookName;
        b.bookAuthor = bookAuthor;
        b.rent = sales;
        return b;
    }


    public void addSales(int qty) {
        this.rent = this.rent - qty;
    }

    public void removeSales(int qty) {
        this.rent -= qty;
    }

}
