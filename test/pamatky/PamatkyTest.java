package pamatky;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import kolekce.KolekceException;
import kolekce.eTypProhl;
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
public class PamatkyTest {

    private static PamatkyInterface pamatky;

    public PamatkyTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pamatky = new Pamatky();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of importDatZTXT method, of class Pamatky.
     *
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws kolekce.KolekceException
     */
    @Test
    public void testImportDatZTXT() throws IOException, FileNotFoundException, KolekceException {
        pamatky.importDatZTXT();
        Zamek z = pamatky.najdiZamek("DRITEN");
        assertNotNull(z);
    }

    /**
     * Test of vlozZamek method, of class Pamatky.
     *
     * @throws kolekce.KolekceException
     */
    @Test
    public void testVlozZamek() throws KolekceException {
        Zamek[] array = new Zamek[]{
            new Zamek(1, "1", "1GPS"),
            new Zamek(2, "2", "2GPS"),
            new Zamek(3, "3", "3GPS")
        };

        for (Zamek z : array) {
            pamatky.vlozZamek(z);
        }

        for (Zamek z : array) {
            assertEquals(z, pamatky.odeberZamek(z.getNazev()));
        }
    }

    @Test(expected = KolekceException.class)
    public void testVlozZamek_02() throws KolekceException {
        Zamek z = new Zamek(1, "1", "11");

        pamatky.vlozZamek(z);
        assertEquals(z, pamatky.najdiZamek("1"));
        pamatky.vlozZamek(z);

        fail();
    }

    public void testVlozZamek_03() throws KolekceException {
        Zamek z = new Zamek(1, "1", "11");

        pamatky.vlozZamek(z);
        assertEquals(z, pamatky.odeberZamek("1"));

        pamatky.vlozZamek(z);
    }

    /**
     * Test of najdiZamek method, of class Pamatky.
     *
     * @throws kolekce.KolekceException
     */
    @Test
    public void testNajdiZamek() throws KolekceException {

        Zamek z = new Zamek(1, "1", "1");
        Zamek z1 = new Zamek(2, "2", "2");
        Zamek z2 = new Zamek(3, "3", "3");

        pamatky.vlozZamek(z);
        pamatky.vlozZamek(z1);
        pamatky.vlozZamek(z2);

        assertEquals(z1, pamatky.najdiZamek("2"));
        assertEquals(z, pamatky.najdiZamek("1"));
        assertEquals(z2, pamatky.najdiZamek("3"));

        assertNull(pamatky.najdiZamek("NONE"));
    }

    /**
     * Test of odeberZamek method, of class Pamatky.
     *
     * @throws kolekce.KolekceException
     */
    @Test
    public void testOdeberZamek() throws KolekceException {

        Zamek z = new Zamek(1, "1", "1");
        Zamek z1 = new Zamek(2, "2", "2");
        Zamek z2 = new Zamek(3, "3", "3");

        pamatky.vlozZamek(z);
        pamatky.vlozZamek(z1);
        pamatky.vlozZamek(z2);

        assertNull(pamatky.odeberZamek("NULL"));
        assertEquals(z, pamatky.odeberZamek("1"));
        assertNull(pamatky.odeberZamek("1"));
        assertEquals(z1, pamatky.odeberZamek("2"));

        pamatky.zrus();
        assertNull(pamatky.odeberZamek("3"));

    }

    /**
     * Test of zrus method, of class Pamatky.
     *
     * @throws kolekce.KolekceException
     */
    @Test
    public void testZrus() throws KolekceException {
        Zamek z = new Zamek(1, "1", "1");
        Zamek z1 = new Zamek(2, "2", "2");
        Zamek z2 = new Zamek(3, "3", "3");

        pamatky.vlozZamek(z);
        pamatky.vlozZamek(z1);
        pamatky.vlozZamek(z2);

        assertEquals(z, pamatky.najdiZamek("1"));
        pamatky.zrus();

        assertNull(pamatky.najdiZamek("1"));
    }

