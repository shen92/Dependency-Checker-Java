import java.util.Scanner;

public class TestGenerator {

  public static void main(String[] args) {
    System.out.println("=========Test Generator=========");
    Scanner sc = new Scanner(System.in);
    System.out.print("Start Num: ");
    int start = 0;
    if (sc.hasNextInt()) {
      start = sc.nextInt();
    }
    System.out.print("End Num: ");
    int end = 0;
    if (sc.hasNextInt()) {
      end = sc.nextInt();
    }
    String blank = sc.nextLine();
    blank.length();
    for (int i = start; i <= end; i++) {
      if (i < 10) {
        System.out.println("Test_00" + i + "_<Name> (input seprated by space)");
      } else {
        System.out.println("Test_0" + i + "_<Name> (input seprated by space)");
      }
      System.out.print(">");
      String name = "";
      if (sc.hasNextLine()) {
        name = sc.nextLine();
      }
      System.out.println();
      comment(name);
      text(name, i);
    }
    sc.close();
  }

  private static void comment(String name) {
    System.out.println("/**");
    System.out.println("* This method checks if " + name);
    System.out.println("*/");
  }

  private static String generateSignature(String name) {
    String[] test = name.split(" ");
    String signature = "";
    for (int j = 0; j < test.length; j++) {
      signature += "_" + test[j];
    }
    return signature;
  }

  private static void text(String name, int i) {
    System.out.println("@Test");
    if (i < 10) {
      System.out.println("public void test00" + i + generateSignature(name) + "() {");
    } else {
      System.out.println("public void test0" + i + generateSignature(name) + "() {");
    }
    System.out.println("try{");
    System.out.println("//TODO");
    System.out.println("//if");
    System.out.println("fail(\"" + name + ".\");");
    System.out.println("} catch (Exception e) {");
    System.out.println("e.printStackTrace();");
    if (i < 10) {
      System.out.println("fail(\"Unexpected exception 00" + i + ": \" + e.getMessage());");
    } else {
      System.out.println("fail(\"Unexpected exception 0" + i + ": \" + e.getMessage());");
    }
    System.out.println("}");
    System.out.println("}");
    System.out.println();
  }
}
