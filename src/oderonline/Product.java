
package oderonline;

public class Product {

    private int id;
    private String type;
    private String taste;
    private int size;
    private String[] topping;
    private String spacialTopping = "";
    private int price;
    private int piece;

    public Product(int id, String type, String taste, int size, String[] topping, int price, String spacialTopping) {
        this.id = id;
        this.type = type;
        this.taste = taste;
        this.size = size;
        this.topping = topping;
        this.price = price;
        this.spacialTopping = spacialTopping;
    }

    public Product(int id, String type, String taste, int size, String[] topping, int price) {
        this.id = id;
        this.type = type;
        this.taste = taste;
        this.size = size;
        this.topping = topping;
        this.price = price;

    }

    public Product(int id, String type, String taste, int piece, int price) {
        this.id = id;
        this.type = type;
        this.taste = taste;
        this.piece = piece;
        this.price = price;
    }
    
    public void showProduct() {
        System.out.print(id + ". " + type + ", " + taste + "\n");
        if (type.equals("Bingsu")) {
            if (size == 0) {
                System.out.println(", Size : M");
            } else {
                System.out.println(", Size : L");
            }

        }
        if (type.equals("Bingsu") || type.equals("Pangyen")) {

            System.out.println("topping = " + topping[0] + ", " + topping[1] + ", " + topping[2]);
        } else if (type.equals("Nampan")) {

            System.out.println("topping = " + topping[0]);

        } else if (type.equals("Italian Soda")) {

            System.out.println("Amount = " + piece + " cup");

        } else {

            System.out.println("Amount = " + piece + " piece");
        }
        if (!spacialTopping.equals("")) {
            System.out.println("Spacial topping = " + spacialTopping);
        }
        System.out.println("Price = " + price + " Bath");
       screenCut(1);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTopping(String[] topping) {
        this.topping = topping;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTaste() {
        return taste;
    }

    public int getSize() {
        return size;
    }

    public String[] getTopping() {
        return topping;
    }

    public int getPrice() {
        return price;
    }

    public void screenCut(int type) {
        if (type == 1) {
            System.out.println("\n------------------------------------\n");
        } else {
            System.out.println("\n====================================\n");
        }
    }
}
