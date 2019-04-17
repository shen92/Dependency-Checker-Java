
import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import java.util.*;

/**
 * This test test the HashTable class
 * 
 * @author Yingjie Shen
 */
public class GraphTest {

  private Graph g;

  /**
   * Constructor of the HashTableTest Class
   */
  public GraphTest() {
    this.g = createInstance();
  }

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  public void setUp() throws Exception {
    // The setup must initialize this class's instances
    // and the super class instances.
    // Because of the inheritance between the interfaces and classes,
    // we can do this by calling createInstance() and casting to the desired type
    // and assigning that same object reference to the super-class fields.
    @SuppressWarnings("unused")
    GraphTest test = new GraphTest();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  public void tearDown() throws Exception {
    // The setup must initialize this class's instances
    // and the super class instances.
    // Because of the inheritance between the interfaces and classes,
    // we can do this by calling createInstance() and casting to the desired type
    // and assigning that same object reference to the super-class fields.
    this.g = null;
  }

  /**
   * This method creates an instance of hash table
   * 
   * @return HashTable instance
   */
  protected Graph createInstance() {
    return new Graph();
  }


  /**
   * This method checks if should not allow null vertex
   */
  @Test
  public void test000_should_not_allow_null_vertex() {
    try {
      g.addVertex(null);
      if (g.order() == 1) {
        fail("add vertex should not allow null vertex.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 000: " + e.getMessage());
    }
  }

  /**
   * This method checks if add vertex order increase
   */
  @Test
  public void test001_add_vertex_order_increase() {
    try {
      g.addVertex("a");
      g.addVertex("b");
      g.addVertex("c");
      if (g.order() != 3) {
        fail("add vertex order not increase.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 001: " + e.getMessage());
    }
  }

  /**
   * This method checks if should not allow duplicate vertex
   */
  @Test
  public void test002_should_not_allow_duplicate_vertex() {
    try {
      g.addVertex("a");
      g.addVertex("a");
      if (g.order() == 2) {
        fail("add veretx should not allow duplicate vertex.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 002: " + e.getMessage());
    }
  }

  /**
   * This method checks if add edge vertex not exist then add vertex
   */
  @Test
  public void test003_add_edge_vertex_not_exist_then_add_vertex() {
    try {
      g.addVertex("a");
      g.addEdge("a", "b");
      if (g.order() != 2 && g.size() != 1) {
        fail("add edge when vertex not exist then not add vertex.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 003: " + e.getMessage());
    }
  }

  /**
   * This method checks if do not allow duplicate edge
   */
  @Test
  public void test004_do_not_allow_duplicate_edge() {
    try {
      g.addVertex("a");
      g.addVertex("b");
      g.addEdge("a", "b");
      g.addEdge("a", "b");
      if (g.size() == 2) {
        fail("do not allow duplicate edge.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }

  /**
   * This method checks if add valid edge size increase
   */
  @Test
  public void test005_add_valid_edge_size_increase() {
    try {
      g.addVertex("a");
      g.addVertex("b");
      g.addEdge("a", "b");
      if (g.size() != 1) {
        fail("add valid edge size not increase.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 005: " + e.getMessage());
    }
  }

  /**
   * This method checks if should not remove not exist vertex
   */
  @Test
  public void test006_should_not_remove_not_exist_vertex() {
    try {
      g.addVertex("a");
      g.addVertex("b");
      g.addEdge("a", "b");
      g.removeEdge("a", "c");
      if (g.size() == 0) {
        fail("should not remove not exist vertex.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 006: " + e.getMessage());
    }
  }

  /**
   * This method checks if remove vertex has predecessor
   */
  @Test
  public void test007_remove_vertex_has_predecessor() {
    try {
      g.addVertex("a");
      g.addVertex("b");
      g.addVertex("c");
      g.addEdge("a", "b");
      g.addEdge("c", "b");
      g.removeVertex("b");
      if (g.order() != 2 && g.size() != 0) {
        fail("remove vertex has predecessor.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 007: " + e.getMessage());
    }
  }

  /**
   * This method checks if remove vertex has no predecessor
   */
  @Test
  public void test008_remove_vertex_has_no_predecessor() {
    try {
      g.addVertex("a");
      g.addVertex("b");
      g.addVertex("c");
      g.removeVertex("a");
      if (g.order() != 2 && g.size() != 0) {
        fail("remove vertex has no predecessor.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 008: " + e.getMessage());
    }
  }

  /**
   * This method checks if remove edge not exist
   */
  @Test
  public void test009_remove_edge_not_exist() {
    try {
      g.addVertex("a");
      g.addVertex("b");
      g.addVertex("c");
      g.addEdge("a", "b");
      g.addEdge("c", "b");
      g.removeEdge("a", "c");
      if (g.size() != 2) {
        fail("remove edge not exist.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 009: " + e.getMessage());
    }
  }

  /**
   * This method checks if get all vertices
   */
  @Test
  public void test010_get_all_vertices() {
    try {
      g.addVertex("a");
      g.addVertex("b");
      g.addVertex("c");
      Set<String> allVertices = g.getAllVertices();
      if (allVertices.size() != 3) {
        fail("not get all vertices.");
      }
      if (!allVertices.contains("a")) {
        fail("return set not contains \"a\"");
      }
      if (!allVertices.contains("b")) {
        fail("return set not contains \"b\"");
      }
      if (!allVertices.contains("c")) {
        fail("return set not contains \"c\"");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 010: " + e.getMessage());
    }
  }

  /**
   * This method checks if get adjacent vertices list
   */
  @Test
  public void test011_get_adjacent_vertices_list() {
    try {
      g.addVertex("a");
      g.addVertex("b");
      g.addVertex("c");
      g.addEdge("a", "b");
      g.addEdge("a", "c");
      List<String> adjList = new ArrayList<>();
      adjList = g.getAdjacentVerticesOf("a");
      if (adjList.size() != 2) {
        fail("not get all adjacent vertices.");
      }
      if (!adjList.contains("b")) {
        fail("return list not contains \"b\"");
      }
      if (!adjList.contains("c")) {
        fail("return list not contains \"c\"");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 011: " + e.getMessage());
    }
  }
}
