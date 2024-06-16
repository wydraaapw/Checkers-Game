package GUI;

import Logic.TableModel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardTable extends JTable
{
    private TableModel tableModel;

    public BoardTable()
    {
        tableModel = new TableModel();

        this.setModel(tableModel);

        setCellSize(50);

    }
    public void setCellSize(int size)
    {
        setRowHeight(size);

        TableColumn tableColumn;
        for (int i = 0; i < this.getColumnCount(); i++)
        {
            tableColumn = this.getColumnModel().getColumn(i);

            tableColumn.setPreferredWidth(size);
        }
    }

    public TableModel getTableModel()
    {
        return tableModel;
    }
}
