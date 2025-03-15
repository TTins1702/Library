import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
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

    public void AddDocument() {
        System.out.println("Choose document type (1: Book, 2: Thesis):");
        int type = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Document ID:");
        String id = sc.nextLine();

        if(findDocumentbyID(id) != null ) {
            System.out.println("Document already exists!");
            return;
        }

        System.out.println("Enter Book Name:");
        String name = sc.nextLine();
        System.out.println("Enter Book Author:");
        String author = sc.nextLine();
        System.out.println("Enter Publication Year:");
        int year = sc.nextInt();
        System.out.println("Enter Quantity:");
        int quantity = sc.nextInt();
        sc.nextLine();

        if(type == 1) {
            System.out.println("Enter ISBN:");
            String isbn = sc.nextLine();
            System.out.println("Enter Genre:");
            String genre = sc.nextLine();
            documents.add(new Book(id,name,author,year,quantity,isbn,genre));
            System.out.println("Book added successfully!");
        }
        else if(type == 2) {
            System.out.println("Enter Major:");
            String major = sc.nextLine();
            System.out.println("Enter Advisor:");
            String advisor = sc.nextLine();
            documents.add(new Thesis(id,name,author,year,quantity,major,advisor));
            System.out.println("Thesis added successfully!");
        }
        else {
            System.out.println("Invalid choice");
            return;
        }
    }

    public boolean RemoveDocument() {
        System.out.println("Choose document ID:");
        String id = sc.nextLine();
        Document docToRemove = findDocumentbyID(id);
        if(docToRemove == null) {
            System.out.println("Document not found!");
            return false;
        }
        System.out.println("Removed Document: " + docToRemove.getTitle());
        documents.remove(docToRemove);
        return true;
    }

    public boolean UpdateDocument() {
        System.out.println("Choose document ID:");
        String id = sc.nextLine();
        Document docToUpdate = findDocumentbyID(id);
        if(docToUpdate != null) {
            System.out.println("Enter new Quantity:");
            int quantity = sc.nextInt();
            sc.nextLine();
            docToUpdate.setQuantity(quantity);
            docToUpdate.setAvailableQuantity(docToUpdate.getAvailableQuantity()
                    + (quantity - docToUpdate.getQuantity()));
            System.out.println("Document Quantity updated successfully!");
            return true;
        }
        return false;

    }

    public Document findDocument() {
        System.out.println("Enter keyword to be searched:");
        String keyword = sc.nextLine();
        List<Document> foundDocument = findDocument(keyword);
        if(foundDocument == null) {
            System.out.print("No documents found matching keyword: " + keyword);
            return null;
        }
        else {
            System.out.println("Document found:");
            for(Document doc : foundDocument) {
                doc.printInfo();
                System.out.println();
            }
        }
        return null;
    }

    public List<Document> findDocument(String keyword) {
        List<Document> result = new ArrayList<>();
        for(Document doc : documents) {
            if(doc.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    doc.getAuthor().toLowerCase().contains(keyword.toLowerCase())||
                    doc.getDocumentID().toLowerCase().contains(keyword.toLowerCase())) {

                result.add(doc);
            }
        }
        return result;
    }

    public void displayDocument() {
        if(documents.isEmpty()) {
            System.out.println("No documents in this library");
            return;
        }
        System.out.println("Documents in this library:");
        for(Document doc : documents) {
            doc.printInfo();
            System.out.println("----------------------------");
        }
    }

    public Document findDocumentbyID(String id) {
        for(Document doc : documents) {
            if(doc.getDocumentID().equals(id)) {
                return doc;
            }
        }
        return null;
    }

    public void addUser() {
        System.out.println("Enter UserName:");
        String username = sc.nextLine();
        if(findUser(username) != null) {
            System.out.println("Username already exists!");
            return;
        }
        System.out.println("Enter Password:");
        String password = sc.nextLine();
        users.add(new User(username, password));
        System.out.println("Users added successfully!");
        System.out.println("Please remember your account");
        System.out.println("UserName: " + username);
        System.out.println("Password: " + password);
    }

    public User findUser(String username) {
        for(User user : users) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean signIn(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return true;
                } else {
                    System.out.println("Wrong Password!");
                    return false;
                }
            } else {
                System.out.println("Username not found!");
                return false;
            }
        }
        return false;
    }

    public void borrowDocument() {
        System.out.println("Enter UserName:");
        String username = sc.nextLine();
        System.out.println("Enter Password:");
        String password = sc.nextLine();
        User user = new User(username, password);
        if (!signIn(username, password)) {
            return;
        }
        System.out.println("Enter DocumentID to be borrowed:");
        String id = sc.nextLine();
        Document docToBorrow = findDocumentbyID(id);
        if(docToBorrow == null) {
            System.out.println("Document not found!");
            return;
        }
        if(user.borrowDocument(docToBorrow)) {
            docToBorrow.setAvailableQuantity(docToBorrow.getAvailableQuantity() - 1);
            System.out.println("Document successfully borrowed!");
        }
        else {
            System.out.println("Document is not available for borrowing!");
        }
    }

    public void returnDocument() {
        System.out.println("Enter UserName:");
        String username = sc.nextLine();
        if(findUser(username) == null) {
            System.out.println("Username not found!");
            return;
        }
        User user = findUser(username);
        System.out.println("Enter DocumentID to be returned:");
        String id = sc.nextLine();
        Document docToReturn = findDocumentbyID(id);
        if(docToReturn == null) {
            System.out.println("Document not found!");
            return;
        }

        Document borrowedDocByUser = null;
        for(Document doc : user.getBorrowedDocuments()) {
            if(doc.getDocumentID().equals(id)) {
                borrowedDocByUser = doc;
                break;
            }
        }

        if(borrowedDocByUser != null && user.returnDocument(borrowedDocByUser)) {
            user.returnDocument(findDocumentbyID(id));
            docToReturn.setAvailableQuantity(docToReturn.getAvailableQuantity() + 1);
            System.out.println("Document successfully returned!");
            return;
        }
        else {
            System.out.println("Document was not borrowed or return failed!");
        }
    }

    public void displayUserInfo() {
        System.out.println("Enter UserName:");
        String username = sc.nextLine();
        if(findUser(username) == null) {
            System.out.println("Username not found!");
            return;
        }
        User user = findUser(username);
        System.out.println("Enter Password:");
        String password = sc.nextLine();
        if(user.getPassword().equals(password)) {
            user.displayUserInfo();
            return;
        }
        else {
            System.out.println("Password does not match!");
        }
    }

    public void run() {
        int choice;
        do {
            displayMenu();
            System.out.println("Enter your choice:");
            if(sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 0:
                        System.out.println("See you next time! Thank you!");
                        break;
                    case 1:
                        AddDocument();
                        break;
                    case 2:
                        RemoveDocument();
                        break;
                    case 3:
                        UpdateDocument();
                        break;
                    case 4:
                        findDocument();
                        break;
                    case 5:
                        displayDocument();
                        break;
                    case 6:
                        addUser();
                        break;
                    case 7:
                        borrowDocument();
                        break;
                    case 8:
                        returnDocument();
                        break;
                    case 9:
                        displayUserInfo();
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter a number between [1] and [9]!");
                }
            }
            else {
                System.out.println("Invalid input. Please enter a number");
                sc.nextLine();
                choice = -1;
            }
            System.out.println();
        }
        while(choice != 0);
    }

    public void displayMenu() {
        System.out.println("Wellcome to TTins's Library");
        System.out.println("[0] Exit");
        System.out.println("[1] Add Document");
        System.out.println("[2] Remove Document");
        System.out.println("[3] Update Document");
        System.out.println("[4] Find Documents");
        System.out.println("[5] Display Documents");
        System.out.println("[6] Add User");
        System.out.println("[7] Borrow Document");
        System.out.println("[8] Return Document");
        System.out.println("[9] Display User Info");
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.run();
    }
}