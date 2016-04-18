/**
 * The KVPair class
 */

/**
 * @author Jazmine Zurita and Jessica McCready
 * @version Feb 03 2016
 * @param <K> The key
 * @param <E> The value
 */
public class KVPair<K extends Comparable<K>, E> 
    implements Comparable<KVPair<K, E>> {
    /**
     * The key
     */
    K theKey;   

    /**
     * The value
     */
    E theVal;

    /**
     * The constructor for the KVPair
     * @param k the key
     * @param v the value
     */
    KVPair(K k, E v) {     
        theKey = k;     
        theVal = v;   
    }

    /**
     * Compare KVPairs  
     * @param it the element 
     * @return if they are same or not
     */
    public int compareTo(KVPair<K, E> it) {     
        return theKey.compareTo(it.key());   
    }

    /**
     * Compare against a key   
     * @param it the element
     * @return if they are the same or not 
     */
    public int compareTo(K it) {     
        return theKey.compareTo(it);   
    }

    /**
     * Finds the key
     * @return the key
     */
    public K key() {     
        return theKey;   
    }

    /**
     * Finds the value
     * @return the value
     */
    public E value() {     
        return theVal;   
    }

    /**
     * Returns the to String
     * @return the toString
     */
    public String toString() {     
        return theKey.toString() + ", " + theVal.toString();   
    } 
} /* *** ODSAendTag: KVPair *** */
