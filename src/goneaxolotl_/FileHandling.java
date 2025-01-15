package goneaxolotl_;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * The FileHandling class is meant to handle all of the work for the files
 *
 * @author Lawnguy
 */
public class FileHandling  
{

    private String regionFilePath;
    public final List<File> fileNameList = new ArrayList<>();
    private final List<String> fileName = new ArrayList<>();
    private final List<String> parseingInfo = new ArrayList<>();
    private final List<String> xToken = new ArrayList<>();
    private final List<String> zToken = new ArrayList<>();

    /**
     * The getFilePath method gets the path to where the region files are
     * located
     *
     * @return the path to the location of the region files
     */
    public final String getFilePath() 
    {
        return regionFilePath;
    }

    /**
     * the setFilePath method takes the users input and sends it off to the
     * getFilePath method
     *
     * @param initalFilePath takes in the path from the user
     */
    public void setFilePath(String initalFilePath) 
    {
        regionFilePath = initalFilePath;
    }

    /**
     * the getFiles method obtains all of the file names in that path and stores
     * it in an arrayList
     *
     * @return all of the elements of the array containing all of the names
     */
    public List<File> getFiles() 
    {
        File directoryPath = new File(regionFilePath);
        File contents[] = directoryPath.listFiles();

        //System.out.println("Files -----------------------");

        fileNameList.clear();
        
        for (File content : contents)
        {
            fileNameList.add(content);
        }
        return fileNameList;
    }
    
    public List<String> getFile() 
    {
        File directoryPath = new File(regionFilePath);
        String contents[] = directoryPath.list();

        //System.out.println("File ---------------------------------");

        fileName.clear();
        
        for (String content : contents)
        {
            fileName.add(content);
        }
        return fileName;
    }
    

    /**
     * the getFileNameParseingInfo method removes all unnecessary info from the
     * file name
     *
     * @return the array with all of the names of the files without the clunky
     * info
     */
    public List<String> getFileNameParseingInfo() 
    {

        //List<String> parseingInfo = new ArrayList<>();
        for (String fileFirstParse : fileName) 
        {
            String paresedInfo = fileFirstParse.replaceAll("[r.,mca]", " ");
            parseingInfo.add(paresedInfo);
        }

        return parseingInfo;

    }

    /**
     * The getTokens method gets the gets the tokens and splits them up into x
     * and z string numbers
     *
     *
     */
    public void getTokens()
    {
        //List<String> tokens = new ArrayList<>();
        //List<String> token = new ArrayList<>();

        for (String parseString : parseingInfo)
        {
            StringTokenizer tokenizer = new StringTokenizer(parseString, " ");

            while (tokenizer.hasMoreElements()) 
            {
                xToken.add(tokenizer.nextToken());
                zToken.add(tokenizer.nextToken());
            }
        }

    }

    /**
     * The getRegionXCords method returns the x region cord from the file name
     *
     * @return the x cord from the file names
     */
    public List<String> getRegionXCords() 
    {
        return xToken;
    }

    /**
     * The getRegionZCords method returns the z region cord from the file name
     *
     * @return the z region cord from the file names
     */
    public List<String> getRegionZCords() 
    {
        return zToken;
    }
    
   /**
    * The csvWriter() method writes data into csv file format
    * @param data any String data type
     * @param pathAndName
    * @throws IOException 
    */
public void csvWriter(String pathAndName, String... data) throws IOException 
{
    try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("C:\\MinecraftInfo\\Files" + pathAndName, true))) 
    {
        StringBuilder csvLine = new StringBuilder();
        
        for (int i = 0; i < data.length; i++)
        {
            csvLine.append(data[i]);
            if (i < data.length - 1) 
            {
                csvLine.append(",");  // Add comma between values
            }
        }
        // Use Windows-style line break
        csvLine.append("\r\n");  // Add a new line after the row for Excel compatibility
        
        fileWriter.write(csvLine.toString());
        fileWriter.flush();  // Ensure data is written immediately
    }
}



}
