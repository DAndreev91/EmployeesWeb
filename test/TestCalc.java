/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author andreevds91
 */
@RunWith(Parameterized.class)
public class TestCalc {
    
    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
            {7,12,19,-5},
            {43,5,48,38},
            {24,98,122,-74}
        });
    }
    
    int x,y,sum,sub;
    
    public TestCalc(int x, int y, int sum, int sub) {
        this.x = x;
        this.y = y;
        this.sum = sum;
        this.sub = sub;
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("All tests started");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("All tests finished");
    }
    
    @Before
    public void setUp() {
        System.out.println("Started");
    }
    
    @After
    public void tearDown() {
        System.out.println("Finished");
    }
    
    
    @Test
    // Обратите внимание аннотацию - она говорит, что тест будет проигнорирован. Если ее убрать,
    // то сообщение появиться
    @Ignore
    public void testIgnored() {
        System.out.println("Ignored test");
    }
    
    @Test
    public void get001SumTest() {
        Calc c = new Calc();
        assertEquals(c.getSum(x, y), sum);
    }
    
    @Test
    public void get002SubstractionTest() {
        Calc c = new Calc();
        assertEquals(sub, c.getSubstraction(x, y));
    }
    
    private static Comparator forward() {
        return new Comparator() {
            public int compare(Object o1, Object o2) {
                Description d1 = (Description) o1;
                Description d2 = (Description) o2;
                return d1.getDisplayName().compareTo(d2.getDisplayName());
            }
        };
    }
    
    /*public static void main(String[] args) {
        JUnitCore core = new JUnitCore();
        core.run(Request.aClass(TestCalc.class).sortWith(forward()));
    }*/
    
}
