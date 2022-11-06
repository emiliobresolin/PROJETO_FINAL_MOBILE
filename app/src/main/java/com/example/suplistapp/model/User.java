package com.example.suplistapp.model;

public class User {

  private int id;
  private String name;
  private String userLogin;
  private String password;

  public User(int id, String name, String userLogin, String password) {
    this.id = id;
    this.name = name;
    this.userLogin = userLogin;
    this.password = password;
  }

  public User(String name, String userLogin, String password) {
    this.name = name;
    this.userLogin = userLogin;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getUserLogin() {
    return userLogin;
  }

  public String getPassword() {
    return password;
  }
}
