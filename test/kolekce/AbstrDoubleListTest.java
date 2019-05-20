package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class AbstrDoubleListTest {

    public AbstrDoubleListTest() {
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
     * Test of zrus method, of class AbstrDoubleList.
     */
    @Test
    public void testZrus() {
        System.out.println("zrus");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        assertEquals(false, instance.jePrazdny());
        assertEquals(1, instance.getMohutnost());
        instance.zrus();
        assertEquals(0, instance.getMohutnost());
        assertEquals(true, instance.jePrazdny());
    }

    /**
     * Test of jePrazdny method, of class AbstrDoubleList.
     */
    @Test
    public void testJePrazdny() {
        System.out.println("jePrazdny");
        AbstrDoubleList instance = new AbstrDoubleList();
        assertEquals(true, instance.jePrazdny());
        instance.vlozPrvni(1);
        assertEquals(false, instance.jePrazdny());
    }

    /**
     * Test of getMohutnost method, of class AbstrDoubleList.
     */
    @Test
    public void testGetMohutnost() {
        System.out.println("getMohutnost");
        AbstrDoubleList instance = new AbstrDoubleList();
        assertEquals(0, instance.getMohutnost());
        instance.vlozPrvni(1);
        assertEquals(1, instance.getMohutnost());

    }

    /**
     * Test of vlozPrvni method, of class AbstrDoubleList.
     */
    @Test
    public void testVlozPrvni() throws KolekceException, NullPointerException {
        System.out.println("vlozPrvni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        assertEquals(1, instance.odeberPosledni());
        instance.vlozPrvni(1);
        assertEquals(1, instance.odeberPrvni());
    }

    @Test(expected = NullPointerException.class)
    public void testVlozPrvniException() throws NullPointerException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(null);

    }

    /**
     * Test of vlozPosledni method, of class AbstrDoubleList.
     */
    @Test
    public void testVlozPosledni() throws KolekceException, NullPointerException {
        System.out.println("vlozPosledni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(1);
        assertEquals(1, instance.odeberPosledni());
        instance.vlozPosledni(1);
        assertEquals(1, instance.odeberPrvni());
    }

    @Test(expected = NullPointerException.class)
    public void testVlozPosledniException() throws NullPointerException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(null);
    }

    /**
     * Test of vlozNaslednika method, of class AbstrDoubleList.
     */
    @Test
    public void testVlozNaslednika_posledni() throws NullPointerException, KolekceException {
        System.out.println("vlozNaslednika");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.vlozPrvni(2);
        instance.zpristupniPosledni();
        instance.vlozNaslednika(3);

        assertEquals(3, instance.getMohutnost());
        assertEquals(3, instance.odeberPosledni());
    }

    @Test
    public void testVlozNaslednika() throws NullPointerException, KolekceException {
        System.out.println("vlozNaslednika");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.vlozPrvni(2);
        instance.zpristupniPrvni();
        instance.vlozNaslednika(3);

        assertEquals(3, instance.getMohutnost());
        assertEquals(1, instance.odeberPosledni());
    }

    @Test(expected = NullPointerException.class)
    public void testVlozNaslednikaException_1() throws NullPointerException, KolekceException {
        System.out.println("vlozNaslednika");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.zpristupniPosledni();
        instance.vlozNaslednika(null);

    }

    @Test(expected = KolekceException.class)
    public void testVlozNaslednikaException_2() throws NullPointerException, KolekceException {
        System.out.println("vlozNaslednika");
        AbstrDoubleList instance = new AbstrDoubleList();
        //instance.vlozPrvni(1);
        instance.vlozNaslednika(1);
    }

    /**
     * Test of vlozPredchudce method, of class AbstrDoubleList.
     */
    @Test
    public void testVlozPredchudce_prvni() throws Exception {
        System.out.println("vlozPredchudce");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.vlozPrvni(2);
        instance.zpristupniPrvni();
        instance.vlozPredchudce(3);

        assertEquals(3, instance.getMohutnost());
        assertEquals(3, instance.odeberPrvni());
    }

    @Test
    public void testVlozPredchudce() throws Exception {
        System.out.println("vlozPredchudce");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(3);
        instance.vlozPrvni(1);
        instance.zpristupniPosledni();
        instance.vlozPredchudce(2);
        instance.zpristupniPrvni();
        instance.zpristupniNaslednika();

        assertEquals(3, instance.getMohutnost());
        assertEquals(2, instance.odeberAktualni());
    }

    @Test(expected = NullPointerException.class)
    public void testVlozPredchudceException_1() throws NullPointerException, KolekceException {
        System.out.println("vlozNaslednika");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.zpristupniPrvni();
        instance.vlozNaslednika(null);
    }

    @Test(expected = KolekceException.class)
    public void testVlozPredchudceException_2() throws NullPointerException, KolekceException {
        System.out.println("vlozNaslednika");
        AbstrDoubleList instance = new AbstrDoubleList();
        //instance.vlozPrvni(1);
        instance.vlozNaslednika(1);
    }

    /**
     * Test of zpristupniAktualni method, of class AbstrDoubleList.
     */
    @Test
    public void testZpristupniAktualni() throws NoSuchElementException, KolekceException {
        System.out.println("zpristupniAktualni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.zpristupniPrvni();
        assertEquals(1, instance.zpristupniAktualni());
    }

    @Test(expected = KolekceException.class)
    public void testZpristupniAktualniException_1() throws NoSuchElementException, KolekceException, KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.zpristupniAktualni();
    }

    @Test(expected = NoSuchElementException.class)
    public void testZpristupniAktualniException_2() throws NoSuchElementException, KolekceException, KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.zpristupniAktualni();
    }

    /**
     * Test of zpristupniPrvni method, of class AbstrDoubleList.
     */
    @Test
    public void testZpristupniPrvni() throws NoSuchElementException, KolekceException {
        System.out.println("zpristupniPrvni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        assertEquals(1, instance.zpristupniPrvni());
        assertEquals(1, instance.zpristupniAktualni());
    }

    @Test(expected = NoSuchElementException.class)
    public void testZpristupniPrvniException() throws NoSuchElementException, KolekceException, KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.zpristupniPrvni();
    }

    /**
     * Test of zpristupniPosledni method, of class AbstrDoubleList.
     */
    @Test
    public void testZpristupniPosledni() throws NoSuchElementException, KolekceException {
        System.out.println("zpristupniPrvni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.vlozPrvni(2);
        assertEquals(1, instance.zpristupniPosledni());
        assertEquals(1, instance.zpristupniAktualni());
    }

    @Test(expected = NoSuchElementException.class)
    public void testZpristupniPosledniException() throws NoSuchElementException, KolekceException, KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.zpristupniPosledni();
    }

    /**
     * Test of zpristupniNaslednika method, of class AbstrDoubleList.
     */
    @Test
    public void testZpristupniNaslednika() throws NoSuchElementException, KolekceException {
        System.out.println("zpristupniNaslednika");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(1);
        instance.vlozPosledni(2);
        instance.vlozPosledni(3);
        instance.zpristupniPrvni();
        assertEquals(2, instance.zpristupniNaslednika());
        assertEquals(2, instance.zpristupniAktualni());
    }

    @Test(expected = KolekceException.class)
    public void textZpristupniNaslednikaException_1() throws NoSuchElementException, KolekceException, KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.zpristupniNaslednika();
    }

    @Test(expected = NoSuchElementException.class)
    public void testZpristupniNaslednikaException_2() throws NoSuchElementException, KolekceException, KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.zpristupniPrvni();
        instance.zpristupniNaslednika();
    }

    /**
     * Test of zpristupniPredchudce method, of class AbstrDoubleList.
     */
    @Test
    public void testZpristupniPredchudce() throws NoSuchElementException, KolekceException {
        System.out.println("zpristupniPredchudce");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(1);
        instance.vlozPosledni(2);
        instance.vlozPosledni(3);
        instance.zpristupniPosledni();
        assertEquals(2, instance.zpristupniPredchudce());
        assertEquals(2, instance.zpristupniAktualni());
    }

    @Test(expected = KolekceException.class)
    public void testZpristupniPredchudceException_1() throws NoSuchElementException, KolekceException, KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.zpristupniPredchudce();
    }

    @Test(expected = NoSuchElementException.class)
    public void testZpristupniPredchudceException_2() throws NoSuchElementException, KolekceException, KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.zpristupniPrvni();
        instance.zpristupniPredchudce();
    }

    /**
     * Test of odeberAktualni method, of class AbstrDoubleList.
     */
    @Test
    public void testOdeberAktualni_1() throws KolekceException {
        System.out.println("odeberAktualni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(3);
        instance.vlozPrvni(2);
        instance.vlozPrvni(1);
        instance.zpristupniPrvni();
        instance.zpristupniNaslednika();
        instance.zpristupniNaslednika();
        assertEquals(3, instance.odeberAktualni());
        assertEquals(2, instance.getMohutnost());
        assertEquals(1, instance.zpristupniAktualni());
    }

    @Test(expected = NoSuchElementException.class)
    public void testOdeberAktualniException_1() throws KolekceException {
        System.out.println("odeberAktualni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.zpristupniPrvni();
        assertEquals(1, instance.odeberAktualni());
        assertEquals(0, instance.getMohutnost());
        instance.zpristupniAktualni();
    }

    @Test(expected = KolekceException.class)
    public void testOdeberAktualniException_2() throws KolekceException {
        System.out.println("odeberAktualni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(1);
        instance.odeberAktualni();
    }

    /**
     * Test of odeberPrvni method, of class AbstrDoubleList.
     */
    @Test
    public void testOdeberPrvni() throws KolekceException {
        System.out.println("odeberPrvni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.vlozPrvni(2);
        instance.vlozPosledni(3);
        assertEquals(2, instance.odeberPrvni());
        assertEquals(2, instance.getMohutnost());
        assertEquals(1, instance.odeberPrvni());
        assertEquals(3, instance.odeberPrvni());
    }

    @Test
    public void testOdeberPrvni_1() throws KolekceException {
        System.out.println("odeberPrvni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.vlozPrvni(2);
        instance.vlozPosledni(3);
        instance.zpristupniPrvni();
        assertEquals(2, instance.odeberPrvni());
        assertEquals(1, instance.zpristupniAktualni());

    }

    @Test(expected = KolekceException.class)
    public void testOdeberPrvniException() throws KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.odeberPrvni();

    }

    /**
     * Test of odeberPosledni method, of class AbstrDoubleList.
     */
    @Test
    public void testOdeberPosledni() throws Exception {
        System.out.println("odeberPosledni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.vlozPosledni(2);
        instance.vlozPrvni(3);
        assertEquals(2, instance.odeberPosledni());
        assertEquals(2, instance.getMohutnost());
        assertEquals(1, instance.odeberPosledni());
        assertEquals(3, instance.odeberPosledni());
    }

    @Test
    public void testOdeberPosledni_1() throws Exception {
        System.out.println("odeberPosledni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.vlozPosledni(2);
        instance.vlozPrvni(3);
        instance.zpristupniPosledni();
        assertEquals(2, instance.odeberPosledni());
        assertEquals(1, instance.zpristupniAktualni());
    }

    @Test(expected = KolekceException.class)
    public void testOdeberPosledniException() throws KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.odeberPosledni();
    }

    /**
     * Test of odeberNaslednika method, of class AbstrDoubleList.
     */
    @Test
    public void testOdeberNaslednika() throws Exception {
        System.out.println("odeberNaslednika");
        AbstrDoubleList instance = new AbstrDoubleList();
    }

    @Test(expected = NoSuchElementException.class)
    public void testOdeberNaslednikaException_1() throws KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.odeberNaslednika();
    }

    @Test(expected = KolekceException.class)
    public void testOdeberNaslednikaException_2() throws KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.odeberNaslednika();
    }

    @Test(expected = NoSuchElementException.class)
    public void testOdeberNaslednikaeException_3() throws KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.zpristupniPrvni();
        instance.odeberNaslednika();
    }

    /**
     * Test of odeberPredchudce method, of class AbstrDoubleList.
     */
    @Test
    public void testOdeberPredchudce() throws Exception {
        System.out.println("odeberPredchudce");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(3);
        instance.vlozPrvni(2);
        instance.vlozPrvni(1);
        instance.zpristupniPosledni();
        assertEquals(2, instance.odeberPredchudce());
        assertEquals(1, instance.zpristupniPrvni());
        assertEquals(3, instance.zpristupniPosledni());

    }

    @Test(expected = NoSuchElementException.class)
    public void testOdeberPredchudceException_1() throws KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.odeberPredchudce();
    }

    @Test(expected = KolekceException.class)
    public void testOdeberPredchudceException_2() throws KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.odeberPredchudce();
    }

    @Test(expected = NoSuchElementException.class)
    public void testOdeberPredchudceException_3() throws KolekceException {
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(1);
        instance.zpristupniPrvni();
        instance.odeberPredchudce();
    }

    /**
     * Test of iterator method, of class AbstrDoubleList.
     */
    @Test
    public void testIterator_1() {
        System.out.println("iterator");
        AbstrDoubleList instance = new AbstrDoubleList();
        for (int i = 0; i < 10; i++) {
            instance.vlozPosledni(i);
        }

        int i = 0;
        for (Object object : instance) {
            assertEquals(i++, object);
        }
    }

    @Test
    public void testIterator_2() throws NoSuchElementException, KolekceException {
        System.out.println("iterator");
        AbstrDoubleList instance = new AbstrDoubleList();
        for (int i = 1; i < 11; i++) {
            instance.vlozPosledni(i);
        }

        Iterator iterator = instance.iterator();
        for (int i = 1; i < 11; i++) {
            iterator.next();
            iterator.remove();            
        }
        
        assertEquals(true, instance.jePrazdny());
        
    }
    
    @Test
    public void testIterator_prvniZeTri() throws NoSuchElementException, KolekceException {
        System.out.println("iterator");
        
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(1);
        instance.vlozPosledni(2);
        instance.vlozPosledni(3);
        
        Iterator iterator = instance.iterator();
        iterator.next();
        iterator.remove();
        
        assertEquals(2, instance.zpristupniPrvni());
        assertEquals(3, instance.zpristupniNaslednika());
        assertEquals(3, instance.zpristupniPosledni());
    }
    
    @Test
    public void iterator_druhyZeTri() throws NoSuchElementException, KolekceException {
        System.out.println("iterator");
        
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(1);
        instance.vlozPosledni(2);
        instance.vlozPosledni(3);
        
        Iterator iterator = instance.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        
        assertEquals(1, instance.zpristupniPrvni());
        assertEquals(3, instance.zpristupniNaslednika());
        assertEquals(3, instance.zpristupniPosledni());
    }
    
    @Test
    public void iterator_tretiZeTri() throws NoSuchElementException, KolekceException {
        System.out.println("iterator");
        
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(1);
        instance.vlozPosledni(2);
        instance.vlozPosledni(3);
        
        Iterator iterator = instance.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.remove();
        
        assertEquals(1, instance.zpristupniPrvni());
        assertEquals(2, instance.zpristupniPosledni());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void iterator_jedenZjednoho() throws NoSuchElementException, KolekceException {
        System.out.println("iterator");
        
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(1);
        
        Iterator iterator = instance.iterator();
        iterator.next();
        iterator.remove();
        instance.zpristupniPrvni();
    }

}
