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
public class TimerEventTest {
    
    public TimerEventTest() {
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
     * Test of getEvent method, of class TimerEvent.
     */
    @Test
    public void testGetEvent() {
        System.out.println("getEvent");
        TimerEvent instance = new TimerEvent("AAA");
        String expResult = "AAA";
        String result = instance.getEvent();
        assertEquals(expResult, result);
    }


    /**
     * Test of getMemory method, of class TimerEvent.
     */
    @Test
    public void testGetMemory() {
        System.out.println("getMemory");
        TimerEvent instance = new TimerEvent("AAA");
        Integer expResult = null;
        String result = instance.getMemory();
        assertNotNull(result);
    }

    /**
     * Test of getTime method, of class TimerEvent.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        TimerEvent instance = new TimerEvent("AAA");
        Integer expResult = null;
        Long result = instance.getTime();
        assertNotNull(result);
    }   
}
