/*
 * Created by JFormDesigner on Sun Aug 30 16:57:04 CST 2020
 */

package com.vmsg.update.window;

import com.vmsg.update.thread.MainThread;
import com.vmsg.update.thread.UpdateEtc;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

/**
 * @author JERRY
 */
public class UpdateEtcAlterWindow extends JFrame {
    public UpdateEtcAlterWindow() {
        initComponents();
    }

    private void ckbox_flpActionPerformed(ActionEvent e) {
        // TODO add your code here
        btn_flp.setEnabled(ckbox_flp.isSelected());
        txt_flp.setEditable(ckbox_flp.isSelected());
    }

    private void ckbox_hostActionPerformed(ActionEvent e) {
        // TODO add your code here
        txt_host.setEditable(ckbox_host.isSelected());
    }

    private void ckbox_portActionPerformed(ActionEvent e) {
        // TODO add your code here
       txt_port.setEditable(ckbox_port.isSelected());
    }

    private void ckbox_fspActionPerformed(ActionEvent e) {
        // TODO add your code here
        txt_fsp.setEditable(ckbox_fsp.isSelected());
    }

    private void btn_saveActionPerformed(ActionEvent e) {
        // TODO add your code here
        UpdateEtc update_Etc=new UpdateEtc();
        UpdateEtc.alter_UpdateEtc(txt_flp.getText(), Integer.valueOf(txt_port.getText()), txt_fsp.getText(), txt_host.getText());
    }

    private void btn_flpActionPerformed(ActionEvent e) {
        // TODO add your code here
        JFileChooser fc=new JFileChooser();
        if(new File(MainThread.etcLocalFilePath).exists()){
            fc.setCurrentDirectory(new File(MainThread.etcLocalFilePath));
        }else{
            fc.setCurrentDirectory(new File("."));
        }
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result=fc.showOpenDialog(this);
        if(result==JFileChooser.APPROVE_OPTION){
            File file=fc.getSelectedFile();
            txt_flp.setText(file.getPath());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        panel3 = new JPanel();
        ckbox_flp = new JCheckBox();
        txt_flp = new JTextField();
        btn_flp = new JButton();
        panel4 = new JPanel();
        ckbox_host = new JCheckBox();
        txt_host = new JTextField();
        panel5 = new JPanel();
        ckbox_port = new JCheckBox();
        txt_port = new JTextField();
        panel6 = new JPanel();
        ckbox_fsp = new JCheckBox();
        txt_fsp = new JTextField();
        panel2 = new JPanel();
        btn_save = new JButton();

        //======== this ========
        setTitle("\u4fee\u6539\u914d\u7f6e\u6587\u4ef6");
        setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
        setMinimumSize(new Dimension(416, 249));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayout(4, 0));

            //======== panel3 ========
            {
                panel3.setLayout(new BorderLayout());

                //---- ckbox_flp ----
                ckbox_flp.setText("\u4e0b\u8f7d\u5230\u672c\u5730\u8def\u5f84:");
                ckbox_flp.setSelected(true);
                ckbox_flp.addActionListener(e -> ckbox_flpActionPerformed(e));
                panel3.add(ckbox_flp, BorderLayout.NORTH);
                panel3.add(txt_flp, BorderLayout.CENTER);

                //---- btn_flp ----
                btn_flp.setText("\u6d4f     \u89c8");
                btn_flp.addActionListener(e -> btn_flpActionPerformed(e));
                panel3.add(btn_flp, BorderLayout.EAST);
            }
            panel1.add(panel3);

            //======== panel4 ========
            {
                panel4.setLayout(new BorderLayout());

                //---- ckbox_host ----
                ckbox_host.setText("\u670d\u52a1\u5668IP:(\u4e0d\u5efa\u8bae\u4fee\u6539)");
                ckbox_host.addActionListener(e -> ckbox_hostActionPerformed(e));
                panel4.add(ckbox_host, BorderLayout.NORTH);

                //---- txt_host ----
                txt_host.setEditable(false);
                panel4.add(txt_host, BorderLayout.CENTER);
            }
            panel1.add(panel4);

            //======== panel5 ========
            {
                panel5.setLayout(new BorderLayout());

                //---- ckbox_port ----
                ckbox_port.setText("\u670d\u52a1\u5668\u7aef\u53e3:(\u4e0d\u5efa\u8bae\u4fee\u6539)");
                ckbox_port.addActionListener(e -> ckbox_portActionPerformed(e));
                panel5.add(ckbox_port, BorderLayout.NORTH);

                //---- txt_port ----
                txt_port.setEditable(false);
                panel5.add(txt_port, BorderLayout.CENTER);
            }
            panel1.add(panel5);

            //======== panel6 ========
            {
                panel6.setLayout(new BorderLayout());

                //---- ckbox_fsp ----
                ckbox_fsp.setText("\u670d\u52a1\u5668\u6587\u4ef6\u5730\u5740:(\u4e0d\u5efa\u8bae\u4fee\u6539)");
                ckbox_fsp.addActionListener(e -> ckbox_fspActionPerformed(e));
                panel6.add(ckbox_fsp, BorderLayout.NORTH);

                //---- txt_fsp ----
                txt_fsp.setEditable(false);
                panel6.add(txt_fsp, BorderLayout.CENTER);
            }
            panel1.add(panel6);
        }
        contentPane.add(panel1, BorderLayout.CENTER);

        //======== panel2 ========
        {
            panel2.setLayout(new BorderLayout());

            //---- btn_save ----
            btn_save.setText("\u4fdd     \u5b58");
            btn_save.addActionListener(e -> btn_saveActionPerformed(e));
            panel2.add(btn_save, BorderLayout.EAST);
        }
        contentPane.add(panel2, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JPanel panel3;
    private JCheckBox ckbox_flp;
    public JTextField txt_flp;
    private JButton btn_flp;
    private JPanel panel4;
    private JCheckBox ckbox_host;
    public JTextField txt_host;
    private JPanel panel5;
    private JCheckBox ckbox_port;
    public JTextField txt_port;
    private JPanel panel6;
    private JCheckBox ckbox_fsp;
    public JTextField txt_fsp;
    private JPanel panel2;
    private JButton btn_save;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
