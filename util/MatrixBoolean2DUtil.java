package util;

/**
 *
 * @author Ferit Tunçer
 */

public class MatrixBoolean2DUtil
{

    public static boolean[][] or(boolean[][] b1, boolean[][] b2)
    {
        boolean[][] temp = new boolean[b1.length][b1[0].length];

        for (int i = 0; i < b1.length; i++)
        {
            for (int j = 0; j < b1[0].length; j++)
            {
                temp[i][j] = b1[i][j] | b2[i][j];
            }
        }

        return temp;
    }

    public static boolean[][] xor(boolean[][] b1, boolean[][] b2)
    {
        boolean[][] temp = new boolean[b1.length][b1[0].length];

        for (int i = 0; i < b1.length; i++)
        {
            for (int j = 0; j < b1[0].length; j++)
            {
                temp[i][j] = b1[i][j] ^ b2[i][j];
            }
        }

        return temp;
    }

    public static boolean[][] and(boolean[][] b1, boolean[][] b2)
    {
        boolean[][] temp = new boolean[b1.length][b1[0].length];

        for (int i = 0; i < b1.length; i++)
        {
            for (int j = 0; j < b1[0].length; j++)
            {
                temp[i][j] = b1[i][j] & b2[i][j];
            }
        }

        return temp;
    }

    public static boolean[][] nand(boolean[][] b1, boolean[][] b2)
    {
        boolean[][] temp = new boolean[b1.length][b1[0].length];

        for (int i = 0; i < b1.length; i++)
        {
            for (int j = 0; j < b1[0].length; j++)
            {
                temp[i][j] = !(b1[i][j] & b2[i][j]);
            }
        }

        return temp;
    }

    public static boolean[][] align(boolean[][] b1, boolean[][] b2, int alignx, int aligny)
    {
        boolean[][] ba = new boolean[b1.length][b1[0].length];

        for (int i = 0; i < b2.length; i++)
        {
            System.arraycopy(b2[i], 0, ba[alignx + i], aligny, b2[i].length); //
        }

        return ba;

    }

    public static boolean[][] alignedOr(boolean[][] b1, boolean[][] b2, int alignx, int aligny)
    {
        boolean[][] temp = new boolean[b1.length][b1[0].length];
        boolean[][] ba = align(b1, b2, alignx, aligny);

        for (int i = 0; i < b1.length; i++)
        {
            for (int j = 0; j < b1[0].length; j++)
            {
                temp[i][j] = b1[i][j] | ba[i][j];
            }
        }

        return temp;
    }

    public static boolean[][] alignedXor(boolean[][] b1, boolean[][] b2, int alignx, int aligny)
    {
        boolean[][] temp = new boolean[b1.length][b1[0].length];
        boolean[][] ba = align(b1, b2, alignx, aligny);

        for (int i = 0; i < b1.length; i++)
        {
            for (int j = 0; j < b1[0].length; j++)
            {
                temp[i][j] = b1[i][j] ^ ba[i][j];
            }
        }

        return temp;
    }

    public static boolean[][] alignedAnd(boolean[][] b1, boolean[][] b2, int alignx, int aligny)
    {
        boolean[][] temp = new boolean[b1.length][b1[0].length];
        boolean[][] ba = align(b1, b2, alignx, aligny);

        for (int i = 0; i < b1.length; i++)
        {
            for (int j = 0; j < b1[0].length; j++)
            {
                temp[i][j] = b1[i][j] & ba[i][j];
            }
        }

        return temp;
    }

    public static boolean[][] alignedNand(boolean[][] b1, boolean[][] b2, int alignx, int aligny)
    {
        boolean[][] temp = new boolean[b1.length][b1[0].length];
        boolean[][] ba = align(b1, b2, alignx, aligny);

        for (int i = 0; i < b1.length; i++)
        {
            for (int j = 0; j < b1[0].length; j++)
            {
                temp[i][j] = !(b1[i][j] & ba[i][j]);
            }
        }

        return temp;
    }

    public static boolean isFullySparseFor(boolean[][] test, boolean value)
    {
        boolean temp = value;

        for (boolean[] bs : test)
        {
            for (boolean b : bs)
            {
                if (value == true)
                {
                    temp = temp & b;
                } else
                {
                    temp = temp | b;
                }
            }
        }

        return (temp == value);
    }

    

    public static boolean[][] transpose(boolean[][] toBeTransposed)
    {
        boolean[][] toReturn = new boolean[toBeTransposed[0].length][toBeTransposed.length];

        for (int i = 0; i < toBeTransposed.length; i++)
        {

            for (int j = 0; j < toBeTransposed[0].length; j++)
            {
                toReturn[j][i] = toBeTransposed[i][j];
            }
        }

        return toReturn;
    }

    public static boolean[][] mirror(boolean[][] toBeMirrored)
    {
        int dimension1 = toBeMirrored.length;
        int dimension2 = toBeMirrored[0].length;
        int minDimension = Math.min(dimension1, dimension2);

        boolean[][] mirroringFactor = new boolean[minDimension][minDimension];

        for (int i = 0; i < mirroringFactor.length; i++)
        {
            mirroringFactor[i][mirroringFactor.length - 1 - i] = true;
        }

        if (toBeMirrored[0].length == mirroringFactor.length)
        {
            return multiply(toBeMirrored, mirroringFactor);
        } else
        {
            return multiply(mirroringFactor, toBeMirrored);
        }

    }


    public static boolean[][] multiply(boolean[][] m1, boolean[][] m2)
    {
        int mA = m1.length;
        int nA = m1[0].length;
        int mB = m2.length;
        int nB = m2[0].length;
        if (nA != mB)
        {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        boolean[][] toReturn = new boolean[mA][nB];
        for (int i = 0; i < mA; i++)
        {
            for (int j = 0; j < nB; j++)
            {
                for (int k = 0; k < nA; k++)
                {
                    toReturn[i][j]= toReturn[i][j] | (m1[i][k] & m2[k][j]);
                }
            }
        }
        return toReturn;
    }
    
    public static boolean[][] reverseRows(boolean[][] m1)
    {
        boolean[][] toReturn = new boolean[m1.length][m1[0].length];
        
        for (int i = 0; i < m1.length; i++)
        {
            System.arraycopy(m1[i], 0, toReturn[m1.length-1-i], 0, m1[0].length);
        }
        
        return toReturn;
    }

}
