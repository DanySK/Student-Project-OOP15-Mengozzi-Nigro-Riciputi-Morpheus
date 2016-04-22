package morpheus.model;

import java.io.Serializable;
import java.util.Comparator;


/**
 * 
 * @author jacopo
 *
 */
public class Element implements Comparator<Element>, Serializable {

    private static final long serialVersionUID = 5071215386654123511L;
    private final String name;
    private int score;

    /**
     * don't build anything.
     */
    public Element() {
        this.name = "NULL";
        this.score = 0;
    }

    /**
     * Build a Element with name = pair.getKey()
     *                 and  score = pair.getValue().
     * @param pair
     *          valor of the Element
     */
    public Element(final Pair<String, Integer> pair) {
        this.name = pair.getKey();
        this.score = pair.getValue();
    }
    /**
     * /**
     * Build a Element. 
     *
     * @param name
     *          the name 
     * @param score
     *          the score
     */
    public Element(final String name, final int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Setta lo score.
     * 
     * @param score
     *            valore del nuovo score
     */
    public void setScore(final int score) {
        this.score = score;
    }

    /**
     * 
     * @return Il nome dell'elemento
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @return Il punteggio dell'elemento
     */
    public int getScore() {
        return this.score;
    }

    /**
     * 
     * @return La descrizione Stringa dell'elemento
     */
    public String getText() {
        return this.name + "\t" + this.score;
    }

    /**
     * Un Pair che rappresenta l'elemento.
     * 
     * @return Un Pair che rappresenta l'elemento
     */
    public Pair<String, Integer> getAsPair() {
        return new Pair<String, Integer>(this.getName(), this.getScore());
    }

    @Override
    public int compare(final Element a, final Element b) {
        return b.score - a.score;
    }

}
