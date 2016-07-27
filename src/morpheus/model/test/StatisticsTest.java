package morpheus.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.awt.event.KeyEvent;

import org.junit.Test;

import morpheus.model.Statistics;
/**
 * Test for class Statistics.
 * @author jacopo
 *
 */
public class StatisticsTest {

    private static final int NUM_TEST_11 = 11;
    private static final int NUM_TEST_5 = 5;
    private static final int NUM_TEST_16 = 16;
    private static final int NUM_TEST_20 = 20;
    private static final int NUM_TEST_10 = 10;
    /**
     * .
     */
    @Test
    public void test() {
        final Statistics s = new Statistics();
       
        
        if (s.isFirstOpen()) {
            assertEquals(s.getFileName(), "res/Statistics.dat");
            assertEquals(s.getCoins(), 0);
            assertEquals(s.getKeyDown(), KeyEvent.VK_S);
            assertEquals(s.getKeyJump(), KeyEvent.VK_W);
            assertFalse(s.isBlueMorpheusOpen());
            assertFalse(s.isRedMorpheusOpen());
        
      
            s.incCoins();
            assertEquals(s.getCoins(), 1);
        
            for (int i = 0; i < NUM_TEST_10; i++) {
                s.incCoins();
            }
            assertEquals(s.getCoins(), NUM_TEST_11);
            s.incCoins(NUM_TEST_5);
            assertEquals(s.getCoins(), NUM_TEST_16);
            s.setKeyDown(KeyEvent.VK_0);
            assertEquals(s.getKeyDown(), KeyEvent.VK_0);
            s.setKeyJump(KeyEvent.VK_1);
            assertEquals(s.getKeyJump(), KeyEvent.VK_1);
            s.close();
        } else {
            final int coin = s.getCoins();
            final int keyJump = s.getKeyJump();
            final int keyDown = s.getKeyDown();
            
            for (int i = 0; i < 10; i++) {
                s.incCoins(2);
            }
            assertEquals(s.getCoins(), coin + NUM_TEST_20);
            assertEquals(keyJump, KeyEvent.VK_W);
            assertEquals(keyDown, KeyEvent.VK_S);
             
            s.close();
        }
        
    }

}
