package Logic;

import GUI.BoardPanel;
import GUI.ConsolePanel;

import java.util.ArrayList;
import java.util.List;

public class GameLogicImpl implements GameLogic, GUINewMoveFromGUIListener
{
    private BoardPanel boardPanel;
    private Board board;

    private ConsoleEventObject consoleEventObject;
    private List<GUIUpdateAfterMoveListener> guiUpdateAfterMoveListenerList;

    private List<ConsoleListener> consoleListeners;

    public GameLogicImpl()
    {
        this.consoleListeners = new ArrayList<>();

        this.consoleEventObject = new ConsoleEventObject();

        boardPanel = new BoardPanel();
        board = new Board();
        this.guiUpdateAfterMoveListenerList = new ArrayList();

    }
    @Override
    public BoardPanel getBoardPanel()
    {
        return this.boardPanel;
    }

    @Override
    public Board getBoard()
    {
        return this.board;
    }


    @Override
    public void doNewMoveAction(MoveObject obj)
    {
        Field fromField = board.getFieldAt(obj.getMoveFrom().getRow(), obj.getMoveFrom().getColumn());
        Field toField = board.getFieldAt(obj.getMoveTo().getRow(), obj.getMoveTo().getColumn());

        if (fromField.getCurrentPawnOnField().getColor() == Color.EMPTY)
            consoleEventObject.setConsoleMessage("You can't move empty field!");
        else
        {
            ChessPawn chessPawn = board.getFieldAt(fromField.getRow(), fromField.getColumn()).getCurrentPawnOnField();

            if (chessPawn.isValidMove(toField))
            {
                chessPawn.moveTo(toField);
                consoleEventObject.setConsoleMessage("Pawn was moved!");
                fireGUIAfterMoveListeners();
            }
            else
            {
                consoleEventObject.setConsoleMessage("Invalid move, choose pawn to move again");
            }
        }

        fireConsoleListeners();

    }



    public void addNewGUIAfterMoveListener(GUIUpdateAfterMoveListener listener)
    {
        this.guiUpdateAfterMoveListenerList.add(listener);
    }

    public void fireGUIAfterMoveListeners()
    {
        GUIUpdateEvent guiUpdateEvent = new GUIUpdateEvent(this, board.getBoardFields());

        for (GUIUpdateAfterMoveListener listener : guiUpdateAfterMoveListenerList)
            listener.updateGUI(guiUpdateEvent);
    }

    public void addConsoleListener(ConsoleListener consoleListener)
    {
        this.consoleListeners.add(consoleListener);
    }

    public void fireConsoleListeners()
    {
        for (ConsoleListener consoleListener : consoleListeners)
        {
            consoleListener.doConsoleAction(consoleEventObject);
        }
    }
}
