package morpheus.model.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import morpheus.model.Element;
import morpheus.model.Ranking;

/**
 * 
 * @author jacopo
 *
 */
public class RankingTest {
    private Ranking r;
    /**
     * .
     */
    @Test
    public void testSorting() {
        
        r = new Ranking();
        
        final List<Element> list = new ArrayList<>();
        list.add(new Element("Mirko", 1000));
        list.add(new Element("Luca", 500));
        list.add(new Element("Niccolo", 59));
        list.add(new Element("Jacopo", 10));
        list.add(new Element("Matteo", 2));
        list.add(new Element("Gio", 1001));
        list.add(new Element("giac", 1021));
        list.add(new Element("sab", 1201));
        list.add(new Element("sturaro", 1111));
        list.add(new Element("taba", 3001));
        list.add(new Element("paolo", 111));
        Collections.sort(list, new Element() :: compare);
        System.out.println("Test sorting");
        r.getRankingOnTerm();
        
        for (int i = 1; i < 11; i++) {
            assertEquals(r.getPosition(i).getText(), list.get(i - 1).getText());
        }  
       
        try {
            System.out.println("Sto per scrivere il file");
            r.close();
            System.out.println("Ho appena scritto il file");
        } catch (IOException e) {
            System.out.println("Entro qui?");
        }
        System.out.println("Test add");
        r = new Ranking();
    }
    
    /**
     * .
     */
    @Test
    public void testAddElementAndLoadingFields() {
        
        //r.getRankingOnTerm();
    }

}
