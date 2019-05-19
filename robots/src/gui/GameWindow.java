package gui;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GameWindow extends JInternalFrame
{
    private final GameVisualizer m_visualizer;
    public static Database database = new Database();

    public GameWindow() throws IOException {
        super("Игровое поле", true, true, true, true);
        m_visualizer = new GameVisualizer();
        database.initDatabase();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }

    public GameVisualizer getVisualizer(){
        return m_visualizer;
    }

}
