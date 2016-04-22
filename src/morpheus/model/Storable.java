package morpheus.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @author jacopo
 *
 */
public class Storable implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -4374984176196962972L;
    
    private final String fileName;
    
    /**
     * Prende in input il nome del file su/da cui scrivere/caricare l'oggetto.
     * @param fileName
     *          il nome del file
     */
    public Storable(final String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * Legge l'oggetto dal file passato e fa direttamente il casting.
     * 
     * @return
     *          l'oggetto gia castato
     *          null in caso il file non esista o in caso l'oggetto sul file non sia trovato
     * @throws IOException 
     *          if don't find the file
     *           
     */
    protected Object readObject() throws IOException {
        
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(fileName))) {
            return in.readObject();
        } catch (ClassNotFoundException e) {
            return null;
        }
        
    }

    /**
     * Scrive l'oggetto sul file passato.
     * 
     *
     * @param <X>
     *          tipo di oggetto da salvare
     * @param object
     *          oggetto da salvare
     */
    protected <X> void writeObject(final X object) {
        System.out.println(fileName);
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            
            out.writeObject(object);
            
        } catch (FileNotFoundException e) {
            System.out.println("File inestistente!");
        } catch (IOException e) {
            System.out.println("File non trovato");
        }
        
    }
    
    /**
     * Il nome del file. 
     * @return
     *          il nome del file
     */
    public String getFileName() {
        return this.fileName;
    }
}
