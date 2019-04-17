
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
public class PackageManagerTest {

  private PackageManager m;

  /**
   * Constructor of the HashTableTest Class
   */
  public PackageManagerTest() {
    this.m = createInstance();
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
    this.m = null;
  }

  /**
   * This method creates an instance of hash table
   * 
   * @return HashTable instance
   */
  protected PackageManager createInstance() {
    return new PackageManager();
  }
}
