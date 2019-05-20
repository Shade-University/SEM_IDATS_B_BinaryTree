package pamatky;

import java.util.Iterator;
import java.util.Random;
import kolekce.AbstrTable;
import kolekce.KolekceException;
import kolekce.eTypProhl;
import kolekce.interfaces.AbstrTableInterface;

/**
 *
 * @author Tomáš Vondra
 */
public final class ZamekGenerator {

    private ZamekGenerator() {
    }

    public static Iterator<Zamek> generujZamky(eTypKey klic, int pocet) throws KolekceException {
        AbstrTableInterface<String, Zamek> table = new AbstrTable<>();
        Integer id;
        String nazev;
        String gps;
        
        for (int i = 0; i < pocet; i++) {

            id = getRandomNumber(1000, 1);
            nazev = getRandomChar() + String.valueOf(getRandomNumber(10, 1));
            gps = createGPS();
            Zamek z = new Zamek(id,nazev,gps);
            String klicString = klic == eTypKey.GPS ? z.getGps() : z.getNazev();
            table.vloz(klicString, z);
        }

        return table.vytvorIterator(eTypProhl.SIRKA);
    }

    private static String createGPS() {
        StringBuilder sb = new StringBuilder();

        sb.append('N');
        sb.append(getRandomNumber(100, 10));
        sb.append('.');
        sb.append(getRandomNumber(100000, 1000));
        sb.append(' ');
        sb.append('E');
        sb.append(getRandomNumber(100, 10));
        sb.append('.');
        sb.append(getRandomNumber(100000, 1000));

        return sb.toString();
    }

    private static char getRandomChar() {
        return (char) (new Random().nextInt(26) + 'a');
    } //Pomocná na vrácení random znaku

    private static int getRandomNumber(int max, int min) {

        return new Random().nextInt((max - min) + 1) + min;
    } //Pomocná na vrácení random čísla v rozmezí

}
