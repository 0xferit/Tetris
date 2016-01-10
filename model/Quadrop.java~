
package model;

import java.util.ArrayList;

/**
 *
 * @author Ferit Tunçer
 */
public class Quadrop 
{
    
    private ArrayList<Game> endedGames;
    private Game current;

    public Quadrop()
    {
        endedGames = new ArrayList<>();
    }
    
    public void newGame(Player p, Field f)
    {
        Game newGame = new Game(p, f);
        current = newGame;
    }
    
    public void endGame()
    {
        current.setEnded(true);
        current.setEndTime(System.currentTimeMillis());
        current.getField().deleteObservers();
        endedGames.add(current);
    }
    
    public Game getCurrentGame()
    {
        return current;
    }
    
    
    
    
}
