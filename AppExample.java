import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import javax.imageio.*;
import javax.swing.*;

public class AppExample {
    public static void main(String avg[]) throws IOException
    {
        String appName = "AppExample";
        String imgFilename = "marsduke.png";
        JLabel textLabel = new JLabel("<html>Spirit Mars Rover, Pancam, "
            + "Sol 120, 2004-05-05, ID 2P137011382EFF4000P2563L5M1</html>");
        String links[] = {
            "https://areo.info/mer/spirit/120",
            "https://mars.nasa.gov/mer/gallery/all/spirit_p120.html"
        };
        JLabel linkLabels[] = {
            new JLabel("<html>Color image: <a href='"
                + links[0] + "'>" + links[0] + "</a></html>"),
            new JLabel("<html>Raw image, NASA / JPL-Caltech: <a href='"
                + links[1] + "'>" + links[1] + "</a></html>")
        };
        int space = 10;

        String appDir = AppExample.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (appDir.endsWith(".jar"))
        {
            appDir = new File(appDir).getParent();
        }
        BufferedImage img = ImageIO.read(new File(appDir, imgFilename));

        JFrame frame=new JFrame(appName);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(space, space, space, space));
        ImageIcon icon=new ImageIcon(img);
        JLabel iconLabel=new JLabel();
        iconLabel.setIcon(icon);
        panel.add(iconLabel);
        panel.add(Box.createRigidArea(new Dimension(0, space)));
        panel.add(textLabel);
        for (int i=0; i<2; i++) {
            String link = links[i];
            linkLabels[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            linkLabels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent ev) {
                    try {
                        Desktop.getDesktop().browse(new URI(link));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            panel.add(linkLabels[i]);
        }
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
