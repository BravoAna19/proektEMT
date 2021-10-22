package mk.ukim.finki.emt.librarymanagement.domain.valueobjects;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class Book implements ValueObject {

    private final BookId id;
    private final String name;
    private final Money price;
    private final int rent;

    private Book (){
        this.id = BookId.randomId(BookId.class);
        this.name = "";
        this.price = Money.valueOf(Currency.MKD, 0);
        this.rent = 0;
    }

    @JsonCreator
    public Book(@JsonProperty("id") BookId id,
                   @JsonProperty("bookName") String name,
                   @JsonProperty("price") Money price,
                   @JsonProperty("rent") int rent) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rent = rent;
    }


}
