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
       Timer.getInstance().reset();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class Timer.
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testAdd() throws InterruptedException {
        Timer.getInstance().add("AAA 1");
        Timer.getInstance().add("BBB 1");
        assertEquals(2, Timer.getInstance().getEventCount());
    }

    /**
     * Test of getEvents method, of class Timer.
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testGetEvents() throws InterruptedException {
        Timer.getInstance().add("AAA 2");
        Timer.getInstance().add("BBB 2");
        Thread.sleep(3000);
        Timer.getInstance().add("CCC 2");
        int i = Timer.getInstance().getEventCount();
        TimerEvent[] list = Timer.getInstance().getEvents();
        for(TimerEvent event : list){
            System.out.println(event.getTime()+": "+event.getEvent());
        }
        assertEquals(3, list.length);
    }
    
    /**
     * Test the total running time.
     * @throws InterruptedException 
     */
    @Test
    public void testRunningTime() throws InterruptedException{
        Timer.getInstance().add("AAA 3");
        Timer.getInstance().add("BBB 3");
        Thread.sleep(2000);
        Timer.getInstance().add("CCC 3");
        int r = Timer.getInstance().getRunningTime();
        System.out.println("Running time: "+r);
        assertTrue("Total running time should be > 0 but got "+r, r > 0);
    }

    /**
     * Test of getEventCount method, of class Timer.
     */
    @Test
    public void testGetEventCount() {
        int i = 0;
        while( i < 50){
            Timer.getInstance().add("T"+i);
            i++;
        }
        assertEquals(i, Timer.getInstance().getEventCount());
    }
    
}
