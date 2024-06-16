package Logic;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class CheckersPawn extends ChessPawn
{
    public CheckersPawn(Field boardField, Color color, Board board)
    {
        super(boardField, color, board);

        URL url;

        if (this.getColor() == Color.BLACK)
        {
            try
            {
                 url = new URL("https://www.iconsdb.com/icons/preview/black/circle-xxl.png");
                 this.setChessIcon(new ImageIcon(url));
            }
            catch (MalformedURLException e)
            {
                throw new RuntimeException(e);
            }
        }

        else if (this.getColor() == Color.WHITE)
        {
            try
            {
                url = new URL("https://creazilla-store.fra1.digitaloceanspaces.com/emojis/43437/brown-circle-emoji-clipart-md.png");
                this.setChessIcon(new ImageIcon(url));
            }
            catch (MalformedURLException e)
            {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void moveTo(Field field)
    {
        super.moveTo(field);
    }


    // zrobic dobre is valid move dla pionka z warcab
    @Override
    public boolean isValidMove(Field fieldToMove)
    {
        int x = this.getColor() == Color.BLACK ? 1 : -1;

        if (!isBlackHasMove() && this.getColor().equals(Color.BLACK))
        {
            return false;
        }

        if (isBlackHasMove() && this.getColor().equals(Color.WHITE))
        {
            return false;
        }

        // rozbijam na dwa przypadki w zaleznosci czy ruch jest w lewo czy w prawo po przekatnej
            if (this.getChessPawnFieldOnBoard().getRow() + x == fieldToMove.getRow() &&
                    this.getChessPawnFieldOnBoard().getColumn() + x == fieldToMove.getColumn())
            {
                // ruch na puste pole
                if (fieldToMove.getCurrentPawnOnField().getColor().equals(Color.EMPTY))
                    return true;
                    // bicie pionka
                else
                {
                    if (getBoard().isInBoard(this.getChessPawnFieldOnBoard().getRow() + 2 * x,
                            this.getChessPawnFieldOnBoard().getColumn() + 2 * x))
                    {
                        if (getBoard().getFieldAt(this.getChessPawnFieldOnBoard().getRow() + 2 * x,
                                this.getChessPawnFieldOnBoard().getColumn() + 2 * x).getCurrentPawnOnField().getColor().equals(Color.EMPTY))
                        {
                            fieldToMove.setCurrentPawnOnField(new ChessPawn(this.getChessPawnFieldOnBoard(),Color.EMPTY, this.getBoard())
                            {
                                @Override
                                public boolean isValidMove(Field field)
                                {
                                    return false;
                                }
                            });

                            fieldToMove.setRow(fieldToMove.getRow() + x);
                            fieldToMove.setColumn(fieldToMove.getColumn() + x);
                            return true;
                        }
                    }
                }
            }
            // rozbijam na dwa przypadki w zaleznosci czy ruch jest w lewo czy w prawo po przekatnej
            else if (this.getChessPawnFieldOnBoard().getRow() + x == fieldToMove.getRow() &&
                    this.getChessPawnFieldOnBoard().getColumn() - x == fieldToMove.getColumn())
            {
                // ruch na puste pole
                if (fieldToMove.getCurrentPawnOnField().getColor().equals(Color.EMPTY))
                    return true;
                    // bicie pionka
                else
                {
                    if (getBoard().isInBoard(this.getChessPawnFieldOnBoard().getRow() + 2 * x,
                            this.getChessPawnFieldOnBoard().getColumn() - 2 * x))
                    {
                        if (getBoard().getFieldAt(this.getChessPawnFieldOnBoard().getRow() + 2 * x,
                                this.getChessPawnFieldOnBoard().getColumn() - 2 * x).getCurrentPawnOnField().getColor().equals(Color.EMPTY))
                        {
                            fieldToMove.setCurrentPawnOnField(new ChessPawn(this.getChessPawnFieldOnBoard(),Color.EMPTY, this.getBoard())
                            {
                                @Override
                                public boolean isValidMove(Field field)
                                {
                                    return false;
                                }
                            });

                            fieldToMove.setRow(fieldToMove.getRow() + x);
                            fieldToMove.setColumn(fieldToMove.getColumn() - x);
                            return true;
                        }
                    }
                }
            }

        return false;
    }
}
