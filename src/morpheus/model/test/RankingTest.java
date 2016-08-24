package morpheus.model.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

import morpheus.model.Element;
import morpheus.model.Ranking;
import morpheus.model.exceptions.IllegalNameException;
import morpheus.model.exceptions.NoElementsException;

/**
 * 
 * @author jacopo
 *
 */
public class RankingTest {
    private Ranking r;
    private static final int NUM_TEST1 = 1;
    private static final int NUM_TEST4 = 4;
    private static final int NUM_TEST198 = 198;
    private static final int NUM_TEST79 = 79;
    private static final int NUM_TEST3 = 3;
    private static final int NUM_TEST5 = 5;

    /**
     * .
     */
    @Test
    public void testSorting() {

        final List<Element> list = new ArrayList<>();
        list.add(new Element("giac", RankingTest.NUM_TEST1));
        list.add(new Element("sab", RankingTest.NUM_TEST4));
        list.add(new Element("sturaro", RankingTest.NUM_TEST198));
        list.add(new Element("taba", RankingTest.NUM_TEST79));
        list.add(new Element("paolo", RankingTest.NUM_TEST3));
        Collections.sort(list, new Element()::compare);
        int max = list.get(0).getScore();
        boolean app = false;
        for (int i = 1; i < RankingTest.NUM_TEST5 - 1; i++) {
            if (max > list.get(i).getScore()) {
                app = true;
            }
            assertTrue("Is " + i + "^ > " + i + 1 + "^ ? ", app);
            app = false;
            max = list.get(i).getScore();
        }

    }

    /**
     * .
     */
    @Test
    public void testAddElementAndLoadingFields() {

        r = Ranking.getRankingClass();
        r.getRankingOnTerm();
        Element app = null;
        Element nuovo = null;
        Element old = null;
        try {
            nuovo = new Element(r.getPosition(1).getName() + "X", r.getPosition(1).getScore() + 5);
        } catch (NoElementsException e) {
            assertTrue("Empty list ?", r.getPlayers().isEmpty());
        }
        try {
            old = new Element(r.getPosition(1).getName(), r.getPosition(1).getScore());
        } catch (NoElementsException e) {
            assertTrue("Empty list ?", r.getPlayers().isEmpty());
        }

        if (nuovo == null) {
            nuovo = new Element("ciao", 1);
            old = nuovo;
        }
        try {
            r.add(nuovo);
        } catch (IllegalNameException e1) {
            e1.printStackTrace();
        }
        try {
            assertTrue("New Record? ", nuovo.equals(r.getPosition(1)));
        } catch (NoElementsException e1) {
            e1.printStackTrace();
        }

        try {
            r.add(old);
        } catch (IllegalNameException e1) {
            app = old;
            r.forceAdd(new Element(old.getName(), old.getScore() + 1));
            try {
                
                int app1 = 0;
                if(r.getPlayers().size() == 1) {
                    app1 = 1;
                    assertTrue("Added a new score to the old element? ",
                            (app.getScore()) == r.getPosition(app1).getScore());
                } else {
                    if (r.getPlayers().size() == 2) {
                    app1 = r.getPlayers().size();
                    assertTrue("Added a new score to the old element? ",
                            (app.getScore() + 1) == r.getPosition(app1).getScore());
                    } else {
                        System.out.println("app:" + app.getScore() + "r: " + r.getPosition(1).getScore());

                        assertTrue("Added a new score to the old element? ",
                                (app.getScore() + 5) == (r.getPosition(1).getScore() ));
                    }
                }
                
                
            } catch (NoElementsException e) {
                e.printStackTrace();
            }
        }

        try

        {
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
