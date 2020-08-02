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
public class Payment {

    private int id;
    private PurchaseDetail purchase;
    private Customer customer;
    private boolean status;

    public Payment(int id, PurchaseDetail purchase, Customer customer, boolean status) {
        this.id = id;
        this.purchase = purchase;
        this.customer = customer;
        this.status = status;
    }
    
    public void showPayment(){
        
        System.out.println("ID : "+id);
        System.out.println("Table : "+purchase.getTable());
        System.out.println("Customer : "+customer.getName());
        System.out.println("Total : "+purchase.sumPrice()+" Bath");
       screenCut(1);
        
    }
    
    public void showDetail(){
        purchase.showPurchaseDetail();
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getSum(){
        return purchase.sumPrice();
    }
    
    public int getpoint(){
        return customer.getPoint();
    }
    
    public void setPoint(int point){
        customer.setPoint(point);
    }
    
    
    public void setStatus(boolean status) {
        this.status = status;
    }

    public PurchaseDetail getPurchase() {
        return purchase;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getId() {
        return id;
    }
    
    

    public boolean isStatus() {
        return status;
    }

    public void screenCut(int type) {
        if (type == 1) {
            System.out.println("\n------------------------------------\n");
        } else {
            System.out.println("\n====================================\n");
        }
    }
}
