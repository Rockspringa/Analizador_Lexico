import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class GUI extends JFrame implements ActionListener {

    static {
        UIManager.put("ScrollBar.thumbDarkShadow", Color.LIGHT_GRAY);
        UIManager.put("ScrollBar.thumbHighlight", Color.DARK_GRAY);
        UIManager.put("ScrollBar.thumbShadow", Color.DARK_GRAY);
        UIManager.put("ScrollBar.background", Color.GRAY);
        UIManager.put("ScrollBar.thumb", Color.DARK_GRAY);
        UIManager.put("ScrollBar.track", Color.GRAY);
        UIManager.put("ScrollBar.width", 10);

        UIManager.put("ScrollPane.background", Color.LIGHT_GRAY);
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
    private JScrollPane scrollTxt;
    private JTextArea txtArea;

    private JScrollPane scrollList;
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
        this.txtArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            
        this.scrollTxt = new JScrollPane(txtArea);
        this.scrollTxt.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        this.scrollTxt.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
        this.scrollTxt.setBorder(
                    BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK, 2, true),
                        " Entrada de Texto ", TitledBorder.LEFT, TitledBorder.TOP)
                );
        this.txtPanel.add(scrollTxt);

        this.listModel = new DefaultListModel<Object>();

        this.list = new JList<>();
        this.list.setModel(listModel);
        this.list.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.scrollList = new JScrollPane(this.list);
        this.scrollList.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        this.scrollList.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
        this.scrollList.setBorder(
                    BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK, 2, true),
                        " Lista de tokens ", TitledBorder.RIGHT, TitledBorder.TOP)
                );
        this.txtPanel.add(scrollList);

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
