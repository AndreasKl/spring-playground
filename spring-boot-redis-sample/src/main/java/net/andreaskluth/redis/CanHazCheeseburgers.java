package net.andreaskluth.redis;

import java.io.Serializable;

public class CanHazCheeseburgers implements Serializable {

  private static final long serialVersionUID = 1L;

  private String cat;
  private String dog;
  private String cheez;

  public CanHazCheeseburgers(String cat, String dog, String cheez) {
    this.cat = cat;
    this.dog = dog;
    this.cheez = cheez;
  }

  public String getCat() {
    return cat;
  }

  public String getDog() {
    return dog;
  }

  public String getCheez() {
    return cheez;
  }
}