/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fredrik Grönqvist <fredrik.gronqvist@gmail.com>
 */
public class CompressTest {

    String inputfile = "test/testinput.txt";
    String outputfile = "test/testoutput.txt.zipper";
    String decompressOutputfile = "test/testdecompress.txt";

    public CompressTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * The files should be setup only once per test-cycle, so running them
     * before each test is not an option.
     *
     * @throws java.io.FileNotFoundException
     */
    public void setupFiles() throws FileNotFoundException, IOException {
        System.out.println("setupFiles");
        // Remove existing test datafiles
        if (this.getFile(this.outputfile).exists()) {
            this.getFile(this.outputfile).delete();
        }
        if (this.getFile(this.decompressOutputfile).exists()) {
            this.getFile(this.decompressOutputfile).delete();
        }

        // Create testdata
        FileOutputStream out = new FileOutputStream(this.inputfile);
        for (int i = 0; i < 1000000; i++) {
            String input = "Line " + i + " of data with random string at the end: ";
            input = input + String.valueOf(Math.random() + "\n");
            out.write(input.getBytes());
        }
        out.close();

    }

    /**
     * Get a File object based on name.
     *
     * @param filename
     * @return
     */
    public File getFile(String filename) {
        File file = new File(filename);
        return file;
    }

    /**
     * A file hash calculator. 
     * https://www.codejava.net/coding/how-to-calculate-md5-and-sha-hash-values-in-java
     * 
     * @param file
     * @param algorithm that java.security.MessageDigest accepts
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException 
     */
    private String hashFile(File file, String algorithm) throws IOException, NoSuchAlgorithmException {
        FileInputStream inputStream = new FileInputStream(file);
        MessageDigest digest = MessageDigest.getInstance(algorithm);

        byte[] bytesBuffer = new byte[1024];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(bytesBuffer)) != -1) {
            digest.update(bytesBuffer, 0, bytesRead);
        }

        byte[] hashedBytes = digest.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < hashedBytes.length; i++) {
            stringBuffer.append(Integer.toString((hashedBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }

    /**
     * Test of javaUtilCompress method, of class Compress.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testJavaUtilCompression() throws Exception {
        System.out.println("javaUtilCompression");
        this.setupFiles();
        
        Compress instance = new Compress();
        instance.javaUtilCompress(this.inputfile, this.outputfile);
        long inputfileSize = this.getFile(this.inputfile).length();
        long outputfileSize = this.getFile(this.outputfile).length();
        if (outputfileSize < inputfileSize && outputfileSize > 0) {
            assertTrue("Output filesize is smaller than input filesize", true);
        }
    }

    /**
     * Test that the decompressed file equals the original.
     *
     * @throws Exception
     */
    @Test
    public void testJavaUtilDecompress() throws Exception {
        System.out.println("javaUtilDecompression");
        Compress instance = new Compress();
        instance.javaUtilCompress(this.inputfile, this.outputfile);
        instance.javaUtilDecompress(this.outputfile, this.decompressOutputfile);
        String inputfileHash = this.hashFile(this.getFile(this.inputfile), "MD5");
        String inflatedHash = this.hashFile(this.getFile(this.decompressOutputfile), "MD5");
        assertEquals(inputfileHash, inflatedHash);
    }

}
