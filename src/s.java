import javax.swing.*;
import java.awt.*;

public class s {
    public static void createAndShowGUI(){

        JFrame frame = new JFrame("Enigma Expedition: Path to Enlightenment");

        JLabel title = new JLabel("ENIGMA EXPEDITION: PATH TO ENLIGHTENMENT", JLabel.CENTER);
        Font heading = new Font("Times New Roman", Font.BOLD, 24);
        title.setFont(heading);
        title.setPreferredSize(new Dimension(1000, 100));
        title.setOpaque(true);
        title.setBackground(Color.BLACK);
        frame.getContentPane().add(title, BorderLayout.PAGE_START);

        JPanel pane = new JPanel(new FlowLayout());
        pane.setPreferredSize(new Dimension(300, 400));
        pane.setBackground(Color.PINK);
        pane.setOpaque(true);
        frame.getContentPane().add(pane, BorderLayout.CENTER);

        JButton Play = new JButton("Play");
        JButton Load = new JButton("Load");
        JButton htp = new JButton("How to Play");
        JButton fb = new JButton("Feedback");

        Play.setPreferredSize(new Dimension(100, 50));
        Play.setBackground(Color.YELLOW);

        Load.setPreferredSize(new Dimension(100, 50));
        Load.setBackground(Color.YELLOW);

        htp.setPreferredSize(new Dimension(100, 50));
        htp.setBackground(Color.YELLOW);

        fb.setPreferredSize(new Dimension(100, 50));
        fb.setBackground(Color.YELLOW);

        pane.add(Play);
        pane.add(Load);
        pane.add(htp);
        pane.add(fb);

        frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
