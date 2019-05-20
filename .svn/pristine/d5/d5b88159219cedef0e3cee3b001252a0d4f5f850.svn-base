package pamatky;

import IO.FileReaderImpl;
import IO.RegexMatcher;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import kolekce.AbstrTable;
import kolekce.KolekceException;
import kolekce.eTypProhl;
import kolekce.interfaces.AbstrTableInterface;

/**
 *
 * @author Tomáš Vondra
 */
public class Pamatky implements PamatkyInterface {

    private static final String SOUBOR = "data.csv";
    private static final String IDPATTERN = "(\\d*?);"; //=> Vezme všechny čísla od začátku po ;
    private static final String GPSPATTERN = "[N]\\d(.*?)[F]";  //=> Vyhledá N s nějakým číslem. Poté vezme všechno mezi tím a písmenem F.
    private static final String NAZEVPATTERN = "2000(.*?)[\\^]"; //=> Vyhlledá všechno mezi stringem 2000 a ^. 

    private final AbstrTableInterface<String, Zamek> table;
    private eTypKey typKlice;

    public Pamatky() {
        table = new AbstrTable<>();
        typKlice = eTypKey.NAZEV; //Defaultní klíč je NAZEV. POZOR - způsobuje duplicitu u importDatZTXT
    }

    @Override
    public int importDatZTXT() throws FileNotFoundException, IOException {
        FileReaderImpl reader = new FileReaderImpl(SOUBOR);
        String id;
        String gps;
        String nazev;

        String line;
        while ((line = reader.readLine()) != null) {
            id = RegexMatcher.getMatch(line, IDPATTERN, 1);
            gps = RegexMatcher.getMatch(line, GPSPATTERN, 0).replaceFirst(".$", "").trim();
            //Abychom měli hezkou gps i s N, musíme vzít celej ten string. A z celého toho stringu odstranit F
            nazev = RegexMatcher.getMatch(line, NAZEVPATTERN, 1).trim();
            //Trim odstraní přebytečné mezery

            Zamek zamek = new Zamek(Integer.parseInt(id), nazev, gps);
            try {
                vlozZamek(zamek);
            } catch (KolekceException ex) {
                //Logger.getLogger(Pamatky.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Duplikační klíč v souboru" + " Zámek: " + zamek.getNazev());
            }
        }

        return table.getMohutnost(); //Zde možná vracet -1 nebo 1 podle toho, jestli se vyvolala exception. Ale tu propisuju dál a handluju v GUI
    }

    @Override
    public int vlozZamek(Zamek zamek) throws KolekceException {
        String key = typKlice == eTypKey.GPS ? zamek.getGps() : zamek.getNazev();
        table.vloz(key, zamek);

        return table.getMohutnost();
    }

    @Override
    public Zamek najdiZamek(String klic) throws KolekceException {
        return table.najdi(klic);
    }

    @Override
    public Zamek odeberZamek(String klic) throws KolekceException {
        return table.odeber(klic);
    }

    @Override
    public void zrus() {
        table.zrus();
    }

    @Override
    public void prebuduj() {
        int mohutnost = table.getMohutnost();
        Zamek[] pole = new Zamek[mohutnost];
        Iterator<Zamek> iterator = vytvorIterator(eTypProhl.HLOUBKA);

        int i = 0;
        while (iterator.hasNext()) { //Díky InOrder nám to vrátí seřazené prvky
            pole[i++] = iterator.next();
        }

        table.zrus();

        pole = prebudujArray(pole, mohutnost);
        for (Zamek z : pole) {
            try {
                vlozZamek(z);
            } catch (KolekceException ex) {
                Logger.getLogger(Pamatky.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void nastavKlic(eTypKey typ) {
        //TODO Ošetření null
        typKlice = typ;
        prebuduj();
    }

    @Override
    public Iterator vytvorIterator(eTypProhl typ) {
        return table.vytvorIterator(typ);
    }

    private Zamek[] prebudujArray(Zamek[] pole, int mohutnost) {
        prebudujArray(pole, 0, mohutnost - 1);
        return pole;
    }

    private void prebudujArray(Zamek[] pole, int start, int end) {
        if (start > end) {
            return;
        }

        int mid = (start + end) / 2;

        Zamek z = pole[start];
        pole[start] = pole[mid];
        pole[mid] = z;

        prebudujArray(pole, start + 1, mid - 1);
        prebudujArray(pole, mid + 1, end);

    }
}
