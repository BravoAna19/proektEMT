package mk.ukim.finki.emt.librarymanagement.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.librarymanagement.domain.valueobjects.Book;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class RentItemForm {

    @NotNull
    private Book book;

    @Min(1)
    private int quantity = 1;

}
