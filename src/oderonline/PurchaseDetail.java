/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oderonline;

/**
 *
 * @author Kommy IT NO.1
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PurchaseDetail {

    private int id;
    private Customer customer;
    private Product product[];
    private int table;
    private Date date;
    private boolean status;

    public PurchaseDetail() {
        id = 0;
        customer = new Customer();
        product = new Product[1];
        table = 0;
        date = new Date();
        status = false;
    }

    public PurchaseDetail(int id, Customer customer, Product[] product, int table) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.table = table;
        date = new Date();
        status = false;
    }

    public void showPurchaseDetail() {
        int sum = 0;
        screenCut(0);
        System.out.println("ID : " + this.id);
        System.out.println("Table : " + this.table);
        System.out.println("Customer : " + this.customer.getName());
        String fm;
        fm = String.format("%s %tB %<te, %<tY", "Order Date : ", date);
        System.out.println(fm);

        screenCut(1);
        System.out.println("Product list \n");
        for (int i = 0; i < product.length; i++) {
            product[i].showProduct();
            sum = sum + product[i].getPrice();
        }

        System.out.println("Total : " + sum+" Bath");
       
    }
    
    public int sumPrice(){
        int sum=0;
        for(int i=0;i<product.length;i++){
            sum = sum + product[i].getPrice();
        }
        return sum;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product[] getProduct() {
        return product;
    }

    public int getTable() {
        return table;
    }

    public Date getDate() {
        return date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setProduct(Product[] product) {
        this.product = product;
    }
    
    public void screenCut(int type) {
        if (type == 1) {
            System.out.println("\n------------------------------------\n");
        } else {
            System.out.println("\n====================================\n");
        }
    }

}