    /**
     * Test of prebuduj method, of class Pamatky.
     *
     * @throws kolekce.KolekceException
     */
    @Test
    public void testPrebuduj_3prvky() throws KolekceException {
        //Case 3 prvky

        Zamek z1 = new Zamek(1, "1", "1");
        Zamek z2 = new Zamek(2, "2", "2");
        Zamek z3 = new Zamek(3, "3", "3");

        pamatky.vlozZamek(z1);
        pamatky.vlozZamek(z2);
        pamatky.vlozZamek(z3);

        pamatky.prebuduj();

        Iterator iterator = pamatky.vytvorIterator(eTypProhl.SIRKA);

        assertEquals(z2, iterator.next());
        assertEquals(z1, iterator.next());
        assertEquals(z3, iterator.next());
    }

    @Test
    public void testPrebuduj_4prvky() throws KolekceException {
        //case 4 prvky
        Zamek z1 = new Zamek(1, "1", "1");
        Zamek z2 = new Zamek(2, "2", "2");
        Zamek z3 = new Zamek(3, "3", "3");
        Zamek z4 = new Zamek(4, "4", "4");

        pamatky.vlozZamek(z1);
        pamatky.vlozZamek(z2);
        pamatky.vlozZamek(z3);
        pamatky.vlozZamek(z4); //Klidně i opačné pořadí

        pamatky.prebuduj();

        Iterator iterator = pamatky.vytvorIterator(eTypProhl.SIRKA);

        assertEquals(z2, iterator.next());
        assertEquals(z1, iterator.next());
        assertEquals(z3, iterator.next());
        assertEquals(z4, iterator.next());
    }

    @Test
    public void testPrebuduj_4prvkyZnova() throws KolekceException {
        //case 4 prvky. Znova
        Zamek z1 = new Zamek(9, "9", "9");
        Zamek z2 = new Zamek(8, "8", "8");
        Zamek z3 = new Zamek(7, "7", "7");
        Zamek z4 = new Zamek(6, "6", "6");
        Zamek z5 = new Zamek(5, "5", "5");

        pamatky.vlozZamek(z1);
        pamatky.vlozZamek(z2);
        pamatky.vlozZamek(z3);
        pamatky.vlozZamek(z4);
        pamatky.vlozZamek(z5);

        pamatky.prebuduj();

        Iterator iterator = pamatky.vytvorIterator(eTypProhl.SIRKA);

        assertEquals(z3, iterator.next());
        assertEquals(z4, iterator.next());
        assertEquals(z2, iterator.next());
        assertEquals(z5, iterator.next());
        assertEquals(z1, iterator.next());

    }

    @Test
    public void testPrebuduj_5prvku() throws KolekceException {

        Zamek z1 = new Zamek(1, "1", "1");
        Zamek z2 = new Zamek(2, "2", "2");
        Zamek z3 = new Zamek(3, "3", "3");
        Zamek z4 = new Zamek(4, "4", "4");
        Zamek z5 = new Zamek(5, "5", "5");
        
        pamatky.vlozZamek(z2);
        pamatky.vlozZamek(z1);
        pamatky.vlozZamek(z3);
        pamatky.vlozZamek(z4);
        pamatky.vlozZamek(z5);
        
        pamatky.prebuduj();
        
        Iterator iterator = pamatky.vytvorIterator(eTypProhl.SIRKA);
        
        assertEquals(z3, iterator.next());
        assertEquals(z2, iterator.next());
        assertEquals(z4, iterator.next());
        assertEquals(z1, iterator.next());
        assertEquals(z5, iterator.next());
    }

    @Test
    public void testPrebuduj_7prvku() throws KolekceException {
        //case 7 prvků

        Zamek z1 = new Zamek(1, "1", "1");
        Zamek z2 = new Zamek(2, "2", "2");
        Zamek z3 = new Zamek(3, "3", "3");
        Zamek z4 = new Zamek(4, "4", "4");
        Zamek z5 = new Zamek(5, "5", "5");
        Zamek z6 = new Zamek(6, "6", "6");
        Zamek z7 = new Zamek(7, "7", "7");

        pamatky.vlozZamek(z4);
        pamatky.vlozZamek(z3);
        pamatky.vlozZamek(z5);
        pamatky.vlozZamek(z2);
        pamatky.vlozZamek(z6);
        pamatky.vlozZamek(z1);
        pamatky.vlozZamek(z7);

        pamatky.prebuduj();

        Iterator iterator = pamatky.vytvorIterator(eTypProhl.SIRKA);

        assertEquals(z4, iterator.next());
        assertEquals(z2, iterator.next());
        assertEquals(z6, iterator.next());
        assertEquals(z1, iterator.next());
        assertEquals(z3, iterator.next());
        assertEquals(z5, iterator.next());
        assertEquals(z7, iterator.next());
    }

