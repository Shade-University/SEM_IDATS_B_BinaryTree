package pamatky;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import kolekce.KolekceException;
import kolekce.eTypProhl;

/**
 *
 * @author user
 */
public interface PamatkyInterface {
    public int importDatZTXT() throws IOException, FileNotFoundException, KolekceException;
    public int vlozZamek(Zamek zamek) throws KolekceException;
    public Zamek najdiZamek(String klic) throws KolekceException;
    public Zamek odeberZamek(String klic) throws KolekceException;
    public void zrus();
    public void prebuduj();
    public void nastavKlic(eTypKey typ);
    public Iterator vytvorIterator(eTypProhl typ);
}
