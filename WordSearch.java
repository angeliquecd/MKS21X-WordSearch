import java.util.*;
import java.io.*;
public class WordSearch{
    private char[][] data;
    private int seed;
    private Random randgen;
    private ArrayList<String> wordsToAdd;
    private ArrayList<String> wordsAdded;

public WordSearch(int rows,int cols, String fileName, int Randseed, boolean key) throws FileNotFoundException{
      data= new char[rows][cols];
	     clear();
       wordsToAdd= new ArrayList<String>();
       wordsAdded= new ArrayList<String>();
      if (seed<0){
      	Random seedgen= new Random();
      	int seed= Math.abs(seedgen.nextInt()%1000);}
      randgen= new Random(seed);
       File f = new File(fileName);//can combine
       Scanner in = new Scanner(f);//into one line
      while (in.hasNext()){
        String word= in.next();
        wordsToAdd.add(word);
      }
	addAllWords();
if(!key){
  fillinletters();
}
}
public void clear(){
	for (int a = 0;a<data.length;a++){
	for (int b = 0; b<data[0].length;b++){
	data[a][b]=' ';
	}}}

  public String toString(){
      String puzzle="";
      for (int a =0;a<data.length;a++){
        puzzle+="|";
        for (int b =0;b<data[a].length;b++){
          puzzle+=data[a][b]+" ";
        }
        puzzle+="|\n";
      }
      puzzle+= "Words: "+wordsAdded;
    return puzzle;
        }
    public boolean addWord(String word, int r, int c, int rowIncrement, int colIncrement){
      boolean therey= true;
      if(
      (r+word.length()>data.length && rowIncrement>0) || (r-word.length()<0 && rowIncrement<0)
      ||(c+word.length()>data.length && colIncrement> 0) || (c-word.length()<0 && colIncrement<0))
      return false;
      for (int a=0;a<word.length();a++){
          if (data[r+a*rowIncrement][c+a*colIncrement]!=' '&&
          data[r+a*rowIncrement][c+a*colIncrement]!=word.charAt(a)){
            for(int b=a-1;b>-1;b-=1){
              data[r+b*rowIncrement][c+a*colIncrement]=' ';
            }
            return false;
          }
            else{
          data[r+a*rowIncrement][c+a*colIncrement]=word.charAt(a);
        }
}
      return true;
    }

      public void addAllWords(){
          boolean added= false;
          int tries = 0;
          int index = Math.abs(randgen.nextInt()% wordsToAdd.size());
          String randwordy= wordsToAdd.get(index);
          int rowIncrement= 0;
          int colIncrement=0;
          while ((rowIncrement==0 && colIncrement==0)){
          rowIncrement= randgen.nextInt()%2;
          colIncrement= randgen.nextInt()%2;}
          while (added==false || tries<1000){
            int r = Math.abs(randgen.nextInt()%data.length);
            int c = Math.abs(randgen.nextInt()% data[0].length);
          added=addWord(randwordy,r, c, rowIncrement, colIncrement);
          tries++;
          if (added){
        addWord(randwordy,r,c,rowIncrement,colIncrement);
        wordsToAdd.remove(randwordy);
        wordsAdded.add(randwordy);
      }
        }
  }
  public void fillinletters(){
    for (int a =0;a<data.length;a++){
      for (int b=0;b<data[0].length;b++){
        if (data[a][b]==' '){
		String randos = 'A'+randgen.nextInt()%26+"";
		data[a][b]=randos.charAt(0);
        }
      }
    }
  }
public static void main(String[]args){
  try{
  if (args.length<3) {
printdirections();
System.exit(1);}
  if(args.length==3){
      int rowbuilder= Integer.parseInt(args[0]);
      int colbuilder= Integer.parseInt(args[1]);
      String filename= args[2];
    int seed = -10;
    boolean key = false;
     WordSearch a = new WordSearch(rowbuilder,colbuilder,filename,seed,key);
   System.out.println(a);
     }
   if (args.length==4){
 	int rowbuilder= Integer.parseInt(args[0]);
    int colbuilder= Integer.parseInt(args[1]);
      String filename= args[2];
    boolean key = false;
    int seed = Integer.parseInt(args[3]);
    if (seed<0){
      throw new IllegalArgumentException();}
 WordSearch a = new WordSearch(rowbuilder,colbuilder,filename,seed,key);
   System.out.println(a);}
    if (args.length==5){
 	int rowbuilder= Integer.parseInt(args[0]);
      int colbuilder= Integer.parseInt(args[1]);
      String filename= args[2];
        int seed = Integer.parseInt(args[3]);
        if (seed<0){
          throw new IllegalArgumentException();}
boolean key = false;
        if(args[4].equals("key")){
          key = true;}
         WordSearch a = new WordSearch(rowbuilder,colbuilder,filename,seed,key);
   	System.out.println(a);
  }
}
/*  catch(IllegalArgumentException f){
        printdirections();
        System.exit(1);}
catch (NullPointerException c){
  printdirections();
  System.exit(1);
}*/
catch(NumberFormatException a){
        System.out.println("Number format exception.");
      printdirections();
      System.exit(1);}
catch (FileNotFoundException b){
          System.out.println("File not found");
        printdirections();
        System.exit(1);}
}
public static void printdirections(){
System.out.println(" You've encountered an error! \n To start your program, input at least a number of rows, \n a number of columns and a file name in that order. \n On top of that you may input a seed (must be positive) \n and whether or not you want an answer key \n (If so mark: key), also in that order.");
}

}
