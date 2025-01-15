package goneaxolotl_;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.querz.mca.Chunk;
import net.querz.mca.MCAFile;
import net.querz.mca.MCAUtil;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.ListTag;
import net.querz.nbt.tag.StringTag;
/**
 *
 * @author Lawnguy
 */
public class Chunks extends Region 
{

    private int signCounter = 0;
    private int bannerCounter = 0;
    private int fileBeingUsed = 0;

    /**
     * the setFileName() method sets the files names, 
     * should not have to use it but I must sadly 
     * 
     * @param initalFilePath the file path
     */
    public void setFileNames(String initalFilePath) 
    {
        super.setFilePath(initalFilePath);
    }

    /**
     * The getNBTData() method gets all nbt data you are looking for 
     * 
     * @return the nbt data 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public int getNBTData() throws FileNotFoundException, IOException 
    {
        super.getChunkLocations();
        Object[] filename = getFiles().toArray();

        while (fileBeingUsed != filename.length) 
        {
            System.out.println((File) filename[fileBeingUsed]);
            System.out.println("\n===============================================\n");

            MCAFile mcaFile = MCAUtil.read((File) filename[fileBeingUsed]);

            for (int x = 0; x < 32; x++) 
            {
                for (int z = 0; z < 32; z++) 
                {
                    
                    mcaFile.cleanupPalettesAndBlockStates();

                    Chunk chunk = mcaFile.getChunk(x, z);

                    if (chunk == null) 
                    {
                        System.out.println("Chunk data is not there");
                        continue;
                    }
                    if (chunk.getHandle() == null) 
                    {
                        System.out.println("\nChunk data is not there\n");
                        continue;
                    }

                    CompoundTag handle = chunk.getHandle();
                    
                    if (handle == null) 
                    {
                        System.out.println("Chunk Data is null at" + x + " -- " + z);
                    }

                    if (handle != null) 
                    {
                        /*
                        ListTag sections = (ListTag) handle.getListTag("sections");
                        
                        if (sections != null) 
                        {
                            for (int i = 0; i < sections.size(); i++) 
                            {
                                System.out.println("\n\n" + sections.size());

                                CompoundTag section = (CompoundTag) sections.get(i);

                                int sectionY = section.getByte("Y");
                                System.out.println(sectionY);
                                //processBlockStates(section, x, z);

                                if (section.containsKey("biomes")) 
                                {
                                    
                                    processBiomes(section, x, z);
                                    CompoundTag biomes = (CompoundTag) section.getCompoundTag("biomes");
                                  // System.out.println("Biomes tag found: at Chunk"
                                  //          + " " + x + " " + z + biomes);
                                                                        
                                    //csvWriter("biomes.csv", biomes.toString(), Integer.toString(x), Integer.toString(z));
                                }
                                if (section.containsKey("block_states")) 
                                {

                                    CompoundTag block = (CompoundTag) section.getCompoundTag("block_states");

                                    //System.out.println("Blocks: " + block);

                                }

                            }
                            
                        }
                            */
                        ListTag blockEntites = (ListTag) handle.getListTag("block_entities");

