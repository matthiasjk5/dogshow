package com.gyeongju.dogshow.service;

import com.gyeongju.dogshow.entities.Dog;
import com.gyeongju.dogshow.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

  private JavaMailSender javaMailSender;

  @Autowired
  public MailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public void sendEmail(Dog dog) throws MailException {

    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setTo(dog.getEmail());
    mail.setSubject("Your dog has been registered successfully");
    mail.setText("/** Your Information **/\n\n" + "Dog Name: " + dog.getName() +
            ", Owner Name: " + dog.getOwnerName() + ", Breed: " + dog.getBreed() + "\n" +
            "Group: " + dog.getGroupName() + ", Gender: " + dog.getGender() + ", Ranking: " + dog.getRanking());
    javaMailSender.send(mail);
  }

  public void sendEmailWithAttachment(User user) throws MailException, MessagingException {

    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

    helper.setTo(user.getEmailAddress());
    helper.setSubject("Testing Mail API with Attachment");
    helper.setText("Please find the attached document below.");

    ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
    helper.addAttachment(classPathResource.getFilename(), classPathResource);

    javaMailSender.send(mimeMessage);
  }

}
