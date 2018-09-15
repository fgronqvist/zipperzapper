/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

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
public class ZipperzapperTest {
    
    public ZipperzapperTest() {
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
     * Test of run method, of class Zipperzapper.
     */
    @Test
    public void testRun() throws Exception {
        System.out.println("run");
        Zipperzapper instance = new Zipperzapper();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompressionOption method, of class Zipperzapper.
     */
    @Test
    public void testGetCompressionOption() {
        System.out.println("getCompressionOption");
        Zipperzapper instance = new Zipperzapper();
        int expResult = 0;
        int result = instance.getCompressionOption();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCompressionOption method, of class Zipperzapper.
     */
    @Test
    public void testSetCompressionOption() {
        System.out.println("setCompressionOption");
        int compression_option = 0;
        Zipperzapper instance = new Zipperzapper();
        instance.setCompressionOption(compression_option);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInpufile method, of class Zipperzapper.
     */
    @Test
    public void testGetInpufile() {
        System.out.println("getInpufile");
        Zipperzapper instance = new Zipperzapper();
        String expResult = "";
        String result = instance.getInpufile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInpufile method, of class Zipperzapper.
     */
    @Test
    public void testSetInpufile() {
        System.out.println("setInpufile");
        String inpufile = "";
        Zipperzapper instance = new Zipperzapper();
        instance.setInpufile(inpufile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutputfile method, of class Zipperzapper.
     */
    @Test
    public void testGetOutputfile() {
        System.out.println("getOutputfile");
        Zipperzapper instance = new Zipperzapper();
        String expResult = "";
        String result = instance.getOutputfile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOutputfile method, of class Zipperzapper.
     */
    @Test
    public void testSetOutputfile() {
        System.out.println("setOutputfile");
        String outputfile = "";
        Zipperzapper instance = new Zipperzapper();
        instance.setOutputfile(outputfile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
