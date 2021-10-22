package mk.ukim.finki.emt.librarymanagement.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class RentForm {
    @NotNull
    private Currency currency;

    @Valid
    @NotEmpty
    private List<RentItemForm> items = new ArrayList<>();

}
