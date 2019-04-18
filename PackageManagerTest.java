
import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * This test test the HashTable class
 * 
 * @author Yingjie Shen
 */
public class PackageManagerTest {

  private PackageManager valid;
  private PackageManager inValidJSON;
  private PackageManager cycle;

  /**
   * Constructor of the HashTableTest Class
   * 
   * @throws ParseException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public PackageManagerTest() throws FileNotFoundException, IOException, ParseException {
    this.valid = createInstance();
    valid.constructGraph("valid.json");
    this.inValidJSON = createInstance();
    this.cycle = createInstance();
    cycle.constructGraph("cyclic.json");
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
    PackageManagerTest test = new PackageManagerTest();
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
    this.valid = null;
    this.inValidJSON = null;
    this.cycle = null;
  }

  /**
   * This method creates an instance of hash table
   * 
   * @return HashTable instance
   */
  protected PackageManager createInstance() {
    return new PackageManager();
  }

  /**
   * This method checks if invalid json file should throw exception
   */
  @Test
  public void test001_invalid_json_file_should_throw_exception() {
    try {
      // TODO
      // if
      fail("invalid json file should throw exception.");
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 001: " + e.getMessage());
    }
  }

  /**
   * This method checks if get all packages return correct packages
   */
  @Test
  public void test002_get_all_packages_return_correct_packages() {
    try {
      // TODO
      // if
      fail("get all packages return correct packages.");
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 002: " + e.getMessage());
    }
  }

  /**
   * This method checks if get installation order of pkg should check cycle exception
   */
  @Test
  public void test003_get_installation_order_of_pkg_should_check_cycle_exception() {
    try {
      // TODO
      // if
      fail("get installation order of pkg should check cycle exception.");
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 003: " + e.getMessage());
    }
  }

  /**
   * This method checks if get installation order of pkg should check if pkg exists
   */
  @Test
  public void test004_get_installation_order_of_pkg_should_check_if_pkg_exists() {
    try {
      // TODO
      // if
      fail("get installation order of pkg should check if pkg exists.");
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }

  /**
   * This method checks if get installation order of pkg return correct order
   */
  @Test
  public void test005_get_installation_order_of_pkg_return_correct_order() {
    try {
      // TODO
      // if
      fail("get installation order of pkg return correct order.");
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 005: " + e.getMessage());
    }
  }

  /**
   * This method checks if to install checks cycle exception
   */
  @Test
  public void test006_to_install_checks_cycle_exception() {
    try {
      // TODO
      // if
      fail("to install checks cycle exception.");
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 006: " + e.getMessage());
    }
  }

  /**
   * This method checks if to install checks package not found exception
   */
  @Test
  public void test007_to_install_checks_package_not_found_exception() {
    try {
      // TODO
      // if
      fail("to install checks package not found exception.");
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 007: " + e.getMessage());
    }
  }

  /**
   * This method checks if to install returns correct install order
   */
  @Test
  public void test008_to_install_returns_correct_install_order() {
    try {
      // TODO
      // if
      fail("to install returns correct install order.");
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 008: " + e.getMessage());
    }
  }

  /**
   * This method checks if get installation order for all packages checks cycle exception
   */
  @Test
  public void test009_get_installation_order_for_all_packages_checks_cycle_exception() {
    try {
      // TODO
      // if
      fail("get installation order for all packages checks cycle exception.");
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 009: " + e.getMessage());
    }
  }

  /**
   * This method checks if get installation order for all packages returns correct order
   */
  @Test
  public void test010_get_installation_order_for_all_packages_returns_correct_order() {
    try {
      // TODO
      // if
      fail("get installation order for all packages returns correct order.");
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 010: " + e.getMessage());
    }
  }

  /**
   * This method checks if get package with max dependencies checks cycle exception
   */
  @Test
  public void test011_get_package_with_max_dependencies_checks_cycle_exception() {
    try {
      // TODO
      // if
      fail("get package with max dependencies checks cycle exception.");
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 011: " + e.getMessage());
    }
  }

  /**
   * This method checks if get package with max dependencies returns the correct package name
   */
  @Test
  public void test012_get_package_with_max_dependencies_returns_the_correct_package_name() {
    try {
      // TODO
      // if
      fail("get package with max dependencies returns the correct package name.");
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 012: " + e.getMessage());
    }
  }
}
