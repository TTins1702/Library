public class Book extends Document {
    private String isbn;
    private String genre;

    public Book(String documentId, String title, String author, int publicationYear,
                int quantity, String isbn, String genre) {
        super(documentId, title, author, publicationYear, quantity);
        this.isbn = isbn;
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void printInfo() {
        System.out.println("BOOK INFO");
        System.out.println("Document ID: " + getDocumentID());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Publication Year: " + getPublicationYear());
        System.out.println("IBSN: " + isbn);
        System.out.println("Genre: " + genre);
        System.out.println("Avaiable Quantity: " + getQuantity() + "/" + getQuantity());
    }
}
