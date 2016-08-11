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
     * Returns all the option.
     * @return
     *          all the option
     */
    Option getOption();
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
}
