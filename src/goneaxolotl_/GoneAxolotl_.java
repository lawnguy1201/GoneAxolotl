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
        
        String endProgram = "Cancel";
        String regionCordCalc = "Yes";

        // Progam loop will allow to end the program at any time the user wants 
        while (true) 
        {
            // user input 
            System.out.println("To stop the program at anytime type: Cancel");
            System.out.println("If not enter Continue");
            System.out.println("--------------------------------");

            String userInput = input.nextLine();

            // if the user enters cencel, the program will end 
            if (userInput.equals(endProgram)) 
            {
                System.out.println("Program has been added");
                break;
            }

            // Ask the user if they want to calculate the region file cord 
            System.out.println("\n------------------------------------");
            System.out.println("Would you like to calculate Region Cords");
            System.out.println("Enter Yes for yes, Enter No for No");
            System.out.println("\n------------------------------------");
            String userREgionInput = input.nextLine();

            // if they say yes go through this block 
            if (userREgionInput.equals(regionCordCalc)) 
            {
                System.out.println("\n------------------------------------");
                System.out.println("Enter Your X Cord Then Y Cord");
                int userXInputs = input.nextInt();
                int useryInputs = input.nextInt();

                region.setRegionCords(userXInputs, useryInputs);
                int[] strongMan = region.getRegionCords();

                // did not know array class had a tostring method built in
                System.out.println(Arrays.toString(strongMan));

                System.out.println("\n\n---------------------------------");
            }

            System.out.println("Please copy and Paste your Path to the regionFiles");
            String initalFilePath = input.nextLine();
            
            files.setFilePath(initalFilePath);
            files.getFilePath();
            List<String> fileNames = files.getFile(); // Now stores file names in the list
            System.out.println("---------------------------------\n------------------------------");

            System.out.println(fileNames);

            files.getFileNameParseingInfo();
            files.getFileNameParseingInfo();
            
            files.getFileNameParseingInfo();
            files.getTokens();
           
            files.getRegionXCords();
            files.getRegionZCords();

            region.setFilePath(initalFilePath);
            files.getFiles();
            region.getChunkLocations();
            
          region.getChunkLocationOffset();
          
          //Use to get the time stamps 
        //  region.getChunkTimeStamps();
        
        
           chunk.setFileNames(initalFilePath);
           System.out.println(chunk.getNBTData());
           
  
        }
    }
    
}
