//Kiran Vuksanaj helped me with this lab, with conceptual stuff like the exceptions and random, but I didn't look at her code
import java.util.*;
import java.io.*;
public class WordSearch{
  private char[][] data;
   //the random seed used to produce this WordSearch
     private int seed;
    //a random Object to unify your random calls
    private Random randgen;
    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String> wordsToAdd;
    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String> wordsAdded;
//constructor takes up to five parameters
public WordSearch(int rows,int cols, String fileName, int Randseed, boolean key) throws FileNotFoundException{//constructs entire puzzle
      data= new char[rows][cols];
	     clear();
       wordsToAdd= new ArrayList<String>();
       wordsAdded= new ArrayList<String>();
      if (Randseed<0){
      	Random seedgen= new Random();
      	seed= Math.abs(seedgen.nextInt()%10001);}
      else seed= Randseed;
      randgen= new Random(seed);
       File f = new File(fileName);//can combine
       Scanner in = new Scanner(f);//into one line
      while (in.hasNext()){
        String word= in.next().toUpperCase();
        wordsToAdd.add(word);
      }
	addAllWords();
if(!key){
  fillinletters();
}
}
public void clear(){//fills in array with white space
	for (int a = 0;a<data.length;a++){
	for (int b = 0; b<data[0].length;b++){
	data[a][b]=' ';
	}}}
/**Prints out the puzzle.
@return is 2D array of uppercase chars bounded by "|" and a list of hidden words and the seed at the bottom*/
  public String toString(){//prints out the puzzle
      String puzzle="";
      for (int a =0;a<data.length;a++){
        puzzle+="|";
        for (int b =0;b<data[a].length;b++){
          puzzle+=data[a][b]+" ";
        }
        puzzle+="|\n";
      }
      puzzle+= "Words: "+wordsAdded+"\n"+"Seed: "+this.seed;
    return puzzle;
        }
        /**Attempts to add a given word to the specified position of the WordGrid.
  *The word is added in the direction rowIncrement,colIncrement
  *Words must have a corresponding letter to match any letters that it overlaps.
  *
  *@param word is any text to be added to the word grid.
  *@param row is the vertical locaiton of where you want the word to start.
  *@param col is the horizontal location of where you want the word to start.
  *@param rowIncrement is -1,0, or 1 and represents the displacement of each letter in the row direction
  *@param colIncrement is -1,0, or 1 and represents the displacement of each letter in the col direction
  *@return true when: the word is added successfully.
  *        false when: the word doesn't fit, OR  rowchange and colchange are both 0,
  *        OR there are overlapping letters that do not match
  */
    public boolean addWord(String word, int r, int c, int rowIncrement, int colIncrement){//fits inside addallwords and places word where specified
      boolean therey= true;
      if(
      (r+word.length()>data.length && rowIncrement>0) || (r-word.length()<0 && rowIncrement<0)
      ||(c+word.length()>data.length && colIncrement> 0) || (c-word.length()<0 && colIncrement<0))
      //too long
      return false;
      else{
        if (doesitfit(word,r,c, rowIncrement, colIncrement)){//collides with other letters
      for (int a=0;a<word.length();a++){
          data[r+a*rowIncrement][c+a*colIncrement]=word.charAt(a);
        }
      return true;
    }
  else return false;
}
}
private boolean doesitfit(String word, int r, int c, int rowIncrement, int colIncrement){//checks for colliding words
  for (int a = 0;a<word.length();a++){
    char space= data[r+a*rowIncrement][c+a*colIncrement];
    if (!(word.charAt(a)==space|| space==' ')){
      return false;
  }}
  return true;
}
//For debugging purposes
/*public void printarray(ArrayList<String> ary){
  String value= "";
  for (int a=0;a<ary.size();a++){
      value+=ary.get(a)+",";
    }
  System.out.println(value+" end.");
}*/
public void addAllWords(){
  int limit= wordsToAdd.size();
      for (int a = 0; a<limit;a++){//how to make sure it tries each word
        //printarray(wordsToAdd); debugging
        boolean added= false;
        int tries = 0;
      int index = Math.abs(randgen.nextInt()% wordsToAdd.size());
      String randwordy= wordsToAdd.get(index);
    //  System.out.println(randwordy);
      int rowIncrement= 0;
      int colIncrement=0;
      while ((rowIncrement==0 && colIncrement==0)){
      rowIncrement= randgen.nextInt()%2;
      colIncrement= randgen.nextInt()%2;}//assigns word direction
      while (!added && tries<10000){
            int r = Math.abs(randgen.nextInt()%data.length);
            int c = Math.abs(randgen.nextInt()% data[0].length);
          added=addWord(randwordy,r, c, rowIncrement, colIncrement);
          tries++;
          if (added){
        addWord(randwordy,r,c,rowIncrement,colIncrement);
        wordsToAdd.remove(randwordy);
        wordsAdded.add(randwordy);}
      //  printarray(wordsToAdd);
        //printarray(wordsAdded);
        }
      }
  }
  public void fillinletters(){//fills in random letters into the puzzle
    for (int a =0;a<data.length;a++){
      for (int b=0;b<data[0].length;b++){
        if (data[a][b]==' '){
		char randos = (char)('A'+Math.abs(randgen.nextInt()%26));
		data[a][b]=randos;
        }
      }
    }
  }
public static void main(String[]args){//runs program from terminal
  try{
  if (args.length<3) {
printdirections("Need more information.");
System.exit(1);}
  if(args.length==3){//row, col, filename give
      int rowbuilder= Integer.parseInt(args[0]);
      int colbuilder= Integer.parseInt(args[1]);
      if (rowbuilder<=0||colbuilder<=0) throw new IllegalArgumentException();
      String filename= args[2];
    int seed = -10;
    boolean key = false;
     WordSearch a = new WordSearch(rowbuilder,colbuilder,filename,seed,key);
   System.out.println(a);
     }
   if (args.length==4){//row, col, filename, seed given
 	int rowbuilder= Integer.parseInt(args[0]);
    int colbuilder= Integer.parseInt(args[1]);
    if (rowbuilder<=0||colbuilder<=0) throw new IllegalArgumentException();
      String filename= args[2];
    boolean key = false;
    int seed = Integer.parseInt(args[3]);
    if (seed<0 || seed>10000){
      throw new IllegalArgumentException();}
 WordSearch a = new WordSearch(rowbuilder,colbuilder,filename,seed,key);
   System.out.println(a);}
    if (args.length>=5){//row, col, filename, seed and key given
 	int rowbuilder= Integer.parseInt(args[0]);
      int colbuilder= Integer.parseInt(args[1]);
    if (rowbuilder<=0||colbuilder<=0) throw new IllegalArgumentException();
      String filename= args[2];
        int seed = Integer.parseInt(args[3]);
        if (seed<0|| seed>10000){
          throw new IllegalArgumentException();}
boolean key = false;
        if(args[4].equals("key")){
          key = true;}
         WordSearch a = new WordSearch(rowbuilder,colbuilder,filename,seed,key);
   	System.out.println(a);
  }
}
catch(IllegalArgumentException f){
        printdirections("Illegal argument");
        System.exit(1);}
catch (NullPointerException c){
  printdirections("Null pointer");
  System.exit(1);
}
catch (FileNotFoundException b){//thrown in the constructor
        printdirections("File not found");
        System.exit(1);}
}

public static void printdirections(String type){//in case of error in the terminal
System.out.println(" You've encountered an error! ("+ type+") \n \n To start your program, input at least: \n 1.A positive number of rows, \n 2. A positive number of columns \n 3. The name of an existing file \n On top of that you may input: \n 4.A positive seed (must be less than 10,000) \n 5. Whether or not you want an answer key (If so mark: key) \n all in that order.");
}

}
