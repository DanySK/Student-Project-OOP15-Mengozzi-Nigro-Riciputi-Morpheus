package morpheus.model;

import morpheus.model.monster.AbstractMonster;
import morpheus.view.state.GameState;
/**
 * 
 * @author jacopo
 *
 */
public interface Model {
    
    /**
     * Setta il tasto per alzarsi.
     * 
     * @param key
     *          Valore del tasto da settare in formato stringa
     */
    void setKeyShoot(int key);
    /**
     * Setta il tasto per sparare.
     * 
     * @param key
     *          Valore del tasto da settare in formato stringa
     */
    void setKeyJump(int key);
    
    /**
     * Set the game volume.
     * @param volume
     *          the new volume          
     */
    void setVolume(double volume);
    
    /**
     * 
     * @return
     *          il valore in formato stringa del tasto per alzarsi
     */
    int getKeyJump();
    /**
     * @return          
     *          il valore in formato stringa del tasto per sparare
     */
    int getKeyShoot();
    
    /**
     * Returns the game volume.
     * @return
     *          the game volume
     */
    double getVolume();
    
    /**
     * Returns true if the player is selected, false otherwise.
     * @return
     *          true if the player is selected, false otherwise.
     */
    boolean isMainPlayerOpen();
    
    /**
     * Returns true if the player is selected, false otherwise.
     * @return
     *          true if the player is selected, false otherwise.
     */
    boolean isSidePlayerOpen();
    
    /**
     * Set the player selection.
     * @param status
     *          true this is the selected player, false otherwise.
     */
    void setMainPlayerOpening(final boolean status);
    
    /**
     * Set the player selection.
     * @param status
     *          true this is the selected player, false otherwise.
     */
    void setSidePlayerOpening(final boolean status);
    
    /**
     * Save in the file the Option.
     */
    void saveOption();
    
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
    AbstractPill getBluePill(double x, double y, GameState state);
    
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
    AbstractPill getRedPill(double x, double y, GameState state);
    
    /**
     * Add an obstacle at the game state.
     * This obstacle are a spikes and his reaction Method will decrement a life to the MainPlayer.
     * @param x
     *          X position
     * @param y 
     *          Y position
     * @param state
     *          GameState
     * @return
     *          the obstacle
     */
    Spikes getSpikes(double x, double y, GameState state);
    
    /**
     * Create the main player if doesn't exist. And return it.
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
     * Create the side player if doesn't exist. And return it.
     * 
     * @param x
     *            x position
     * @param y
     *            y position
     * @param state
     *            the state of game
     * @return the main player
     */
    SidePlayer getSidePlayer(double x, double y, GameState state);
    
    /**
     * 
     * Returns to the side player , it has not been initialized return null.
     * 
     * @return the side player , it has not been initialized return null.
     */
    SidePlayer getSidePlayer();
    
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
     * Add a coin at the game state.
     * The coin's type is: Normal.
     * @param x
     *          X position
     * @param y 
     *          Y position
     * @param state
     *          GameState
     * @return
     *          the Coin
     */
    Coin getNormalCoin(double x, double y, GameState state);
    
    /**
     * Add a coin at the game state.
     * The coin's type is: x2 coin.
     * @param x
     *          X position
     * @param y 
     *          Y position
     * @param state
     *          GameState
     * @return
     *          the Coin
     */
    Coin getDoubleCoin(double x, double y, GameState state);
    
    /**
     * Add a coin at the game state.
     * The coin's type is: Special.
     * @param x
     *          X position
     * @param y 
     *          Y position
     * @param state
     *          GameState
     * @return
     *          the Coin
     */
    Coin getSpecialCoin(double x, double y, GameState state);
    
    /**
     * Returns the ranking.
     * @return
     *          the ranking
     */
    Ranking getRanking();
}
