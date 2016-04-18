RES - LAB 02 - HEIG-VD 
===================

Repository for the SMTP client lab.

----------
#Description Of The Project
-------------

###**Technologies used**

JAVA 8, MockMock Server, Netbeans 8.0.2

###**Descrption**
A simple SMTP client which goal is to send e-mails to a list of addresses. A mock SMTP server is used. The main purpose of this project is to implement a SMTP client.

###**Installation**
**Step 1**: Clone the repository into yours.  
**Step 2**: Launch the "MockMock.jar". Instructions can be found in the section "Mock SMTP Server Installation"  
**Step 3**: Move the files *appconfig.properties*, *emails.txt*, *messages.txt* to the repertory where MailRobot.jar is located. Example: c:\test\MailRobot.jar -> config files must be placed into c:\test\  
**Step 4**: Compile and  the "MailRobot.jar" using "java -jar MailRobot.jar" in your prompt.  

###**Configuration**
> ***appconfig.properties*** is the file in which all the configuration propreties are stored. 
>
 - field : smtpServerAddress --> localhost by default but it can be edited.  
 - field : smtpServerPort   --> Cannot be edited since it is the   assigned port of the SMTP protocol. 
 - field : numberOfGroups --> Depends on the number of e-mails you want to use. 
The composition of a group is "One sender and two receivers". So three e-mail addresses means  one group. For example, to be able to send e-mails to two groups, emails.txt must contain at least six e-mail adrresses.
 - field : emails --> emails.txt is the file in which the list of e-mail addresses is stored.
 - field : messages --> messages.txt is the file in which the textual content of the e-mails to send is stored. Attention, one message only is possible with our implementation.

###**Implementation**
 Please, have a look at the figure stored in the *figures * repertory. The file *diagram.png* contains the class diagram of the project.
   ![class diagram](/figures/diagram.png)
 **Organizer.java**: Important class in which all the necessary information are collected from the different files. 
 Class functions: 
 -- readMessages() open the file *message.txt* in which the message to send is stored. 
 -- readEmails() open the *email.txt* file where the list of e-mail address is stored
 -- generateGroups() creates the wanted number of groups. The number of groups depends on the number of e-mail address and the field numberOfGroups of the configuration file *appconfig.properties*
 
**SmtpClient.java**: The SMTP client. This class can connect to a SMTP server and send messages. The protocol used in our implementation is the one recommended by RFC standards:

>Example:  
telnet smtp.xxxx.xxxx/IP address 25  
Connected to smtp.xxxx.xxxx./IP address  
220 smtp.xxxx.xxxx SMTP Ready  
EHLO client  
250-smtp.xxxx.xxxx  
250-PIPELINING  
250 8BITMIME         
MAIL FROM: <author@yyyy.yyyy>  
250 Sender ok  
RCPT TO: <dest@xxxx.xxxx>  
250 Recipient ok.  
DATA  
354 Enter mail, end with "."   
Subject: Test  
>
Corps du texte 
> 
.  
250 Ok  
QUIT  
221 Closing connection  
Connection closed by foreign host.   

**Person.java**: Representation of a person for whom we want to send an e-mail. Contains the e-mail address as a private attribut.
**Group.java**: A group of persons.
**Message.java**: The message we want to send.

###**Mock SMTP Server Installation**
 A jar file is included in the root of the repo. The command to execute the program in any promp is "java -jar MockMock.jar -p 25", where -p is the port used by the server. Be carreful, you should always have the lastest java version installed on your system.

###**Mock SMTP Server Information**
A Mocker Server allows to mock any server or service that you connect to over SMTP (in our case). It can easily recreate all types of responses for SMTP services to test applications easily and efficiently.

Authors
-------------
Romain Albasini & Dardan Selimi