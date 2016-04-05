package morpheus.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * 
 * @author jacopo
 *
 */
public class Ranking extends Storable {
    /**
     * 
     */
    private static final long serialVersionUID = 3005073739818117637L;
    private static final String FILE_NAME = "/res/Ranking.dat";
    private NavigableSet<Element> values;
    private List<String> app;
    
    
    
    /**
     * Legge la classifica e la carica in memoria.
     * 
     * @param fileName
     *            path del file
     * @throws IOException
     *             In caso il file non esistesse
     */
    public Ranking(final String fileName) throws IOException {
        super(fileName);
        final Ranking rank = (Ranking) this.readObject();
        this.values = rank.getRanking();
        this.app = rank.getPlayers();
       
        this.getRankingOnTerm();
    }
    
    /**
     * Legge la classifica e la carica in memoria.
     * 
     * @throws IOException
     *             In caso il file non esistesse
     */
    public Ranking() throws IOException {
        super(FILE_NAME);
        final Ranking rank = (Ranking) this.readObject();
        this.values = rank.getRanking();
        this.app = rank.getPlayers();
       
        this.getRankingOnTerm();
    }

    /**
     * Aggiunge un elemento alla classifica.
     * 
     * @param el
     *            Elemento da aggiungere alla classifica:
     *                  chiave-> il nome;
     *                  valore-> il punteggio.
     */
    public void add(final Pair<String, Integer> el) {
        if (app.contains(el.getKey())) {
            throw new IllegalArgumentException();
        }
        app.add(el.getKey());
        values.add(new Element(el));
    }
    
    /**
     * Forza l'aggiornamento dello score di un Risultato già inserito.
     * @param el
     *         Elemento da aggiornare:
     *                  chiave-> il nome;
     *                  valore-> il punteggio 
     */
    public void forceAdd(final Pair<String, Integer> el) {
        for (Element e : values) {
            if (e.getName().equals(el.getKey())) {
                e.setScore(el.getValue());
            }
        }
    }
/*
    private void associate() {
        int y = 0;
        List<String> appList = new ArrayList<>();
        if (app.isEmpty()) {
            values = null;
        } else {

            for (String e : app) {
                y = 0;
                for (Character x : e.toCharArray()) {
                    if (x.equals('\t')) {
                        values.add(new Element(new Pair<>(e.substring(0, y), Integer.parseInt(e.substring(y + 1)))));
                        appList.add(e.substring(0, y));
                        break;
                    }
                    y++;
                }

            }
            app = new ArrayList<>(appList);
        }
    }*/

    /**
     * Stampa la classifica su terminale.
     */
    public void getRankingOnTerm() {
        for (Element e : values) {
            System.out.println(e.getName() + "\t" + e.getScore());
        }
    }

    /**
     * Salva i cambiamenti alla classifica su file.
     * 
     * @throws IOException
     *             In caso il file non esistesse
     */
    public void close() throws IOException {
        this.writeObject(this);
    }

    /**
     * Ritorna un NavigableSet con i primi x elementi desiderati, se x è più
     * grande del numero degli elementi disponibili verranno riportati tutti gli
     * elementi.
     * 
     * @param x
     *            il numero degli elementi da ritornare
     * @return un NavigableSet, già ordinato con i primi x elementi della
     *         classifica
     * 
     */
    public NavigableSet<Element> getPartOfRanking(final int x) {
        NavigableSet<Element> set = new TreeSet<>(new Element());
        for (Element e : values) {
            set.add(new Element(new Pair<>(e.getName(), e.getScore())));
            if (set.size() == x) {
                break;
            }
        }
        return set;
    }
    
    /**
     * Ritorna la classifica per intero.
     * @return
     *          un NavigableSet con l'intera classifica
     */
    public NavigableSet<Element> getRanking() {
        return new TreeSet<>(values);
    }
    
    /**
     * La lista dei nomi del giocatori presenti nella classifica.
     * @return
     *         La lista dei nomi del giocatori presenti nella classifica
     */
    public List<String> getPlayers() {
        return new ArrayList<>(this.app);
    }
    
    /**
     * Fornisce il punteggio massimo e il giocatore che l'ha fatto.
     * @return
     *          un Pair composto da:
     *                  -Nome giocatore;
     *                  -Punteggio.
     */
    public Pair<String, Integer> getRecord() {
        return this.values.first().getAsPair();
    }

    /**
     * 
     * @author jacopo
     *
     */
    public static class Element implements Comparator<Element>, Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 5071215386654123511L;
        private String name;
        private int score;

        Element() {
        }

        Element(final Pair<String, Integer> pair) {
            this.name = pair.getKey();
            this.score = pair.getValue();
        }
        
        /**
         * Setta lo score.
         * @param score
         *              valore del nuovo score
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
         * @return
         *      Un Pair che rappresenta l'elemento
         */
        public Pair<String, Integer> getAsPair() {
            return new Pair<String, Integer>(this.getName(), this.getScore());
        }

        @Override
        public int compare(final Element a, final Element b) {
            return b.score - a.score;
        }
    }
}
