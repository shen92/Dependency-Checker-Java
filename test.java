import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class test {

  public static void main(String[] args)
      throws FileNotFoundException, IOException, ParseException, CycleException {

    gTest();
    // mTest();
  }

  public static void mTest()
      throws FileNotFoundException, IOException, ParseException, CycleException {
    PackageManager test = new PackageManager();
    test._constructGraph("valid.json");
    System.out.println("End constructGraph");
    test.getInstallationOrderForAllPackages();
  }

  public static void gTest() {
    Graph g = new Graph();
    g.addVertex("a");
    g.addVertex("b");
    g.addVertex("c");
    g.addVertex("d");
    g.addVertex("e");
    g.addVertex("f");
    g.addEdge("a", "b");
    g.addEdge("a", "g");
    g.addEdge("a", "c");
    g.addEdge("a", "g");
    g.addEdge("c", "b");
    g.addEdge("c", "g");
    g.removeVertex("b");
    g.removeVertex("g");
    g.removeVertex("c");
    System.out.println("a's adjs: " + g.getAdjacentVerticesOf("a"));
    System.out.println("f's adjs: " + g.getAdjacentVerticesOf("f"));
  }
}
