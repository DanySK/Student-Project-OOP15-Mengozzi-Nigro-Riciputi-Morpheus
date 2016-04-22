package morpheus.model;

/**
 * 
 * @author jacopo
 *
 * @param <X>
 * @param <Y>
 */
public class Pair<X, Y> {
        
        private final X x;
        private final Y y;
        private static final int PRIME = 31;
        /**
         * Pair of two generics object.
         * @param x
         *      first element
         * @param y
         *      second element
         */
        public Pair(final X x, final Y y) {
                super();
                this.x = x;
                this.y = y;
        }

        /**
         * Value of the first element.
         * @return
         *      Value of the first element.
         */
        public X getKey() {
                return x;
        }

        /**
         * Value of the second element.
         * @return
         *      Value of the second element.
         */
        public Y getValue() {
                return y;
        }

        @Override
        public int hashCode() {
                
                int result = 1;
                result = PRIME * result + ((x == null) ? 0 : x.hashCode());
                result = PRIME * result + ((y == null) ? 0 : y.hashCode());
                return result;
        }

        @SuppressWarnings("rawtypes")
        @Override
        public boolean equals(final Object obj) {
                if (this == obj) {
                        return true;
                }
                if (obj == null) {
                        return false;
                }
                if (getClass() != obj.getClass()) {
                        return false;
                }
                final Pair other = (Pair) obj;
                if (x == null) {
                        if (other.x != null) {
                                return false;
                        }
                } else if (!x.equals(other.x)) {
                        return false;
                }
                if (y == null) {
                        if (other.y != null) {
                                return false;
                        }
                } else if (!y.equals(other.y)) {
                        return false;
                }
                return true;
        }

        @Override
        public String toString() {
                return "Pair [x=" + x + ", y=" + y + "]";
        }
        
        

}