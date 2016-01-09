
package model;

import java.util.Arrays;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MatrixBoolean2DUtil;

/**
 *
 * @author Ferit Tunçer
 */
public class Piece extends Observable implements Cloneable
{
    int x, y, type;
    private boolean piece_bits[][];
    private boolean hitsBottom = false;
    
    public Piece(int x, int y, int type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        piece_bits = PIECE_BITS[type];

    }
    
    public Piece(int x, int y, boolean[][] piece_bits)
    {
        this.x = x;
        this.y = y;
        this.piece_bits = piece_bits;
    }
    public static final boolean PIECE_BITS[][][] = {
		{
			{true},
			{true},
			{true},
			{true},
		},
                {
			{true},
			{true},
			{true},
			{true},
                        {true}
		},
                {
			{true, false},
			{true, false},
			{true, false},
			{true, true}
		},
		{
			{true, true},
			{true, false},
			{true, false},
		},
		{
			{true, false},
			{true, false},
			{true, true},
		},
		{
			{true, false},
			{true, true,},
			{false, true},
		},
		{
			{false, true},
			{true, true},
			{true, false},
		},
		{
			{true, false},
			{true, true},
			{true, false},
		},
		{
			{true, true},
			{true, true},
		},
                {
                        {true, true},
                        {true, false}
                },
                {
			{true},
			{true},
			{true},
		},
                {
                        {true, true},
                        {true, true},
                        {true, false}
                }
                
	};

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getS()
    {
        return type;
    }

    public boolean[][] getPiece_bits()
    {
        return piece_bits;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }
    
    public void hitsBottom()
    {
        this.hitsBottom = true;
    }
    

    
    public Piece getRotated()
    {
        Piece toReturn = null;
        try
        {
            toReturn = (Piece)this.clone();
        } catch (CloneNotSupportedException ex)
        {
            System.out.println(ex.getMessage());
        }
        toReturn.piece_bits = MatrixBoolean2DUtil.reverseRows(MatrixBoolean2DUtil.transpose(this.piece_bits));
        
        return toReturn;
    }
    
}
