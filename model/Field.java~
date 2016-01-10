
package model;

import java.awt.Color;
import java.util.Arrays;
import java.util.Observable;
import util.MatrixBoolean2DUtil;

/**
 *
 * @author Ferit Tunçer
 */
public class Field extends Observable
{
    private boolean showEndGameMessage = false;
    private boolean coordinates[][];
    private Piece currentPiece;
    Color backg = Color.ORANGE, foreg = Color.WHITE;
    private int score = 0;

    public Field(int row, int col)
    {
        coordinates = new boolean[row][col];
    }

    public boolean[][] getCoordinates()
    {
        return coordinates;
    }


    public void setCoordinates(boolean[][] coordinates)
    {
        this.coordinates = coordinates;
    }



    public void mark(int x, int y)
    {
        this.coordinates[x][y] = true;
        setChanged();
        notifyObservers();
    }

    public void unmark(int x, int y)
    {
        this.coordinates[x][y] = false;
    }

    public boolean isMarked(int x, int y)
    {
        return coordinates[x][y];

    }

    public boolean spawn(Piece p)
    {
        currentPiece = p;

        if (roomExist(coordinates, p))
        {
            coordinates = MatrixBoolean2DUtil.alignedOr(coordinates, p.getPiece_bits(), p.x, p.y);

         
            setChanged();
            notifyObservers();
            return true;
        } else
        {
            showEndGameMessage = true;
            setChanged();
            notifyObservers();
            return false;
        }

    }

    public boolean roomExist(boolean[][] tempCoordinates, Piece p)
    {
        boolean[][] temp;
        try
        {
            temp = MatrixBoolean2DUtil.alignedNand(tempCoordinates, p.getPiece_bits(), p.x, p.y);
        } catch (ArrayIndexOutOfBoundsException aie)
        {
            return false;
        }

        return MatrixBoolean2DUtil.isFullySparseFor(temp, true);
    }

    public void fall()
    {
        Piece imaginary = new Piece(currentPiece.getX() + 1, currentPiece.getY(), currentPiece.getPiece_bits());
        boolean[][] tempResult = MatrixBoolean2DUtil.alignedNand(coordinates, currentPiece.getPiece_bits(), currentPiece.getX(), currentPiece.getY());
        boolean[][] tempCoordinates = MatrixBoolean2DUtil.and(coordinates, tempResult);

        if (roomExist(tempCoordinates, imaginary))
        {
            coordinates = tempCoordinates;
            spawn(imaginary);
        } else
        {
            currentPiece.hitsBottom();

            checkForPoints();

            currentPiece = null;
        }
    }

    public void moveLeft()
    {
        Piece imaginary = new Piece(currentPiece.getX(), currentPiece.getY() - 1, currentPiece.getPiece_bits());
        boolean[][] tempResult = MatrixBoolean2DUtil.alignedNand(coordinates, currentPiece.getPiece_bits(), currentPiece.getX(), currentPiece.getY());
        boolean[][] tempCoordinates = MatrixBoolean2DUtil.and(coordinates, tempResult);

        if (roomExist(tempCoordinates, imaginary))
        {
            coordinates = tempCoordinates;
            spawn(imaginary);
        }

    }

    public void moveRight()
    {
        Piece imaginary = new Piece(currentPiece.getX(), currentPiece.getY() + 1, currentPiece.getPiece_bits());
        boolean[][] tempResult = MatrixBoolean2DUtil.alignedNand(coordinates, currentPiece.getPiece_bits(), currentPiece.getX(), currentPiece.getY());
        boolean[][] tempCoordinates = MatrixBoolean2DUtil.and(coordinates, tempResult);

        if (roomExist(tempCoordinates, imaginary))
        {
            coordinates = tempCoordinates;
            spawn(imaginary);
        }

    }

    public Piece getCurrentPiece()
    {
        return currentPiece;
    }

    public void rotateCurrentPiece()
    {
        Piece imaginary = currentPiece.getRotated();

        boolean[][] tempResult = MatrixBoolean2DUtil.alignedNand(coordinates, currentPiece.getPiece_bits(), currentPiece.getX(), currentPiece.getY());
        boolean[][] tempCoordinates = MatrixBoolean2DUtil.and(coordinates, tempResult);

        if (roomExist(tempCoordinates, imaginary))
        {
            coordinates = tempCoordinates;
            spawn(imaginary);
        }

    }

    private void checkForPoints()
    {
        for (int i = 0; i < coordinates.length; i++)
        {
            boolean test = true;

            for (int j = 0; j < coordinates[i].length; j++)
            {
                test = test & coordinates[i][j];
                
            }

            if (test == true) 
            {
                
                addScore(coordinates[i].length);
                
                for (int j = i-1; j >= 0; j--)
                {
                    System.arraycopy(coordinates[j], 0, coordinates[j+1], 0, coordinates[j+1].length);
                }
            }

  
        }
    }

    public boolean isShowEndGameMessage()
    {
        return showEndGameMessage;
    }
    
    
    
    private void addScore(int toAdd)
    {
        score += toAdd;
        setChanged();
        notifyObservers();
    }

    public int getScore()
    {
        return score;
    }

    public Color getBackg()
    {
        return backg;
    }

    public Color getForeg()
    {
        return foreg;
    }

    
    
    

 

}
