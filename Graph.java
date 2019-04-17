import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Filename: Graph.java Project: p4 Authors: Yingjie Shen, Dongxia Wu
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {
  private int size;
  private int order;

  private ArrayList<ArrayList<String>> adjList;


  /**
   * Default no-argument constructor
   */
  public Graph() {
    this.size = 0;
    this.order = 0;
    this.adjList = new ArrayList<>();
  }

  /**
   * Add new vertex to the graph.
   *
   * If vertex is null or already exists, method ends without adding a vertex or throwing an
   * exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   */
  public void addVertex(String vertex) {
    System.out.println("add " + vertex);
    if (vertex == null) {// check if vertex is null or exist
      System.out.println("null vertex");
      return;
    }
    if (containsV(vertex)) {// check if vertex exists
      System.out.println(vertex + " already exist");
      return;
    }
    ArrayList<String> subList = new ArrayList<>();// the adjacent vertex ArrayList
    subList.add(vertex);// add the first vertex
    adjList.add(subList);// add the list of vertex to the adjacent list
    this.order++;
    debug();
  }

  /**
   * Remove a vertex and all associated edges from the graph.
   * 
   * If vertex is null or does not exist, method ends without removing a vertex, edges, or throwing
   * an exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   */
  public void removeVertex(String vertex) {
    System.out.println("remove " + vertex);
    if (vertex == null) {// check if vertex is null or exist
      System.out.println("null vertex");
      return;
    }
    if (!containsV(vertex)) {// check if vertex exists
      System.out.println(vertex + " not exist");
      return;
    }
    for (int i = 0; i < adjList.size(); i++) {// remove the vertices point to vertex
      for (int j = adjList.get(i).size() - 1; j > 0; j--) {
        if (adjList.get(i).get(j).equals(vertex)) {
          System.out.println("remove " + vertex + " from " + adjList.get(i).get(0));
          adjList.get(i).remove(j);
          this.size--;
        }
      }
    }
    for (int i = 0; i < adjList.size(); i++) {
      if (adjList.get(i).get(0).equals(vertex)) {// get the vertex
        this.order--;
        this.size -= (adjList.get(i).size() - 1);
        adjList.remove(i);// remove vertices the vertex point to
        debug();
        return;
      }
    }
  }

  /**
   * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and unweighted) If either
   * vertex does not exist, add the non-existing vertex to the graph and then create an edge. If the
   * edge exists in the graph, no edge is added and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. the edge is not in the graph
   */
  public void addEdge(String vertex1, String vertex2) {
    System.out.println(vertex1 + "->" + vertex2);
    // check if neither vertex is null and the edge is not in the graph
    if (vertex1 == null) {
      System.out.println("null vertex1");
      return;
    }
    if (vertex2 == null) {
      System.out.println("null vertex2");
      return;
    }
    if (!containsV(vertex1)) {// add vertex1 if vertex1 not exist
      System.out.println(vertex1 + " not exist");
      addVertex(vertex1);
    }
    if (!containsV(vertex2)) {// add vertex1 if vertex2 not exist
      System.out.println(vertex2 + " not exist");
      addVertex(vertex2);
    }
    if (contiansE(vertex1, vertex2)) {
      System.out.println(vertex1 + "->" + vertex2 + " exists");
      return;
    }
    for (int i = 0; i < adjList.size(); i++) {
      if (adjList.get(i).get(0).equals(vertex1)) {// add edge to the sublist
        adjList.get(i).add(vertex2);
        this.size++;
        debug();
        return;
      }
    }
  }



  /**
   * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed and unweighted) If
   * either vertex does not exist, or if an edge from vertex1 to vertex2 does not exist, no edge is
   * removed and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge from vertex1 to vertex2 is in the graph
   */
  public void removeEdge(String vertex1, String vertex2) {
    // check if neither vertex is null, both vertices are in the graph or the edge from vertex1 to
    // vertex2 is in the graph
    System.out.println(vertex1 + "-x->" + vertex2);
    if (vertex1 == null) {
      System.out.println("null vertex1");
      return;
    }
    if (vertex2 == null) {
      System.out.println("null vertex2");
      return;
    }
    if (!containsV(vertex1)) {// add vertex1 if vertex1 not exist
      System.out.println(vertex1 + " not exist");
      return;
    }
    if (!containsV(vertex2)) {// add vertex1 if vertex2 not exist
      System.out.println(vertex2 + " not exist");
      return;
    }
    if (!contiansE(vertex1, vertex2)) {
      System.out.println(vertex1 + "->" + vertex2 + " not exists");
      return;
    }
    for (int i = 0; i < adjList.size(); i++) {
      if (adjList.get(i).get(0).equals(vertex1)) {// get vertex1
        for (int j = 1; j < adjList.get(i).size(); j++) {
          if (adjList.get(i).get(j).equals(vertex2)) {// remove edge to the sublist
            adjList.get(i).remove(j);
            this.size--;
            debug();
            return;
          }
        }
      }
    }
  }

  /**
   * Returns a Set that contains all the vertices
   * 
   */
  public Set<String> getAllVertices() {
    Set<String> allVertices = new HashSet<String>();
    for (int i = 0; i < adjList.size(); i++) {// iterate through the adjacent vertex ArrayList
      allVertices.add(adjList.get(i).get(0));// add the first element to the set
    }
    return allVertices;
  }

  /**
   * Get all the neighbor (adjacent) vertices of a vertex
   *
   */
  public List<String> getAdjacentVerticesOf(String vertex) {
    if (!containsV(vertex)) {
      System.out.println(vertex + " not exist");
      return null;
    }
    ArrayList<String> adjacentVertices = new ArrayList<>();// the list to return
    for (int i = 0; i < adjList.size(); i++) {// iterate through the adjacent vertex ArrayList
      if (adjList.get(i).get(0).equals(vertex)) {// check if vertex is in the graph
        adjacentVertices.addAll(adjList.get(i));
        adjacentVertices.remove(0);// remove the vertex in the adjacent vertex ArrayList
        return adjacentVertices;
      }
    }
    return adjacentVertices;
  }

  /**
   * Returns the number of edges in this graph.
   */
  public int size() {
    return this.size;
  }

  /**
   * Returns the number of vertices in this graph.
   */
  public int order() {
    return this.order;
  }

  /**
   * This method checks if vertex is in the graph
   */
  private boolean containsV(String vertex) {
    for (int i = 0; i < this.adjList.size(); i++) {// get the vertex
      if (adjList.get(i).get(0).equals(vertex)) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method checks if there exists an edge from vertex1 to vertex2
   */
  private boolean contiansE(String vertex1, String vertex2) {
    for (int i = 0; i < adjList.size(); i++) {
      if (adjList.get(i).get(0).equals(vertex1)) {// find vertex1
        for (int j = 1; j < adjList.get(i).size(); j++) {
          if (adjList.get(i).get(j).equals(vertex2)) {// find vertex2
            return true;
          }
        }
      }
    }
    return false;
  }

  private void debug() {
    System.out.println("========graphView========");
    System.out.println("Size: " + this.size);
    System.out.println("Order: " + this.order);
    for (int i = 0; i < adjList.size(); i++) {
      System.out.print(adjList.get(i).get(0) + "->");
      for (int j = 1; j < adjList.get(i).size(); j++) {
        System.out.print(adjList.get(i).get(j) + ", ");
      }
      System.out.println();
    }
    System.out.println();
  }
}