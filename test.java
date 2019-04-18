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
    test.constructGraph("valid.json");
    System.out.println("End constructGraph");
    for (String s : test.getGraph().getAllVertices()) {
      System.out.println();
      System.out.println(s + ": " + test.getInstallationOrderForAllPackages());
    }
    System.out.println("Over");
    // System.out.println(test.getInstallationOrderForAllPackages());
  }

  public static void gTest() {
    Graph g = new Graph();
  }
}
