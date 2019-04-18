import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Filename: PackageManager.java Project: p4 Authors:
 * 
 * PackageManager is used to process json package dependency files and provide function that make
 * that information available to other users.
 * 
 * Each package that depends upon other packages has its own entry in the json file.
 * 
 * Package dependencies are important when building software, as you must install packages in an
 * order such that each package is installed after all of the packages that it depends on have been
 * installed.
 * 
 * For example: package A depends upon package B, then package B must be installed before package A.
 * 
 * This program will read package information and provide information about the packages that must
 * be installed before any given package can be installed. all of the packages in
 * 
 * You may add a main method, but we will test all methods with our own Test classes.
 */

public class PackageManager {

  private Graph graph;
  private Graph rGraph;

  private boolean isDAG;

  /*
   * Package Manager default no-argument constructor.
   */
  public PackageManager() {
    this.graph = new Graph();
    this.rGraph = new Graph();
    this.isDAG = true;
  }

  /**
   * Takes in a file path for a json file and builds the package dependency graph from it.
   * 
   * @param jsonFilepath the name of json data file with package dependency information
   * @throws FileNotFoundException if file path is incorrect
   * @throws IOException if the give file cannot be read
   * @throws ParseException if the given json cannot be parsed
   */
  public void constructGraph(String jsonFilepath)
      throws FileNotFoundException, IOException, ParseException {
    Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
    JSONObject jo = (JSONObject) obj; // parse the file to a JSONOBject
    // System.out.println(jo);

    // convert "package" array in json to JSONArray in java
    JSONArray packages = (JSONArray) jo.get("packages");
    // parse each "package" objects in json file, create Package instances and put in graph
    for (int i = 0; i < packages.size(); i++) {
      // parse "package" json object to JSONObject
      JSONObject jsonPackage = (JSONObject) packages.get(i);
      // get name fields from each "package" object in json file
      String packageName = (String) jsonPackage.get("name");// get "package" name

      // convert "dependencies" field in "package" to JSONArray in java
      JSONArray dependencies = (JSONArray) jsonPackage.get("dependencies");

      // parse the "dependencies" fields in "package" field
      String[] packageDependencies = new String[dependencies.size()];
      for (int j = 0; j < dependencies.size(); j++) {
        packageDependencies[j] = (String) dependencies.get(j);
      }
      // create a pack instance
      Package pack = new Package(packageName, packageDependencies);

      // add vertex to the graph
      graph.addVertex(pack.getName());
      // add vertex to the rGraph
      rGraph.addVertex(pack.getName());

      for (int j = 0; j < packageDependencies.length; j++) {
        // add edges point to the vertex
        System.out.println("graph:");
        graph.addEdge(pack.getDependencies()[j], pack.getName());
        System.out.println("rGraph:");
        rGraph.addEdge(pack.getName(), pack.getDependencies()[j]);
      }
    }
  }

  /**
   * Helper method to get all packages in the graph.
   * 
   * @return Set<String> of all the packages
   */
  public Set<String> getAllPackages() {
    return graph.getAllVertices();
  }

  /**
   * Given a package name, returns a list of packages in a valid installation order.
   * 
   * Valid installation order means that each package is listed before any packages that depend upon
   * that package.
   * 
   * @return List<String>, order in which the packages have to be installed
   * 
   * @throws CycleException if you encounter a cycle in the graph while finding the installation
   *         order for a particular package. Tip: Cycles in some other part of the graph that do not
   *         affect the installation order for the specified package, should not throw this
   *         exception.
   * 
   * @throws PackageNotFoundException if the package passed does not exist in the dependency graph.
   */
  public List<String> getInstallationOrder(String pkg)
      throws CycleException, PackageNotFoundException {
    checkCycle();
    if (!isDAG) {
      throw new CycleException();
    }
    if (!graph.getAllVertices().contains(pkg)) {
      System.out.println(pkg + " not exist");
      throw new PackageNotFoundException();
    }
    Stack<String> st = new Stack<>();
    Set<String> unvisited = graph.getAllVertices();
    List<String> installOrder = new ArrayList<>();
    unvisited.remove(pkg);
    st.push(pkg);
    while (!st.empty()) {
      String curr = st.peek();
      boolean allVisited = true;
      for (String s : rGraph.getAdjacentVerticesOf(curr)) {
        if (unvisited.contains(s)) {
          allVisited = false;
        }
      }
      if (allVisited) {
        st.pop();
        installOrder.add(0, curr);
      } else {
        for (String s : rGraph.getAdjacentVerticesOf(curr)) {
          if (unvisited.contains(s)) {
            unvisited.remove(s);
            st.push(s);
            break;
          }
        }

      }
    }
    Collections.reverse(installOrder);
    return installOrder;
  }

