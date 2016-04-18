/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.mailrobot.model.mail;

/**
   * Represents a person.
   * 
   * @author Romain, Dardan 
   */
public class Person {
   
   /**
   * Constructor with an email address.
   * 
   */
   public Person(String email){
      this.email = email;
   }
   
   /**
   * Returns a printable version of the class
   * 
   */
   public String toString(){
      return this.email;
   }
   
   /**
   * Returns the email
   * 
   */
   public String getEmail(){
      return this.email;
   }
   
   private String email;
}
