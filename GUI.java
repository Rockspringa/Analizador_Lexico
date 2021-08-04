import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame implements ActionListener {

    static {
        UIManager.put("TextArea.background", Color.LIGHT_GRAY);
        UIManager.put("Panel.background", Color.LIGHT_GRAY);
        UIManager.put("List.background", Color.LIGHT_GRAY);

        UIManager.put("Button.background", Color.DARK_GRAY);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.select", Color.BLACK);
        UIManager.put("Button.focus", Color.WHITE);
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(10, 15, 10, 15));
    }

    private JPanel txtPanel;
    private JTextArea txtArea;
    private JList<Object> list;
    private DefaultListModel<Object> listModel;

    private JPanel btnPane;
    private JButton btn;

    public GUI() {
        super("Analizador lexico");
        this.setLayout(new BorderLayout());
        ((JPanel) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(8, 8, 0, 8));

        this.setBounds(0, 0, 550, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.txtPanel = new JPanel(new GridLayout(1, 2, 8, 0));
        this.getContentPane().add(txtPanel, BorderLayout.CENTER);

        this.txtArea = new JTextArea();
        this.txtArea.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(Color.BLACK, 2, true),
                            " Entrada de Texto ", TitledBorder.LEFT, TitledBorder.TOP),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                    )
            );
        this.txtPanel.add(txtArea);

        this.listModel = new DefaultListModel<Object>();

        this.list = new JList<>();
        this.list.setModel(listModel);
        this.list.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(Color.BLACK, 2, true),
                            " Lista de tokens ", TitledBorder.RIGHT, TitledBorder.TOP),
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
        this.listModel.clear();

        if (txtArea.getText().equals("") || txtArea.getText().equals("\n"))
            this.listModel.addElement("Error: No se ingreso ningun token.");
        else {
            for (Object s : Analizador.analizar(txtArea.getText()))
                this.listModel.addElement(s);
        }
        
        this.txtArea.setText("");
    }
}
