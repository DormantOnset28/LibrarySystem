import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CheckoutRecord {
    private Patron patron;
    private Book book;
    private LocalDate checkoutDate;
    private LocalDate dueDate;

    public CheckoutRecord(Patron patron, Book book, int dueDays) {
        this.patron = patron;
        this.book = book;
        this.checkoutDate = LocalDate.now();
        this.dueDate = checkoutDate.plusDays(dueDays);
    }

    public Patron getPatron() {
        return patron;
    }

    public Book getBook() {
        return book;
    }

    public String getCheckoutDate() {
        return checkoutDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public String getDueDate() {
        return dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(dueDate);
    }

    @Override
    public String toString() {
        return book.getTitle() + " checked out by " + patron.getName() + " on " + getCheckoutDate();
    }
}