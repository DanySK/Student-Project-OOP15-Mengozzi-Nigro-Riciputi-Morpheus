package morpheus.model;

import java.awt.image.BufferedImage;
import java.util.Map;

import morpheus.model.monster.AbstractMonster;
import morpheus.view.state.GameState;
/**
 * 
 * @author jacopo
 *
 */
public interface Model {
    /**
     * Carica l'immagine.
     * 
     * @param file
     *          prende come parametro il persorso del file da caricare
     * @return
     *          un BufferedImage con l'immagine passata
     */
     BufferedImage loader(String file);
    /**
     * Setta il tasto per alzarsi.
     * 
     * @param key
     *          Valore del tasto da settare in formato stringa
     */
    void setKeyShoot(String key);
    /**
     * Setta il tasto per sparare.
     * 
     * @param key
     *          Valore del tasto da settare in formato stringa
     */
    void setKeyJump(String key);
    
    /**
     * 
     * @return
     *          il valore in formato stringa del tasto per alzarsi
     */
    String getKeyJump();
    /**
     * @return          
     *          il valore in formato stringa del tasto per sparare
     */
    String getKeyShoot();
    
    /**
     * Add an obstacle at the game state.
     * This obstacle is a blue pill and his reaction Method will add a bullet to the MainPlayer.
     * @param x
     *          X position
     * @param y 
     *          Y position
     * @param state
     *          GameState
     * @return
     *          the obstacle
     */
    Obstacle getBluePill(double x, double y, GameState state);
    
    /**
     * Add an obstacle at the game state.
     * This obstacle is a red pill and his reaction Method will add a life to the MainPlayer.
     * @param x
     *          X position
     * @param y 
     *          Y position
     * @param state
     *          GameState
     * @return
     *          the obstacle
     */
    Obstacle getRedPill(double x, double y, GameState state);
    
    /**
     * Create the player if doesn't exist. And return it.
     * 
     * @param x
     *            x position
     * @param y
     *            y position
     * @param state
     *            the state of game
     * @return the main player
     */
    MainPlayer getMainPlayer(double x, double y, GameState state);
    
    /**
     * 
     * Returns to the main player , it has not been initialized return null.
     * 
     * @return the main player , it has not been initialized return null.
     */
    MainPlayer getMainPlayer();
    
    /**
     * Add a monster at the game state.
     * This monster is a Ghost
     * @param x
     *          X position
     * @param y 
     *          Y position
     * @param state
     *          GameState
     * @return
     *          the Ghost
     */
    AbstractMonster getGhost(double x, double y, GameState state);
    
    /**
     * Add a monster at the game state.
     * This monster is a Tree
     * @param x
     *          X position
     * @param y 
     *          Y position
     * @param state
     *          GameState
     * @return
     *          the Tree
     */
    AbstractMonster getTree(double x, double y, GameState state);
    
    /**
     * Add a monster at the game state.
     * This monster is a Penguin
     * @param x
     *          X position
     * @param y 
     *          Y position
     * @param state
     *          GameState
     * @return
     *          the Penguin
     */
    AbstractMonster getPenguin(double x, double y, GameState state);
    
    /**
     * Aggiunge alla mappa delle texture una nuova texture.
     * @param p
     *          un Pair con:
     *                  - nome da associare alla texture;
     *                  - path del file da caricare;
     */
    void addTexture(Pair<String, String> p);
    
    /**
     * Fornisce la texture richiesta.
     * @param name
     *          nome associato alla texture
     * @return
     *          la texture o null se non è tra quelle caricate
     */
    BufferedImage getTexture(String name);
    
    /**
     * Associate a map of string to a map of String and bufferedImage.
     * @param map
     *          the map<String, String>
     * @return
     *          a map
     */
    Map<String, BufferedImage> associate (Map<String, String> map);
}
