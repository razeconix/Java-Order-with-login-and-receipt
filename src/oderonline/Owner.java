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
public class Owner {
    private int id;
    private String username;
    private String password;
    private String name;

    public Owner(int id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
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
    
    public boolean validatePIN(String userPIN)
   {    
      if (userPIN.equals(password))
         return true;
      else
         return false;
   }
}
