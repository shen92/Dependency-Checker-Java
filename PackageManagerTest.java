//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: p4_project
// Files: CycleException.java, Graph.java, GraphADT.java, GraphTest.java,
// Package.java, PackageManager.java, PackageManagerTest.java,
// PackageNotFoundException.java
// Course: CS 400 Spring 2019 002
//
// Author: Yingjie Shen
// Email: shen92@wisc.edu
// Lecturer's Name: Deb Deppeler
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Dongxia Wu
// Partner Email: dwu93@wisc.edu
// Partner Lecturer's Name: Deb Deppeler
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
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
 * This test test the PackageManager class
 * 
 * @author Yingjie Shen, Dongxia Wu
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
      inValidJSON.constructGraph("invalid.json");
      fail("invalid json file should throw exception.");
    } catch (FileNotFoundException e) {
    } catch (IOException e) {
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
      ArrayList<String> expect = new ArrayList<>();
      expect.add("C");
      expect.add("D");
      expect.add("B");
      expect.add("E");
      expect.add("A");
      List<String> output = valid.getInstallationOrderForAllPackages();
      if (expect.size() != output.size()) {
        fail("get all packages should return correct packages.");
      }
      for (int i = 0; i < expect.size(); i++) {
        if (!expect.get(i).equals(output.get(i))) {
          fail("get all packages should return correct packages.");
        }
      }
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
      cycle.getInstallationOrder("B");
      fail("get installation order of pkg should check cycle exception.");
    } catch (CycleException e) {
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
      valid.getInstallationOrder("1");
      fail("get installation order of pkg should check if pkg exists.");
    } catch (PackageNotFoundException e) {
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
      List<String> expect = new ArrayList<>();
      expect.add("C");
      expect.add("D");
      expect.add("B");
      List<String> output = valid.getInstallationOrder("B");
      if (expect.size() != output.size()) {
        fail("get installation order of pkg return correct order.");
      }
      for (int i = 0; i < expect.size(); i++) {
        if (!expect.get(i).equals(output.get(i))) {
          fail("get installation order of pkg return correct order.");
        }
      }
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
      cycle.toInstall("A", "B");
    } catch (CycleException e) {
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
      valid.toInstall("1", "A");
      fail("to install should check if pkg exists.");
    } catch (PackageNotFoundException e) {
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
      List<String> expect = new ArrayList<>();
      expect.add("D");
      expect.add("B");
      List<String> output = valid.toInstall("B", "C");
      if (expect.size() != output.size()) {
        fail("to install returns correct install order.");
      }
      for (int i = 0; i < expect.size(); i++) {
        if (!expect.get(i).equals(output.get(i))) {
          fail("to install returns correct install order.");
        }
      }
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
      cycle.getInstallationOrderForAllPackages();
      fail("get installation order for all packages should check cycle exception.");
    } catch (CycleException e) {
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
      List<String> expect = new ArrayList<>();
      expect.add("C");
      expect.add("D");
      expect.add("B");
      expect.add("E");
      expect.add("A");
      List<String> output = valid.getInstallationOrderForAllPackages();
      if (expect.size() != output.size()) {
        fail("get installation order for all packages returns correct order.");
      }
      for (int i = 0; i < expect.size(); i++) {
        if (!expect.get(i).equals(output.get(i))) {
          fail("get installation order for all packages returns correct order.");
        }
      }
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
      cycle.getPackageWithMaxDependencies();
      fail("get package with max dependencies checks cycle exception.");
    } catch (CycleException e) {
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
      String expect = "A";
      String output = valid.getPackageWithMaxDependencies();
      if (!expect.equals(output)) {
        fail("get package with max dependencies returns the correct package name.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 012: " + e.getMessage());
    }
  }
}
