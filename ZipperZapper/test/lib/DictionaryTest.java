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
public class DictionaryTest {
    
    public DictionaryTest() {
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

    @Test
    public void testDictionaryInit(){
        Dictionary dic = new Dictionary();
        int a = dic.get("a");
        int b = dic.get("b");
        int nine = dic.get("9");
        assertEquals("a was not found", 97, a);
        assertEquals("b was not found", 98, b);
        assertEquals("9 was not found", 57, nine);
    }
    
    @Test
    public void testDictionaryRetrival() {
        Dictionary dic = new Dictionary();
        dic.put("AAA", 123);
        dic.put("BBB", 456);
        dic.put("CCC", 789);
        int a = dic.get("AAA");
        int b = dic.get("BBB");
        int c = dic.get("CCC");
        //int d = dic.get("NOT FOUND");
        assertEquals("AAA was not found", 123, a);
        assertEquals("BBB was not found", 456, b);
        assertEquals("CCC was not found", 789, c);
        //assertNull("NULL not returned", c);
    }
    
    
}
