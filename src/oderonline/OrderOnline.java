package oderonline;

import java.util.*;

public class OrderOnline {

    private int productID;
    private int purchaseID;
    private boolean userOrder;
    private boolean userAuthenticated;
    private PurchaseDetail[] purchase;
    private Owner owner;
    private Customer customer;
    private Database database;
    private Payment[] payment;
    private String type;
    private Product[] product;
    private Scanner sc = new Scanner(System.in);
    private int selection;

    public OrderOnline() {
        userAuthenticated = false;
        database = new Database();
        product = new Product[0];
        payment = new Payment[0];
        purchase = new PurchaseDetail[0];
        productID = 0;
    }

    // start ATM 
    public void run() {
        userOrder = false;
        System.out.println("\nWelcome! to SweetHome Order Online System");
        System.out.println("Do you want to login? (Login to get point)");
        System.out.println("1 : Login");
        System.out.println("2 : No login");
        System.out.print("select : ");
        selection = sc.nextInt();
        while (selection != 1 && selection != 2) {
            System.out.print("select : ");
            selection = sc.nextInt();

        }

        if (selection == 1) {
            while (!userAuthenticated) {
                String username, password;
                System.out.print("username : ");
                username = sc.next();
                System.out.print("password : ");
                password = sc.next();
                userAuthenticated = database.authenticateOwner(username, password);
                if (userAuthenticated) {
                    owner = database.getAccountOwner(username);
                    menuOwner();
                    continue;
                }
                userAuthenticated = database.authenticateCustomer(username, password);
                if (userAuthenticated) {
                    customer = database.getAccountCustomer(username);
                    menuCustomer();
                    continue;
                }
            }

        } else {
            customer = database.getAccountCustomer("guest");
            menuCustomer();
        }
    }

    public void menuOwner() {

        System.out.println("Welcome " + owner.getName() + " You are Owner");
        System.out.println("Press select number of menu");
        System.out.println("1. Show order queue");
        System.out.println("2. Payment");
        System.out.println("3. Previous sales");
        System.out.println("4. Exit");
        System.out.print("Select : ");
        selection = sc.nextInt();
        while (selection != 1 & selection != 2 & selection != 3 & selection != 4) {
            System.out.print("please key number 1,2,3 or 4 \nselect : ");
            selection = sc.nextInt();
        }
        if (selection == 1) {
            showOrderQueue();
        } else if (selection == 2) {
            payment();
        } else if (selection == 3) {
            previousSales();
        } else {

            userAuthenticated = false;
            run();
        }
    }

    public void menuCustomer() {
        screenCut(0);
        System.out.print("Welcome " + customer.getName());
        if (customer.getId() != 0) {
            System.out.println(" You are Customer");
            System.out.println("Your point :  " + customer.getPoint());
        } else {
            System.out.println("");
        }

        System.out.println("Press select number of menu");
        System.out.println("1. Order");
        System.out.println("2. Exit");
        System.out.print("Select : ");
        selection = sc.nextInt();
        while (selection != 1 & selection != 2) {
            System.out.print("please key number 1 or 2 \nselect : ");
            selection = sc.nextInt();
        }
        if (selection == 1) {
            order();
        } else {
            productID = 0;
            product = new Product[0];
            userAuthenticated = false;
            run();
        }

    }

