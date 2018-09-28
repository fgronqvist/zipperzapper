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
     *
     * @param inputfile
     * @param outputfile
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void zipperCompress(String inputfile, String outputfile) throws FileNotFoundException, IOException {
        Timer.getInstance().add("zipperCompress started");
        BinaryIn inStream = new BinaryIn(inputfile);
        BinaryOut outStream = new BinaryOut(outputfile);
        Timer.getInstance().add("File streams open");
        
        Dictionary dict = new Dictionary();
        dict.initializeDictionary();
        Timer.getInstance().add("Initial dictionary created");

        Timer.getInstance().add("Starting processing");
        String word = "";
        char cha;
        while (inStream.isEmpty() == false) {
            cha = inStream.readChar();
            String wordChar = word + cha;

            if (dict.get(wordChar) != null) {
                // These characters allready exists in the dictionary, so make
                // the new word the characters and test again with characters
                // that are one character longer than word.
                word = wordChar;
            } else {
                // The word does not yet exist in the dictionary. Add them
                // and then reset the word to be the current characters.
                outStream.write(dict.get(word), 32);

                dict.put(wordChar);

                // We just pushed a new word into the dictionary, so reset
                // the word to just the current character.
                word = "" + cha;
            }
        }

        // The last word has to be output as well, as the while loop will terminate
        // before it is printed.
        if (word.equals("") == false) {
            outStream.write(dict.get(word), 32);
        }
        outStream.close();        
        Timer.getInstance().add("Processing completed");
    }

    /**
     * Decompress a file with the ZipperZapper compression
     *
     * @param inputfile
     * @param outputfile
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void zipperDecompress(String inputfile, String outputfile) throws FileNotFoundException, IOException {
        Timer.getInstance().add("zipperDecompress started");
        BinaryIn inStream = new BinaryIn(inputfile);
        BinaryOut outStream = new BinaryOut(outputfile);

        Timer.getInstance().add("File streams open");
        Dictionary dict = new Dictionary();
        dict.initializeDictionary();
        Timer.getInstance().add("Initial dictionary created");

        Timer.getInstance().add("Starting processing");
        String w = dict.get(inStream.readInt(32));
        outStream.write(w);
        Integer key;
        while (inStream.isEmpty() == false) {
            key = inStream.readInt(32);

            String entry = "";
            if (dict.hasKey(key)) {
                // Word is in the dictionary
                entry = dict.get(key);
            } else if (dict.getIntegerDictionarySize().equals(key)) {
                // The word is not in the dictionary
                entry = w + w.charAt(0);
            }

            outStream.write(entry);

            // Add the string to the dictionary with the largest position. This
            // will match the compressed id/position.
            dict.put(dict.getIntegerDictionarySize(), w + entry.charAt(0));

            w = entry;
        }

        outStream.close();
        Timer.getInstance().add("Processing complete");
    }

    /**
     * Compress a file using java.util.ZipOutputStream
     *
     * @param inputfile
     * @param outputfile
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void javaUtilCompress(String inputfile, String outputfile) throws FileNotFoundException, IOException {
        FileInputStream inStream = new FileInputStream(inputfile);
        FileOutputStream outStream = new FileOutputStream(outputfile);
        ZipOutputStream zipOutStream = new ZipOutputStream(outStream);

        File inFile = new File(inputfile);
        FileInputStream fileInputStream = new FileInputStream(inFile);
        ZipEntry zipEntry = new ZipEntry(inFile.getName());
        zipOutStream.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = inStream.read(bytes)) != -1) {
            zipOutStream.write(bytes, 0, length);
        }
        zipOutStream.close();
        inStream.close();
        outStream.close();
    }

    /**
     * Decompress a file using java.util.ZipInputStream
     *
     * @param inputfile
     * @param outputfile
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void javaUtilDecompress(String inputfile, String outputfile) throws FileNotFoundException, IOException {
        byte[] buffer = new byte[1024];
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(inputfile));
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        while (zipEntry != null) {
            File outFile = new File(outputfile);
            FileOutputStream outStream = new FileOutputStream(outFile);
            int length;
            while ((length = zipInputStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            outStream.close();
            zipEntry = zipInputStream.getNextEntry();
        }
        zipInputStream.closeEntry();
        zipInputStream.close();
    }
}
