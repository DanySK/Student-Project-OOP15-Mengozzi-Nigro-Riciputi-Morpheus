package morpheus.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import morpheus.model.exceptions.IllegalNameException;
import morpheus.model.exceptions.NoElementsException;

/**
 * 
 * @author jacopo
 *
 */
public final class Ranking extends Storable {

    private static Ranking rank;
    /**
     * 
     */
    private static final long serialVersionUID = 3005073739818117637L;
    /**
     * File name.
     */
    private static final String FILE = "res/Ranking.dat";
    /**
     * Max Element in the file.
     */
    private static final int MAX_SIZE = 5;
    private final List<Element> values;
    private final List<String> app;
    private boolean toSort;

    /**
     * Reads the ranking and load it in memory. If the file doesn't exist it
     * will create a new File.
     */
    private Ranking() {
        super(FILE);
        boolean avvert = false;
        Ranking rank = null;
        try {
            rank = (Ranking) this.readObject();
        } catch (IOException e) {
            new File(FILE);
            avvert = true;
        }
        if (avvert) {
            this.values = new ArrayList<>();
            this.app = new ArrayList<>();
        } else {
            this.values = rank.getRanking();
            this.app = rank.getPlayers();
        }
    }

    /**
     * Returns the Ranking object.
     * 
     * @return the Ranking object
     */
    public static Ranking getRankingClass() {
        synchronized (Ranking.class) {
            if (rank == null) {
                rank = new Ranking();
            }
        }
        return rank;
    }

    /**
     * Add an element to the ranking.
     * 
     * @param el
     *            Element to add: key-> name; value-> score.
     * @throws IllegalNameException
     *             if contains the name yet.
     */
    public void add(final Element el) throws IllegalNameException {
        System.out.println("Inserisco: Nome -> " + el.getName() + " Score: " + el.getScore());
        
        if (app.contains(el.getName())) {
        	System.out.println("nome" + el.getName());
            System.out.println("Lancio eccezione per nome già presente");
            throw new IllegalNameException();
        }
        app.add(el.getName());
        values.add(el);
        toSort = true;
        if (values.size() > MAX_SIZE) {
            remove();
        }
    }

    /**
     * Force the update of the score of a Element already in the ranking.
     * 
     * @param el
     *            Element to add: key-> name; value-> score.
     */
    public void forceAdd(final Element el) {
        for (final Element e : values) {
            if (e.getName().equals(el.getName())) {
                e.setScore(el.getScore());
                toSort = true;
            }
        }
        if (values.size() > MAX_SIZE) {
            remove();
        }

    }

    /**
     * Print the ranking on terminal.
     */
    public void getRankingOnTerm() {
        if (toSort) {
            Collections.sort(values, new Element()::compare);
            toSort = false;
        }

        for (final Element e : values) {
            System.out.println(e.getName() + "\t" + e.getScore());
        }
    }

    /**
     * Save the ranking in the file.
     * 
     * @throws IOException
     *             if file doesn't exist.
     */
    public void close() throws IOException {
        if (toSort) {
            Collections.sort(values, new Element()::compare);
            toSort = false;
        }
        this.writeObject(this);
    }

    /**
     * Returns a list with the first x items you want, if x is greater of all
     * the number of available items will be reported all elements.
     * 
     * @param x
     *            elements to return
     * @return a list , already ordered with the first x elements of the ranking
     * 
     * 
     */
    public List<Element> getPartOfRanking(final int x) {
        int app = x;
        if (app > values.size()) {
            app = values.size();
        }
        if (toSort) {
            Collections.sort(values, new Element()::compare);
            toSort = false;
        }
        return new ArrayList<>(values.subList(0, app));
    }

    /**
     * Returns the full ranking.
     * 
     * @return a list with the full ranking
     */
    public List<Element> getRanking() {
        if (toSort) {
            Collections.sort(values, new Element()::compare);
            toSort = false;
        }
        return new ArrayList<>(values);
    }

    /**
     * Returns a List of name of the players in the ranking.
     * 
     * @return List of name of the players in the ranking.
     */
    public List<String> getPlayers() {
        return new ArrayList<>(app);
    }

    /**
     * Get the Element at the x position.
     * 
     * @param x
     *            rank of the element
     * @return the required element
     * @throws NoElementsException
     *             if the list is Empty.
     */
    public Element getPosition(final int x) throws NoElementsException {
        if (x > values.size()) {
            throw new NoElementsException();
        }
        
        if (toSort) {
            Collections.sort(values, new Element()::compare);
            toSort = false;
        }
        return values.get(x - 1);
    }

    /**
     * Returns a Pair with the best score and the name of the player.
     * 
     * @throws NoElementsException
     *             if the list hasn't the required element
     * @return a Pair: -Player name; -Score.
     */
    public Pair<String, Integer> getRecord() throws NoElementsException {
        if (values.isEmpty()) {
            throw new NoElementsException();
        }
        return values.get(0).getAsPair();
    }

    /**
     * Returns true if the list is to sort, false otherwise.
     * 
     * @return true if the list is to sort, false otherwise.
     */
    public boolean isToSort() {
        return toSort;
    }

    private void remove() {
        Collections.sort(values, new Element()::compare);
        toSort = false;
        if (app.remove(values.get(MAX_SIZE).getName())) {
            System.out.println("Rimozione nome avvenuta");
        }
        values.remove(MAX_SIZE);

    }

}
