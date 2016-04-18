/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.mailrobot.smtp;

import ch.heigvd.res.mailrobot.config.Organizer;
import ch.heigvd.res.mailrobot.model.mail.Group;
import ch.heigvd.res.mailrobot.model.mail.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romai
 */
public class SmtpClient {

   private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

   Socket clientSocket;
   BufferedReader in;
   PrintWriter out;
   boolean connected = false;

   public void connect(String serverAddress, int serverPort) {
      System.out.println("Address : " + serverAddress + ", port : " + serverPort);

      try {
         clientSocket = new Socket("localhost", 25);
         System.out.println("ok");
         in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
         out = new PrintWriter(clientSocket.getOutputStream());
         String serverMsg = "";

         while ((serverMsg = in.readLine()) != null) {
            System.out.println(serverMsg);

            // Envoi du EHLO
            out.println("EHLO localhost");
            out.flush();

            while ((serverMsg = in.readLine()) != null) {
               System.out.println(serverMsg);
               if (serverMsg.equals("250 Ok")) {
                  System.out.println("EHLO envoyé !");
                  System.out.println("Dernier message : " + serverMsg);
                  return;
               }
            }
         }

      } catch (IOException e) {
         LOG.log(Level.SEVERE, "Unable to connect to server: {0}", e.getMessage());
         //cleanup();
         return;
      }

   }

   public void send(Organizer o, Group group) throws IOException {

      System.out.println("Envoi du mail a " + o.getGroupsTab().size() + " groupe(s)");
      
      for (int i = 0; i < o.getGroupsTab().size(); ++i) {

         System.out.println(group.getSender());
         System.out.println(group.getRecipients());
         int rdm = ThreadLocalRandom.current().nextInt(i, o.getMessagesTab().size());
         System.out.println("Random : " + rdm);

         Message m = o.getMessagesTab().elementAt(rdm);
         String mailFrom = group.getSender();
         String rcptTo = group.getRecipients();
         String subject = m.getSubject();
         String mess = m.getMessage();

         System.out.println("=============");
         System.out.println(m.getMessage());
         System.out.println("=============");

         /*mailFrom = "romain.albasini@heig-vd.ch";
          rcptTo = "romain.albasini@live.com,romain.albasini@netplus.ch";
          subject = "Informations";
          mess = "Salut\nMoi c'est roro lol";*/
         String serverMsg = "";
         boolean mailFromOk = false;
         boolean rcptToOk = false;

         out.println("MAIL FROM: " + mailFrom);
         System.out.println("MAIL FROM: " + mailFrom);
         out.flush();

         // TODO : n'arrive pas à recevoir les messages
         while ((serverMsg = in.readLine()) != "250 Ok") {
            System.out.println(serverMsg);
            out.println("RCPT TO: " + rcptTo);
            System.out.println("RCPT TO: " + rcptTo);
            out.flush();
            while ((serverMsg = in.readLine()) != "250 OK") {
               System.out.println(serverMsg);
               out.println("DATA");
               System.out.println("DATA");
               out.flush();

               // Envoi des DATA
               while ((serverMsg = in.readLine()) != "354 End data with <CR><LF>.<CR><LF>") {
                  out.println("From: " + mailFrom);
                  out.flush();
                  out.println("To: " + rcptTo);
                  out.flush();
                  out.println("Subject: " + subject);
                  out.flush();
                  out.println("");
                  out.flush();
                  out.println(mess);
                  out.flush();
                  out.println(".");
                  out.flush();

                  // Mail envoyé avec succès
                  return;

               }
               break;
            }
            break;
         }
         break;
      }
      
      return;
   }

   /*public void connect(String address, int port) {
    System.out.println("Hello");
    System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tH:%1$tM:%1$tS::%1$tL] Client > %5$s%n");

    Socket clientSocket = null;
    PrintWriter outToServer = null;
    BufferedReader inFromServer = null;
    String msgSend = "";
    String msgReceived = "";

    try {
    System.out.print("Connexion...");
    // Se connecte au serveur
    clientSocket = new Socket("localhost", 2525);
    System.out.println("OK !");

    // Initialise les streams/buffers d'entrée/sortie
    outToServer = new PrintWriter(clientSocket.getOutputStream());
    inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    // Read input
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String messageSend = "";

    outToServer.println("EHLO");
         
    do {
            
    System.out.println("Connecté au client");

    outToServer.println("EHLO");
    outToServer.flush();

    // Ecoute les messages envoyés du serveur
    while ((msgReceived = inFromServer.readLine()) != null) {

    System.out.println("RECU : " + msgReceived);
    break;
    }
    } while (!messageSend.equalsIgnoreCase("BYE"));

    clientSocket.close();
    } catch (IOException ex) {

    }
    }*/
}
