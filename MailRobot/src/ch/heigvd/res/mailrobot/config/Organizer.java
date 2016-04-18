/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.mailrobot.config;

import ch.heigvd.res.mailrobot.*;
import ch.heigvd.res.mailrobot.model.mail.Group;
import ch.heigvd.res.mailrobot.model.mail.Message;
import ch.heigvd.res.mailrobot.model.mail.Person;
import ch.heigvd.res.mailrobot.smtp.SmtpClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;

/**
 *
 * @author romai
 */
public class Organizer {
   
   // Lit le fichier messages.txt
   public Vector<Message> readMessages(String messages) throws FileNotFoundException, IOException{
      Vector<Message> messagesTab = new Vector<>(0);
      
      File file = new File(messages);
      
      try {

         BufferedReader br = new BufferedReader(new FileReader(file));
         String line;

         String msg = "";
         while ((line = br.readLine()) != null) {
            while(!line.equals("====")){
            
               msg += line + "\n";
               line = br.readLine();
            }

            // Supprime le retour à la ligne supplémentaire
            msg = msg.substring(0, msg.length()-1);

            // Ajoute un nouveau message à messagesTab
            messagesTab.add(new Message(msg));
            
            // Réinitialise la string pour passer au message suivant
            msg = "";
         }


      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }

      return messagesTab;
   }
   
   // Lit le fichier emails.txt
   public Vector<Person> readEmails(String emails) throws FileNotFoundException, IOException{
      Vector<Person> emailsTab = new Vector<>(0);
      
      File file = new File(emails);
      
      try {

         BufferedReader br = new BufferedReader(new FileReader(file));
         String line;

         String email = "";
         while ((line = br.readLine()) != null) {
            emailsTab.add(new Person(line));
         }


      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }

      return emailsTab;
   }

   
   // Génère le nombre de groupes voulus
   public Vector<Group> generateGroups(Vector<Person> emailsTab, int numberOfGroups){
      Vector<Group> groupsTab = new Vector<>(0);

      if(numberOfGroups > emailsTab.size()/numberOfGroups )
      {
         System.out.println("Nombre de personnes insuffisantes par rapport au nombre de groupes demandés");
      } 
      else {
         
         // Taille des groupes. Le dernier groupe peut éventuellement avoir moins de destinataires
         int tailleGroupe = (int)(((double)emailsTab.size()/numberOfGroups)+0.9);
         //System.out.println("Taille groupe : " + tailleGroupe);
         int membres = 0;
         
         // Crée le nombre de groupe voulu en adaptant le dernier groupe éventuellement plus petit que les autres
         for(int i = 0; i < numberOfGroups; ++i){
            int indexDébut = i*tailleGroupe;
            int indexFin = indexDébut+tailleGroupe;
            
            // Dans le cas ou le dernier groupe devait avoir moins de membres
            if(indexFin > emailsTab.size()){
               indexFin = emailsTab.size();
            }
            
            // Ajoute un nouveau groupe avec les destinataires voulus
            groupsTab.add(new Group(emailsTab.subList(indexDébut, indexFin)));
         }
                 
      }

      return groupsTab;
   }
   
   
   // Récupère les messages, les emails, et crée les groupes voulus
   public Organizer(String emails, int numberOfGroups, String messages) throws FileNotFoundException, IOException {

      messagesTab = readMessages(messages);
      emailsTab = readEmails(emails);
      groupsTab = generateGroups(emailsTab, numberOfGroups); 

   }
   
   public void display(){
      System.out.println("ORGANIZER");
      
      System.out.println("MESSAGES");
      for(int i = 0; i < messagesTab.size(); ++i){
         System.out.println(messagesTab.elementAt(i));
      }
      
      System.out.println("GROUPES");
      for(int i = 0; i < groupsTab.size(); ++i){
         System.out.println(groupsTab.elementAt(i));
      }
              
   }
   
   public Vector<Message> getMessagesTab(){
      return messagesTab;
   }
   
   public Vector<Group> getGroupsTab(){
      return groupsTab;
   }
   
   private Vector<Message> messagesTab = new Vector<>(0);
   private Vector<Person> emailsTab = new Vector<>(0);
   private Vector<Group> groupsTab = new Vector<>(0);
}
