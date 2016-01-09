package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Field;
import model.Quadrop;


/**
 *
 * @author Ferit Tunçer
 */
public final class View extends JFrame implements Observer
{

    int row, col;
    JPanel game, help;
    Quadrop qModel;
    

    public View(Quadrop model)
    {
        super("Quadrop by Ferit Tunçer");
        super.setName("Quadrop Frame");
        super.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.qModel = model;
       

        game = new JPanel(true);

        game.setFocusable(true);
        game.setPreferredSize(new Dimension(330, 600));

        row = qModel.getCurrentGame().getField().getCoordinates().length;
        col = qModel.getCurrentGame().getField().getCoordinates()[0].length;
        game = new JPanel(new GridLayout(row, col), true);
        game.setName("game");
        game.setFocusable(true);
        
        help = new JPanel(new FlowLayout(), true);
        help.setName("help");
        
        JLabel helpLabel = new JLabel("A - Left    D - Right    S - Falldown    W - Rotate    P - Pause");
        help.add(helpLabel);


        add(game);
        add(help);

        for (int i = 0; i < row * col; i++)
        {
            JLabel temp = new JLabel();
            
            temp.add(new JLabel());
            temp.setFocusable(true);
            temp.setName("temp");
            temp.setPreferredSize(new Dimension(30, 30));
            temp.setOpaque(true);
            Field tempField = qModel.getCurrentGame().getField();
            game.add(temp);
        }
        
        
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setFocusable(true);
        pack();
        setLocationRelativeTo(null);

    }

    @Override
    public void update(Observable o, Object arg)
    {
        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        Color backg = qModel.getCurrentGame().getField().getBackg();
        Color foreg = qModel.getCurrentGame().getField().getForeg();
        
        for (int i = 0; i < 6; i++)
        {
            int firstPart = qModel.getCurrentGame().getField().getScore()/1000;
            int secondPart = qModel.getCurrentGame().getField().getScore()%1000;
            ((JLabel)game.getComponent(col-3)).setText(String.format("%03d", firstPart));
            ((JLabel)game.getComponent(col-2)).setText(String.format("%03d", secondPart));
        }
        
        

        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                Field tempField = qModel.getCurrentGame().getField();
                if (tempField.isMarked(i, j))
                {
                    game.getComponent((i*col)+j).setBackground(foreg);
                }
                
                else
                {
                    game.getComponent((i*col)+j).setBackground(backg);
                }
            }

        }
        
        if(qModel.getCurrentGame().getField().isShowEndGameMessage())
        {
            int xPos = row/2;
            int yPos = col/2;
            
            if(col >= 9)
            {
                ((JLabel)game.getComponent(xPos*col + yPos -4)).setText("G");
                ((JLabel)game.getComponent(xPos*col + yPos -3)).setText("A");
                ((JLabel)game.getComponent(xPos*col + yPos -2)).setText("M");
                ((JLabel)game.getComponent(xPos*col + yPos -1)).setText("E");
                ((JLabel)game.getComponent(xPos*col + yPos +1)).setText("O");
                ((JLabel)game.getComponent(xPos*col + yPos +2)).setText("V");
                ((JLabel)game.getComponent(xPos*col + yPos +3)).setText("E");
                ((JLabel)game.getComponent(xPos*col + yPos +4)).setText("R");
                
            }
            
        }
        
        else if(qModel.getCurrentGame().isPaused())
        {
            int xPos = row/2;
            int yPos = col/2;
            
            if(col >= 6)
            {
                ((JLabel)game.getComponent((xPos-3)*col + yPos )).setText("P");
                ((JLabel)game.getComponent((xPos-2)*col + yPos )).setText("A");
                ((JLabel)game.getComponent((xPos-1)*col + yPos )).setText("U");
                ((JLabel)game.getComponent((xPos)*col + yPos )).setText("S");
                ((JLabel)game.getComponent((xPos+1)*col + yPos )).setText("E");
                ((JLabel)game.getComponent((xPos+2)*col + yPos )).setText("D");
                
            }
        }
        
        else
        {
            int xPos = row/2;
            int yPos = col/2;
            ((JLabel)game.getComponent((xPos-3)*col + yPos )).setText("");
                ((JLabel)game.getComponent((xPos-2)*col + yPos )).setText("");
                ((JLabel)game.getComponent((xPos-1)*col + yPos )).setText("");
                ((JLabel)game.getComponent((xPos)*col + yPos )).setText("");
                ((JLabel)game.getComponent((xPos+1)*col + yPos )).setText("");
                ((JLabel)game.getComponent((xPos+2)*col + yPos )).setText("");
            
        }
        
        for (Component component : game.getComponents())
        {
            if(component.getBackground() == foreg)
                component.setForeground(backg);
            else
                component.setForeground(foreg);
        }

        pack();
        super.paint(g);
    }

    

    

}
