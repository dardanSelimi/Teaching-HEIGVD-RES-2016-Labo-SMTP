package ch.heigvd.res.mailrobot.model.mail;

/**
 *
 * Represents a Message. Consists of a subject and a message
 *
 * @author Romain, Dardan
 */
public class Message {

   /**
    *
    * Constructor with a String
    *
    */
   public Message(String m) {
      subject = m.substring(0, m.indexOf("\n"));
      message = m.substring(m.indexOf("\n"), m.length());

   }

   /**
    * Returns a printable version of the class
    *
    */
   public String toString() {
      String formattedString = "";

      formattedString += "MESSAGE\n";
      formattedString += "=============\n";
      formattedString += "Subject: " + subject + "\n";
      formattedString += "Message: " + message + "\n";
      formattedString += "=============\n";
      return formattedString;
   }

   /**
    * Returns the subject
    *
    */
   public String getSubject() {
      return this.subject;
   }

   /**
    * Returns the message
    *
    */
   public String getMessage() {
      return this.message;
   }

   private String subject;
   private String message;
}
