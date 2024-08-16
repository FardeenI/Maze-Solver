import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args) throws IOException
    {
        File mazeFile = new File("maze.dat"); // Create a file object with pathname maze.dat
        Scanner scan = new Scanner(mazeFile); // Create a scanner which reads from the maze file
        int rows = scan.nextInt(); // Read the first integer in the maze file, the number of rows
        int cols = scan.nextInt(); // Read the second integer in the maze file, the number of columns

        String pass = scan.nextLine(); // Passes over the first line of the file, moves the scanner to the first line of the maze


        char[][] maze = new char[rows][cols];
        int x = 0, y  = 0;
        for (int i = 0; i < rows; i++)
        {
            String mazeLine = scan.nextLine();
            for (int j = 0; j < cols; j++)
            {
                maze[i][j] = mazeLine.charAt(j); // Populates the maze
                if (mazeLine.charAt(j) == '+')
                {
                    x = i;
                    y = j;
                }
            }
        }


        maze[x][y] = ' ';


        if (solve(maze, x, y)) {
            System.out.println("Maze solved");
        }
        else {
            System.out.println("No exit");
        }


        for (char[] line : maze)
        {
            for (char space : line)
            {
                System.out.print(space); // Print maze
            }
            System.out.println();
        }


    }


    public static boolean isValid(char[][] maze, int x, int y)
    {
        if (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length) // IN ARRAYS, ROWS IS X, and COLUMNS IS Y, CHECK IF WE ARE WITHIN THE BOUNDS
        {
            return maze[x][y] == ' ' || maze[x][y] == '-'; // IF the point is WITHIN the maze, return whether the point is at a movable space
        }


        // If the above condition does NOT return true, we are either within the maze and at a wall, or NOT within the maze
        // Therefore, we are not on the path to the exit, return false
        // Consider if we are at the start or at the exit??
        return false;
    }
    public static boolean solve(char[][] maze, int x, int y)
    {
        boolean solved = false; // Assume the maze is unsolved


        if (isValid(maze, x, y)) // If the spot is valid
        {
            if (maze[x][y] == '-')
            {
                solved = true; // If we are AT the exit, the maze is solved
            }


            maze[x][y] = '.'; // If we have not reached the exit, assume that we are on the path to, and will back-track to the exit


            if (!solved) // If we haven't solved the maze yet
            {
                solved = solve(maze, x-1, y); // Check the value to the North
            }


            if (!solved) // If we haven't solved the maze yet
            {
                solved = solve(maze, x+1, y); // Check the value to the South
            }


            if (!solved) // If we haven't solved the maze yet
            {
                solved = solve(maze, x, y+1); // Check the value to the East
            }


            if (!solved) // If we haven't solved the maze yet
            {
                solved = solve(maze, x, y-1); // Check the value to the West
            }
        }


        if (solved) // After traversing the maze, if the maze is solved
        {
            maze[x][y] = '+'; // Flag that you were on the path to the exit
        }


        return solved; // Return whether we have reached the exit
    }


}
