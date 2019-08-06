package com.gyeongju.dogshow.dao;

import com.gyeongju.dogshow.entities.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class DaoDog {

  @Autowired
  protected JdbcTemplate jdbcTemplate;

  public static void addDogs(Dog dog) {

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = null;

      conn = DriverManager.getConnection("jdbc:mysql://localhost/gyeongju", "root", "root");

      String query ="INSERT INTO dogs (name, ownerName, email, breed, groupName, gender, ranking) VALUES (?,?,?,?,?," +
              "?,?)";

      PreparedStatement ps = conn.prepareStatement(query);

      ps.setString(1, dog.getName());
      ps.setString(2, dog.getOwnerName());
      ps.setString(3, dog.getEmail());
      ps.setString(4, dog.getBreed());
      ps.setString(5, dog.getGroupName());
      ps.setString(6, dog.getGender());
      ps.setString(7, dog.getRanking());

      ps.executeUpdate();

      conn.close();
    } catch(Exception e) {
      System.out.println(e);
    }
  }

  public static void generateDogs() {

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = null;

      conn = DriverManager.getConnection("jdbc:mysql://localhost/gyeongju", "root", "root");

      String query ="INSERT INTO dogs (name, ownerName, breed, groupName, gender, ranking) VALUES (?,?,?,?,?,?)";

      PreparedStatement ps = conn.prepareStatement(query);

      for (int i = 0; i < 10; i++) {

        //      ps.setString(1, Long.toString(dog.getId()));
        ps.setString(1, DaoRandomDog.DogName.getRandomDogName());
        ps.setString(2, DaoRandomDog.OwnerName.getRandomOwnerName());
        ps.setString(3, DaoRandomDog.Breed.getRandomBreed());
        ps.setString(4, DaoRandomDog.GroupName.getRandomGroupName());
        ps.setString(5, DaoRandomDog.Gender.getRandomGender());
        ps.setString(6, DaoRandomDog.Ranking.getRandomRanking());

        ps.executeUpdate();
      }

      conn.close();

    } catch(Exception e) {

      System.out.println(e);
    }
  }

  public static ArrayList<Dog> viewDogs() {
    ArrayList<Dog> dogArrayList = new ArrayList<Dog>();

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = null;

      conn = DriverManager.getConnection("jdbc:mysql://localhost/gyeongju", "root", "root");

      String Query = "SELECT * FROM dogs";

      Statement st = conn.createStatement();

      ResultSet rs = st.executeQuery(Query);
      ResultSetMetaData rsmd = rs.getMetaData();

      while (rs.next()) {
        long id = rs.getLong(1);
        String name = rs.getString(2);
        String ownerName = rs.getString(3);
        String email = rs.getString(4);
        String breed = rs.getString(5);
        String groupName = rs.getString(6);
        String gender = rs.getString(7);
        String ranking = rs.getString(8);

        Dog dog = new Dog(id, name, ownerName, email, breed, groupName, gender, ranking);
        dogArrayList.add(dog);
      }

    } catch (Exception e) {
      System.out.println(e);
    }

    return dogArrayList;
  }

  public static ArrayList<Dog> searchDogs(String condition, String text) {
    ArrayList<Dog> dogArrayList = new ArrayList<Dog>();

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = null;

      conn = DriverManager.getConnection("jdbc:mysql://localhost/gyeongju", "root", "root");

      String Query = "SELECT * FROM dogs WHERE UPPER(" + condition + ") = UPPER(\'" + text + "\')";

      Statement st = conn.createStatement();

      ResultSet rs = st.executeQuery(Query);
      ResultSetMetaData rsmd = rs.getMetaData();

      while (rs.next()) {
        long id = rs.getLong(1);
        String name = rs.getString(2);
        String ownerName = rs.getString(3);
        String email = rs.getString(4);
        String breed = rs.getString(5);
        String groupName = rs.getString(6);
        String gender = rs.getString(7);
        String ranking = rs.getString(8);

        Dog dog = new Dog(id, name, ownerName, email, breed, groupName, gender, ranking);
        dogArrayList.add(dog);
      }

    } catch (Exception e) {
      System.out.println(e);
    }

    return dogArrayList;
  }

  public Dog getDogById(int id) {
    String query = "SELECT * FROM dogs WHERE id=?";
    Dog dog = (Dog) jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(Dog.class));
    return dog;
  }

  public void deleteDogById(int id) {
    String query = "DELETE FROM dogs WHERE id=" + id;
    this.jdbcTemplate.update(query);
  }

  public void updateDog(Dog dog) {
    String query = "UPDATE dogs SET name=?, ownerName=?, email=?, breed=?, groupName=?, gender=?, ranking=?";
    Object[] params = new Object[]{dog.getName(), dog.getOwnerName(),
            dog.getEmail(), dog.getBreed(),
            dog.getGroupName(), dog.getGender(),
            dog.getRanking()
    };

    this.jdbcTemplate.update(query, params);

  }



}
