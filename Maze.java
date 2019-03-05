import java.util.*;
import java.io.*;
public class Maze{

    private char[][]maze;
    private int count;
    private boolean solved;
    private boolean animate;//false by default

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!

      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:
         throw a FileNotFoundException or IllegalStateException
    */
    public static void main(String[] args) throws FileNotFoundException{
      Maze m = new Maze("Maze3.txt");
      System.out.println(m.solve());
      System.out.println(m);
    }

    public String toString() {
      String output = "";
      for (char[] x: maze) {
        for (char y: x) {
          output += y;
        }
        output += "\n";
      }
      return output;
    }

    public Maze(String filename) throws FileNotFoundException{
        //COMPLETE CONSTRUCTOR
        try {
          File data = new File(filename);
          Scanner info = new Scanner(data);
          int x = 0;
          int y = 0;
          while (info.hasNextLine()) {
            x = info.nextLine().length();
            y ++;
          }
          maze = new char[y][x];
          info = new Scanner(data);
          int a = 0;
          while (info.hasNextLine()) {
            String line = info.nextLine();
            for (int b = 0; b < x; b ++) {
              maze[a][b] = line.charAt(b);
            }
            a ++;
          }
        }
        catch (FileNotFoundException e) {
          System.out.println("Maze not found... make sure file name is correct.");
        }
        //animate = true;
    }

    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }

    public void setAnimate(boolean b){
        animate = b;
    }

    public void clearTerminal(){
        //erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");
    }


    /*Wrapper Solve Function returns the helper function
      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
      */
    public int solve(){
            //find the location of the S.
        count = 0;
        solved = false;
        int x = 0;
        int y = 0;
        for (int a = 0; a < maze.length; a ++) {
          for (int b = 0; b < maze[a].length; b ++) {
            if (maze[a][b] == 'S') {
              x = b;
              y = a;
            }
          }
        }
            //erase the S
        maze[y][x] = ' ';
            //and start solving at the location of the s.
        solve(y, x);
        if (count == 0) return -1;
        return count + 1;
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.

      Postcondition:
        The S is replaced with '@' but the 'E' is not.
        All visited spots that were not part of the solution are changed to '.'
        All visited spots that are part of the solution are changed to '@'
    */
    private void solve(int row, int col){ //you can add more parameters since this is private
        //automatic animation! You are welcome.
        if(animate){
            clearTerminal();
            System.out.println(this);
            wait(20);
        }

        //COMPLETE SOLVE
        boolean finished = false;
        if (solved);
        else if (row < 0 || row >= maze.length || col < 0 ||col >= maze[row].length);
        else if (maze[row][col] == 'E') solved = true;
        else if (maze[row][col] == '#' || maze[row][col] == '@');
        else {
          maze[row][col] = '@';
          count ++;
          solve(row, col + 1);
          solve(row, col - 1);
          solve(row + 1, col);
          solve(row - 1, col);
          if (! solved) {
            maze[row][col] = '.';
            count --;
          }
        }


    }

}
