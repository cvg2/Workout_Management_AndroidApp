package com.example.termproj;

public class Contact {
  private String name;
  private String phone;
  private String email;

  private String user;

  private String pass;

  private String role;

  private Integer stats;


  public Contact(String name, String phone, String email, String user, String pass, String role, Integer stats) {
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.user = user;
    this.pass = pass;
    this.role = role;
    this.stats = stats;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getUser() {
    return user;
  }

  public String getPass() {
    return pass;
  }

  public String getRole() {
    return role;
  }

  public Integer getStats() {
    return stats;
  }



}
