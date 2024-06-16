package GUI;

import Logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame
{
    private GameLogic gameLogic;
    private List<GUINewMoveFromGUIListener> guiNewMoveFromGUIListeners;

    private List<ConsoleListener> consoleListenerList;
    int pawnChoice = 1;

    MoveObject moveObject;

    ConsoleEventObject consoleEventObject;
    public GUI(int width, int height, GameLogic gameLogic)
    {
        this.gameLogic = gameLogic;
        moveObject = new MoveObject(this);

        this.guiNewMoveFromGUIListeners= new ArrayList<>();

        this.add(gameLogic.getBoardPanel(), BorderLayout.CENTER);

        this.setSize(width,height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //resize
        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                int x = Math.min(getWidth(), getHeight());
                gameLogic.getBoardPanel().getBoardTable().setCellSize(x / 10);
            }
        });

        gameLogic.getBoardPanel().getBoardTable().addMouseListener(
                new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        int row = gameLogic.getBoardPanel().getBoardTable().rowAtPoint(e.getPoint());
                        int column = gameLogic.getBoardPanel().getBoardTable().columnAtPoint(e.getPoint());

                        if (pawnChoice == 1)
                        {
                            consoleEventObject.setConsoleMessage("Chosen move from field = row["+row+"], column["+column+"].");

                            fireConsoleListeners();

                            moveObject.setMoveFrom(row, column);
                            pawnChoice = 2;

                        }
                        else if (pawnChoice == 2)
                        {
                            consoleEventObject.setConsoleMessage("Chosen move to field = row["+row+"], column["+column+"].");

                            fireConsoleListeners();

                            moveObject.setMoveTo(row, column);
                            fireNewMoveFromGUIListeners();
                            pawnChoice = 1;

                        }


                    }
                }
        );


        this.consoleListenerList = new ArrayList<>();
        this.consoleEventObject = new ConsoleEventObject();

        this.setVisible(true);
    }

    public void addNewMoveFromGUIListener(GUINewMoveFromGUIListener chessListener)
    {
        this.guiNewMoveFromGUIListeners.add(chessListener);
    }

    public void fireNewMoveFromGUIListeners()
    {
        for (GUINewMoveFromGUIListener listener : guiNewMoveFromGUIListeners)
            listener.doNewMoveAction(moveObject);
    }

    public void addNewConsoleListener(ConsoleListener consoleListener)
    {
        this.consoleListenerList.add(consoleListener);
    }

    public void fireConsoleListeners()
    {
        for (ConsoleListener consoleListener : consoleListenerList)
        {
            consoleListener.doConsoleAction(this.consoleEventObject);
        }
    }



}
