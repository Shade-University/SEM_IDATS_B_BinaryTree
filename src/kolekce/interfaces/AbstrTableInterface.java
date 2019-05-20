package kolekce.interfaces;

import java.util.Iterator;
import kolekce.KolekceException;
import kolekce.eTypProhl;

/**
 *
 * @author user
 * @param <K>
 * @param <V>
 */
public interface AbstrTableInterface<K extends Comparable<K>, V> {
    public int getMohutnost();
    
    public void zrus();
    public boolean jePrazdny();
    
    public V najdi(K key) throws KolekceException;
    public void vloz(K key, V value) throws KolekceException;
    public V odeber(K key) throws KolekceException;
    public Iterator vytvorIterator(eTypProhl typ);
    
    
    
}
