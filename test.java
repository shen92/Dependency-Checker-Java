import java.io.FileNotFoundException;
import java.util.*;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class test {

  public static void main(String[] args) throws FileNotFoundException, IOException, ParseException,
      CycleException, PackageNotFoundException {

    // gTest();
    mTest();
  }

  public static void mTest() throws FileNotFoundException, IOException, ParseException,
      CycleException, PackageNotFoundException {
    PackageManager test = new PackageManager();
    test.constructGraph("shared_dependencies.json");
    System.out.println("End constructGraph");
    System.out.println("A: " + test.getInstallationOrder("A"));
  }

  public static void gTest() {
    Graph g = new Graph();
  }
}
