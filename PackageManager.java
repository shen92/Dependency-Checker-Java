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

  private HashSet<Package> allPackages;

  /*
   * Package Manager default no-argument constructor.
   */
  public PackageManager() {
    this.graph = new Graph();
    this.rGraph = new Graph();
    this.allPackages = new HashSet<>();
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

      Package pack = new Package(packageName, packageDependencies);// create a pack instance
      System.out.println("Directed Graph:");
      graph.addVertex(pack.getName());// add vertex to the graph
      System.out.println("Reverse Graph:");
      rGraph.addVertex(pack.getName());
      for (int j = 0; j < packageDependencies.length; j++) {
        System.out.println("Directed Graph:");
        graph.addEdge(pack.getDependencies()[j], pack.getName());// add edges point to the vertex
        System.out.println("Reverse Graph:");
        rGraph.addEdge(pack.getName(), pack.getDependencies()[j]);
      }
      allPackages.add(pack);
      // debug
      System.out.print("Package Name: ");
      this._pinf(pack);
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
    if (!graph.getAllVertices().contains(pkg)) {
      System.out.println(pkg + " not exist");
      throw new PackageNotFoundException();
    }
    List<String> DFS = new ArrayList<>();
    Set<String> unvisited = rGraph.getAllVertices();
    DFS = DFSHelper(pkg, DFS, unvisited);
    DFS.remove(0);
    return DFS;
  }

  private List<String> DFSHelper(String v, List<String> DFS, Set<String> unvisited)
      throws CycleException {
    System.out.println("DFS: " + v);
    unvisited.remove(v);// mark v as visited
    DFS.add(v);
    System.out.println("DFS Sort: " + DFS);
    for (String s : rGraph.getAdjacentVerticesOf(v)) {// for each successors of v
      if (s.equals(v)) {
        throw new CycleException();
      }
      if (unvisited.contains(s)) {
        return DFSHelper(s, DFS, unvisited);
      }
      return DFS;
    }
    return DFS;
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
    return null;
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
   */
  public List<String> getInstallationOrderForAllPackages() throws CycleException {
    // List<String> seq = new ArrayList<String>();// topological order of all packages
    // int num = graph.order();// num of vertices
    // Set<String> unvisited = graph.getAllVertices();
    return null;
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
   */
  public String getPackageWithMaxDependencies() throws CycleException {
    return "";
  }

  public static void main(String[] args) {
    System.out.println("PackageManager.main()");
  }

  public void _pinf(Package p) {
    System.out.println(p.getName());
    System.out.print("Dependencies: ");
    for (int i = 0; i < p.getDependencies().length; i++) {
      System.out.print(p.getDependencies()[i] + " ");
    }
    System.out.println();
  }

  private boolean hasPredecessor(String vertex) {
    for (Package p : allPackages) {// traverse the HashSet for the package with name of vertex
      if (p.getName().equals(vertex) && p.getDependencies().length > 0) {
        return true;
      }
    }
    return false;
  }

  private List<String> predecessorsOf(String vertex) {
    for (Package p : allPackages) {// traverse the HashSet for the package with name of vertex
      if (p.getName().equals(vertex) && p.getDependencies().length > 0) {
        return Arrays.asList(p.getDependencies());
      }
    }
    return null;
  }

  private boolean hasSuccessor(String vertex) {
    // System.out.println(graph.getAdjacentVerticesOf(vertex));
    return graph.getAdjacentVerticesOf(vertex).size() > 0;
  }

  private List<String> successorsOf(String vertex) {
    return graph.getAdjacentVerticesOf(vertex);
  }

  private boolean containsCycle() {

    return false;
  }

  // public Graph getGraph() {
  // return this.graph;
  // }
}
