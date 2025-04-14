package task1;

public class Driver {

  private String name;
  private String location;
  private int loadSize;

  // Instantiate Driver object to variables.
  public Driver(String name, String location, int loadSize) {
    this.name = name;
    this.location = location;
    this.loadSize = loadSize;
  }

  // Getters
  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }

  public int getLoadSize() {
    return loadSize;
  }

}
