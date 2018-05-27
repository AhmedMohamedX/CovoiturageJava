/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author Hard-System-Info
 */
public class temoignage {
      private int id;
      private User user;
      private String message;
      private Date  date_tem;
      

    public temoignage( int id, User user,String message,Date date_tem) {
       
        this.id=id;
        this.date_tem=date_tem;
        this.user=user;
        this.message=message;
      
      
    
    
}

    public temoignage(int id,String message) {
                this.id=id;
             this.message = message;
    }
    
     public temoignage(  User user,String message,Date date_tem) {
       
       
        this.date_tem=date_tem;
        this.user=user;
        this.message=message;
      
      
    
    
}


    public void setId(int id) {
        this.id = id;
    }

    

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate_tem(Date date_tem) {
        this.date_tem = date_tem;
    }

    public int getId() {
        return id;
    }

  

    public String getMessage() {
        return message;
    }

    public Date getDate_tem() {
        return date_tem;
    }

   
    public temoignage() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "temoignage{" + "id=" + id + ", user=" + user + ", message=" + message + ", date_tem=" + date_tem + '}';
    }

  
    
}
