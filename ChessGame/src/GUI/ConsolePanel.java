package GUI;

import Logic.ConsoleEventObject;
import Logic.ConsoleListener;

import javax.swing.*;
import java.awt.*;

public class ConsolePanel extends JPanel implements ConsoleListener
{
    private String currentGameMessage;

    private JTextArea textArea;

    public ConsolePanel()
    {
        Color color = new Color(102, 51, 0);
        this.setBackground(color);
        this.textArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(textArea);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Font font = new Font(Font.DIALOG, Font.ITALIC, 10);
        textArea.setFont(font);
        textArea.setForeground(Color.RED);

        this.add(jScrollPane);
    }

    public String getCurrentGameMessage()
    {
        return currentGameMessage;
    }

    public JTextArea getTextArea()
    {
        return textArea;
    }

    public void setCurrentGameMessage(String currentGameMessage)
    {
        this.currentGameMessage = currentGameMessage;
    }

    @Override
    public void doConsoleAction(ConsoleEventObject consoleEventObject)
    {
        String message = "\n" + consoleEventObject.getConsoleMessage();

        this.textArea.append(message);

    }
}
