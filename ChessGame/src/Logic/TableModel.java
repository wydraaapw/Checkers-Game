package Logic;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class TableModel extends AbstractTableModel implements GUIUpdateAfterMoveListener
{
    private String[] columnNames = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private Object[][] data;

    public TableModel()
    {
        data = new Object[8][8];

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\pawel\\Downloads\\PAWNICON.jpg");
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
                data[i][j] = new Object();
            }
        }

    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    public Class<?> getColumnClass(int c)
    {
        return getValueAt(0,c).getClass();
    }

    @Override
    public int getRowCount()
    {
        return data.length;
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void updateGUI(GUIUpdateEvent obj)
    {

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                data[i][j] = obj.getBoard()[i][j].getCurrentPawnOnField().getChessIcon();
            }
        }
        fireTableDataChanged();
    }

    public Object[][] getData()
    {
        return data;
    }
}


//naprawic trzeba bo zle dziala dalej wyswietlanie na podstawie field[][], ogarnad jak dzial fireDataChanged