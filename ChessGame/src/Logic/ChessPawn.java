package Logic;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class ChessPawn
{
    private Field boardField;
    private Color color;
    private Board board;

    private static boolean blackHasMove = false;

    private ImageIcon chessIcon;

    public ChessPawn(Field boardField, Color color, Board board)
    {
        URL url = null;

        try
        {
            url = new URL("https://www.iconsdb.com/icons/preview/brown/square-xxl.png");
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }

        this.chessIcon = new ImageIcon(url);
        this.boardField = boardField;
        this.color = color;
        this.board = board;
    }

    public void moveTo(Field field)
    {
        // nie można dwa razy pod rząd tym samym kolorem
        setBlackHasMove(!isBlackHasMove());


            this.boardField.setCurrentPawnOnField(new ChessPawn(this.boardField, Color.EMPTY, board)
            {
                @Override
                public boolean isValidMove(Field field)
                {
                    return false;
                }
            });

            Field newField = this.getBoard().getBoardFields()[field.getRow()][field.getColumn()];

            if (this.getColor() == Color.WHITE && field.getRow() == 0)
            {
                System.out.println("damka");

                ChessPawn damka = new ChessPawn(this.boardField, Color.WHITE, board)
                {
                    @Override
                    public boolean isValidMove(Field field)
                    {
                        return false;
                    }

                    @Override
                    public void setChessIcon(ImageIcon chessIcon)
                    {
                            super.setChessIcon(chessIcon);
                    }
                };


                try
                {
                    damka.setChessIcon(new ImageIcon(new URL("https://c8.alamy.com/comp/A896JD/red-circle-road-sign-on-white-background-A896JD.jpg")));
                }
                catch (MalformedURLException e)
                {
                    throw new RuntimeException(e);
                }

                newField.setCurrentPawnOnField(damka);
            }
            else
            {
                newField.setCurrentPawnOnField(this);

            }

            this.boardField = newField;

    }

    public abstract boolean isValidMove(Field field);

    public Field getChessPawnFieldOnBoard()
    {
        return boardField;
    }

    public Color getColor()
    {
        return color;
    }

    public Board getBoard()
    {
        return board;
    }

    public void setBoardField(Field boardField)
    {
        this.boardField = boardField;
    }

    public boolean haveDifferentColor(ChessPawn chessPawn)
    {
        return this.color == chessPawn.color;
    }

    public void setChessIcon(ImageIcon chessIcon)
    {
        this.chessIcon = chessIcon;
    }

    public ImageIcon getChessIcon()
    {
        Image image = this.chessIcon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        this.chessIcon.setImage(image);
        return chessIcon;
    }

    public boolean isBlackHasMove()
    {
        return blackHasMove;
    }

    public void setBlackHasMove(boolean blackHasMove)
    {
        this.blackHasMove = blackHasMove;
    }
}
