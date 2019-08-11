package com.gyeongju.dogshow.controller;

import com.gyeongju.dogshow.entities.User;
import com.gyeongju.dogshow.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
class RegistrationController {

  @Autowired
  private MailService notificationService;

  @Autowired
  private User user;

//  @RequestMapping("/send-email")
//  public String send() {
//    user.setEmailAddress("matiakkj@gmail.com");
//
//    try {
//      notificationService.sendEmail(dog);
//    } catch (MailException mailException) {
//      System.out.println(mailException);
//    }
//
//    return "Congratulations! You mail has been send to the user";
//  }

  @RequestMapping("send-mail-attachment")
  public String sendWithAttachment() throws MessagingException {

    /*
     * Creating a User with the help of User class that we have declared and setting
     * Email address of the sender.
     */
    user.setEmailAddress("matiakkj@gmail.com"); //Receiver's email address

    /*
     * Here we will call sendEmailWithAttachment() for Sending mail to the sender
     * that contains a attachment.
     */
    try {
      notificationService.sendEmailWithAttachment(user);
    } catch (MailException mailException) {
      System.out.println(mailException);
    }
    return "Congratulations! Your mail has been send to the user.";
  }
}
