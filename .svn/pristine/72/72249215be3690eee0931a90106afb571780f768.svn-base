package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Tomáš Vondra
 * @param <E>
 */
public class AbstrDoubleList<E> implements DoubleList<E> {

    private Prvek prvni;
    private Prvek posledni;
    private Prvek aktualni;

    private int velikost;

    private class Prvek {

        Prvek dalsi;
        Prvek predchozi;
        E data;

        public Prvek(E data) {
            this(null, null, data);
        }

        public Prvek(Prvek dalsi, Prvek predchozi, E data) {
            this.dalsi = dalsi;
            this.data = data;
            this.predchozi = predchozi;
        }
    }

    @Override
    public void zrus() {
        prvni = null;
        posledni = null;
        aktualni = null;
        velikost = 0;
    }

    @Override
    public boolean jePrazdny() {
        return velikost == 0;
        //return prvni == null;
    }

    @Override
    public int getMohutnost() {
        return velikost;
    }

    @Override
    public void vlozPrvni(E data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("Null data");
        }

        Prvek prvek = new Prvek(prvni, null, data);
        if (prvni != null) {
            prvni.predchozi = prvek;
        }
        prvni = prvek;
        if (posledni == null) { //Pokud je úrvní jediný prvek, nastav ho i jako poslední
            posledni = prvni;
        }
        velikost++;
    }

    @Override
    public void vlozPosledni(E data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("Null data");
        }

        Prvek prvek = new Prvek(null, posledni, data);
        if (posledni != null) {
            posledni.dalsi = prvek;
        }
        posledni = prvek;
        if (prvni == null) { //Pokud je posledni jediný prvek, nastav ho i jako první
            prvni = posledni;
        }
        velikost++;
    }

    @Override
    public void vlozNaslednika(E data) throws NullPointerException, KolekceException {
        if (aktualni == null) {
            throw new KolekceException("Nenastaven aktualni prvek");
        }
        if (data == null) {
            throw new NullPointerException("Null data");
        }

        Prvek prvek = new Prvek(aktualni.dalsi, aktualni, data);
        if (aktualni != posledni) {
            aktualni.dalsi.predchozi = prvek;
        } else { //Pokud je aktuální prvek poslední
            posledni = prvek;
        }

        aktualni.dalsi = prvek;
        velikost++;
    }

    @Override
    public void vlozPredchudce(E data) throws KolekceException, NullPointerException {
        if (aktualni == null) {
            throw new KolekceException("Nenastaven aktualni prvek");
        }
        if (data == null) {
            throw new NullPointerException("Null data");
        }

        Prvek prvek = new Prvek(aktualni, aktualni.predchozi, data);
        if (aktualni != prvni) { //aktualni.predchozi != null
            aktualni.predchozi.dalsi = prvek;
        } else { //Pokud je aktuální prvek první
            prvni = prvek;
        }
        aktualni.predchozi = prvek;
        velikost++;
    }

    @Override
    public E zpristupniAktualni() throws NoSuchElementException, KolekceException {
        if (velikost == 0) {
            throw new NoSuchElementException();
        }
        if (aktualni == null) {
            throw new KolekceException("Nenastaven aktuální prvek");
        }
        return aktualni.data;
    }

    @Override
    public E zpristupniPrvni() throws NoSuchElementException {
        if (prvni == null) {
            throw new NoSuchElementException();
        }
        aktualni = prvni;
        return aktualni.data;
    }

    @Override
    public E zpristupniPosledni() throws NoSuchElementException {
        if (posledni == null) {
            throw new NoSuchElementException();
        }
        aktualni = posledni;
        return aktualni.data;
    }

    @Override
    public E zpristupniNaslednika() throws NoSuchElementException, KolekceException {
        if (aktualni == null) {
            throw new KolekceException("Nenastaven aktuální prvek");
        }
        if (aktualni.dalsi == null) {
            throw new NoSuchElementException();
        }
        aktualni = aktualni.dalsi;
        return aktualni.data;
    }

    @Override
    public E zpristupniPredchudce() throws NoSuchElementException, KolekceException {
        if (aktualni == null) {
            throw new KolekceException("Nenastaven aktuální prvek");
        }
        if (aktualni.predchozi == null) {
            throw new NoSuchElementException();
        }
        aktualni = aktualni.predchozi;
        return aktualni.data;
    }

    @Override
    public E odeberAktualni() throws KolekceException, NoSuchElementException {
        if (velikost == 0) {
            throw new NoSuchElementException();
        }
        if (aktualni == null) {
            throw new KolekceException("Nenastaven aktuální prvek");
        }

        Prvek prvek = aktualni;
        if(aktualni.dalsi != null){
            aktualni.dalsi.predchozi = aktualni.predchozi;
        }
        if(aktualni.predchozi != null){
            aktualni.predchozi.dalsi = aktualni.dalsi;
        } //Spojení stran
        
        if (aktualni == prvni) { //Pokud byl první aktuální, nastav první nový
            prvni = aktualni.dalsi;
        }
        if(aktualni == posledni){ //Pokud byl poslední aktuální, nastav poslední nový
            posledni = aktualni.predchozi;
        }
        aktualni = prvni; //Nastav aktuální na první
        velikost--;
        return prvek.data;
    }

    @Override
    public E odeberPrvni() throws KolekceException {
        if (velikost == 0) {
            throw new KolekceException("Prázdný seznam");
        }

        Prvek prvek = prvni;
        prvni = prvni.dalsi;
        if (prvni != null) {
            prvni.predchozi = null;
        }
        if (prvek == aktualni) {
            aktualni = prvni;
        } //Pokud byl aktuální, nastav nový aktuální
        velikost--;
        return prvek.data;
    }

    @Override
    public E odeberPosledni() throws KolekceException {
        if (velikost == 0) {
            throw new KolekceException("Prázdný seznam");
        }
        Prvek prvek = posledni;

        posledni = posledni.predchozi;
        if (posledni != null) {
            posledni.dalsi = null;
        }
        if (prvek == aktualni) {
            aktualni = posledni;
        } //Pokud byl aktuální, nastav nový aktuální
        velikost--;
        return prvek.data;
    }

    @Override
    public E odeberNaslednika() throws KolekceException, NoSuchElementException {
        if (velikost == 0) {
            throw new NoSuchElementException();
        }
        if (aktualni == null) {
            throw new KolekceException("Není nastaven aktuální prvek");
        }
        if (aktualni.dalsi == null) {
            throw new NoSuchElementException();
        }
        Prvek prvek = aktualni.dalsi;
        aktualni.dalsi = prvek.dalsi;
        if (aktualni.dalsi != null) {
            aktualni.predchozi = aktualni;
        }
        velikost--;
        return prvek.data;
    }

    @Override
    public E odeberPredchudce() throws KolekceException, NoSuchElementException {
        if (velikost == 0) {
            throw new NoSuchElementException();
        }
        if (aktualni == null) {
            throw new KolekceException("Není nastaven aktuální prvek");
        }
        if (aktualni.predchozi == null) {
            throw new NoSuchElementException();
        }
        Prvek prvek = aktualni.predchozi;
        aktualni.predchozi = prvek.predchozi;
        if (aktualni.predchozi != null) {
            aktualni.dalsi = aktualni;
        }
        velikost--;
        return prvek.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator() {

            private Prvek prvek = null;
            private Prvek predchozi = null; //Stačilo by možná prvek.predchozi

            @Override
            public boolean hasNext() {
                if (prvek == null) {
                    return prvni != null;
                }
                return prvek.dalsi != null;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (prvek == null) {
                    prvek = prvni;
                } else {
                    predchozi = prvek;
                    prvek = prvek.dalsi;
                }
                //aktualni = prvek
                return prvek.data;

            }

            @Override
            public void remove() {
                if (prvek != null) {
                    if (prvek == prvni && prvek == posledni) {
                        prvni = null;
                        posledni = null;
                        prvek = null;
                    } else if (prvek == prvni) {
                        prvni = prvek.dalsi;
                        if (prvni != null) {
                            prvni.predchozi = null;
                        } //Duplicita, ale musel bych handlovat vyjímku
                        prvek = null;
                    } else if (prvek == posledni) {
                        posledni = prvek.predchozi;
                        if (posledni != null) {
                            posledni.dalsi = null;
                        }
                        prvek = posledni;
                    } else {
                        predchozi.dalsi = prvek.dalsi;
                        if (predchozi.dalsi != null) {
                            prvek.dalsi.predchozi = predchozi;
                        }
                    }
                    velikost--;

                }

            }
        };
    }

}
