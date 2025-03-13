import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private ArrayList<Document> documents;
    private ArrayList<User> users;
    private Scanner sc = new Scanner(System.in);

    public Library() {
        this.documents = new ArrayList<>();
        this.users = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    /**
     * Add new document to documents.
     * @param document new document.
     */
    public void addDocument(Document document) {
        System.out.println("Document ID:");
        String id = sc.nextLine();
        if(findDocumentbyID(id) == null) {
            System.out.println("This Document ID was added by another user");
            return;
        }
        System.out.println("Document Title:");
        String title = sc.nextLine();
        System.out.println("Document Author:");
        String author = sc.nextLine();
        System.out.println("Document Publication Year:");
        int publicationYear = sc.nextInt();
        System.out.println("IBSN:");
        String ibsn = sc.nextLine();
        System.out.println("Genre:");
        String genre = sc.nextLine();
        System.out.println("Document Quantity:");
        int quantity = sc.nextInt();

        documents.add(new Book(id, title, author, publicationYear, quantity, ibsn, genre));
        System.out.println("Documents added successfully");
    }

    /**
     * Return document.
     * @param document the document want to return.
     */
    public void removeDocument(Document document) {
        System.out.printf("Enter the ID of the document you want to remove: ");
        String id = sc.nextLine();

        if(findDocumentbyID(id) == null) {
            System.out.println("This Document ID was removed by another user");
        }

        documents.remove(findDocumentbyID(id));
        System.out.println("Documents removed successfully");
    }

    /**
     * Find document by ID.
     * @param id id of the document.
     * @return founded (document) / null.
     */
    public Document findDocumentbyID(String id) {
        for(Document document : documents) {
            if(document.getDocumentID().equals(id)) {
                return document;
            }
        }
        return null;
    }
}
