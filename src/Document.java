import java.util.Objects;

public abstract class Document {
    private String documentID;
    private String title;
    private String author;
    private int publicationYear;
    private int quantity;
    private int availableQuantity;

    /**
     * Document constuctor.
     * @param documentID ID.
     * @param title title.
     * @param author author.
     * @param publicationYear publicationYear.
     * @param quantity quantity.
     */
    public Document(String documentID, String title, String author,
                    int publicationYear, int quantity) {
        this.documentID = documentID;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.quantity = quantity;
        this.availableQuantity = quantity;
    }

    //Getter/Setter
    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public boolean borrowDocument() {
        if(availableQuantity > 0) {
            availableQuantity--;
            return true;
        }
        return false;
    }

    public void returnDocument() {
        if(availableQuantity < quantity) {
            availableQuantity++;
        }
    }

    public abstract void printDocument();

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Document other = (Document) obj;
        return Objects.equals(documentID, other.documentID);
    }
}
