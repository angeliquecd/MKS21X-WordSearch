public class Tester{
  public static void main(String[] args){
  WordSearch betap = new WordSearch(10,10);
  //tests toString
  System.out.println("Testing initializing of grid.");
  System.out.println(betap);
  System.out.println("\n");
  //tests if word can be put in, should not return an error
  System.out.println("adding hi horizontally: ");
  betap.addWordHorizontal("hi",0,1);
  System.out.println(betap.addWordHorizontal("hi",0,1));
  System.out.println(betap);
  System.out.println("\n");
  //tests overlapping letters
  System.out.println("adding yo horizontally (in an unacceptable place): ");
  betap.addWordHorizontal("yo",0,2);
  System.out.println(betap.addWordHorizontal("yo",0,2));
  System.out.println(betap);
  System.out.println("\n");
  //tests if word cannot be put in: should not return an error but grid shouldn't change
  System.out.println("adding antidisestablishmentarian horizontally: ");
  betap.addWordHorizontal("antidisestablismentarianism",1,1);
  System.out.println(betap.addWordHorizontal("antidisestablismentarianism",0,1));
  System.out.println(betap);
  System.out.println("\n");
  //tests clear. you will have to make it public if you want to test this part
  //System.out.println("Clearing: ")
//  betap.clear();
  //System.out.println(betap);
  //tests vertical words
  System.out.println("adding hello vertically: ");
  betap.addWordVertical("hello",1,1);
  System.out.println(betap.addWordVertical("hello",1,1));
  System.out.println(betap);
  System.out.println("\n");
  //shouldn't work because spot is taken
  System.out.println("adding hello vertically (in an unacceptable place): ");
  betap.addWordVertical("hello",0,2);
  System.out.println(betap.addWordVertical("hello",0,2));
  System.out.println(betap);
  System.out.println("\n");
  //should return false and not change the puzzle
  System.out.println("adding antidisestablishmentarian vertically: ");
  betap.addWordVertical("antidisestablismentarianism",1,1);
  System.out.println(betap.addWordVertical("antidisestablismentarianism",0,1));
  System.out.println(betap);
  System.out.println("Testing diagonal: ");
  betap.clear();
    System.out.println("adding hello diagonally: ");
  System.out.println(  betap.addWordDiagonal("hey",0,0));
  betap.addWordDiagonal("hey",0,0);
  System.out.println(betap);
  System.out.println("\n");
    System.out.println("again: ");
  System.out.println(betap.addWordDiagonal("hey",0,7));
  betap.addWordDiagonal("hey",0,7);
  System.out.println(betap);
  System.out.println("adding hey out of bounds: ");
System.out.println(betap.addWordDiagonal("hey",0,8));
  betap.addWordDiagonal("hey",0,8);
  System.out.println(betap);
    System.out.println("adding hello diagonally (in an unacceptable place): ");
    System.out.println(betap.addWordDiagonal("hey",2,2));
    betap.addWordDiagonal("hey",2,2);
System.out.println(betap);
System.out.println("adding hello horizontally (in an unacceptable place): ");
System.out.println(betap.addWordHorizontal("hey",2,2));
betap.addWordHorizontal("hey",2,2);
System.out.println(betap);
}
}
