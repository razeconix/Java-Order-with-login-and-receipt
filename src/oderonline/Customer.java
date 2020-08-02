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
public class Customer {
    private int id;
    private String username;
    private String password;
    private String name;
    private int point;

    public Customer(int id, String username, String password, String name, int point) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.point = point;
    }

    Customer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }
     public boolean validatePIN(String userPIN)
   {
      if (userPIN.equals(password))
         return true;
      else
         return false;
   }
    
    
    
}
