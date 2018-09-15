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
public class TimerTest {
    
    public TimerTest() {
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
     * Test of add method, of class Timer.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        long milliseconds = 0L;
        String event = "";
        Timer instance = new Timer();
        instance.add(milliseconds, event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEvents method, of class Timer.
     */
    @Test
    public void testGetEvents() {
        System.out.println("getEvents");
        Timer instance = new Timer();
        String[][] expResult = null;
        String[][] result = instance.getEvents();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventCount method, of class Timer.
     */
    @Test
    public void testGetEventCount() {
        System.out.println("getEventCount");
        Timer instance = new Timer();
        int expResult = 0;
        int result = instance.getEventCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
