import GUI.GUI;
import Logic.*;
import GUI.ConsolePanel;

import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        GameLogic gameLogic = new GameLogicImpl();
        GUI gui = new GUI(900,600, gameLogic);

        gameLogic.getBoardPanel().getBoardTable().getTableModel().updateGUI(new GUIUpdateEvent(gameLogic, gameLogic.getBoard().getBoardFields()));
        gameLogic.getBoardPanel().getBoardTable().getTableModel().fireTableDataChanged();


        gui.addNewMoveFromGUIListener((GameLogicImpl)gameLogic);
        ((GameLogicImpl) gameLogic).addNewGUIAfterMoveListener(gameLogic.getBoardPanel().getBoardTable().getTableModel());

        // game notifications console
        ConsolePanel consolePanel = new ConsolePanel();
        ((GameLogicImpl) gameLogic).addConsoleListener(consolePanel);
        gui.add(consolePanel, BorderLayout.WEST);
        gui.addNewConsoleListener(consolePanel);

    }
}

