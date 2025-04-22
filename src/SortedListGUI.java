import javax.swing.*;
import java.awt.*;

public class SortedListGUI extends JFrame {
    SortedList sortedList;

    JPanel mainPanel, searchPanel, displayPanel, controlPanel, inputPanel, inputInnerPanel, dialogPanel;

    JLabel searchLbl, inputLabel;
    JTextArea displayTA, searchTA, listTA;
    JScrollPane scroller, scroller2;

    JButton exitBtn, searchBtn, addStringBtn;
    JTextField inputField;

    public SortedListGUI(SortedList sortedList) {
        this.sortedList = sortedList;

        setTitle("Text Finder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        createSearchPanel();
        createDisplayPanel();
        createControlPanel();
        createInputPanel();
        add(mainPanel);
        setVisible(true);
    }
    private void createSearchPanel() {
        searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        searchLbl = new JLabel("  Search  ");
        searchTA = new JTextArea();
        searchBtn = new JButton("Search");
        searchBtn.addActionListener(e -> {
            if (searchTA.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter text to search");
            }
            else {
                displayTA.setText(sortedList.search(searchTA.getText().trim()));
            }
        });
        searchPanel.add(searchTA, BorderLayout.CENTER);
        searchPanel.add(searchBtn, BorderLayout.EAST);
        searchPanel.add(searchLbl, BorderLayout.WEST);
        mainPanel.add(searchPanel, BorderLayout.NORTH);
    }
    private void createDisplayPanel() {
        displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(1, 2));
        displayTA = new JTextArea();
        displayTA.setEditable(false);

        scroller = new JScrollPane(displayTA);

        displayPanel.add(scroller);

        mainPanel.add(displayPanel, BorderLayout.CENTER);
    }
    private void createControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1,1));
        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this,"Are you sure you want to quit?","Confirm Exit",JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        controlPanel.add(exitBtn);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
    }
    private void createInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        dialogPanel = new JPanel();
        inputInnerPanel = new JPanel();
        inputInnerPanel.setLayout(new BorderLayout());
        inputInnerPanel.add(dialogPanel, BorderLayout.CENTER);
        inputPanel.add(inputInnerPanel, BorderLayout.SOUTH);

        listTA = new JTextArea();
        listTA.setEditable(false);
        scroller2 = new JScrollPane(listTA);
        inputPanel.add(scroller2, BorderLayout.CENTER);

        inputLabel = new JLabel("Enter strings");
        inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputInnerPanel.add(inputLabel, BorderLayout.NORTH);
        inputField = new JTextField(20);
        dialogPanel.add(inputField);
        addStringBtn = new JButton("Add String");
        addStringBtn.addActionListener(e -> {
            if (inputField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a string");
            }
            else {
                sortedList.addString(inputField.getText().trim());
                System.out.println("Added string: " + inputField.getText().trim());
                inputField.setText("");
                updateListTA();
            }
        });
        inputInnerPanel.add(addStringBtn, BorderLayout.SOUTH);
        mainPanel.add(inputPanel, BorderLayout.WEST);
    }
    private void updateListTA() {
        listTA.setText("");
        int index = 0;
        for (String s : sortedList) {
            listTA.append(index + ": " + s + "\n");
            index++;
        }
    }
}
