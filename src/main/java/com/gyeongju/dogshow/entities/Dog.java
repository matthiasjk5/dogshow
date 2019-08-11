package com.gyeongju.dogshow.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dog {

  private long id;
  private String name;
  private String ownerName;
  private String email;
  private String breed;
  private String groupName;
  private String gender;
  private String ranking;
  private String handlerName;

  public Dog(long id, String name, String ownerName, String email, String breed, String groupName, String gender,
             String ranking) {
    this.id = id;
    this.name = name;
    this.ownerName = ownerName;
    this.email = email;
    this.breed = breed;
    this.groupName = groupName;
    this.gender = gender;
    this.ranking = ranking;
  }

  public Dog(String name, String ownerName, String breed, String groupName, String gender, String ranking) {
    this.name = name;
    this.ownerName = ownerName;
    this.breed = breed;
    this.groupName = groupName;
    this.gender = gender;
    this.ranking = ranking;
  }

  public Dog(String name, String ownerName, String email, String breed, String groupName, String gender,
             String ranking) {
    this.name = name;
    this.email = email;
    this.ownerName = ownerName;
    this.breed = breed;
    this.groupName = groupName;
    this.gender = gender;
    this.ranking = ranking;
  }

  public Dog(String name, String ownerName, String email, String breed, String groupName, String gender,
             String ranking, String handlerName) {
    this.name = name;
    this.ownerName = ownerName;
    this.email = email;
    this.breed = breed;
    this.groupName = groupName;
    this.gender = gender;
    this.ranking = ranking;
    this.handlerName = handlerName;
  }
}
