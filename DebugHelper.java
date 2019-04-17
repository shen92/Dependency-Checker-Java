import java.util.Scanner;

public class DebugHelper {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Scanner sc = new Scanner(System.in);
    while (true) {
      String s = "";
      System.out.print("Method: ");
      if (sc.hasNextLine()) {
        s = sc.nextLine();
      }
      genLbl(s);
    }

  }

  public static void genLbl(String s) {
    System.out.println("System.out.println(\"∨∨∨∨∨∨∨∨∨∨" + s + "∨∨∨∨∨∨∨∨∨∨\");");
    System.out.println("System.out.println(\"∧∧∧∧∧∧∧∧∧∧" + s + " END∧∧∧∧∧∧\");");
    System.out.println();
  }
}
