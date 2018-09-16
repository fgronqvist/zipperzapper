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