  /**
   * Given two packages - one to be installed and the other installed, return a List of the packages
   * that need to be newly installed.
   * 
   * For example, refer to shared_dependecies.json - toInstall("A","B") If package A needs to be
   * installed and packageB is already installed, return the list ["A", "C"] since D will have been
   * installed when B was previously installed.
   * 
   * @return List<String>, packages that need to be newly installed.
   * 
   * @throws CycleException if you encounter a cycle in the graph while finding the dependencies of
   *         the given packages. If there is a cycle in some other part of the graph that doesn't
   *         affect the parsing of these dependencies, cycle exception should not be thrown.
   * 
   * @throws PackageNotFoundException if any of the packages passed do not exist in the dependency
   *         graph.
   */
  public List<String> toInstall(String newPkg, String installedPkg)
      throws CycleException, PackageNotFoundException {
    checkCycle();
    if (!isDAG) {
      throw new CycleException();
    }
    if (!graph.getAllVertices().contains(newPkg)) {
      System.out.println(newPkg + " not exist");
      throw new PackageNotFoundException();
    }
    if (!graph.getAllVertices().contains(installedPkg)) {
      System.out.println(installedPkg + " not exist");
      throw new PackageNotFoundException();
    }
    Stack<String> st = new Stack<>();
    Set<String> unvisited = graph.getAllVertices();
    List<String> installOrder = new ArrayList<>();
    unvisited.remove(newPkg);
    unvisited.remove(installedPkg);
    st.push(newPkg);
    while (!st.empty()) {
      String curr = st.peek();
      boolean allVisited = true;
      for (String s : rGraph.getAdjacentVerticesOf(curr)) {
        if (unvisited.contains(s)) {
          allVisited = false;
        }
      }
      if (allVisited) {
        st.pop();
        installOrder.add(0, curr);
      } else {
        for (String s : rGraph.getAdjacentVerticesOf(curr)) {
          if (unvisited.contains(s)) {
            unvisited.remove(s);
            st.push(s);
            break;
          }
        }
      }
    }
    Collections.reverse(installOrder);
    return installOrder;
  }

  /**
   * Return a valid global installation order of all the packages in the dependency graph.
   * 
   * assumes: no package has been installed and you are required to install all the packages
   * 
   * returns a valid installation order that will not violate any dependencies
   * 
   * @return List<String>, order in which all the packages have to be installed
   * @throws CycleException if you encounter a cycle in the graph
   * @throws PackageNotFoundException
   */
  public List<String> getInstallationOrderForAllPackages()
      throws CycleException, PackageNotFoundException {
    checkCycle();
    if (!isDAG) {
      throw new CycleException();
    }
    Stack<String> st = new Stack<>();
    Set<String> unvisited = graph.getAllVertices();
    List<String> topoOrder = new ArrayList<>();
    for (String v : rGraph.getAllVertices()) {
      if (!hasPredecessor(v)) {
        unvisited.remove(v);
        st.push(v);
      }
    }
    while (!st.empty()) {
      String curr = st.peek();
      boolean allVisited = true;
      for (String s : rGraph.getAdjacentVerticesOf(curr)) {
        if (unvisited.contains(s)) {
          allVisited = false;
        }
      }
      if (allVisited) {
        st.pop();
        topoOrder.add(0, curr);
      } else {
        for (String s : rGraph.getAdjacentVerticesOf(curr)) {
          if (unvisited.contains(s)) {
            unvisited.remove(s);
            st.push(s);
            break;
          }
        }
      }
    }
    Collections.reverse(topoOrder);
    return topoOrder;
  }

  /**
   * Find and return the name of the package with the maximum number of dependencies.
   * 
   * Tip: it's not just the number of dependencies given in the json file. The number of
   * dependencies includes the dependencies of its dependencies. But, if a package is listed in
   * multiple places, it is only counted once.
   * 
   * Example: if A depends on B and C, and B depends on C, and C depends on D. Then, A has 3
   * dependencies - B,C and D.
   * 
   * @return String, name of the package with most dependencies.
   * @throws CycleException if you encounter a cycle in the graph
   * @throws PackageNotFoundException
   */
  public String getPackageWithMaxDependencies() throws CycleException, PackageNotFoundException {
    ArrayList<ArrayList<String>> allInstallOrders = new ArrayList<>();
    for (String u : rGraph.getAllVertices()) {
      allInstallOrders.add((ArrayList<String>) getInstallationOrder(u));
    }
    int num = allInstallOrders.get(0).size();
    String max = allInstallOrders.get(0).get(allInstallOrders.get(0).size() - 1);
    for (int i = 0; i < allInstallOrders.size(); i++) {
      if (allInstallOrders.get(i).size() > num) {
        max = allInstallOrders.get(i).get(allInstallOrders.get(i).size() - 1);
      }
    }
    return max;
  }

  public static void main(String[] args) {
    System.out.println("PackageManager.main()");
  }

  private boolean hasPredecessor(String vertex) {
    return graph.getAdjacentVerticesOf(vertex).size() > 0;
  }

  private List<String> predecessorsOf(String vertex) {
    return graph.getAdjacentVerticesOf(vertex);
  }

  private boolean hasSuccessor(String vertex) {
    // System.out.println(graph.getAdjacentVerticesOf(vertex));
    return rGraph.getAdjacentVerticesOf(vertex).size() > 0;
  }

  private List<String> successorsOf(String vertex) {
    return rGraph.getAdjacentVerticesOf(vertex);
  }

  private void checkCycle() {
    System.out.println("check");
    Set<String> unvisited = rGraph.getAllVertices();
    Set<String> visited = new HashSet<>();
    for (String u : rGraph.getAllVertices()) {
      DFS(u, unvisited, visited);
    }
  }

  private void DFS(String v, Set<String> unvisited, Set<String> visited) {

  }

  public Graph getGraph() {
    return this.graph;
  }

}
