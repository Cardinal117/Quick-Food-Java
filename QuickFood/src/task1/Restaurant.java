package task1;

import java.util.ArrayList;

public class Restaurant {

  private String name;
  private String contactNum;
  private String location;
  private ArrayList<String> item = new ArrayList<>();
  private ArrayList<Double> price = new ArrayList<>();

  // Instantiate Restaurant object to all variables.
  public Restaurant(String name, String contactNum, String location, String menu) {

    this.name = name;
    this.contactNum = contactNum;
    this.location = location;

    // Split menu string into two arrays.
    String[] orderArr = menu.split("\\|");

    for (int i = 0; i < orderArr.length; i++) {
      String[] details = orderArr[i].split("#");

      // Adds all split values to item and price arrays.
      if (details.length == 2) {
        item.add(details[0].trim());

        price.add(Double.parseDouble(details[1].trim()));
      }
    }
  }

  // Getters
  public ArrayList<String> getItems() {
    return item;
  }

  public ArrayList<Double> getPrices() {
    return price;
  }

  public String getName() {
    return this.name;
  }

  public String getContactNum() {
    return contactNum;
  }

  public String getLocation() {
    return location;
  }
}
