
package model;

import java.util.Date;
import java.util.Observable;

/**
 *
 * @author Ferit Tunçer
 */
public class Game extends Observable
{
    
    private Player player;
    private Field field;
    private long startTime;
    private long endTime;
    private boolean ended;
    private boolean paused;
    private boolean rotateHandicap = false;
    private int maximumDelay = 600;
    private int minimumDelay = 200;
    private int score;

    public Game(Player player, Field field)
    {
        this.player = player;
        this.field = field;
        this.startTime = System.currentTimeMillis();
        
    }

    public Player getPlayer()
    {
        return player;
    }

    public Field getField()
    {
        return field;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public long getEndTime()
    {
        return endTime;
    }

    public boolean isEnded()
    {
        return ended;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public void setField(Field field)
    {
        this.field = field;
    }

    public void setStartTime(long startTime)
    {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime)
    {
        this.endTime = endTime;
    }

    public void setEnded(boolean ended)
    {
        this.ended = ended;
    }

    public boolean isPaused()
    {
        return paused;
    }

    public void setPaused(boolean paused)
    {
        this.paused = paused;
        setChanged();
        notifyObservers();
    }

    public void setRotateHandicap(boolean rotateHandicap)
    {
        this.rotateHandicap = rotateHandicap;
    }

    public boolean isRotateHandicap()
    {
        return rotateHandicap;
    }
    
    public int getDelay()
    {
        long temp = System.currentTimeMillis();
        
        long minutesPassed = ((temp - startTime)/30000);
        return Math.max(maximumDelay - 100*(int)minutesPassed, minimumDelay); 
        //return 600;
    }

    public int getMaximumDelay()
    {
        return maximumDelay;
    }

    public int getMinimumDelay()
    {
        return minimumDelay;
    }

    public int getScore()
    {
        return score;
    }

    public void setMaximumDelay(int maximumDelay)
    {
        this.maximumDelay = maximumDelay;
    }

    public void setMinimumDelay(int minimumDelay)
    {
        this.minimumDelay = minimumDelay;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
    
    
    public boolean getPaused()
    {
        return paused;
    }
    
    
}
