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
     * File name, for load and save infomation.
     * 
     * @param fileName
     *            file name
     */
    public Storable(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads the object and return it.
     * 
     * @return the object, null if the file doesn't exist or the object isn't in
     *         the file.
     * 
     * @throws IOException
     *             if don't find the file
     * 
     */
    protected Object readObject() throws IOException {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return in.readObject();
        } catch (ClassNotFoundException e) {
            return null;
        }

    }

    /**
     * Write the object in the file.
     *
     * @param <X>
     *            Type of the object to be saved
     * @param object
     *            object to be saved
     */
    protected <X> void writeObject(final X object) {
        System.out.println(fileName);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {

            out.writeObject(object);

        } catch (FileNotFoundException e) {
            System.out.println("File inestistente!");
        } catch (IOException e) {
            System.out.println("File non trovato");
        }

    }

    /**
     * Returns the file name.
     * 
     * @return 
     *          file name
     */
    public String getFileName() {
        return this.fileName;
    }
}
