package ch.heigvd.res.mailrobot;

import ch.heigvd.res.mailrobot.config.Organizer;
import ch.heigvd.res.mailrobot.model.mail.Group;
import ch.heigvd.res.mailrobot.model.mail.Message;
import ch.heigvd.res.mailrobot.smtp.SmtpClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Romain, Dardan
 */
public class MailRobot {

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) throws IOException {

      try {

         // Déclaration des variables
         String smtpServerAddress ="";
         int smtpServerPort = 0;
         int numberOfGroups = 0;

         Properties prop = new Properties();

         try {
            
            // Récupère les informations du fichier properties
            String filename = "appconfig.properties";

            BufferedReader br = new BufferedReader(new FileReader(filename));
            prop.load(br);
            
            smtpServerAddress = prop.getProperty("smtpServerAddress");
            smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
            numberOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups"));

            System.out.println("Infos obtenues");
            System.out.println("smtpServerAddress : " + smtpServerAddress);
            System.out.println("smtpServerPort : " + smtpServerPort);
            System.out.println("numberOfGroups : " + numberOfGroups);
            System.out.println("");

         } catch (IOException ex) {
            ex.printStackTrace();
         }

         Organizer ac = new Organizer("emails.txt", numberOfGroups, "messages.txt");
         
         //ac.display();
         
         
         SmtpClient sm = new SmtpClient();
         sm.connect(smtpServerAddress,smtpServerPort);
         
         Message mes = new Message("Bonjour\nC'est roro");
         
         for(Group g : ac.getGroupsTab()){
            sm.send(ac,g);
         }
         

      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}
