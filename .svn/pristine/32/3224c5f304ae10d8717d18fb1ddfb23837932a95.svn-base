package kolekce;

import kolekce.interfaces.DoubleList;
import java.util.Iterator;
import kolekce.interfaces.AbstrLIFOInterface;

/**
 *
 * @author Tomáš Vondra
 * @param <T>
 * Stack vytvořený ze SEM A
 */
public class AbstrLIFO<T> implements AbstrLIFOInterface<T> {

    private final DoubleList<T> seznam;

    public AbstrLIFO() throws KolekceException {
        this.seznam = new AbstrDoubleList<>();
    }

    @Override
    public void zrus() {
        seznam.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return seznam.jePrazdny();
    }

    @Override
    public void vloz(T data) throws KolekceException {
        seznam.vlozPosledni(data);
    }

    @Override
    public T odeber() throws KolekceException {
        return seznam.odeberPosledni();
    }

    @Override
    public Iterator<T> iterator() {
        return seznam.iterator();
    }

}
