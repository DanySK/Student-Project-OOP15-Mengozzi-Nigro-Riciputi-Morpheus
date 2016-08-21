package morpheus.model.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    /**
     * .
     */
    public void testSorting() {
        
        r = Ranking.getRankingClass();
        
        final List<Element> list = new ArrayList<>();
        list.add(new Element("Mirko", 1));
        list.add(new Element("Luca", 1));
        list.add(new Element("Niccolo", 1));
        list.add(new Element("Jacopo", 1));
        list.add(new Element("Matteo", 2));
        list.add(new Element("Gio", 1));
        list.add(new Element("giac", 1));
        list.add(new Element("sab", 1));
        list.add(new Element("sturaro", 1));
        list.add(new Element("taba", 1));
        list.add(new Element("paolo", 1));
        Collections.sort(list, new Element() :: compare);
        System.out.println("Test sorting");
        r.getRankingOnTerm();
        
        for (int i = 1; i < 11; i++) {
            try {
                assertEquals(r.getPosition(i).getText(), list.get(i - 1).getText());
            } catch (NoElementsException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }  
       
        try {
            System.out.println("Sto per scrivere il file");
            r.close();
            System.out.println("Ho appena scritto il file");
        } catch (IOException e) {
            System.out.println("Entro qui?");
        }
        System.out.println("Test add");
        r = Ranking.getRankingClass();
        
    }
    
    /**
     * .
     */
    
    @Test
    public void testAddElementAndLoadingFields() {
        
        r = Ranking.getRankingClass();
        r.getRankingOnTerm();
        try {
            r.add(new Element("taba12", 100));
            r.add(new Element("taba", 110));
            r.add(new Element("taba1", 1100));
            r.add(new Element("taba2", 1200));
            r.add(new Element("taba3", 10));
            r.add(new Element("taba12", 800));
        } catch (IllegalNameException e1) {
            System.out.println("Nome presente");
            r.forceAdd(new Element("taba12", 1100));
        } 
        r.getRankingOnTerm();
        try {
            r.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