    public void order() {

        int table;
        int type;
        int taste;
        int size;
        int[] topping;
        String[] toppingName;
        int spacialTopping = -1;

         screenCut(0);

        System.out.println("Please key type of product");
        System.out.println("1.Bingsu");
        System.out.println("2.Pangyen");
        System.out.println("3.Nampan");
        System.out.println("4.Toast");
        System.out.println("5.Italian Soda");
        System.out.println("6.<--Back");
        System.out.print("Select : ");
        type = sc.nextInt();
        while (type < 1 || type > 6) {
            System.out.print("please key number 1,2,3,4,5 or 6 \nselect : ");
            type = sc.nextInt();
        }
        if (type != 6) {
             screenCut(0);
            System.out.print("Menu type : ");
        }
        if (type == 1) {
            String[] bingsu = database.getBingsu();
            int[] bingsuPrice = database.getBingsuPrice();
            System.out.println("Bingsu");
            System.out.println("Please select size");
            System.out.println("1. M (" + bingsuPrice[0] + "-)");
            System.out.println("2. L (" + bingsuPrice[1] + "-)");
            System.out.print("Select size : ");
            selection = sc.nextInt();
            while (selection != 1 && selection != 2) {
                System.out.print("please key number 1 or 2 \nselect : ");
                selection = sc.nextInt();
            }
            size = selection - 1;
            System.out.println("\nTaste");

            for (int i = 0; i < bingsu.length; i++) {
                System.out.print((i + 1) + ". " + bingsu[i]);
                if (bingsu[i].length() > 12) {
                    System.out.print("\t");
                } else {
                    System.out.print("\t\t");
                }
                i++;
                if (i == bingsu.length) {
                    System.out.println("");
                    break;
                }
                System.out.println((i + 1) + ". " + bingsu[i]);
            }
            System.out.print("Select taste : ");
            selection = sc.nextInt();
            while (selection < 1 || selection > 14) {
                System.out.print("please key number 1-14 \nselect : ");
                selection = sc.nextInt();
            }
            taste = selection - 1;
            System.out.println("Your select : " + bingsu[taste]);

            String[] bingsuTopping = database.getToppingsBingsuAndPanyen();
            topping = new int[3];
            toppingName = new String[3];
            System.out.println("\nTopping");
            for (int i = 0; i < bingsuTopping.length; i++) {
                System.out.print((i + 1) + ". " + bingsuTopping[i]);
                if (bingsuTopping[i].length() > 12) {
                    System.out.print("\t");
                } else {
                    System.out.print("\t\t");
                }
                i++;
                if (i == bingsuTopping.length) {
                    System.out.println("");
                    break;
                }
                System.out.println((i + 1) + ". " + bingsuTopping[i]);
                
            }
            for (int i = 0; i < 3; i++) {
                screenCut(1);
                System.out.print("Select topping (" + (i + 1) + "/3): ");
                selection = sc.nextInt();
                while (selection < 1 || selection > 18) {
                    System.out.print("please key number 1-18 \nSelect topping (" + (i + 1) + "/3): ");
                    selection = sc.nextInt();
                }
                topping[i] = selection - 1;
                toppingName[i] = bingsuTopping[topping[i]];
                System.out.println("Your select : " + bingsuTopping[topping[i]]);
                
            }

            System.out.println("\nDo you want special Topping?");
            System.out.println("1.yes");
            System.out.println("2.no");
            System.out.print("Select : ");
            selection = sc.nextInt();
            while (selection != 1 && selection != 2) {
                System.out.print("please key number 1 or 2 \nselect : ");
                selection = sc.nextInt();
            }
            String[] spacial = database.getToppingSpacial();
            int[] spacialPrice = database.getToppingSpacialPrice();
            if (selection == 1) {
                System.out.println("\nSpacial topping");
                for (int i = 0; i < spacial.length; i++) {
                    System.out.print((i + 1) + ". " + spacial[i] + "( " + spacialPrice[i] + "-)");
                    if (spacial[i].length() > 12) {
                        System.out.print("\t");
                    } else {
                        System.out.print("\t\t");
                    }
                    i++;
                    if (i == spacial.length) {
                        System.out.println("");
                        break;
                    }
                    System.out.println((i + 1) + ". " + spacial[i] + "( " + spacialPrice[i] + "-)");
                }

                System.out.print("Select spacial topping : ");
                selection = sc.nextInt();
                while (selection < 1 || selection > 5) {
                    System.out.print("please key number 1-5 \nSelect spacial topping : ");
                    selection = sc.nextInt();
                }
                spacialTopping = selection - 1;
                System.out.println("");

            }
             screenCut(0);
            System.out.println("Confirm your order");
            System.out.println("Type : Bingsu");
            System.out.print("Size : ");
            if (size == 0) {
                System.out.println("M");
            } else {
                System.out.println("L");
            }
            System.out.println("Taste : " + bingsu[taste]);
            System.out.print("Topping : ");
            for (int i = 0; i < 3; i++) {
                System.out.print(bingsuTopping[topping[i]]);
                if (i < 2) {
                    System.out.print(", ");
                } else {
                    System.out.println("");
                }
            }
            int sum = bingsuPrice[size];
            if (spacialTopping != -1) {
                System.out.println("Spacial Topping : " + spacial[spacialTopping]);
                sum = sum + spacialPrice[spacialTopping];
            }

            System.out.println("Price : " + sum+" Bath");
            System.out.println("\nConfirm? ");
            System.out.println("1.Yes");
            System.out.println("2.No");
            System.out.print("Select : ");
            selection = sc.nextInt();
            while (selection != 1 && selection != 2) {
                System.out.print("please key number 1 or 2 \nSelect : ");
                selection = sc.nextInt();
            }

            if (selection == 1) {
                this.productID++;
                Product[] temp = product;
                product = new Product[product.length + 1];
                for (int i = 0; i < temp.length; i++) {
                    product[i] = temp[i];
                }
                if (spacialTopping != -1) {
                    product[product.length - 1] = new Product(productID, "Bingsu", bingsu[taste], size, toppingName, sum, spacial[spacialTopping]);
                     screenCut(0);
                    System.out.println("Add product success!!");
                    product[product.length - 1].showProduct();
                } else {
                    product[product.length - 1] = new Product(productID, "Bingsu", bingsu[taste], size, toppingName, sum);
                     screenCut(0);
                    System.out.println("Add product success!!");
                    product[product.length - 1].showProduct();
                }
            }
            if (product.length != 0) {
                System.out.println("\nDo you want to order more product ? ");
                System.out.println("1.Yes");
                System.out.println("2.No");
                System.out.print("Select : ");
                selection = sc.nextInt();
                while (selection != 1 && selection != 2) {
                    System.out.print("please key number 1 or 2 \nSelect : ");
                    selection = sc.nextInt();
                }
                if (selection == 1) {
                    order();
                } else {
                    confirmOrder();
                }
            } else {
                order();
            }
//------------------------------------------------------Pangyen--------------------------------------------------------------
        } else if (type == 2) {
            int[] pangyenPrice;
            String[] pangyen = database.getPangyen();
            pangyenPrice = database.getPangyenPrice();
            System.out.println("Pangyen");
            System.out.print("Please select taste : " + "\n");
            for (int i = 0; i < pangyen.length; i++) {
                System.out.print((i + 1) + ". " + pangyen[i] + "( " + pangyenPrice[i] + "-)");
                if (pangyen[i].length() > 12) {
                    System.out.print("\t");
                } else {
                    System.out.print("\t\t");
                }
                i++;
                if (i == pangyen.length) {
                    System.out.println("");
                    break;
                }
                System.out.println((i + 1) + ". " + pangyen[i] + "( " + pangyenPrice[i] + "-)");
            }
            System.out.print("Select taste : ");
            selection = sc.nextInt();
            while (selection < 1 || selection > 11) {
                System.out.print("please key number 1-11 \nselect : ");
                selection = sc.nextInt();
            }
            taste = selection - 1;
            System.out.println("Your select : " + pangyen[taste]);

            String[] pangyenTopping = database.getToppingsBingsuAndPanyen();
            topping = new int[3];
            toppingName = new String[3];
            System.out.println("\nTopping");
            for (int i = 0; i < pangyenTopping.length; i++) {
                System.out.print((i + 1) + ". " + pangyenTopping[i]);
                if (pangyenTopping[i].length() > 12) {
                    System.out.print("\t");
                } else {
                    System.out.print("\t\t");
                }
                i++;
                if (i == pangyenTopping.length) {
                    System.out.println("");
                    break;
                }
                System.out.println((i + 1) + ". " + pangyenTopping[i]);
            }
            for (int i = 0; i < 3; i++) {
                screenCut(1);
                System.out.print("Select topping (" + (i + 1) + "/3): ");
                selection = sc.nextInt();
                while (selection < 1 || selection > 18) {
                    System.out.print("please key number 1-18 \nSelect topping (" + (i + 1) + "/3): ");
                    selection = sc.nextInt();
                }
                topping[i] = selection - 1;
                toppingName[i] = pangyenTopping[topping[i]];
                System.out.println("Your select : " + pangyenTopping[topping[i]]);
            }

            System.out.println("\nDo you want special Topping?");
            System.out.println("1.yes");
            System.out.println("2.no");
            System.out.print("Select : ");
            selection = sc.nextInt();
            while (selection != 1 && selection != 2) {
                System.out.print("please key number 1 or 2 \nselect : ");
                selection = sc.nextInt();
            }
            String[] spacial = database.getToppingSpacial();
            int[] spacialPrice = database.getToppingSpacialPrice();
            if (selection == 1) {
                System.out.println("\nSpacial topping");
                for (int i = 0; i < spacial.length; i++) {
                    System.out.print((i + 1) + ". " + spacial[i] + "( " + spacialPrice[i] + "-)");
                    if (spacial[i].length() > 12) {
                        System.out.print("\t");
                    } else {
                        System.out.print("\t\t");
                    }
                    i++;
                    if (i == spacial.length) {
                        System.out.println("");
                        break;
                    }
                    System.out.println((i + 1) + ". " + spacial[i] + "( " + spacialPrice[i] + "-)");
                }

                System.out.print("Select spacial topping : ");
                selection = sc.nextInt();
                while (selection < 1 || selection > 5) {
                    System.out.print("please key number 1-5 \nSelect spacial topping : ");
                    selection = sc.nextInt();
                }
                spacialTopping = selection - 1;
                System.out.println("");
            }
            screenCut(0);
            System.out.println("Confirm your order");
            System.out.println("Type : Pangyen");
            System.out.println("Taste : " + pangyen[taste]);
            System.out.print("Topping : ");
            for (int i = 0; i < 3; i++) {
                System.out.print(pangyenTopping[topping[i]]);
                if (i < 2) {
                    System.out.print(", ");
                } else {
                    System.out.println("");
                }
            }
            int sum = pangyenPrice[taste];
            if (spacialTopping != -1) {
                System.out.println("Spacial Topping : " + spacial[spacialTopping]);
                sum = sum + spacialPrice[spacialTopping];
            }

            System.out.println("Price : " + sum+" Bath");
            System.out.println("\nConfirm? ");
            System.out.println("1.Yes");
            System.out.println("2.No");
            System.out.print("Select : ");
            selection = sc.nextInt();
            while (selection != 1 && selection != 2) {
                System.out.print("please key number 1 or 2 \nSelect : ");
                selection = sc.nextInt();
            }
            if (selection == 1) {
                this.productID++;
                Product[] temp = product;
                product = new Product[product.length + 1];
                for (int i = 0; i < temp.length; i++) {
                    product[i] = temp[i];
                }
                if (spacialTopping != -1) {
                    product[product.length - 1] = new Product(productID, "Pangyen", pangyen[taste], 0, toppingName, sum, spacial[spacialTopping]);
                    screenCut(0);
                    System.out.println("Add product success!!");
                    product[product.length - 1].showProduct();
                } else {
                    product[product.length - 1] = new Product(productID, "Pangyen", pangyen[taste], 0, toppingName, sum);
                     screenCut(0);
                    System.out.println("Add product success!!");
                    product[product.length - 1].showProduct();
                }
            }
            if (product.length != 0) {
                System.out.println("\nDo you want to order more product ? ");
                System.out.println("1.Yes");
                System.out.println("2.No");
                System.out.print("Select : ");
                selection = sc.nextInt();
                while (selection != 1 && selection != 2) {
                    System.out.print("please key number 1 or 2 \nSelect : ");
                    selection = sc.nextInt();
                }
                if (selection == 1) {
                    order();
                } else {
                    confirmOrder();
                }
            } else {
                order();
            }

        } else if (type == 3) {

            //----------------------------------------Nampan-------------------------------------------------------------
            int[] nampanPrice;
            String[] nampan = database.getNampan();
            nampanPrice = database.getNampanPrice();
            System.out.println("Nampan");
            System.out.print("Please select taste : " + "\n");
            for (int i = 0; i < nampan.length; i++) {
                System.out.print((i + 1) + ". " + nampan[i] + "( " + nampanPrice[i] + "-)");
                if (nampan[i].length() > 12) {
                    System.out.print("\t");
                } else {
                    System.out.print("\t\t");
                }
                i++;
                if (i == nampan.length) {
                    System.out.println("");
                    break;
                }
                System.out.println((i + 1) + ". " + nampan[i] + "( " + nampanPrice[i] + "-)");
            }
            System.out.print("Select taste : ");
            selection = sc.nextInt();
            while (selection < 1 || selection > 11) {
                System.out.print("please key number 1-11 \nselect : ");
                selection = sc.nextInt();
            }
            taste = selection - 1;
            System.out.println("Your select : " + nampan[taste]);

            //Topping
            String[] nampanTopping = database.getToppingsNampan();
            topping = new int[3];
            toppingName = new String[3];
            System.out.println("\nTopping");
            for (int i = 0; i < nampanTopping.length; i++) {
                System.out.print((i + 1) + ". " + nampanTopping[i]);
                if (nampanTopping[i].length() > 12) {
                    System.out.print("\t");
                } else {
                    System.out.print("\t\t");
                }
                i++;
                if (i == nampanTopping.length) {
                    System.out.println("");
                    break;
                }
                System.out.println((i + 1) + ". " + nampanTopping[i]);
            }
            for (int i = 0; i < 1; i++) {
                screenCut(1);
                System.out.print("Select topping (" + (i) + "/1): ");
                selection = sc.nextInt();
                while (selection < 1 || selection > 4) {
                    System.out.print("please key number 1-4 \nSelect topping (" + (i) + "/1): ");
                    selection = sc.nextInt();
                }
                topping[i] = selection - 1;
                toppingName[i] = nampanTopping[topping[i]];
                System.out.println("Your select : " + nampanTopping[topping[i]]);
            }
             screenCut(0);
            System.out.println("Confirm your order");
            System.out.println("Type : Nampan");
            System.out.println("Taste : " + nampan[taste]);
            System.out.print("Topping : ");
            for (int i = 0; i < 1; i++) {
                System.out.print(nampanTopping[topping[i]]+"\n");
            }
            int sum = nampanPrice[taste];
            System.out.println("Price : " + sum+" Bath");
            System.out.println("\nConfirm? ");
            System.out.println("1.Yes");
            System.out.println("2.No");
            System.out.print("Select : ");
            selection = sc.nextInt();
            while (selection != 1 && selection != 2) {
                System.out.print("please key number 1 or 2 \nSelect : ");
                selection = sc.nextInt();
            }
            if (selection == 1) {
                this.productID++;
                Product[] temp = product;
                product = new Product[product.length + 1];
                for (int i = 0; i < temp.length; i++) {
                    product[i] = temp[i];
                }
                if (spacialTopping != -1) {
                    product[product.length - 1] = new Product(productID, "Nampan", nampan[taste], 0, toppingName, sum);
                     screenCut(0);
                    System.out.println("Add product success!!");
                    product[product.length - 1].showProduct();
                } else {
                    product[product.length - 1] = new Product(productID, "Nampan", nampan[taste], 0, toppingName, sum);
                    screenCut(0);
                    System.out.println("Add product success!!");
                    product[product.length - 1].showProduct();
                }
            }
            if (product.length != 0) {
                System.out.println("\nDo you want to order more product ? ");
                System.out.println("1.Yes");
                System.out.println("2.No");
                System.out.print("Select : ");
                selection = sc.nextInt();
                while (selection != 1 && selection != 2) {
                    System.out.print("please key number 1 or 2 \nSelect : ");
                    selection = sc.nextInt();
                }
                if (selection == 1) {
                    order();
                } else {
                    confirmOrder();
                }
            } else {
                order();
            }

        } else if (type == 4) {
            //---------------------------------------------------------Toast--------------------------------------------------------
            String[] toast = database.getToast();
            int[] toastPrice = database.getTostPrice();
            System.out.println("Toast");
            System.out.print("Please select taste : " + "\n");
            for (int i = 0; i < toast.length; i++) {
                System.out.print((i + 1) + ". " + toast[i] + "( " + toastPrice[i] + "-)");
                if (toast[i].length() > 20) {
                    System.out.print("\t");
                } else if (toast[i].length() > 15) {
                    System.out.print("\t\t");
                } else {
                    System.out.print("\t\t\t");
                }
                i++;
                if (i == toast.length) {
                    System.out.println("");
                    break;
                }
                System.out.println((i + 1) + ". " + toast[i] + "( " + toastPrice[i] + "-)");
            }
            screenCut(1);
            System.out.print("Select taste : ");
            selection = sc.nextInt();
            while (selection < 1 || selection > 11) {
                System.out.print("please key number 1-11 \nselect : ");
                selection = sc.nextInt();
            }
            taste = selection - 1;
            System.out.println("Your select : " + toast[taste]);

            System.out.print("Select number of  Piece : ");
            int piece = sc.nextInt();
            System.out.println("Amount : " + piece + " Piece");
            int sum = toastPrice[taste] * piece;

            System.out.println("Price : " + sum + " Bath");
            System.out.println("\nConfirm? ");
            System.out.println("1.Yes");
            System.out.println("2.No");
            System.out.print("Select : ");
            selection = sc.nextInt();
            while (selection != 1 && selection != 2) {
                System.out.print("please key number 1 or 2 \nSelect : ");
                selection = sc.nextInt();
            }
            if (selection == 1) {
                this.productID++;
                Product[] temp = product;
                product = new Product[product.length + 1];
                for (int i = 0; i < temp.length; i++) {
                    product[i] = temp[i];
                }
                if (spacialTopping != -1) {
                    product[product.length - 1] = new Product(productID, "Toast", toast[taste], piece, sum);
                     screenCut(0);
                    System.out.println("Add product success!!");
                    product[product.length - 1].showProduct();
                } else {
                    product[product.length - 1] = new Product(productID, "Toast", toast[taste], piece, sum);
                     screenCut(0);
                    System.out.println("Add product success!!");
                    product[product.length - 1].showProduct();
                }
            }
            if (product.length != 0) {
                System.out.println("\nDo you want to order more product ? ");
                System.out.println("1.Yes");
                System.out.println("2.No");
                System.out.print("Select : ");
                selection = sc.nextInt();
                while (selection != 1 && selection != 2) {
                    System.out.print("please key number 1 or 2 \nSelect : ");
                    selection = sc.nextInt();
                }
                if (selection == 1) {
                    order();
                } else {
                    confirmOrder();
                }
            } else {
                order();
            }

            //------------------------------------------------------Italian Soda------------------------------------------------------------------
        } else if (type == 5) {
            String[] italianSoda = database.getItalianSoda();
            int[] italianSodaPrice = database.getItalianSodaPrice();
            System.out.println("ItalianSoda");
            System.out.print("Please select taste : " + "\n");
            for (int i = 0; i < italianSoda.length; i++) {
                System.out.print((i + 1) + ". " + italianSoda[i] + "( " + italianSodaPrice[i] + "-)");
                if (italianSoda[i].length() > 12) {
                    System.out.print("\t");
                } else if (italianSoda[i].length() > 6) {
                    System.out.print("\t\t");
                } else {
                    System.out.print("\t\t\t");
                }
                i++;
                if (i == italianSoda.length) {
                    System.out.println("");
                    break;
                }
                System.out.println((i + 1) + ". " + italianSoda[i] + "( " + italianSodaPrice[i] + "-)");
            }
            screenCut(1);
            System.out.print("Select taste : ");
            selection = sc.nextInt();
            while (selection < 1 || selection > 8) {
                System.out.print("please key number 1-8 \nselect : ");
                selection = sc.nextInt();
            }
            taste = selection - 1;
            System.out.println("Your select : " + italianSoda[taste]);

            System.out.print("Select number of  Cup : ");
            int piece = sc.nextInt();
            System.out.println("Amount : " + piece + " Cup");
            int sum = italianSodaPrice[taste] * piece;

            System.out.println("Price : " + sum + " Bath");
            System.out.println("\nConfirm? ");
            System.out.println("1.Yes");
            System.out.println("2.No");
            System.out.print("Select : ");
            selection = sc.nextInt();
            while (selection != 1 && selection != 2) {
                System.out.print("please key number 1 or 2 \nSelect : ");
                selection = sc.nextInt();
            }
            if (selection == 1) {
                this.productID++;
                Product[] temp = product;
                product = new Product[product.length + 1];
                for (int i = 0; i < temp.length; i++) {
                    product[i] = temp[i];
                }
                if (spacialTopping != -1) {
                    product[product.length - 1] = new Product(productID, "Italian Soda", italianSoda[taste], piece, sum);
                     screenCut(0);
                    System.out.println("Add product success!!");
                    product[product.length - 1].showProduct();
                } else {
                    product[product.length - 1] = new Product(productID, "Italian Soda", italianSoda[taste], piece, sum);
                     screenCut(0);
                    System.out.println("Add product success!!");
                    product[product.length - 1].showProduct();
                }
            }
            if (product.length != 0) {
                System.out.println("\nDo you want to order more product ? ");
                System.out.println("1.Yes");
                System.out.println("2.No");
                System.out.print("Select : ");
                selection = sc.nextInt();
                while (selection != 1 && selection != 2) {
                    System.out.print("please key number 1 or 2 \nSelect : ");
                    selection = sc.nextInt();
                }
                if (selection == 1) {
                    order();
                } else {
                    confirmOrder();
                }
            } else {
                order();
            }
        } else {
            menuCustomer();
        }
    }

