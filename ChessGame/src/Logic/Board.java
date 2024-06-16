package Logic;

public class Board
{
    private Field[][] boardFields;

    public Board()
    {
        boardFields = new Field[8][8];

        for (int i = 0; i < boardFields.length; i++)
        {
            for (int j = 0; j < boardFields[0].length; j++)
            {
                boardFields[i][j] = new Field(i, j);
            }
        }

        for (int i = 0; i < boardFields.length; i++)
        {
            for (int j = 0; j < boardFields.length; j++)
            {
                if (i == 0 || i == 1)
                    boardFields[i][j].setCurrentPawnOnField(new CheckersPawn(boardFields[i][j],Color.BLACK, this));
                else if (i == 6 || i ==7)
                    boardFields[i][j].setCurrentPawnOnField(new CheckersPawn(boardFields[i][j],Color.WHITE, this));
                else
                    boardFields[i][j].setCurrentPawnOnField(new ChessPawn(boardFields[i][j], Color.EMPTY, this)
                    {
                        @Override
                        public boolean isValidMove(Field field)
                        {
                            return false;
                        }

                    }
                    );
            }
        }

    }

    public Field[][] getBoardFields()
    {
        return boardFields;
    }

    public boolean isInBoard(int row, int column)
    {
        return row < boardFields.length && column < boardFields[0].length && row >= 0 && column >= 0;
    }

    public Field getFieldAt(int row, int column)
    {
        return boardFields[row][column];
    }

}


// zrobić aby ten sam kolor nie mógł sie ruszyc dwa razy pod rząd, dodać awans na damke