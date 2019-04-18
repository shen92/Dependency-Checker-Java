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
    test.constructGraph("cyclic.json");
    System.out.println("End constructGraph");
    for (String u : test.getGraph().getAllVertices()) {
      System.out.println(u + ": " + test.getInstallationOrder(u));
    }
    System.out.println("Over");
    // System.out.println(test.getInstallationOrderForAllPackages());
  }

  public static void gTest() {
    Graph g = new Graph();
  }
}