    public void confirmOrder() {
        int table;
        int sum = 0;
        screenCut(1);
        System.out.println("Please key your table (1-9)");
        System.out.print("Select : ");
        table = sc.nextInt();
        while (table < 1 || table > 9) {
            System.out.print("please key number 1-9 \nselect : ");
            table = sc.nextInt();
        }
        screenCut(0);
        System.out.println("Confirm your order");
        System.out.println("Table : " + table);
        for (int i = 0; i < product.length; i++) {
            product[i].showProduct();
            sum = sum + product[i].getPrice();
        }
        System.out.println("Total : " + sum + " Baht");
        System.out.println("\n Confirm Order?");
        System.out.println("1.Yes");
        System.out.println("2.No");
        System.out.print("Select : ");
        selection = sc.nextInt();
        while (selection != 1 && selection != 2) {
            System.out.print("please key number 1 or 2 \nSelect : ");
            selection = sc.nextInt();
        }
        if (selection == 1) {
            this.purchaseID++;
            PurchaseDetail[] temp = purchase;
            purchase = new PurchaseDetail[purchase.length + 1];
            for (int i = 0; i < temp.length; i++) {
                purchase[i] = temp[i];
            }
            purchase[purchase.length - 1] = new PurchaseDetail(purchaseID, customer, product, table);
            screenCut(0);
            System.out.println("Order success!!");
            purchase[purchase.length - 1].showPurchaseDetail();

            menuCustomer();
        } else {
            product = new Product[0];
            order();
        }

    }

