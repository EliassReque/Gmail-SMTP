/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.util.Properties;    
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;    
import javax.mail.internet.*;    
//Funciona esto!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
class Mailer{  
    public static void send(String from,String password,String to,String sub,String msg){  
        //HAY QUE UTILIZAR LAS PROPIEDADES DE ABAJO
        //Importar los tres jars(mail.jar,javax.mail.jar,activation.jar)
        
          //Get properties object    
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
            return new PasswordAuthentication(from,password);  
           }    
          });    
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           // creating first MimeBodyPart object 
            BodyPart messageBodyPart1 = new MimeBodyPart();  
            messageBodyPart1.setText("This is body of the mail"); 
              
            // creating second MimeBodyPart object 
            BodyPart messageBodyPart2 = new MimeBodyPart();  
            String filename = "hola.txt";
            DataSource source = new FileDataSource(filename);   
            messageBodyPart2.setDataHandler(new DataHandler(source));   
            messageBodyPart2.setFileName(filename);   
              
            // creating MultiPart object 
            Multipart multipartObject = new MimeMultipart();   
            multipartObject.addBodyPart(messageBodyPart1);   
            multipartObject.addBodyPart(messageBodyPart2); 
      
      
      
            // set body of the email. 
            message.setContent(multipartObject);    
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);}    
             
    }  
}  
public class Main{    
 public static void main(String[] args) {    
     //from,password,to,subject,message  
     Mailer.send("elyasreque@gmail.com","rayane2425","elyasreque@gmail.com","hello javatpoint","How r u?");  
     //change from, password and to  
 }    
}    