                        if (blockEntites != null) 
                        {
                            for (int k = 0; k < blockEntites.size(); k++) 
                            {
                                CompoundTag blockEnity = (CompoundTag) blockEntites.get(k);

                                String id = blockEnity.getString("id");

                                if ("minecraft:sign".equalsIgnoreCase(id)) 
                                {
                                    int xSign = blockEnity.getInt("x");
                                    int ySign = blockEnity.getInt("y");
                                    int zSign = blockEnity.getInt("z");
                                    
                                    System.out.println("Sign found at: (X,Y,Z) "
                                            + blockEnity.getInt("x") + " , "
                                            + blockEnity.getInt("y") + " , "
                                            + blockEnity.getInt("z"));
                                            
                                    signCounter++;

                                    CompoundTag frontText = blockEnity.getCompoundTag("front_text");
                                    CompoundTag backText = blockEnity.getCompoundTag("back_text");

                                    ListTag frontMsg = frontText.getListTag("messages");
                                    ListTag backMsg = backText.getListTag("messages");

                                    System.out.println("Front of Sign: ");

                                    System.out.println(frontMsg.asStringTagList());

                                    System.out.println("Back of sign: ");

                                    System.out.println(backMsg.asStringTagList());

                                    csvWriter("Signs.csv", Integer.toString(xSign), 
                                            Integer.toString(ySign), 
                                            Integer.toString(zSign), 
                                            frontMsg.toString(), backMsg.toString());
                                    
                                    csvWriter("SignsV2.csv", Integer.toString(xSign), 
                                            Integer.toString(ySign), 
                                            Integer.toString(zSign), 
                                            frontMsg.toString(), backMsg.toString(), 
                                            frontText.toString(), backText.toString());
                                }
                            }

                            for (int i = 0; i < blockEntites.size(); i++) 
                            {
                                CompoundTag blockEnity = (CompoundTag) blockEntites.get(i);

                                String id = blockEnity.getString("id");

                                if ("minecraft:banner".equalsIgnoreCase(id)) 
                                {
                                   int bannerX =  blockEnity.getInt("x");
                                   int bannerY = blockEnity.getInt("y");
                                   int bannerZ = blockEnity.getInt("z");
                                   
                                    System.out.println("Banner found at: (X,Y,Z) "
                                            + blockEnity.getInt("x") + " , "
                                            + blockEnity.getInt("y") + " , "
                                            + blockEnity.getInt("z"));

                                    if (blockEnity.containsKey("CustomName"))
                                    {
                                     String costumeName = blockEnity.getString("CustomName");
                                        System.out.println("Banner Name" + costumeName);
                                    } 
                                    else 
                                    {
                                        System.out.println("Banner has no name");
                                    }

                                    int baseColor = blockEnity.getInt("Base");

                                    System.out.println("Base Color of banner "
                                            + baseColor);

                                    ListTag patterns = blockEnity.getListTag("Patterns");

                                    bannerCounter++;

                                    if (patterns != null) 
                                    {
                                        
                                    String costumeName = blockEnity.getString("CustomName");
                                    
                                    List<String> bannerColor = new ArrayList<>();
                                    List<String> bannerPat = new ArrayList<>();

                              
                                        for (int j = 0; j < patterns.size(); j++) 
                                        {
                                            System.out.println("Banner pattern");
                                    CompoundTag pattern = (CompoundTag) patterns.get(j);
                                    int color = pattern.getInt("Color");
                                    String patternType = pattern.getString("Pattern");
                                            
                                            String col = null;
                                            
                                            switch (color)
                                            {
                                                case 0:
                                                    col = "Black";
                                                    break;
                                                case 1: 
                                                    col = "Red";
                                                    break;
                                                case 2: 
                                                    col = "Green";
                                                    break;
                                                case 3: 
                                                    col = "Brown";
                                                    break;
                                                case 4: 
                                                    col = "blue";
                                                    break;
                                                case 5: 
                                                    col = "Purple";
                                                    break;
                                                case 6: 
                                                    col = "Cyan";
                                                    break;
                                                case 7: 
                                                    col = "Light Gray";
                                                    break;
                                                case 8: 
                                                    col = "Gray";
                                                    break;
                                                case 9:
                                                    col = "Pink";
                                                    break;
                                                case 10: 
                                                    col = "Lime";
                                                    break;
                                                case 11: 
                                                    col = "Yellow";
                                                    break;
                                                case 12: 
                                                    col = "Light Blue";
                                                    break;
                                                case 13: 
                                                    col = "Magenta";
                                                    break;
                                                case 14: 
                                                    col = "Orange";
                                                    break;
                                                case 15: 
                                                    col = "White";
                                                    break;
                                            }
                                            
                                            
                                            String pat = null;
                                            
                                            switch (patternType)
                                            {
                                                case "b":
                                                    pat = "Fully color Base ";
                                                    break;
                                                case "bs":
                                                    pat = "Bottom Stripe ";
                                                    break;
                                                case "ts":
                                                    pat = "Top Stripe ";
                                                    break;
                                                case "ls":
                                                    pat = "Left Stripe ";
                                                    break;
                                                case "rs":
                                                    pat = "Right Stripe ";
                                                    break;
                                                case "cs":
                                                    pat = "Center Stripe (Vertical) ";
                                                    break;
                                                case "ms":
                                                    pat = "Middle Stripe (Horizontal) ";
                                                    break;
                                                case "drs":
                                                    pat = "Down Right Stripe ";
                                                    break;
                                                case "dls":
                                                    pat = "Down Left Stripe ";
                                                    break;
                                                case "ss":
                                                    pat = "Small (Vertical) Stripes ";
                                                    break;
                                                case "cr":
                                                    pat = "Diagonal Cross ";
                                                    break;
                                                case "sc":
                                                    pat = "Square Cross ";
                                                    break;
                                                case "ld":
                                                    pat = "Left of Diagonal ";
                                                    break;
                                                case "rud":
                                                    pat = "Right of upside-down Diagonal ";
                                                    break;
                                                case "lud":
                                                    pat = "Left of upside-down Diagonal ";
                                                    break;
                                                case "rd":
                                                    pat = "Right of Diagonal ";
                                                    break;
                                                case "vh":
                                                    pat = "Vertical Half (left) ";
                                                    break;
                                                case "vhr":
                                                    pat = "Vertical Half (right) ";
                                                    break;
                                                case "hh":
                                                    pat = "Horizontal Half (top) ";
                                                    break;
                                                case "hhb":
                                                    pat = "Horizontal Half (bottom) ";
                                                    break;
                                                case "bl":
                                                    pat = "Bottom Left Corner ";
                                                    break;
                                                case "br":
                                                    pat = "Bottom Right Corner ";
                                                    break;
                                                case "tl":
                                                    pat = "Top Left Corner ";
                                                    break;
                                                case "tr":
                                                    pat = "Top Right Corner ";
                                                    break;
                                                case "bt":
                                                    pat = "Bottom Triangle ";
                                                    break;
                                                case "tt":
                                                    pat = "Top Triangle ";
                                                    break;
                                                case "bts":
                                                    pat = "Bottom Triangle Sawtooth ";
                                                    break;
                                                case "tts":
                                                    pat = "Top Triangle Sawtooth ";
                                                    break;
                                                case "mc":
                                                    pat = "Middle Circle ";
                                                    break;
                                                case "mr":
                                                    pat = "Middle Rhombus ";
                                                    break;
                                                case "bo":
                                                    pat = "Border ";
                                                    break;
                                                case "cbo":
                                                    pat = "Curly Border ";
                                                    break;
                                                case "bir":
                                                    pat = "Brick ";
                                                    break;
                                                case "gra":
                                                    pat = "Gradient";
                                                    break;
                                                case "gru":
                                                    pat = "Gradient upside-down ";
                                                    break;
                                                case "cre":
                                                    pat = "Creeper ";
                                                    break;
                                                case "sku":
                                                    pat = "Skull";
                                                    break;
                                                case "flo":
                                                    pat = "Flower";
                                                    break;
                                                case "moj":
                                                    pat = "Mojang";
                                                    break;
                                                case "blb":
                                                    pat = "Globe";
                                                    break;
                                                case "pig":
                                                    pat = "Piglin";
                                                    break;
                                                
                                            }
                                            bannerPat.add("("+ col);
                                            bannerPat.add(pat + ")");
                                            
                                            System.out.println("Pattern: "
                                                    + bannerPat + "Color" + bannerColor);
                                            
                                        }
                                        
                                        csvWriter("Banners.csv", Integer.toString(bannerX), 
                                                    Integer.toString(bannerY), 
                                                    Integer.toString(bannerZ),
                                                    costumeName,
                                                    bannerColor.toString(), bannerPat.toString());
                                        
                                    } 
                                    else 
                                    {
                                        System.out.println("No patterns exsit");
                                    }

                                }

                            }
                        }

                    } 
                    else 
                    {
                        System.out.println("Chunk is null at" + x + " -- " + z);
                    }

                    CompoundTag chunkHM = chunk.getHeightMaps();
                    System.out.println(chunkHM);

                    ListTag<CompoundTag> entities = chunk.getTileEntities();
                    if (entities != null) 
                    {
                        System.out.println(entities.asStringTagList());
                    } 
                    else 
                    {
                        System.out.println("No entites found in" + x + " -- " + z);
                    }

                }
            }

            System.out.println("\n===============================================\n");
            fileBeingUsed++;
        }
        System.out.println("\n\n\nsignCounter=======" + signCounter
                + "\n\n\n=========");
        System.out.println("\n\n\nsignCounter=======" + bannerCounter
                + "\n\n\n=========");
        return fileBeingUsed;
    }
    /**
     * The processBlockStates() method handles all block state stuff
     * 
     * @param section the section data for that chunk
     * @param chunkX where in the chunk you are looking 
     * @param chunkZ where in the chunk you are looking 
     * @throws IOException 
     */
    public void processBlockStates(CompoundTag section, int chunkX, int chunkZ) throws IOException 
    {
        if (section.containsKey("block_states")) 
        {
            CompoundTag blockStates = section.getCompoundTag("block_states");
            ListTag<CompoundTag> palette = (ListTag<CompoundTag>) blockStates.getListTag("palette");
            long[] data = blockStates.getLongArray("data");  

            if (data == null || data.length == 0) 
            {
                System.out.println("No block data found in section.");
                return; 
            }

            int bitsPerBlock = data.length * 64 / 4096;  
            int paletteSize = palette.size();

            if (bitsPerBlock == 0) 
            {
                System.out.println("Invalid bitsPerBlock for section: " + bitsPerBlock);
                return;
            }

            if (paletteSize > (1 << bitsPerBlock)) 
            {
                bitsPerBlock++;
            }

            Object[] filename = getFiles().toArray();
            String regionFileName = ((File) filename[fileBeingUsed]).getName();

            String[] splitName = regionFileName.split("\\.");
            int regionX = Integer.parseInt(splitName[1]);
            int regionZ = Integer.parseInt(splitName[2]);

            int baseGlobalX = regionX * 512; 
            int baseGlobalZ = regionZ * 512;

           
            for (int y = 0; y < 16; y++) 
            {
                for (int z = 0; z < 16; z++) 
                {
                    for (int x = 0; x < 16; x++) 
                    {
                        int index = x + (z * 16) + (y * 16 * 16);
                        int paletteIndex = getPaletteIndexFromData(data, index, bitsPerBlock);

                        if (paletteIndex >= 0 && paletteIndex < palette.size()) 
                        {
                            CompoundTag block = palette.get(paletteIndex);

                            String blockName = block.getString("Name");

                            int globalY = (section.getByte("Y") * 16) + y;  
                            int globalX = baseGlobalX + (chunkX * 16) + x;
                            int globalZ = baseGlobalZ + (chunkZ * 16) + z;

                         //  System.out.println("Block: " + blockName + " at ("
                           //         + globalX + ", " + globalY + ", " + globalZ + ")");
                            csvWriter("Blocks.csv", blockName, 
                                    Integer.toString(globalX), 
                                    Integer.toString(globalY), 
                                    Integer.toString(globalZ));
                           
                        }
                    }
                }
            }
        } 
        else 
        {
            System.out.println("No block states found in this section.");
        }
    }
    
