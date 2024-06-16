package Logic;

import jdk.jfr.Event;

import java.util.EventObject;

public class MoveObject extends EventObject
{
    private Field moveFrom;
    private Field moveTo;

    public MoveObject(Object source)
    {
        super(source);
    }

    public void setMoveFrom(int row, int column)
    {
        this.moveFrom = new Field(row, column);
    }

    public void setMoveTo(int row, int column)
    {
        this.moveTo = new Field(row, column);
    }

    public Field getMoveFrom()
    {
        return moveFrom;
    }

    public Field getMoveTo()
    {
        return moveTo;
    }
}
