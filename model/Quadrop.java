
package model;

import java.util.ArrayList;

/*
    Quadrop
    Copyright (C) 2015 - Ferit Tunçer

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
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
