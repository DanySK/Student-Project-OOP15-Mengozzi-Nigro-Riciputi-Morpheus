package gioco.Model;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javafx.util.Pair;

/**
 * 
 * @author jacopo
 *
 */
public class Ranking {
    private final String fileName;
    private Set<Pair<String,Integer>> values;
    private List<String> app;
    public Ranking (String fileName) {
        this.fileName = fileName;
        try {
            app = new LinkedList<>(java.nio.file.Files.readAllLines(FileSystems.getDefault().getPath(this.fileName)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void add(Pair<String,Integer> el) {
        
    }
    
    private void associate() {
        if(app.isEmpty()) { 
            values = null;
        } else {
            for(String e : app) {
                for(char x : e.toCharArray()) {
                    if(x ==' ') {
                        values.add(new Pair<>("",1));
                    }
                }
            }
        }
        
    }
}
