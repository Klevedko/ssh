package ssh;

import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.AccessControlContext;

public class SimpleGUI extends JFrame {

    private JButton buttonGo = new JButton("Start Report");
    private JButton buttonSQL = new JButton("Click here to view SQL-query");
    private JButton buttonSAVE = new JButton("Save");
    private JTextField input1 = new JTextField("2017/07/01", 5);
    private JTextField input2 = new JTextField("2017/07/03", 5);
    private JTextArea input3;
    private JTextField input4 = new JTextField("Ezs4N93oAcZQr8koZ9", 5);
    private JLabel label1 = new JLabel("  date1 ( Example 2017/01/01):");
    private JLabel label2 = new JLabel("  date2 ( Example 2017/01/01):");
    private JLabel label3 = new JLabel("  SQL-query:");
    private JLabel label4 = new JLabel("  SSH login password");

    public SimpleGUI(String sql) {
        super("Report from bars-Jira");
        input3 = new JTextArea(sql);
        this.setBounds(400, 600, 450, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(5, 2, 2, 2));
        container.add(label1);
        container.add(input1);
        container.add(label2);
        container.add(input2);
        container.add(label3);
        container.add(buttonSQL);
        container.add(label4);
        container.add(input4);
        buttonSQL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleGUI r = new SimpleGUI(4, App.sql);
                r.setVisible(true);
            }
        });

        buttonGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App.go(getter1(),getter2(),getter4());
            }
        });

        container.add(buttonGo);
    }

    public SimpleGUI(int w, String sql) {
        super("sql");
        input3 = new JTextArea(App.sql);
        this.setBounds(400, 100, 630, 630);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        input3.setBounds(0, 0, 600, 550);
        buttonSAVE.setBounds(0, 550, 600, 30);
        buttonSAVE.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App.sql = (input3.getText().toString());
                input3.setText(App.sql);
                input3.invalidate();
                dispose();
            }
        });
        panel.add(input3, BorderLayout.NORTH);
        panel.add(buttonSAVE, BorderLayout.SOUTH);
        getContentPane().add(panel);
        setPreferredSize(new Dimension(550, 600));
    }
    public String getter1() {
        return input1.getText().toString();
    }

    public String getter2() {
        return input2.getText().toString();
    }

    public String getter3() {
        return input3.getText().toString();
    }

    public String getter4() {
        return input4.getText().toString();
    }
}