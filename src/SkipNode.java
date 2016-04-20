/**
 * 
 */

/**
     * @author Jazmine Zurita and Jessica McCready
     * @version Feb 1 2016
     *
     */
    public class SkipNode extends Comparable<K>, E> {

        /**
         * forward array
         */
        public int[] forward;

        /**
         * the element 
         */
        public KVPair<K, E> element;

        /**
         * The constructor for the SkipNode
         * @param it  the element
         * @param level  the level
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, E> it, int level)
        {
            element = it;
            forward =  new int[level + 1];
        }

        /**
         * Returns the element
         * @return returns the element
         */
        public KVPair<K, E> element()
        {
            return element;
        }

        /**
         * Returns the level of the SkipNode
         * @return returns the level
         */
        public int getLevel()
        {
            return level;
        }
    }
