package goneaxolotl_;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Scanner;


/**
 
/**
 *
 * @author lawnguy
 */
public class GoneAxolotl_ 
{

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException 
    {

        Scanner input = new Scanner(System.in);
        Region region = new Region();
        FileHandling files = new FileHandling();
        Chunks chunk = new Chunks();
        
        String userInput;

        do 
        {
        System.out.println("To stop the program at any time type: Cancel");
        System.out.println("If not, enter Continue");
        System.out.println("--------------------------------");
        userInput = input.nextLine(); 

        if (!userInput.equalsIgnoreCase("Cancel")) 
        {
            System.out.println("\n------------------------------------");
            System.out.println("Would you like to calculate Region Cords?");
            System.out.println("Enter Yes for yes, Enter No for no");
            System.out.println("\n------------------------------------");
            userInput = input.nextLine();

            if (!userInput.equalsIgnoreCase("Cancel") && userInput.equalsIgnoreCase("Yes")) 
            {
                System.out.println("\n------------------------------------");
                System.out.println("Enter Your X Cord Then Z Cord");

                int userXInputs = input.nextInt();
                int useryInputs = input.nextInt();
                input.nextLine(); 

                region.setRegionCords(userXInputs, useryInputs);
                int[] strongMan = region.getRegionCords();

                System.out.println(Arrays.toString(strongMan));
                System.out.println("\n\n---------------------------------");
            }

            if (!userInput.equalsIgnoreCase("Cancel")) 
            {
                System.out.println("Please copy and paste your path to the regionFiles");
                userInput = input.nextLine();

                if (!userInput.equalsIgnoreCase("Cancel")) 
                {
                    files.setFilePath(userInput);
                    files.getFilePath();
                    List<String> fileNames = files.getFile();
                    System.out.println("---------------------------------\n------------------------------");
                    System.out.println(fileNames);

                    files.getFileNameParseingInfo();
                    files.getTokens();
                    files.getRegionXCords();
                    files.getRegionZCords();

                    region.setFilePath(userInput);
                    files.getFiles();
                    region.getChunkLocations();
                    region.getChunkLocationOffset();

                    chunk.setFileNames(userInput);
                    System.out.println(chunk.getNBTData());
                }
            }
        }
    } while (!userInput.equalsIgnoreCase("Cancel"));

    }
    
}