    @Test
    public void testPrebuduj_6prvku() throws KolekceException {
        //case 6 prvku

        Zamek z1 = new Zamek(1, "1", "1");
        Zamek z2 = new Zamek(2, "2", "2");
        Zamek z3 = new Zamek(3, "3", "3");
        Zamek z4 = new Zamek(4, "4", "4");
        Zamek z5 = new Zamek(5, "5", "5");
        Zamek z6 = new Zamek(6, "6", "6");

        pamatky.vlozZamek(z4);
        pamatky.vlozZamek(z3);
        pamatky.vlozZamek(z5);
        pamatky.vlozZamek(z2);
        pamatky.vlozZamek(z6);
        pamatky.vlozZamek(z1);

        pamatky.prebuduj();

        Iterator iterator = pamatky.vytvorIterator(eTypProhl.SIRKA);

        assertEquals(z3, iterator.next());
        assertEquals(z2, iterator.next());
        assertEquals(z5, iterator.next());
        assertEquals(z1, iterator.next());
        assertEquals(z4, iterator.next());
        assertEquals(z6, iterator.next());

    }

    /**
     * Test of nastavKlic method, of class Pamatky.
     * @throws kolekce.KolekceException
     */
    @Test
    public void testNastavKlic() throws KolekceException {
        
        Zamek z1 = new Zamek(1, "1jmeno", "1gps");
        Zamek z2 = new Zamek(2, "2jmeno", "2gps");
        Zamek z3 = new Zamek(3, "3jmeno", "3gps");
        
        pamatky.vlozZamek(z1);
        pamatky.vlozZamek(z2);
        pamatky.vlozZamek(z3);
        
        assertEquals(z1, pamatky.najdiZamek("1jmeno"));
        assertNull(pamatky.najdiZamek("1gps"));
        
        pamatky.nastavKlic(eTypKey.GPS);        
        assertNull(pamatky.najdiZamek("1jmeno"));
        assertEquals(z1, pamatky.najdiZamek("1gps"));
        
        pamatky.nastavKlic(eTypKey.NAZEV);        
        assertEquals(z1, pamatky.najdiZamek("1jmeno"));
        assertNull(pamatky.najdiZamek("1gps"));
        
        pamatky.nastavKlic(eTypKey.GPS);        
        assertNull(pamatky.najdiZamek("1jmeno"));
        assertEquals(z1, pamatky.najdiZamek("1gps"));
        
        pamatky.nastavKlic(eTypKey.GPS);       
        assertNull(pamatky.najdiZamek("1jmeno"));
        assertEquals(z1, pamatky.najdiZamek("1gps"));
        
    }

    /**
     * Test of vytvorIterator method, of class Pamatky.
     *
     * @throws kolekce.KolekceException
     */
    @Test
    public void testVytvorIterator() throws KolekceException {
        Zamek z = new Zamek(2, "2", "2");
        Zamek z1 = new Zamek(1, "1", "1");
        Zamek z2 = new Zamek(3, "3", "3");

        pamatky.vlozZamek(z);
        pamatky.vlozZamek(z1);
        pamatky.vlozZamek(z2);

        Iterator iterator = pamatky.vytvorIterator(eTypProhl.SIRKA);

        assertEquals(z, iterator.next());
        assertEquals(z1, iterator.next());
        assertEquals(z2, iterator.next());

        iterator = pamatky.vytvorIterator(eTypProhl.HLOUBKA);

        assertEquals(z1, iterator.next());
        assertEquals(z, iterator.next());
        assertEquals(z2, iterator.next());

    }

}
