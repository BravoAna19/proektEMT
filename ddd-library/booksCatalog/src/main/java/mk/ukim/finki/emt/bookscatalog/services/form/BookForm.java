package mk.ukim.finki.emt.bookscatalog.services.form;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Data
public class BookForm {
    private String bookName;
    private String BookAuthor;
    private Money price;
    private int rent;

}
