package com.gyeongju.dogshow.controller;

import com.gyeongju.dogshow.dao.DaoDog;
import com.gyeongju.dogshow.dao.DaoShowList;
import com.gyeongju.dogshow.entities.Dog;
import com.gyeongju.dogshow.entities.User;
import com.gyeongju.dogshow.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

  @Autowired
  private MailService notificationService;

  @Autowired
  private User user;

  @Autowired
  DaoDog daoDog;

  @GetMapping("")
  public String home() {

    return "index.html";
  }

  @GetMapping("/register")
  public String registerDog(@RequestParam(required = false) String name,
                            @RequestParam(required = false) String ownerName,
                            @RequestParam(required = false) String breed,
                            @RequestParam(required = false) String groupName,
                            @RequestParam(required = false) String gender,
                            @RequestParam(required = false) String ranking,
                            @RequestParam(required = false) String email) {

    if (name != null) {

      Dog dog = new Dog(name, ownerName, email, breed, groupName, gender, ranking);
      DaoDog.addDogs(dog);

      try {
        notificationService.sendEmail(dog);
      } catch (MailException mailException) {
        System.out.println(mailException);
      }

      return "registerDog.html";
    }

    return "registerDog.html";
  }

  @GetMapping("/generate")
  public String generateDogs() {

    DaoDog.generateDogs();

    return "redirect:/register";
  }

  @GetMapping("/view")
  public String viewDog(@RequestParam(required = false) String condition,
                        @RequestParam(required = false) String text,
                        Model model) {

    if (condition == null && text == null) {

      model.addAttribute("dogs", DaoDog.viewDogs());

      return "viewDog";

    } else {
      System.out.println(condition);
      System.out.println(text);

      model.addAttribute("dogs", DaoDog.searchDogs(condition, text));

      return "viewDog";
    }
  }

  @GetMapping("/search")
  public String searchDog() {

    return "searchDog";
  }

  @GetMapping("/showList")
  public String showList(Model model) {

    model.addAttribute("showLists", DaoShowList.getShowArrayList());
    return "showList";
  }

  @GetMapping("/editlink/{id}")
  public String goEdit(@PathVariable int id, Model model, @ModelAttribute Dog dog) {
    model.addAttribute("dog", daoDog.getDogById(id));
    return "modify.html";
  }

  @GetMapping("/modify")
  public String modify(@ModelAttribute Dog dog, Model model) {
    daoDog.updateDog(dog);
    return "redirect:/view";
  }

  @GetMapping("/deletelink/{id}")
  public String deleteDog(@PathVariable int id, Model model) {
    daoDog.deleteDogById(id);
    return "redirect:/view";
  }

  @GetMapping("/login")
  public String login() {
    return "login.html";
  }

  @GetMapping("/handler")
  public String goHandler(Model model, Authentication auth) {
    System.out.println(auth.getName());
    model.addAttribute("dogs", daoDog.getDogsByName(auth.getName()));
    return "handler.html";
  }

  @GetMapping("/handler/register")
  public String handlerRegisterDog(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String ownerName,
                                   @RequestParam(required = false) String breed,
                                   @RequestParam(required = false) String groupName,
                                   @RequestParam(required = false) String gender,
                                   @RequestParam(required = false) String ranking,
                                   @RequestParam(required = false) String email,
                                   Authentication auth) {

    if (name != null) {
      System.out.println(auth.getName());
      Dog dog = new Dog(name, ownerName, email, breed, groupName, gender, ranking, auth.getName());
      DaoDog.handlerAddDogs(dog);

      try {
        notificationService.sendEmail(dog);
      } catch (MailException mailException) {
        System.out.println(mailException);
      }

      return "redirect:/handler";
    }

    return "handlerRegisterDog.html";
  }

}
