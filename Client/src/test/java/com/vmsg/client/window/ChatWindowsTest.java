/*
 * Created by JFormDesigner on Fri Sep 18 13:55:56 CST 2020
 */

package com.vmsg.client.window;

import javax.swing.*;
import java.awt.*;

/**
 * @author JERRY
 */
public class ChatWindowsTest extends JFrame {

    public ChatWindowsTest() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        panel5 = new JPanel();

        //======== this ========
        setMinimumSize(new Dimension(400, 300));
        setVisible(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {

                //======== panel5 ========
                {
                    panel5.setLayout(new BorderLayout());
                }
                scrollPane1.setViewportView(panel5);
            }
            panel1.add(scrollPane1, BorderLayout.CENTER);
        }
        contentPane.add(panel1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JPanel panel5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
