import java.util.*;
import java.io.*;
public class WordSearch{
    private char[][] data;
    private int seed;
    private Random randgen;
    private ArrayList<String> wordsToAdd;
    private ArrayList<String> wordsAdded;
    public WordSearch(int rows,int cols, String fileName){
      data= new char[rows][cols];
      randgen= new Random();
      try{
        File f = new File(fileName);//can combine
        Scanner in = new Scanner(f);//into one line
      while (in.hasNext()){
        String word= in.next();
        wordsToAdd.add(word);
      }
    }catch(FileNotFoundException e){
  System.out.println("File not found: " + fileName);
  //e.printSta
  System.exit(1);
  addAllWords();
}
}
  public WordSearch (int rows, int cols, String fileName, int randSeed){
    data= new char[rows][cols];
    seed=randSeed;
      try{
        File f = new File(fileName);//can combine
        Scanner in = new Scanner(f);//into one line
      while (in.hasNext()){
        String word= in.next();
        wordsToAdd.add(word);
      }
  }catch(FileNotFoundException e){
  System.out.println("File not found: " + fileName);
  //e.printSta
  System.exit(1);
  }
    addAllWords();
    }

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
    private boolean addWord(String word, int r, int c, int rowIncrement, int colIncrement){
      boolean therey= true;
      if(
      (r+word.length()>data.length && rowIncrement>0) || (r-word.length()<0 && rowIncrement<0)
      ||(c+word.length()>data.length && colIncrement> 0) || (c-word.length()<0 && colIncrement<0))
      return false;
      for (int a=0;a<word.length();a++){
          if (data[r+a*rowIncrement][c+a*colIncrement]!='_'&&
          data[r+a*rowIncrement][c+a*colIncrement]!=word.charAt(a)){
            for(int b=a-1;b>-1;b-=1){
              data[r+b*rowIncrement][c+a*colIncrement]='_';
            }
            return false;
          }
            else{
          data[r+a*rowIncrement][c+a*colIncrement]=word.charAt(a);
        }
}
      return true;
    }
      /*[rowIncrement,colIncrement] examples:
          *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)
          *[ 1,0] would add downwards because (row+1), with no col change
          *[ 0,-1] would add towards the left because (col - 1), with no row change
          */

        private void addAllWords(){
          boolean added= false;
          int tries = 0;
          Random randword= new Random()% wordsToAdd.length();
          String randwordy= wordsToAdd.get(randword);
          int rowIncrement = new Random()%3 -1;
          int colIncrement = new Random() % 3-1;
          while (added==false || tries<data.length()*data[0].length()){
            int r = new Random()%data.length();
            int c = new Random()% data[0].length();
          added=addWord(randwordy,r, c, rowIncrement, colIncrement);
          tries++;
          if (added){
        this.addWord(randwordy,r,c,rowIncrement,colIncrement);
        wordsToAdd.remove(randwordy);
        wordsAdded.add(randwordy);
      }
        }
  }
}