    public void showOrderQueue() {
        screenCut(0);
        int order;
        int count = 0;
        if (purchase.length >= 0) {
            System.out.println("OrderQueue");
            for (int i = 0; i < purchase.length; i++) {
                System.out.println((i + 1) + ". Table : " + purchase[i].getTable());
                System.out.println(". Customer : " + purchase[i].getCustomer().getName());
                 screenCut(1);
                count++;
            }
            if (count == 0) {
                System.out.println("No any queue now....");
            }

            System.out.print("Select queue (Key 0 to <--back) : ");
            selection = sc.nextInt();
            while (selection < 0 || selection > purchase.length) {
                System.out.print("please key number 1-" + purchase.length + " or 0 to back\nselect : ");
                selection = sc.nextInt();
            }
            order = selection;
            if (selection == 0) {
                menuOwner();
            } else {
                purchase[selection - 1].showPurchaseDetail();
                screenCut(1);
                System.out.println("1.Complete Order");
                System.out.println("2.<--back");
                System.out.print("Select : ");
                selection = sc.nextInt();
                while (selection < 1 || selection > 2) {
                    System.out.print("please key number 1-" + purchase.length + " or 0 to back\nselect : ");
                    selection = sc.nextInt();
                }
                if (selection == 1) {
                    screenCut(1);
                    System.out.println("Order has been completed!");
                    Payment[] paytemp = payment;
                    payment = new Payment[payment.length + 1];
                    for (int i = 0; i < paytemp.length; i++) {
                        payment[i] = paytemp[i];
                    }
                    payment[payment.length - 1] = new Payment(1, purchase[order - 1], customer, false);
                    PurchaseDetail[] temp = purchase;
                    purchase = new PurchaseDetail[purchase.length - 1];

                    for (int i = 0, j = 0; i < purchase.length; i++) {
                        if (!(i == (order - 1))) {
                            purchase[i] = temp[j];
                            j++;
                        } else {
                            j++;
                        }
                    }
                    showOrderQueue();
                } else {
                    showOrderQueue();
                }
            }
        } else {
            System.out.println("Order queue is empty");
        }
    }

