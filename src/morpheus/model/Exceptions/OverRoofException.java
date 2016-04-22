package morpheus.model.Exceptions;

/**
 * 
 * @author jacopo
 *
 */
public class OverRoofException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 3674590469539258612L;

    private final int difference;
    
    /**
     * .
     * @param difference
     *          the difference about the roof and the characters
     */
    public OverRoofException(final int difference) {
        this.difference = difference;
    }
    
    /**
     * Return the difference with between roof height and character's height.
     * @return
     *          the difference with between roof height and character's height
     */
    public int getDifference() {
        return difference;
    }
}
