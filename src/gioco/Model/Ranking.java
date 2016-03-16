package gioco.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import javafx.util.Pair;

/**
 * 
 * @author jacopo
 *
 */
public class Ranking {
    private final String fileName;
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
        this.fileName = fileName;

        app = new LinkedList<>(java.nio.file.Files.readAllLines(FileSystems.getDefault().getPath(this.fileName)));
        values = new TreeSet<>(new Element());
        associate();
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
    }

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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName))) {
            for (Element e : values) {
                bw.write(e.getText());
                bw.newLine();
            }

        }
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
    public NavigableSet<Element> getRanking(final int x) {
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

        @Override
        public int compare(final Element a, final Element b) {
            return b.score - a.score;
        }
    }
}
