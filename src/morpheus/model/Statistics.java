package morpheus.model;



/**
 * 
 * @author jacopo
 *
 */
public class Statistics extends Storable {

        
    /**
     * 
     */
    private static final long serialVersionUID = 3751161991209059959L;
    private static final String FILE_NAME = "/res/Statistics.dat";
    private int coin;
    private boolean blueMorpheus;
    private boolean redMorpheus;
    /**
     * Legge statistiche e la carica in memoria.
     * 
     * @param fileName
     *            path del file
     * 
     */
    public Statistics(final String fileName) {
            super(fileName);
            if (this.readObject() == null) {
                this.coin = 0;
                this.blueMorpheus = false;
                this.redMorpheus = false;
            } else {
                final Statistics stat = this.readObject();
                this.coin = stat.getCoin();
                this.blueMorpheus = stat.getBlueMorpheusOpen();
                this.redMorpheus = stat.getRedMorpheusOpen();
            }
    }
    
    /**
     * Legge le Statistiche dal file predefinito e le carica in memoria.
     */
    public Statistics() {
        super(FILE_NAME);
        if (this.readObject() == null) {
            this.coin = 0;
            this.blueMorpheus = false;
            this.redMorpheus = false;
        } else {
            final Statistics stat = this.readObject();
            this.coin = stat.getCoin();
            this.blueMorpheus = stat.getBlueMorpheusOpen();
            this.redMorpheus = stat.getRedMorpheusOpen();
        }
    }
    
    /**
     * Il totale dei soldi a disposizione del giocatore.
     * @return
     *          Il totale dei soldi a disposizione del giocatore.
     */
    public int getCoin() {
        return this.coin;
    }
    
    /**
     * Dice se il pesonaggio BlueMorpheus è sbloccato.
     * @return
     *          True -> Sbloccato;
     *          False -> Bloccato;
     */
    public boolean getBlueMorpheusOpen() {
        return this.blueMorpheus;
    }
    
    /**
     * Dice se il pesonaggio RedMorpheus è sbloccato.
     * @return
     *          True -> Sbloccato;
     *          False -> Bloccato;
     */
    public boolean getRedMorpheusOpen() {
        return this.redMorpheus;
    }
   
}
