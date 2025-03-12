import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String username;
    private String password;

    private List<Document> documentList = new ArrayList<>();

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    public void canBorrowDocument(Document document) {
        if(document.borrowDocument())  {
            documentList.add(document);
        }
    }

    public void canReturnDocument(Document document) {
        document.returnDocument();
        documentList.remove(document);
    }

    public void printDocumentList() {
        for(Document document: documentList) {
            document.printDocument();
            System.out.println();
        }
    }
}

