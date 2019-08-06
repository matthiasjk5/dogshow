package com.gyeongju.dogshow.dao;

import com.gyeongju.dogshow.entities.DogShowList;

import java.sql.*;
import java.util.ArrayList;

public class DaoShowList {
  public static ArrayList<DogShowList> showArrayList = new ArrayList<DogShowList>();

  public static ArrayList<DogShowList> getShowArrayList() {

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = null;

      conn = DriverManager.getConnection("jdbc:mysql://localhost/gyeongju", "root", "root");

      String Query = "SELECT groupName, breed, gender, ranking, COUNT(*) FROM dogs GROUP BY " +
              "groupName, breed, gender, ranking;\n";

      Statement st = conn.createStatement();

      ResultSet rs = st.executeQuery(Query);
      ResultSetMetaData rsmd = rs.getMetaData();

      while (rs.next()) {

        String groupName = rs.getString(1);
        String breed = rs.getString(2);
        String gender = rs.getString(3);
        String ranking = rs.getString(4);
        long count = rs.getLong(5);

        evaluateShowList(groupName, breed, gender, ranking, count);

      }

    } catch (Exception e) {
      System.out.println(e);
    }

    return showArrayList;
  }

  public static void evaluateShowList(String groupName, String breed, String gender, String ranking, long count) {

        /* 1. At first, Assign Bean 'DogShowList' and Initialize ArrayList 'ShowArrayList'.
           2. From second iteration, Compare current Group and Breed with right previous values.
              If true, ArrayList doesn't create, and ArrayList uses the previous element
           3. If conditions for first two doesn't meet, create new DogShowList, and assign it to ArrayList */

    DogShowList showList = null;

    if (showArrayList.size() == 0) {
      showList = new DogShowList();
      showArrayList.add(showList);
    } else if (showArrayList.get(showArrayList.size()-1).getGroupName().equals(groupName) && showArrayList.get(showArrayList.size()-1).getBreed().equals(breed)) {
      showList = showArrayList.get(showArrayList.size()-1);
    } else {
      showList = new DogShowList();
      showArrayList.add(showList);
    }

    showList.setGroupName(groupName);
    showList.setBreed(breed);

    /* Evaulate the value on what its gender and ranking are, set the value based on the number of each case */
    if (gender.equals("Male") && ranking.equals("Class")) {
      showList.setMaleClass(count);
    } else if (gender.equals("Female") && ranking.equals("Class")) {
      showList.setFemaleClass(count);
    } else if (gender.equals("Male") && ranking.equals("Specialty")) {
      showList.setMaleSpecialty(count);
    } else if (gender.equals("Female") && ranking.equals("Specialty")) {
      showList.setFemaleSpecialty(count);
    }

  }
}
