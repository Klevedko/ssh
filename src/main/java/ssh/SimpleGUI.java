package ssh;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

import ssh.App;

public class SimpleGUI extends JFrame {

    private JButton buttonGo = new JButton("Start Report");
    private JButton buttonSQL = new JButton("Click here to view SQL-query");
    private JButton buttonSAVE = new JButton("Save");
    private JTextField input1 = new JTextField("2017/06/30", 5);
    private JTextField input2 = new JTextField("2017/07/03", 5);
    private JTextArea input3;
    private JTextField input4 = new JTextField("Ezs4N93oAcZQr8koZ9", 5);
    private JTextField inputNameFile = new JTextField("Name", 5);
    private JTextField inputDB = new JTextField("FOMS", 5);
    private JLabel label1 = new JLabel("  date1 ( Example 2017/01/01):");
    private JLabel label2 = new JLabel("  date2 ( Example 2017/01/01):");
    private JLabel label3 = new JLabel("  SQL-query:");
    private JLabel label4 = new JLabel("  SSH login password");
    private JLabel labelNameFile = new JLabel("  Save to file:");
    private JLabel labelQuartal = new JLabel("  Report by Quartals ?");
    private JLabel labelQuartal1 = new JLabel("  Quartal 1?");
    private JLabel labelQuartal2 = new JLabel("  Quartal 2?");
    private JLabel labelQuartal3 = new JLabel("  Quartal 3?");
    private JLabel labelQuartal4 = new JLabel("  Quartal 4?");
    private JLabel labelDB = new JLabel("  Project:(FOMS or ERZ)");
    private Checkbox checkboxQuartal = new Checkbox();
    private Checkbox checkboxQuartal1 = new Checkbox();
    private Checkbox checkboxQuartal2 = new Checkbox();
    private Checkbox checkboxQuartal3 = new Checkbox();
    private Checkbox checkboxQuartal4 = new Checkbox();


    public SimpleGUI(String sql) {
        super("Report from Jira");
        this.input3 = new JTextArea(sql);
        this.setAlwaysOnTop(true);
        this.setBounds(220, 400, 650, 300);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(12, 2, 2, 2));
        container.add(this.label1);
        container.add(this.input1);
        container.add(this.label2);
        container.add(this.input2);
        container.add(this.labelQuartal);
        container.add(this.checkboxQuartal);
        container.add(this.labelQuartal1);
        container.add(this.checkboxQuartal1);
        container.add(this.labelQuartal2);
        container.add(this.checkboxQuartal2);
        container.add(this.labelQuartal3);
        container.add(this.checkboxQuartal3);
        container.add(this.labelQuartal4);
        container.add(this.checkboxQuartal4);
        container.add(this.labelDB);
        container.add(this.inputDB);
        container.add(this.label3);
        container.add(this.buttonSQL);
        container.add(this.label4);
        container.add(this.input4);
        container.add(this.labelNameFile);
        container.add(this.inputNameFile);
        container.add(this.buttonGo);
        this.setvisiblefalse(false);
        this.buttonSQL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleGUI r = new SimpleGUI(4, App.sql);
                r.setVisible(true);
            }
        });
        this.buttonGo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                SimpleGUI.super.setVisible(false);
                App.go(SimpleGUI.this.getter1(), SimpleGUI.this.getter2(), SimpleGUI.this.getter4(), SimpleGUI.this.getter5(), SimpleGUI.this.getter6(), SimpleGUI.this.checkboxQuartal.getState(), SimpleGUI.this.getterState());
            }
        });
        this.checkboxQuartal.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(SimpleGUI.this.checkboxQuartal.getState()) {
                    SimpleGUI.this.setvisiblefalse(true);
                } else {
                    SimpleGUI.this.setvisiblefalse(false);
                }

            }
        });
    }

    public SimpleGUI(int w, String sql) {
        super("sql");
        final JTextArea input3 = new JTextArea(App.sql);
        this.setBounds(100, 100, 630, 630);
        JPanel panel = new JPanel();
        panel.setLayout((LayoutManager)null);
        this.setAlwaysOnTop(true);
        input3.setBounds(0, 0, 600, 550);
        this.buttonSAVE.setBounds(0, 550, 600, 30);
        this.buttonSAVE.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App.sql = input3.getText().toString();
                input3.setText(App.sql);
                input3.invalidate();
                SimpleGUI.this.dispose();
            }
        });
        panel.add(input3, "North");
        panel.add(this.buttonSAVE, "South");

        this.getContentPane().add(panel);
        this.setPreferredSize(new Dimension(550, 600));
    }

    public String getter1() {
        return this.input1.getText().toString();
    }

    public String getter2() {
        return this.input2.getText().toString();
    }

    public String getter4() {
        return this.input4.getText().toString();
    }

    public String getter5() {
        return this.inputNameFile.getText().toString();
    }

    public String getter6() {
        return this.inputDB.getText().toString();
    }

    public boolean[] getterState() {
        boolean[] arr = new boolean[]{this.checkboxQuartal1.getState(), this.checkboxQuartal2.getState(), this.checkboxQuartal3.getState(), this.checkboxQuartal4.getState()};
        return arr;
    }

    public void setvisiblefalse(boolean f) {
        this.label1.setVisible(!f);
        this.input1.setVisible(!f);
        this.label2.setVisible(!f);
        this.input2.setVisible(!f);
        this.checkboxQuartal1.setVisible(f);
        this.checkboxQuartal2.setVisible(f);
        this.checkboxQuartal3.setVisible(f);
        this.checkboxQuartal4.setVisible(f);
        this.checkboxQuartal1.setState(f);
        this.checkboxQuartal2.setState(f);
        this.checkboxQuartal3.setState(f);
        this.checkboxQuartal4.setState(f);
        this.labelQuartal1.setVisible(f);
        this.labelQuartal2.setVisible(f);
        this.labelQuartal3.setVisible(f);
        this.labelQuartal4.setVisible(f);
    }
}
