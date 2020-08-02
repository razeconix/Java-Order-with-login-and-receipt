package oderonline;

public class Database {

    private Owner owner[];
    private Customer customer[];
    private String[] bingsu = {"Fresh milk", "Coco", "Chocolate", "Green tea", "Iced tea", "Cool milk", "Oreo fresh milk", "Peepo fresh milk", "Yogurt",
        "Strawberry", "Blueberries", "Grape", "Orange", "Kiwi"};
    private String[] pangyen = {"Fresh milk", "Coco", "Chocolate", "Ovaltine", "Fresh Milk", "Oreo fresh milk", "Oreo Coco", "Oreo Chocolate", "Green tea", "Iced tea", "Cool milk"};
    private String[] nampan = {"Fresh milk", "Coco", "Chocolate", "Ovaltine", "Fresh Milk", "Oreo fresh milk", "Oreo Coco", "Oreo Chocolate", "Green tea", "Iced tea", "Cool milk"};
    private String[] toast = {"Butter-milk" + "Butter-sugar", "Butter-Milk-Sugar", "Butter-Milk-Ovaltine", "Butter-Milk-Ovaltine",
        "Butter-milk-flossy pork", "Chili Paste-Flossy pork", "Strawberry jam", "Pineapple jam"};
    private String[] italianSoda = {"Strawberry", "Blue Hawaii", "Apple", "Blueberries", "cantaloupe", "kiwi", "Blue Lemon", "Red Punch", "Red Hale's blue boy"};
    private String[] toppingsBingsuAndPanyen = {"Corn flake", "Coco Crunch", "Marshmallow", "Sumo", "Chocolate bear", "Red jelly", "Multi colored jelly",
        "Jelly squares", "Jelly clams", "Bubble", "Fruit salad", "Panda", "Coconut jelly", "Rainbow seed", "Chocolate", "Strawberry stick", "Chocolate Stick", "Peepo"};
    private String[] toppingsNampan = {"Bubble", "Fruit salad", "Panda", "Coconut"};
        private String[] toppingSpacial = {"Chocolate Chip", "Ovaltine Powder", "Oreo", "Foi-thong", "whip cream"};
    private int[] bingsuPrice = {59, 109};
    private int[] pangyenPrice = {35, 35, 35, 40, 40, 40, 40, 40, 35, 35, 40};
    private int[] nampanPrice = {25, 25, 25, 30, 30, 30, 30, 30, 25, 25, 30};
    private int[] tostPrice = {15, 15, 15, 20, 20, 20, 20, 20};
    private int[] italianSodaPrice = {25, 25, 25, 25, 25, 25, 25, 25, 25};
    private int[] toppingSpacialPrice = {5, 5, 5, 10, 12};
    private String type = "guest";

    public Database() {
        owner = new Owner[1];
        customer = new Customer[3];
        owner[0] = new Owner(1, "owner", "1234", "P'J");
        customer[0] = new Customer(0, "guest", "guest", "guest", 0);
        customer[1] = new Customer(4, "cus2", "5678", "Thantawan", 0);
        customer[2] = new Customer(3, "cus1", "1234", "Jaggaphan", 100);
    }

    public Owner getAccountOwner(String accountOwner) {
        for (Owner currentAccount : owner) {
            if (currentAccount.getUsername().equals(accountOwner)) {
                return currentAccount;
            }
        }

        return null;
    }

    public Customer getAccountCustomer(String accountCustomer) {
        for (Customer currentAccount : customer) {
            if (currentAccount.getUsername().equals(accountCustomer)) {
                return currentAccount;
            }
        }

        return null;
    }

    public boolean authenticateOwner(String userName, String userPIN) {
        Owner userAccount = getAccountOwner(userName);
        if (userAccount != null) {
            return userAccount.validatePIN(userPIN);
        } else {
            return false;
        }
    }

    public boolean authenticateCustomer(String userName, String userPIN) {
        Customer userAccount = getAccountCustomer(userName);
        if (userAccount != null) {
            return userAccount.validatePIN(userPIN);
        } else {
            return false;
        }
    }

    public String getType() {
        return type;
    }

    public String[] getBingsu() {
        return bingsu;
    }

    public String[] getPangyen() {
        return pangyen;
    }

    public String[] getNampan() {
        return nampan;
    }

    public String[] getToast() {
        return toast;
    }

    public String[] getItalianSoda() {
        return italianSoda;
    }

    public String[] getToppingsBingsuAndPanyen() {
        return toppingsBingsuAndPanyen;
    }

    public String[] getToppingsNampan() {
        return toppingsNampan;
    }

    public String[] getToppingSpacial() {
        return toppingSpacial;
    }

    public int[] getBingsuPrice() {
        return bingsuPrice;
    }

    public int[] getPangyenPrice() {
        return pangyenPrice;
    }

    public int[] getNampanPrice() {
        return nampanPrice;
    }

    public int[] getTostPrice() {
        return tostPrice;
    }

    public int[] getItalianSodaPrice() {
        return italianSodaPrice;
    }

    public int[] getToppingSpacialPrice() {
        return toppingSpacialPrice;
    }

    /*
   public double getAvailableBalance(int userAccountNumber)
   {
      return getAccount(userAccountNumber).getAvailableBalance();
   } // end method getAvailableBalance

   // return total balance of Account with specified account number
   public double getTotalBalance(int userAccountNumber)
   {
      return getAccount(userAccountNumber).getTotalBalance();
   } // end method getTotalBalance

   // credit an amount to Account with specified account number
   public void credit(int userAccountNumber, double amount)
   {
      getAccount(userAccountNumber).credit(amount);
   } // end method credit

   // debit an amount from Account with specified account number
   public void debit(int userAccountNumber, double amount)
   {
      getAccount(userAccountNumber).debit(amount);
   } // end method debit
     */
    boolean authenticateOwner(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
