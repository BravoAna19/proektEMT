package mk.ukim.finki.emt.librarymanagement.service;

import mk.ukim.finki.emt.librarymanagement.domain.exceptions.RentIdNotExistException;
import mk.ukim.finki.emt.librarymanagement.domain.model.RentBook;
import mk.ukim.finki.emt.librarymanagement.domain.model.RentId;
import mk.ukim.finki.emt.librarymanagement.domain.valueobjects.Book;
import mk.ukim.finki.emt.librarymanagement.domain.valueobjects.BookId;
import mk.ukim.finki.emt.librarymanagement.service.forms.RentForm;
import mk.ukim.finki.emt.librarymanagement.service.forms.RentItemForm;
import mk.ukim.finki.emt.librarymanagement.xport.client.BookClient;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class RentServiceImplTests {

    @Autowired
    private RentService rentService;

    @Autowired
    private BookClient bookClient;

    private static Book newBook (String name, Money price) {
        Book b = new Book(BookId.randomId(BookId.class), name, price, 0);
        return b;
    }

    @Test
    public void testPlaceRent() {

        RentItemForm ri1 = new RentItemForm();
        ri1.setBook(newBook("Book1",Money.valueOf(Currency.MKD,50)));
        ri1.setQuantity(1);

        RentItemForm ri2 = new RentItemForm();
        ri2.setBook(newBook("Book2",Money.valueOf(Currency.MKD,20)));
        ri2.setQuantity(1);

        RentForm rentForm = new RentForm();
        rentForm.setCurrency(Currency.MKD);
        rentForm.setItems(Arrays.asList(ri1,ri2));

        RentId newRentId = rentService.placeRent(rentForm);
        RentBook newRent = rentService.findById(newRentId).orElseThrow(RentIdNotExistException::new);
        Assertions.assertEquals(newRent.total(),Money.valueOf(Currency.MKD,2500));
    }

   /* @Test
    public void testPlaceOrderWithRealData() {
        List<Book> bookList = bookClient.findAll();
        Book b1 = bookList.get(0);
        Book b2 = bookList.get(1);

        RentItemForm ri1 = new RentItemForm();
        ri1.setBook(b1);
        ri1.setQuantity(1);

        RentItemForm ri2 = new RentItemForm();
        ri2.setBook(b2);
        ri2.setQuantity(1);

        RentForm rentForm = new RentForm();
        rentForm.setCurrency(Currency.MKD);
        rentForm.setItems(Arrays.asList(ri1,ri2));

        RentId newRentId = rentService.placeRent(rentForm);
        RentBook newRent = rentService.findById(newRentId).orElseThrow(RentIdNotExistException::new);

        Money outMoney = b1.getPrice().multiply(ri1.getQuantity()).add(b2.getPrice().multiply(ri2.getQuantity()));
        Assertions.assertEquals(newRent.total(),outMoney);
    }
*/

}
