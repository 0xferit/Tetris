
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.Field;
import model.Piece;
import model.Player;
import model.Quadrop;
import util.MatrixBoolean2DUtil;
import view.View;

/**
 *
 * @author Ferit Tunçer
 */
public class Controller implements KeyListener, ActionListener
{

    private Quadrop testModel;
    private View testView;
    Timer fall, rotate;
    private static int timerDelay = 600;

    public Controller(Quadrop model)
    {
        this.testModel = model;
        fall = new Timer(timerDelay, this);
        fall.setActionCommand("timer");
        fall.start();
        rotate = new Timer(timerDelay*2, this);
        rotate.setActionCommand("rotate");
        rotate.start();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        boolean justUnpaused = false;
        
        if (testModel.getCurrentGame().isPaused())
        {
            pauseUnpause();
            justUnpaused = true;
        }
        

        e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
        switch (e.getKeyChar())
        {
            case KeyEvent.VK_S:
                fall();
                break;
            case KeyEvent.VK_A:
                moveLeft();
                break;
            case KeyEvent.VK_D:
                moveRight();
                break;
            case KeyEvent.VK_W:
                rotate();
                break;
            case KeyEvent.VK_P:
                if(!justUnpaused)
                    pauseUnpause();
            default:
                break;

        }

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }


    public void spawnPiece()
    {
        Random random = new Random();

        Piece p = new Piece(0, testModel.getCurrentGame().getField().getCoordinates()[0].length/2-1, random.nextInt(Piece.PIECE_BITS.length));
        boolean gameContinues = testModel.getCurrentGame().getField().spawn(p);

        if (gameContinues == false)
        {
            fall.stop();
            rotate.stop();
            testModel.endGame();

            

        }
    }

    public static void main(String[] args)
    {
        Player p = new Player("Ferit");
        Field f = new Field(24, 14);
        Quadrop testModel = new Quadrop();
        
        testModel.newGame(p, f);
        testModel.getCurrentGame().setRotateHandicap(false);
        
        View testView = new View(testModel);
        Controller testController = new Controller(testModel);
        testView.addKeyListener(testController);

        testModel.getCurrentGame().addObserver(testView);
        testModel.getCurrentGame().getField().addObserver(testView);
        
        testController.timerDelay = testModel.getCurrentGame().getDelay();
        testView.setVisible(true);

        
    }

    public void pauseUnpause()
    {
        if (!testModel.getCurrentGame().getPaused())
        {
            fall.stop();
            rotate.stop();
            testModel.getCurrentGame().setPaused(true);
        } else
        {
            testModel.getCurrentGame().setPaused(false);
            fall.restart();
            rotate.restart();

        }
    }

    private void fall()
    {
        while (testModel.getCurrentGame().getField().getCurrentPiece() != null)
        {
            testModel.getCurrentGame().getField().fall();
        }
    }

    private void moveDown()
    {

        testModel.getCurrentGame().getField().fall();
    }

    private void moveLeft()
    {
        testModel.getCurrentGame().getField().moveLeft();
    }

    private void moveRight()
    {
        testModel.getCurrentGame().getField().moveRight();
    }

    private void rotate()
    {
        testModel.getCurrentGame().getField().rotateCurrentPiece();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "timer":
            {
                if (!testModel.getCurrentGame().isEnded())
                {
                    if (testModel.getCurrentGame().getField().getCurrentPiece() == null)
                    {
                        spawnPiece();
                    } else
                    {
                        moveDown();
                        
                        timerDelay = testModel.getCurrentGame().getDelay();
                        fall.setDelay(timerDelay);
                        rotate.setDelay(timerDelay*2);
                        System.out.println("DELAY " + timerDelay);
                    }
                }
                
            }
            ;
            break;
            case "rotate":
            {
                if (!testModel.getCurrentGame().isEnded())
                {
                    if (testModel.getCurrentGame().getField().getCurrentPiece() == null)
                    {
                        spawnPiece();
                    } else
                    {
                        if(testModel.getCurrentGame().isRotateHandicap())
                            rotate();
                    }
                }
            }
            default:
        }

    }

}
