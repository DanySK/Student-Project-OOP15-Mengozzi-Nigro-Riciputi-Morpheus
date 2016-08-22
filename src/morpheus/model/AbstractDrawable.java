package morpheus.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 *
 */

public abstract class AbstractDrawable implements Drawable {

    private final Image[] image;
    private final Image mainFrame;
    private double x;
    private double y;
    private GameState state;
    private boolean remove;

    /**
     * 
     * Drawable, this class will representing the object on the screen. Give
     * methods for render, tick and set the object.
     * 
     * @param i
     *            array of Image
     * @param x
     *            X position
     * @param y
     *            Y position
     * @param game
     *            game state
     */
    public AbstractDrawable(final double x, final double y, final GameState game, final Image... i) {
        this.x = x;
        this.y = y;
        this.state = game;
        this.state.addEntity(this);
        image = Arrays.copyOf(i, i.length);
        mainFrame = image[0];

    }

    /**
     * 
     * @param x
     *            X position
     * @param y
     *            Y position
     * @param game
     *            game state
     * @param i
     *            array of Image
     */
    public AbstractDrawable(final double x, final double y, final GameState game, final Image i) {
        image = null;
        this.x = x;
        this.y = y;
        this.state = game;
        this.state.addEntity(this);
        mainFrame = i;

    }

    /**
     * Create a object for the game state without image.
     * 
     * @param x
     *            x position
     * @param y
     *            y position
     * @param game
     *            game state
     */
    public AbstractDrawable(final double x, final double y, final GameState game) {
        image = null;
        this.x = x;
        this.y = y;
        this.state = game;
        this.state.addEntity(this);
        mainFrame = null;
    }

    /**
     * Logic implementation for the object move.
     */
    public abstract void tick();

    /**
     * Increments the X position.
     * 
     * @param add
     *            value to add
     */
    protected void incX(final double add) {
        this.x += add;
    }

    /**
     * Increments the Y position.
     * 
     * @param add
     *            value to add
     */

    protected void incY(final double add) {
        this.y += add;
    }

    /**
     * Decrements the X position.
     * 
     * @param rem
     *            value to remove
     */
    protected void decX(final double rem) {
        this.x -= rem;
    }

    /**
     * Decrements the Y position.
     * 
     * @param rem
     *            value to remove
     */
    protected void decY(final double rem) {
        this.y -= rem;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.getX(), (int) this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public Rectangle getTop() {
        return new Rectangle((int) this.getX(), (int) this.getY(), this.getWidth(), 1);
    }

    @Override
    public Rectangle getLeft() {
        return new Rectangle((int) this.getX(), (int) this.getY(), 1, this.getHeight());
    }

    @Override
    public Rectangle getRight() {
        return new Rectangle((int) this.getX() + this.getWidth(), (int) this.getY(), 1, this.getHeight());
    }

    @Override
    public Rectangle getBottom() {
        return new Rectangle((int) this.getX(), (int) this.getY() + getHeight(), this.getWidth(), 1);
    }

    @Override
    public void render(final Graphics2D g) {
        g.drawImage(mainFrame.getImage(), (int) x, (int) y, null);
    }

    @Override
    public void setX(final double x) {
        this.x = x;
    }

    @Override
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Set a new game state and add the drawable.
     * 
     * @param game
     *            the game state
     */
    protected void setState(final GameState game) {
        this.state = game;
        state.addEntity(this);
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    /**
     * Returns a List of image, null if have a single frame.
     * 
     * @return List of image, null if have a single frame
     */
    public List<Image> getSprite() {
        if (image != null) {
            return new ArrayList<>(Arrays.asList(image));
        }
        return null;
    }

    @Override
    public int getHeight() {

        return mainFrame.getHeigth();
    }

    @Override
    public int getWidth() {
        return mainFrame.getWidth();
    }

    @Override
    public BufferedImage getMainImage() {
        return mainFrame.getImage();
    }

    /**
     * Return the state of the game.
     * 
     * @return the state of the game
     */
    public GameState getState() {
        return state;
    }

    @Override
    public void setRemove() {
        remove = true;
    }

    @Override
    public boolean isRemove() {
        return remove;
    }
}