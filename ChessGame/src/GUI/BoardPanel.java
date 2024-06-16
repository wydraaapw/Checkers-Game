package GUI;

import Logic.Board;
import Logic.TableModel;
import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel
{
   private BoardTable boardTable;
    public BoardPanel()
    {
        this.boardTable = new BoardTable();
        Color color = new Color(152, 76, 0);
        this.setBackground(color);
        this.add(boardTable);
        this.setDoubleBuffered(true);
    }

    public BoardTable getBoardTable()
    {
        return boardTable;
    }
}
