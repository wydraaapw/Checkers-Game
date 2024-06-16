package Logic;

import java.util.EventObject;

public class GUIUpdateEvent extends EventObject
{
    private Field[][] board;

    public GUIUpdateEvent(Object source, Field[][] board)
    {
        super(source);
        this.board = board;
    }

    public Field[][] getBoard()
    {
        return board;
    }

    public void setBoard(Field[][] board)
    {
        this.board = board;
    }
}
