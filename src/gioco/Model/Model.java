package gioco.Model;

import java.awt.image.BufferedImage;

public interface Model {
    /**
     * Carica l'immagine
     * 
     * @param file
     *          prende come parametro il persorso del file da caricare
     * @return
     *          un BufferedImage con l'immagine passata
     */
    public BufferedImage loader(String file);
    /**
     * Setta il tasto per il salto
     * @return
     */
    public void setKeyJump();
    
    /**
     * 
     * @return
     *          il valore come stringa del tasto da usare per saltare
     */
    public String getKeyJump();
}
