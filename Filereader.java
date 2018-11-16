import java.util.*;
import java.io.*;
public class Filereader{
  public static void main (String[] args){
    try{
    String fileName= args[0];
    File f = new File(fileName);//can combine
    Scanner in = new Scanner(f);//into one line
   while (in.hasNext()){
     String word= in.next();
     System.out.println(word);
   }}
   catch (FileNotFoundException e){
     System.exit(1);
   }
  }
}
