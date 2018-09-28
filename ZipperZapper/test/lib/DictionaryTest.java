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
    public void testDictionaryInit() {
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
        dic.put("AAA");
        dic.put("BBB");
        dic.put("CCC");
        int a = dic.get("a");
        int b = dic.get("b");
        int c = dic.get("c");
        int aa = dic.get("AAA");
        int bb = dic.get("BBB");
        int cc = dic.get("CCC");
        //int d = dic.get("NOT FOUND");
        assertEquals("a was not found", 97, a);
        assertEquals("b was not found", 98, b);
        assertEquals("c was not found", 99, c);
        assertEquals("AAA", 256, aa);
        assertEquals("BBB", 257, bb);
        assertEquals("CCC", 258, cc);
        //assertNull("NULL not returned", c);
    }

    /**
     * Test that the dictionary gives the right size
     */
    @Test
    public void testDictionarySize() {
        Dictionary dict = new Dictionary();
        assertTrue("", dict.getIntegerDictionarySize().equals(256));
    }

}