    public void payment() {
        screenCut(2);
        int pay;
        System.out.println("Payment");
        int count = 0;
        for (int i = 0; i < payment.length; i++) {
            if (payment[i].isStatus()) {
                continue;
            } else {
                System.out.print((i + 1) + ". ");
                payment[i].showPayment();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No payment queue now...");
        }

        System.out.print("Select payment (Key 0 to <--back) : ");
        selection = sc.nextInt();
        while (selection < 0 || selection > (payment.length)) {
            System.out.print("please key number 1-" + (payment.length + 1) + " or 0 to back\nselect : ");
            selection = sc.nextInt();
        }
        pay = selection - 1;
        if (selection == 0) {
            menuOwner();
        } else {
            payment[selection - 1].showDetail();
            screenCut(0);
            //--------------------------------------------------------------------------------------
            if (payment[selection - 1].getCustomer().getId() != 0) {
                System.out.println("1.Payment");
                System.out.println("2.Payment and Use point");
                System.out.println("3.<--back");
                System.out.print("Select : ");
                selection = sc.nextInt();
                while (selection < 1 || selection > 3) {
                    System.out.print("please key number 1-3 \nselect : ");
                    selection = sc.nextInt();
                }
                if (selection == 1) {
                    int stack = 0;
                    for (int i = payment[pay].getSum(); i >= 100; i -= 100) {
                        stack += 10;
                    }
                    payment[pay].setPoint(payment[pay].getpoint() + stack);
                    payment[pay].setStatus(true);
                    System.out.println("Payment Completed!!!");
                    if (stack > 0) {
                        System.out.println("You get " + stack + " point!!!");
                    }
                    payment();
                } else if (selection == 2) {

                    System.out.println(payment[pay].getCustomer().getName() + " have " + payment[pay].getpoint() + " point");
                    System.out.print("Press key num of point to use(1 point : 1 Baht) : ");
                    selection = sc.nextInt();
                    while (selection < 1 || selection > payment[pay].getpoint()) {
                        System.out.print("please key number 0-" + payment[pay].getpoint() + " \nselect : ");
                        selection = sc.nextInt();
                    }

                    if (selection > 0) {
                        Product[] temp = payment[pay].getPurchase().getProduct();
                        Product[] temp2 = new Product[temp.length + 1];
                        for (int i = 0; i < temp.length; i++) {
                            temp2[i] = temp[i];
                        }
                        temp2[temp2.length - 1] = new Product(0, "point", "...", 1, (selection * (-1)));
                        payment[pay].getPurchase().setProduct(temp2);
                    }
                    payment[pay].showDetail();
                    int stack = 0;
                    for (int i = payment[pay].getSum(); i >= 100; i -= 100) {
                        stack += 10;
                    }
                    payment[pay].setPoint(payment[pay].getpoint() + stack);
                    payment[pay].setStatus(true);
                    System.out.println("Payment Completed!!!");
                    if (stack > 0) {
                        System.out.println(payment[pay].getCustomer().getName() + " get " + stack + " point!!!");
                    }
                    payment();

                } else {
                    payment();

                }
                //--------------------------------------------------------------------------------------
            } else {
                System.out.println("1.Payment");
                System.out.println("2.<--back");
                System.out.print("Select : ");
                selection = sc.nextInt();
                while (selection < 1 || selection > 2) {
                    System.out.print("please key number 1-2 \nselect : ");
                    selection = sc.nextInt();
                }
                if (selection == 1) {
                    payment[pay].setStatus(true);
                    System.out.println("Payment Completed!!!");

                    payment();

                } else {
                    payment();

                }
            }
            //--------------------------------------------------------------------------------------

        }
    }

    public void previousSales() {
        int count = 0;
        int pay;
        screenCut(2);
        System.out.println("previousSales");
        screenCut(1);
        for (int i = 0; i < payment.length; i++) {
            if (payment[i].isStatus()) {
                System.out.print((i + 1) + ". ");
                payment[i].showPayment();
                count++;
            } else {
                continue;
            }
        }
        if (count == 0) {
            System.out.println("No previous sales now...");
        }
        
        System.out.print("Select previous sales detail (Key 0 to <--back) : ");
        selection = sc.nextInt();
        while (selection < 0 || selection > (payment.length)) {
            System.out.print("please key number 1-" + (payment.length + 1) + " or 0 to back\nselect : ");
            selection = sc.nextInt();
        }
        pay = selection - 1;
        
        if (selection == 0) {
            menuOwner();
        } else {
            payment[selection - 1].showDetail();
            screenCut(0);
            //--------------------------------------------------------------------------------------
           
                System.out.println("1.<--back");
                System.out.print("Select : ");
                selection = sc.nextInt();
                while (selection != 1) {
                    System.out.print("please key number 0 to back \nselect : ");
                    selection = sc.nextInt();
                }
                if (selection == 1) {
                    previousSales();
                }
        }
    }
                

    

    public void screenCut(int type) {
        if (type == 1) {
            System.out.println("\n------------------------------------\n");
        } else {
            System.out.println("\n====================================\n");
        }
    }

}
