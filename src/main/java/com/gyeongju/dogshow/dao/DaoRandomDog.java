package com.gyeongju.dogshow.dao;

import java.util.Random;

public class DaoRandomDog {

  public enum DogName {
    Max, Charlie, Cooper, Buddy, Jack, Rocky, Oliver, Bear, Duke, Tucker,
    Bella, Lucy, Daisy, Luna, Lola, Sadie, Molly, Maggie, Bailey, Sophie;

    public static String getRandomDogName() {

      Random random = new Random();

      return values()[random.nextInt(values().length)].toString();
    }
  }

  public enum OwnerName {
    Max, Kristy, Andy, Addie, Johnny, John, Derek, Lucas, Raymond, Anh,
    Brandon, Reinard, Tamana, Breshna, Scott, Mok, Sean, Myron, Robin, Denzel;

    public static String getRandomOwnerName() {

      Random random = new Random();

      return values()[random.nextInt(values().length)].toString();
    }
  }

  public enum Breed {
    Labrador_Retrievers, German_Shepherd, Golden_Retrievers, French_Bulldogs, Bulldogs,
    Beagles, Poodles, Rottweilers, Yorkshire_Terriers, Boxers, Dachshunds, Great_Danes;

    public static String getRandomBreed() {

      Random random = new Random();
      return values()[random.nextInt(values().length)].toString().replaceAll("_"," ");
    }
  }

  public enum GroupName {

    Sporting_Dogs, Hounds, Working_Dogs, Terriers, Toys, Non_Sporting, Herding;

    public static String getRandomGroupName() {

      Random random = new Random();
      return values()[random.nextInt(values().length)].toString().replaceAll("_"," ");
    }
  }

  public enum Gender {

    Male, Female;

    public static String getRandomGender() {

      Random random = new Random();
      return values()[random.nextInt(values().length)].toString();
    }
  }

  public enum Ranking {

    Class, Specialty;

    public static String getRandomRanking() {

      Random random = new Random();
      return values()[random.nextInt(values().length)].toString();
    }
  }

}
