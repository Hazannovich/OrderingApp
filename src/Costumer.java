import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Costumer {
    private final String name;
    private final String id;

    public Costumer(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void generateReceipt(Order order) {
        String separator;
        try {
            separator = System.getProperty("file.separator");
        } catch (SecurityException e) {
            e.printStackTrace();
            separator = "\\";
        }
        try {
            File myObj = new File("assets" + separator + "receipts" + separator + name + id + ".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write(order.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getId() {
        return this.id;
    }


    public String toString() {
        return "Name: " + this.name + " ID: " + this.id;
    }
}