/**
 * The getPaletteIndexFromData() gets all of the palettes from the data 
 * mostly for blocks
 * 
 * @param data Long array of the blocks nbt data 
 * @param index the y index of the section 
 * @param bitsPerBlock amount of bits allowed for the buffer 
 * @return who really knows, idk palette shit 
 */
    private int getPaletteIndexFromData(long[] data, int index, int bitsPerBlock) 
    {
        if (bitsPerBlock == 0) 
        {
            return -1;  
        }

        int blocksPerLong = 64 / bitsPerBlock;
        int longIndex = index / blocksPerLong;
        int bitIndex = (index % blocksPerLong) * bitsPerBlock;

        if (longIndex >= data.length) 
        {
            return -1;
        }

        long blockData = data[longIndex];
        return (int) ((blockData >> bitIndex) & ((1L << bitsPerBlock) - 1)); 
    }
    
/**
 * the processBiomes() method handles the biomes palette
 * 
 * @param section the section data
 * @param chunkX where in the chunk of x 
 * @param chunkZ where in the chunk of z 
 * @throws IOException 
 */
public void processBiomes(CompoundTag section, int chunkX, int chunkZ) throws IOException 
{
    if (section.containsKey("biomes")) 
    {
        CompoundTag biomes = section.getCompoundTag("biomes");

        // Debug
        //System.out.println("Biomes tag found at Chunk " + chunkX + " " + chunkZ + ": " + biomes);

        ListTag<StringTag> palette = (ListTag<StringTag>) biomes.getListTag("palette");
        long[] data = biomes.getLongArray("data");

        // Check if palette is present but no data (entire chunk may be one biome)
        if (palette != null && palette.size() > 0 && (data == null || data.length == 0)) 
        {
            // Assume the entire section is covered by the first biome in the palette
            StringTag biome = palette.get(0);
            String biomeName = biome.getValue();
            System.out.println("Entire chunk (" + chunkX + ", " + chunkZ + ") is biome: " + biomeName);

            Object[] filename = getFiles().toArray();
            String regionFileName = ((File) filename[fileBeingUsed]).getName();

            String[] splitName = regionFileName.split("\\.");
            int regionX = Integer.parseInt(splitName[1]);
            int regionZ = Integer.parseInt(splitName[2]);

            int baseGlobalX = regionX * 512;
            int baseGlobalZ = regionZ * 512;

            // log all coordinates for the entire chunk being the same biome
            for (int i = 0; i < 64; i++) 
            {
                int globalX = baseGlobalX + (i % 16);
                int globalZ = baseGlobalZ + (i / 16);
                
                // log to CSV
                csvWriter("Biomes.csv", biomeName, Integer.toString(globalX), Integer.toString(globalZ));
              //  System.out.println("Biome: " + biomeName + " at (" + globalX + ", " + globalZ + ")");
            }
            return;
        }

        if (palette == null || data == null || palette.size() == 0 || data.length == 0) 
        {
            System.out.println("No biome data found in section.");
            return;
        }

        int bitsPerBiome = data.length * 64 / 64; // 64 blocks per section
        int paletteSize = palette.size();

        // debug
       // System.out.println("Palette size: " + paletteSize + ", bitsPerBiome: " + bitsPerBiome);

        if (bitsPerBiome == 0) 
        {
            System.out.println("Invalid bitsPerBiome for section: " + bitsPerBiome);
            return;
        }

        if (paletteSize > (1 << bitsPerBiome)) 
        {
            bitsPerBiome++;
        }

        Object[] filename = getFiles().toArray();
        String regionFileName = ((File) filename[fileBeingUsed]).getName();

        String[] splitName = regionFileName.split("\\.");
        int regionX = Integer.parseInt(splitName[1]);
        int regionZ = Integer.parseInt(splitName[2]);

        int baseGlobalX = regionX * 512;
        int baseGlobalZ = regionZ * 512;

        for (int i = 0; i < 64; i++) 
        {
            int biomeIndex = getPaletteIndexFromData(data, i, bitsPerBiome);

            // debug
           // System.out.println("Biome Index at i=" + i + ": " + biomeIndex);

            if (biomeIndex >= 0 && biomeIndex < palette.size()) 
            {
                StringTag biome = palette.get(biomeIndex);
                String biomeName = biome.getValue();

                // Print out biome name to check for End-specific biomes
              //  System.out.println("Biome name: " + biomeName);

                int globalX = baseGlobalX + (i % 16);
                int globalZ = baseGlobalZ + (i / 16);

               // System.out.println("Biome: " + biomeName + " at (" + globalX + 
               //        ", " + globalZ + ")");

                csvWriter("Biomes.csv", biomeName, Integer.toString(globalX), 
                        Integer.toString(globalZ));
            } 
            else
            {
              //  System.out.println("Biome at index " + biomeIndex + " is out of bounds.");
            }
        }
    } 
    else 
    {
        System.out.println("No biomes tag found in this section.");
    }
}
}
