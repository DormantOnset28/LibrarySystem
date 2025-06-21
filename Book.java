
public class Book {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private int availableCopies;

    public Book(String title, String author, String isbn, int publicationYear, int availableCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void checkout() {
        if (availableCopies > 0) {
            availableCopies--;
        }
    }

    public void returnBook() {
        availableCopies++;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (" + isbn + ")";
    }
}