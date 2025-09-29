package generators;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class controller {
    
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("AETools Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create top panel for controls
        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // First element: Method selection dropdown
        JLabel methodLabel = new JLabel("Select Method:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        controlPanel.add(methodLabel, gbc);
        
        String[] methods = {"randomChar.main", "randomShip.main"};
        JComboBox<String> methodComboBox = new JComboBox<>(methods);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        controlPanel.add(methodComboBox, gbc);
        
        // Second element: Input box for arguments
        JLabel argsLabel = new JLabel("Arguments:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        controlPanel.add(argsLabel, gbc);
        
        JTextField argsTextField = new JTextField();
        argsTextField.setToolTipText("Enter arguments separated by spaces (leave empty for no arguments)");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        controlPanel.add(argsTextField, gbc);
        
        // Execute button
        JButton executeButton = new JButton("Execute");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        controlPanel.add(executeButton, gbc);
        
        // Third element: Output area
        JTextArea outputTextArea = new JTextArea(15, 40);
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        outputTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
        
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        // Add action listener to execute button
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedMethod = (String) methodComboBox.getSelectedItem();
                    String argsText = argsTextField.getText().trim();
                    
                    // Clear previous output
                    outputTextArea.setText("");
                    
                    // Parse arguments
                    String[] arguments = argsText.isEmpty() ? new String[0] : argsText.split("\\s+");
                    
                    // Capture System.out
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    PrintStream originalOut = System.out;
                    PrintStream capturedOut = new PrintStream(baos);
                    System.setOut(capturedOut);
                    
                    String result = "";
                    
                    try {
                        // Execute the selected method
                        if ("randomChar.main".equals(selectedMethod)) {
                            result = randomChar.main(arguments);
                        } else if ("randomShip.main".equals(selectedMethod)) {
                            result = randomShip.main(arguments);
                        }
                        
                        // Restore System.out
                        System.setOut(originalOut);
                        
                        // Get captured output
                        String capturedOutput = baos.toString();
                        
                        // Display results
                        StringBuilder output = new StringBuilder();
                        
                        if (!capturedOutput.isEmpty()) {
                            output.append(capturedOutput);
                        }
                        
                        if (!result.isEmpty()) {
                            output.append("Return Value: ").append(result);
                        }
                        
                        outputTextArea.setText(output.toString());
                        
                    } catch (Exception ex) {
                        // Restore System.out in case of exception
                        System.setOut(originalOut);
                        outputTextArea.setText("Error executing method: " + ex.getMessage() + "\n" + 
                                             "Stack trace:\n" + getStackTrace(ex));
                    }
                    
                } catch (Exception ex) {
                    outputTextArea.setText("Error: " + ex.getMessage() + "\n" + 
                                         "Stack trace:\n" + getStackTrace(ex));
                }
            }
        });
        
        // Add components to main panel
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add main panel to frame
        frame.add(mainPanel);
        
        // Make frame visible
        frame.setVisible(true);
    }
    
    private static String getStackTrace(Exception e) {
        java.io.StringWriter sw = new java.io.StringWriter();
        java.io.PrintWriter pw = new java.io.PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
