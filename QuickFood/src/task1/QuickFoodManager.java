package task1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class QuickFoodManager {

  private ArrayList<Customer> customer;
  private ArrayList<Restaurant> restaurant;
  private ArrayList<Driver> driver;

  // Calls all Manager methods to feed txt file data into arrays.
  QuickFoodManager() {
    customerManager();
    restaurantManager();
    driverManager();
  }

  // Reads from customer.txt and parses all data into the
  // customer object array.
  public void customerManager() {
    customer = new ArrayList<>();
    try {
      Scanner sc = new Scanner(new File("src/task1/customer.txt"));

      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        Scanner scFile = new Scanner(line).useDelimiter(",");

        String name = scFile.next();
        String contactNum = scFile.next();
        String email = scFile.next();
        String address = scFile.next();
        String location = scFile.next();
        String restaurant = scFile.next();
        String specialInstructions = scFile.next();
        String orderBatch = scFile.next();
        ArrayList<String> orders = new ArrayList<>();

        // Splits the orders into an array of the orders.
        String[] orderArr = orderBatch.split("\\|");

        for (String orderItem : orderArr) {
          orders.add(orderItem.trim());
        }

        customer.add(new Customer(name, contactNum, email, address, location, restaurant,
            specialInstructions, orders));

        scFile.close();
      }
      sc.close();

    } catch (FileNotFoundException e) {
      System.out.println("Could not find file:\n" + e);
    }
  }

  // Reads from restaurant.txt and parses all data into the
  // restaurant object array.
  public void restaurantManager() {
    restaurant = new ArrayList<>();

    try {
      Scanner sc = new Scanner(new File("src/task1/restaurant.txt"));

      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        Scanner scFile = new Scanner(line).useDelimiter(",");

        String name = scFile.next();
        String contactNum = scFile.next();
        String location = scFile.next();
        String menu = scFile.next();

        restaurant.add(new Restaurant(name, contactNum, location, menu));

        scFile.close();
      }
      sc.close();

    } catch (FileNotFoundException e) {
      System.out.println("Could not find file:\n" + e);
    }

  }

  // Reads from the drivers.txt and parses all the information
  // into the driver objects array.
  public void driverManager() {
    driver = new ArrayList<>();
    try {
      Scanner sc = new Scanner(new File("src/task1/driver-info.txt"));

      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        Scanner scFile = new Scanner(line).useDelimiter(",");

        String name = scFile.next();
        String location = scFile.next();
        String load = scFile.next();

        int loadSize = Integer.parseInt(load.trim());
        driver.add(new Driver(name, location, loadSize));

        scFile.close();
      }
      sc.close();

    } catch (FileNotFoundException e) {
      System.out.println("Could not find file:\n" + e);
    }
  }

  // Gets the name of the driver based on the index of the Customer's
  // location using the index of the customer.
  public String getDriverName(int index) {
    String driverName = "";
    for (int d = 0; d < driver.size(); d++) {

      if (customer.get(index).getLocation().equals(driver.get(d).getLocation())) {
        driverName = driver.get(d).getName();
        break;
      } else {
        driverName = "";
      }
    }
    return driverName;
  }

  // Prints the content variable into a specified file.
  public void printInvoice(String content, String orderNum) {
    try {
      StringBuilder fileName = new StringBuilder();

      fileName.append("src/invoices/").append(orderNum + ".txt");

      FileWriter writer = new FileWriter(fileName.toString(), true);
      BufferedWriter bufferedWriter = new BufferedWriter(writer);
      PrintWriter pr = new PrintWriter(bufferedWriter);

      pr.print(content);
      System.out.println("Invoice printed succesfully.");
      bufferedWriter.flush();
      pr.close();
    } catch (IOException e) {
      System.out.println("Could not print into  file: " + e);
    }
  }

  // Calculates, formats and displays the information of the
  // Customer's order. Stores and returns the information as a StringBuilder.
  public void invoice() {

    StringBuilder output = new StringBuilder();
    String customerInvoice = "";
    String orderNum = "";
    String restContact = "";
    String custLocation, custAddress;
    String decorationalLine =
        "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n\n";
    String errorLine =
        "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_--_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\n\n";
    LocalDateTime dateTime = LocalDateTime.now();
    ArrayList<String> orders;
    ArrayList<String> restaurantItems;
    int orderCount = 1;
    double deliveryFee = 59.99;
    double extrasFee = 24.99;
    double custPrice = 0;

    // Loops through all customers.
    for (Customer cust : customer) {

      // Checks if driverName is not null.
      if (getDriverName((orderCount - 1)).equals("")) {
        // Provides a valid error message if driverName (customer location is not
        // recognized or is too far away) is null.
        output.append(errorLine + "Customer: " + cust.getName()).append(
            "\n\nSorry! Our drivers are too far away from you to be able to deliver to your location.\n\n"
                + errorLine);
        
     // Calls the printInvoice method to store the invoice in a text file.
        printInvoice(output.toString(), "TooFarAway"+orderCount);
        output.setLength(0);
      } else {

        dateTime = LocalDateTime.now();
        orders = new ArrayList<>(cust.getOrders());
        custLocation = cust.getLocation();
        custAddress = cust.getAddress();

        // Adds the order code, name, email, contact and location to the invoice.
        orderNum = String.valueOf(dateTime.getYear()) + dateTime.getDayOfYear() + orderCount;// Calculates
                                                                                             // the
                                                                                             // order
                                                                                             // code.
        output.append(decorationalLine + "Order number: " + orderNum)
            .append("\nCustomer: " + cust.getName()).append("\nEmail: " + cust.getEmail())
            .append("\nPhone number: " + cust.getContactNum())
            .append("\nLocation: " + custLocation);

        int insertIndex = output.length();
        // Loops through each order of the customer.
        for (String order : orders) {
          for (Restaurant rest : restaurant) {// Loops through all Restaurant objects.
            // Compares the restaurant name from customer order and restaurant name to get the
            // restaurant menu details.
            if (cust.getRestaurant().equals(rest.getName())) {
              restaurantItems = new ArrayList<>(rest.getItems());
              restContact = rest.getContactNum();

              // Loops through all menu items from the Restaurant objects.
              for (int index = 0; index < restaurantItems.size(); index++) {
                String resItem = restaurantItems.get(index);

                // Compares customer order items and restaurant items to get the correct item
                // details.
                if (order.equals(resItem)) {
                  // Adds up the total price and adds the order details to the invoice.
                  custPrice += rest.getPrices().get(index);
                  output.append("\n1 x " + order + " (R" + rest.getPrices().get(index) + ")");
                  break;
                }
              }
              break;
            }
          }
        }
        // Adds the extra instructions to the invoice.
        output
            .insert(insertIndex,
                "\n\nYou have ordered the following from " + cust.getRestaurant() + " in "
                    + cust.getLocation() + ":\n")
            .append("\n\nSpecial instructions: " + cust.getSpecialInstructions());

        custPrice += deliveryFee;

        output.append("\n\nDelivery fee: R" + deliveryFee);

        // Adds the extra costs if the Customer had extra instructions.
        if (!cust.getSpecialInstructions().equals("none")) {
          custPrice += extrasFee;
          output.append("\nExtras cost: R" + extrasFee);
        }

        // Round off and add the total price to the invoice.
        custPrice = Math.round((custPrice) * 100.00) / 100.00;
        output.append("\nTotal: " + custPrice);

        // Adds the driver information for the customer to the invoice.
        output.append("\n\n" + getDriverName((orderCount - 1))
            + " is nearest to the restaurant and so they will be delivering your order to you at:")
            .append("\n\n" + custAddress + "\n" + custLocation) // Adds the restaurant contact
                                                                // number to the invoice.
            .append("\n\nIf you need to contact the restaurant, their number is " + restContact
                + "\n\n" + decorationalLine);

        customerInvoice = output.toString();

        // Calls the printInvoice method to store the invoice in a text file.
        printInvoice(output.toString(), orderNum);

        // Resets total price and order number for the next customer.
        custPrice = 0;
        orderNum = "";
        output.setLength(0);
      }
      orderCount++;
    }
  }

}
