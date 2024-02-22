import java.io.*;
import java.util.Scanner;

public class HW3 implements HW3_1Interface{
    MyStack<Position> path = new MyStack<Position>();
    private Scanner readCodes;
    public static int[][] grid;
    private int rows;
    private int cols;
    private String result;

    int x, y;
    int startx, starty;

    @Override
    public void read_file(String filepath) {
        try ( BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            // Read the file and count the number of rows and columns
            String line = reader.readLine();
            rows = 0;
            cols = line.split(" ").length;
            while (line != null) {
                rows++;
                line = reader.readLine();
            }
            System.out.println("The number of rows of the read data: " + rows);
            System.out.println("The number of columns of the read data: " + cols);
            //after all rows and cols read assign grid values

            // Initialize the grid with 0s
            grid = new int[rows + 2][cols + 2];
            for (int i = 0; i < rows + 2; i++) {
                for (int j = 0; j < cols + 2; j++) {
                    grid[i][j] = 0;
                }
            }
            // Reset the reader's position to the beginning of the file
            reader.close();
            BufferedReader reader1 = new BufferedReader(new FileReader(filepath));
            // Read the file and assign values to the grid
            line = reader1.readLine();
            int row = 1; // Start from row 1, as the top border is already initialized with 0s
            while (line != null) {
                String[] parts = line.split(" ");
                for (int i = 0; i < rows; i++) {
                    // Add 1 to the column index to skip the left border
                    grid[row][i + 1] = Integer.parseInt(parts[i]);
                }
                row++;
                line = reader1.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String find_path() {
        // Declare an array of positions to hold the possible movement offsets
        // (EAST, SOUTH, WEST, NORTH, SW, NW, SE, NE)

        Position[] offset = new Position[8];
        for (int i = 0; i <= 7; i++) {
            offset[i] = new Position();
        }
        // EAST
        offset[0].row = 0;
        offset[0].col = 1;
        // SOUTH
        offset[1].row = 1;
        offset[1].col = 0;
        // WEST
        offset[2].row = 0;
        offset[2].col = -1;
        // NORTH
        offset[3].row = -1;
        offset[3].col = 0;
        // SW
        offset[4].row = 1;
        offset[4].col = -1;
        // NW
        offset[5].row = -1;
        offset[5].col = -1;
        // SE
        offset[6].row = 1;
        offset[6].col = 1;
        // NE
        offset[7].row = -1;
        offset[7].col = 1;

        // Declare a position variable to hold the current position
        Position here;
        here = new Position();
        // Declare a position variable to hold the next position
        Position next;
        next = new Position();
        // Find the starting position in the grid
        for (x = 1; x <= 8; x++) {
            for (y = 1; y <= 8; y++) {
                if (grid[x][y] == 32) {
                    here.row = x;
                    here.col = y;
                    // Save the starting position
                    startx = x;
                    starty = y;
                    grid[x][y] = 0; // mark the starting position as visited
                    break;
                }
            }
        }
        try {
            // Initialize variables for searching the path
            int option = 0; // next move
            int LastOption = 7;
            int temp = 31;
            // search for a path
            while (temp != 0) {// not exit
                // find a neighbor to move to
                int r = 0;
                int c = 0;
                while (option <= LastOption) {
                    r = here.row + offset[option].row;
                    c = here.col + offset[option].col;
                    if (grid[r][c] == temp) {
                        break;
                    }
                    option++; // next option
                }

                // was a neighbor found?
                if (option <= LastOption) {
                    // move to grid[r][c]
                    here = new Position(r, c);
                    path.push(here); //Add "here" Position into the Stack
                    here.row = r;
                    here.col = c;
                    grid[r][c] = 0; // set to 0 to prevent revisit
                    option = 0;
                    temp--;
                } else {// no neighbor to move to, back up
                    if (path.IsEmpty()) {
                        return "No path found";
                    }
                    next = path.pop();
                    if (next.row == here.row) {
                        option = 2 + next.col - here.col;
                    } else {
                        option = 3 + next.row - here.row;
                    }
                    here = next;
                    ++temp;
                }
            }
        } catch (Exception e) {
            // If an exception is caught, move to the next unvisited position in the grid
            for (int a = (startx + 1); a <= 8; a++) {
                for (int b = starty; b <= 8; b++) {
                    if (grid[a][b] == 32) {
                        here.row = a;
                        here.col = b;
                        grid[x][y] = 0;
                        break;
                    }
                }
            }
        }
        // Create a string builder to store the path
        StringBuilder sb = new StringBuilder();
        sb.append("The path is:\n");
        // Pop the positions from the stack and append them to the string builder
        while (!path.IsEmpty()) {
            here = path.pop();
            sb.append((here.row) + ":" + (here.col) + "->");
        }
        // Append the starting position to the string builder
        sb.append(startx);
        sb.append(":" + starty);
        sb.append("\n");
        result = sb.toString();
        return result;
    }

    @Override
    public void print_path(String mypath) {
        // Print the path to the console
        System.out.println(mypath);

    }

    @Override
    public void print_path_to_file(String filepath) {
        // Try to open the file specified by the filepath parameter
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
            // Write the result string to the file
            bw.write(result);
        } // If an exception occurs, print the stack trace
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
