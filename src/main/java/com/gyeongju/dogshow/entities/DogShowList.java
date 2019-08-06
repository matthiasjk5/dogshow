package com.gyeongju.dogshow.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DogShowList {

  public long numberOfGroup;
  public String groupName;
  public  String breed;
  public long maleClass;
  public long femaleClass;
  public long maleSpecialty;
  public long femaleSpecialty;

  public DogShowList(String groupName, String breed, long maleClass, long femaleClass, long maleSpecialty,
                     long femaleSpecialty) {
    this.groupName = groupName;
    this.breed = breed;
    this.maleClass = maleClass;
    this.femaleClass = femaleClass;
    this.maleSpecialty = maleSpecialty;
    this.femaleSpecialty = femaleSpecialty;
  }
}
