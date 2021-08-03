import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame implements ActionListener {

    private JPanel txtPanel;
    private JTextArea txtArea;
    private JList<String> list;

    private JPanel btnPane;
    private JButton btn;

    public GUI() {
        super("Analizador lexico");
        this.setLayout(new BorderLayout());
        ((JPanel) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(8, 8, 0, 8));

        this.setResizable(false);
        this.setBounds(0, 0, 550, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.txtPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        this.getContentPane().add(txtPanel, BorderLayout.CENTER);

        this.txtArea = new JTextArea();
        this.txtArea.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(Color.GRAY, 2, true),
                            "Entrada de Texto", TitledBorder.LEFT, TitledBorder.TOP),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                    )
            );
        this.txtPanel.add(txtArea);

        this.list = new JList<>();
        this.list.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(Color.GRAY, 2, true),
                            "Lista de tokens", TitledBorder.RIGHT, TitledBorder.TOP),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                    )
            );
        this.txtPanel.add(list);

        this.btnPane = new JPanel();
        this.getContentPane().add(btnPane, BorderLayout.PAGE_END);

        this.btn = new JButton("  Analizar  ");
        this.btn.addActionListener(this);
        this.btnPane.add(btn);
    }

    public void launch() {
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
