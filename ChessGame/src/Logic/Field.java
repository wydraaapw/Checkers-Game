package Logic;

public class Field
{
    private int row;
    private int column;
    private ChessPawn currentPawnOnField;

    public Field(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public ChessPawn getCurrentPawnOnField()
    {
        if (this.currentPawnOnField != null)
            return currentPawnOnField;
        else
            return new ChessPawn(this, Color.EMPTY, new Board())
            {
                @Override
                public boolean isValidMove(Field field)
                {
                    return false;
                }
            };
    }

    public void setCurrentPawnOnField(ChessPawn currentPawnOnField)
    {
        this.currentPawnOnField = currentPawnOnField;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public void setColumn(int column)
    {
        this.column = column;
    }
}
