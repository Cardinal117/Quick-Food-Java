package task1;

import java.util.ArrayList;

public class Customer {
  private String name;
  private String contactNum;
  private String email;
  private String address;
  private String location;
  private String restaurant;
  private String specialInstructions;
  private ArrayList<String> orders;

  // Instantiate Customer object to all variables.
  public Customer(String name, String contactNum, String email, String address, String location,
      String restaurant, String specialInstructions, ArrayList<String> orders) {

    this.name = name;
    this.contactNum = contactNum;
    this.email = email;
    this.address = address;
    this.location = location;
    this.restaurant = restaurant;
    this.specialInstructions = specialInstructions;
    this.orders = new ArrayList<>(orders);
  }

  // Getters
  public String getName() {
    return name;
  }

  public String getContactNum() {
    return contactNum;
  }

  public String getEmail() {
    return email;
  }

  public String getAddress() {
    return address;
  }

  public String getLocation() {
    return location;
  }

  public String getRestaurant() {
    return restaurant;
  }

  public String getSpecialInstructions() {
    return specialInstructions;
  }

  public ArrayList<String> getOrders() {
    return new ArrayList<>(orders);
  }
}
