package ch.heigvd.res.mailrobot.model.mail;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 *
 * Represents a group consisting of several Person
 * 
 * @author Romain, Dardan
 */
public class Group {

   /**
   * Constructor with a list of persons. 
   * Chooses one person as sender, the rest as recipients
   * 
   */
   public Group(List<Person> persons) {
      
      // A group consists of 3 persons minimum
      if (persons.size() < 3) {
         System.out.println("Nombre de personnes insuffisantes ! Il faut 3 personnes minimum");

      } else {
         // Shuffles the list
         Collections.shuffle(persons);
         
         // First Person is choosen as sender, the rest as recipients
         sender = persons.get(0);
         for(int i = 1; i < persons.size(); ++i){
            recipients.add(persons.get(i));
         }
      }
   }

   /**
   * Returns a printable version of the class
   * 
   */
   public String toString() {

      String formattedString = "";

      formattedString += "GROUPE : \n";
      formattedString += "Sender : " + sender.getEmail() + "\n";
      formattedString += "Recipients : \n";
      for (int i = 0; i < recipients.size(); ++i) {
         formattedString += recipients.elementAt(i).getEmail() + "\n";
      }

      return formattedString;
   }
   
   /**
   * Returns the sender
   * 
   */
   public String getSender(){
      return sender.toString();
   }

   /**
   * Returns the recipients
   * 
   */
   public String getRecipients(){
      String formattedString = "";
      for(int i = 0; i < recipients.size(); ++i){
         formattedString += recipients.elementAt(i);
         if(i != recipients.size()-1){
            formattedString+=",";
         }
      }
      
      return formattedString;
   }
   
   private Person sender;
   private Vector<Person> recipients = new Vector<>(0);
}
