package lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Fredrik Grönqvist <fredrik.gronqvist@gmail.com>
 */
public class Compress {
    
    /**
     * Compress a file using the ZipperZapper implementation.
     * @param inputfile
     * @param outputfile
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void zipperCompress(String inputfile, String outputfile) throws FileNotFoundException, IOException{
        FileInputStream inStream = new FileInputStream(inputfile);
        FileOutputStream outStream = new FileOutputStream(outputfile);
        Dictionary dict = new Dictionary();
        byte[] bytes = new byte[1];
        int length; 
        String word = "";
        boolean trip = false;
        while((length = inStream.read(bytes, 0, 1)) != -1){
            char cha = (char)bytes[0];
            String wordChar = word + cha;
            
            if(dict.get(wordChar) != null){
                // These characters allready exists in the dictionary, so make
                // the new word the characters and test again with characters
                // that are one character longer than word.
                word = wordChar;
            } else {
                // The word does not yet exist in the dictionary. Add them
                // and then reset the word to be the current characters.
                System.out.println("Encoded: "+dict.get(word));
                outStream.write(dict.get(word));
                dict.put(wordChar);
                
                // We just pushed a new word into the dictionary, so reset
                // the word to just the current character.
                word = ""+cha;
                //System.out.println(dict.toString());
                //trip = true;
            }
            System.out.print("byte:"+bytes[0]);
            System.out.print(" char: "+cha);
            System.out.print(" wc:"+wordChar);
            System.out.println(" w:"+word);
            
            if(trip){
                break;
            }
        }
    }
    
    /**
     * Compress a file using java.util.ZipOutputStream
     * @param inputfile
     * @param outputfile
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void javaUtilCompress(String inputfile, String outputfile) throws FileNotFoundException, IOException{
        FileInputStream inStream = new FileInputStream(inputfile);
        FileOutputStream outStream = new FileOutputStream(outputfile);        
        ZipOutputStream zipOutStream = new ZipOutputStream(outStream);
        
        File inFile = new File(inputfile);
        FileInputStream fileInputStream = new FileInputStream(inFile);
        ZipEntry zipEntry = new ZipEntry(inFile.getName());
        zipOutStream.putNextEntry(zipEntry);
        
        byte[] bytes = new byte[1024];
        int length;
        while( (length = inStream.read(bytes)) != -1){
            zipOutStream.write(bytes, 0, length);
        }
        zipOutStream.close();
        inStream.close();
        outStream.close();
    }
    
    /**
     * Decompress a file using java.util.ZipInputStream
     * @param inputfile
     * @param outputfile
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void javaUtilDecompress(String inputfile, String outputfile) throws FileNotFoundException, IOException{
        byte[] buffer = new byte[1024];
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(inputfile));
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        while(zipEntry != null){
            File outFile = new File(outputfile);
            FileOutputStream outStream = new FileOutputStream(outFile);
            int length;
            while ((length = zipInputStream.read(buffer)) > 0){
                outStream.write(buffer, 0, length);
            }
            outStream.close();
            zipEntry = zipInputStream.getNextEntry();            
        }
        zipInputStream.closeEntry();
        zipInputStream.close();        
    }
}
