package kolekce;

import kolekce.interfaces.AbstrTableInterface;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import kolekce.interfaces.AbstrFIFOInterface;
import kolekce.interfaces.AbstrLIFOInterface;

/**
 *
 * @author Tomáš Vondra
 * @param <K>
 * @param <V>
 */
public class AbstrTable<K extends Comparable<K>, V> implements AbstrTableInterface<K, V> {

    private BinaryTreeNode root;
    private int mohutnost;

    @Override
    public int getMohutnost() {
        return mohutnost;
    }

    private class BinaryTreeNode {

        private K key;
        private V value;
        private BinaryTreeNode left;
        private BinaryTreeNode right;

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public BinaryTreeNode getLeft() {
            return left;
        }

        public void setLeft(BinaryTreeNode left) {
            this.left = left;
        }

        public BinaryTreeNode getRight() {
            return right;
        }

        public void setRight(BinaryTreeNode right) {
            this.right = right;
        }

        public BinaryTreeNode() {
        }

        ;
        public BinaryTreeNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public AbstrTable() {
        this.root = null;
        mohutnost = 0;
    }

    @Override
    public void zrus() {
        root = null;
        mohutnost = 0;
    }

    @Override
    public boolean jePrazdny() {
        return root == null;
    }

    @Override
    public V najdi(K key) throws KolekceException {
        if (key == null) {
            throw new KolekceException("Nulový klíč");
        }
        return najdiRecursive(root, key);
    }

    @Override
    public void vloz(K key, V value) throws KolekceException {
        if (key == null || value == null) {
            throw new KolekceException("Nulové parametry");
        }
        if (najdi(key) != null) {
            throw new KolekceException("Klíč už existuje");    
        }
        root = vlozRecursive(root, key, value);
        mohutnost++;
    }

    @Override
    public V odeber(K key) throws KolekceException {
        if (key == null) {
            throw new KolekceException("Nulový klíč");
        }
        BinaryTreeNode output = new BinaryTreeNode();
        root = odeberRecursive(root, key, output);
        mohutnost--;
        return output.value;
    }

    @Override
    public Iterator vytvorIterator(eTypProhl typ) {
        switch (typ) {
            case SIRKA:
                return new BreathIterator();
            case HLOUBKA:
                return new DepthIterator();
            default:
                throw new AssertionError();
        }
    }

    private BinaryTreeNode odeberRecursive(BinaryTreeNode node, K key, BinaryTreeNode output) {

        if (node == null) {
            return node;
        } //Strom je prázdný nebo nenalezeno

        if (key.compareTo(node.key) < 0) {
            node.left = odeberRecursive(node.left, key, output);
        } else if (key.compareTo(node.key) > 0) {
            node.right = odeberRecursive(node.right, key, output);
        } //Prozkoumej strom doleva/doprava podle hodnoty
        else { //Pokud je klíč stejný, nalezen node
            if (output.getValue() == null) { //Pokud ještě nebylo nasetováno, nastav. Pokud bylo, už znova nesetuj
                output.setKey(node.key);
                output.setValue(node.value);//Nasetuj hodnotu wraperu
            }

            if (node.left != null && node.right != null) {
                BinaryTreeNode minRight = najdiNejmensiHodnotu(node.right);
                node.value = minRight.value;
                node.key = minRight.key;
                node.right = odeberRecursive(node.right, minRight.key, output);
                //Dva potomci
            } else if (node.left != null) {
                node = node.left;
            } else if (node.right != null) {
                node = node.right;
            } //Pokud pouze jeden potomek
            else {
                node = null;
            } //Žádný potomek

        }
        return node;
    }

    private BinaryTreeNode najdiNejmensiHodnotu(BinaryTreeNode root) {
        return root.left == null ? root : najdiNejmensiHodnotu(root.left);

    }

    private BinaryTreeNode vlozRecursive(BinaryTreeNode node, K key, V value) {
        if (node == null) {
            node = new BinaryTreeNode(key, value); //nalezen prázdný list
        } else if (key.compareTo(node.key) < 0) { //pokud je menší, jdi doleva
            node.left = vlozRecursive(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) { //pokud větší, směruj doprava
            node.right = vlozRecursive(node.right, key, value);
        }

        return node;
    }

    private V najdiRecursive(BinaryTreeNode node, K key) throws KolekceException {
        if (node == null) {
            return null;
        }

        if (key.equals(node.getKey())) {
            return node.getValue();
        } //nalezen

        return key.compareTo(node.getKey()) < 0 //Směruj doprava nebo do leva
                ? najdiRecursive(node.left, key)
                : najdiRecursive(node.right, key);
    }

    private class DepthIterator implements Iterator<V> {

        //InOrder
        private AbstrLIFOInterface<BinaryTreeNode> stack;
        private BinaryTreeNode current;

        public DepthIterator() {
            try {
                stack = new AbstrLIFO();
                vlozVseZLeva(root);
            } catch (KolekceException ex) {
                Logger.getLogger(AbstrTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.jePrazdny();
        }

        @Override
        public V next() {
            try {
                current = stack.odeber();
                vlozVseZLeva(current.right);
            } catch (KolekceException ex) {
                Logger.getLogger(AbstrTable.class.getName()).log(Level.SEVERE, null, ex);
            }

            return current.value;

        }

        @Override
        public void remove() {
            try {
                odeber(current.key);
            } catch (KolekceException ex) {
                Logger.getLogger(AbstrTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Vloží do stacku všechny levé prvky
        private void vlozVseZLeva(BinaryTreeNode node) {
            if (node != null) {
                try {
                    stack.vloz(node);
                    vlozVseZLeva(node.left);
                } catch (KolekceException ex) {
                    Logger.getLogger(AbstrTable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private class BreathIterator implements Iterator<V> {

        private AbstrFIFOInterface<BinaryTreeNode> fronta = null;
        private BinaryTreeNode current;

        public BreathIterator() {

            try {
                fronta = new AbstrFIFO<>();
                if (root != null) {
                    fronta.vloz(root);
                }
            } catch (KolekceException ex) {
                Logger.getLogger(AbstrTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public boolean hasNext() {
            return !fronta.jePrazdny();
        }

        @Override
        public V next() {
            V result = null;
            try {
                current = fronta.odeber();
                result = current.getValue();

                if (current.left != null) {
                    fronta.vloz(current.left);
                }
                if (current.right != null) {
                    fronta.vloz(current.right);
                }

            } catch (KolekceException ex) {
                Logger.getLogger(AbstrTable.class.getName()).log(Level.SEVERE, null, ex);
            }

            return result;
        }

        @Override
        public void remove() {
            try {
                odeber(current.key);
            } catch (KolekceException ex) {
                Logger.getLogger(AbstrTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
