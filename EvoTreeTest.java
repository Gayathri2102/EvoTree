import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class EvoTreeTest {
  private static String testInput0 = "end";
  private static String [] testOutput0 = {};

  private static String testInput1 = "Human evolved from Ameoba\n" +
                                     "end";
  private static String [] testOutput1 = { "Ameoba",
                                           "|-Human" };

  private static String testInput2 = "Homo Sapien evolved from Homo Ergastur\n"+
                                     "end";
  private static String [] testOutput2 = { "Homo Ergastur",
                                           "|-Homo Sapien" };

  private static String testInput3 = "Human evolved from Ergastur\n" +
                                     "Ergastur evolved from Ameoba\n" +
                                     "end";
  private static String [] testOutput3 = { "Ameoba",
                                           "|-Ergastur",
                                           "| |-Human" };

  private static String testInput4 = "Ergastur evolved from Ameoba\n" +
                                     "Human evolved from Ergastur\n" +
                                     "end";
  private static String [] testOutput4 = { "Ameoba",
                                           "|-Ergastur",
                                           "| |-Human" };

  private static String testInput5 = "Human evolved from Ameoba\n" +
                                     "Koala evolved from Ameoba\n" +
                                     "end";
  private static String [] testOutput5 = { "Ameoba",
                                           "|-Human",
                                           "|-Koala" };

  private static String testInput6 = "Koala evolved from Ameoba\n" +
                                     "Human evolved from Ameoba\n" +
                                     "end";
  private static String [] testOutput6 = { "Ameoba",
                                           "|-Human",
                                           "|-Koala" };

  private static String testInput7 = "Koala evolved from Ameoba\n" +
                                     "Ergastur evolved from Ameoba\n" +
                                     "Human evolved from Ergastur\n" +
                                     "end";
  private static String [] testOutput7 = { "Ameoba",
                                           "|-Ergastur",
                                           "| |-Human",
                                           "|-Koala" };

  private static String testInput8 ="leeches evolved from flatworms\n" +
                                    "cecil evolved from snakes\n" +
                                    "monkeys evolved from one-celled forms\n" +
                                    "lochness monster evolved from snakes\n" +
                                    "snakes evolved from one-celled forms\n" +
                                    "flatworms evolved from one-celled forms\n" +
                                    "fung evolved from sponges\n" +
                                    "sponges evolved from one-celled forms\n" +
                                    "end\n";
                                    
  private static String [] testOutput8 = { "one-celled forms",
                                           "|-flatworms",
                                           "| |-leeches",
                                           "|-monkeys",
                                           "|-snakes",
                                           "| |-cecil",
                                           "| |-lochness monster",
                                           "|-sponges",
                                           "| |-fung" };

  private static Scanner mkTest( String input ) {
    return new Scanner( input );
  }
     
  private static ArrayList<String> mkOutput( String [] output ) {
    ArrayList<String> al = new ArrayList<String>();

    for( String s : output ) {
      al.add( s );
    }
    return al;
  }
     
  private static boolean doTest( String input, String [] output ) {
    Tester t = new EvoTree();
    ArrayList<String> al = t.compute( mkTest( input ) );
    System.out.println( "Input: " );
    System.out.println( input );
    System.out.println( "Generated output" );
    for( String s : al ) {
      System.out.println( s );
    }
    System.out.println( "Expected output" );
    for( String s : output ) {
    	  System.out.println( s );
    }
    System.out.println( "---------------------------------------------------" );
    return al != null && al.equals( mkOutput( output ) );
  }

  @Test
  void testEmpty() {
    assertTrue( doTest( testInput0, testOutput0 ), "Empty test" );
  }

  @Test
  void test1() {
    assertTrue( doTest( testInput1, testOutput1 ), "One level test" );
  }

  @Test
  void test2() {
    assertTrue( doTest( testInput2, testOutput2 ), "Multi-word species test" );
  }

  @Test
  void test3() {
    assertTrue( doTest( testInput3, testOutput3 ), "Multilevel tree test" );
  }

  @Test
  void test4() {
    assertTrue( doTest( testInput4, testOutput4 ), "Multilevel tree test" );
  }

  @Test
  void test5() {
    assertTrue( doTest( testInput5, testOutput5 ), "Ordering test (order ok)" );
  }

  @Test
  void test6() {
    assertTrue( doTest( testInput6, testOutput6 ), "Ordering test (reorder)" );
  }

  @Test
  void test7() {
    assertTrue( doTest( testInput7, testOutput7 ), "Indentation test" );
  }

  @Test
  void test8() {
    assertTrue( doTest( testInput8, testOutput8 ), "Assignment example" );
  }

}
