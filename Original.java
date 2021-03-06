import java.util.*;
import java.io.*;
public class WordSearch{
    private char[][]data;
    private int seed;
    private Random randgen;
    private ArrayList<String> wordsToAdd;
    private ArrayList<String> wordsAdded;
    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows,int cols, String filename){
      data = new char[rows][cols];
      for (int i =0;i<data.length;i++){
        for (int a =0;a<data[i].length;a++){
          data[i][a]='_';
        }
      }
    }

    /**Set all values in the WordSearch to underscores'_'*/
    public void clear(){
      for (int i =0;i<data.length;i++){
        for (int a =0;a<data[i].length;a++){
          data[i][a]='_';
        }}
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
      String puzzle="";
      for (int a =0;a<data.length;a++){
        for (int b =0;b<data[a].length;b++){
          puzzle+=data[a][b]+" ";
        }
        puzzle+="\n";
      }
      return puzzle;
    }


    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
    public boolean addWordHorizontal(String word,int row, int col){
      if (col+word.length()>data[0].length) return false;
      boolean therey = true;
      for (int a=0;a<word.length();a++){
        if (data[row][col+a]!='_'&& data[row][col+a]!=word.charAt(a)){
          for(int b=a-1;b>-1;b-=1){
            data[row][col+b]='_';
          }
          return false;}
        else{
          data[row][col+a]=word.charAt(a);
      }
      }
    return true;
  }

   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */
    public boolean addWordVertical(String word,int row, int col){
      boolean therey= true;
      if (row+word.length()>data.length) return false;
      for (int a=0;a<word.length();a++){
          if (data[row+a][col]!='_'&& data[row+a][col]!=word.charAt(a)){
            for(int b=a-1;b>-1;b-=1){
              data[row+b][col]='_';
            }
            return false;
          }
            else{
          data[row+a][col]=word.charAt(a);
        }
}
      return true;
    }
    /**Attempts to add a given word to the specified position of the WordGrid.
   *The word is added from top left to bottom right, must fit on the WordGrid,
   *and must have a corresponding letter to match any letters that it overlaps.
   *
   *@param word is any text to be added to the word grid.
   *@param row is the vertical locaiton of where you want the word to start.
   *@param col is the horizontal location of where you want the word to start.
   *@return true when the word is added successfully. When the word doesn't fit,
   *or there are overlapping letters that do not match, then false is returned.
   */
    public boolean addWordDiagonal(String word, int row, int col){
      boolean therey= true;
      if (row+word.length()>data.length||col+word.length()>data.length) return false;
      for (int a=0;a<word.length();a++){
          if (data[row+a][col+a]!='_'&& data[row+a][col+a]!=word.charAt(a)){
            for(int b=a-1;b>-1;b-=1){
              data[row+b][col+a]='_';
            }
            return false;
          }
            else{
          data[row+a][col+a]=word.charAt(a);
        }
}
      return true;
    }

}
