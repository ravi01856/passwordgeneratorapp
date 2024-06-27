import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasswordGeneratorApp extends JFrame {
    // GUI components
    private JTextField lengthField;
    private JCheckBox includeUpperCase;
    private JCheckBox includeLowerCase;
    private JCheckBox includeNumbers;
    private JCheckBox includeSpecialChars;
    private JButton generateButton;
    private JTextArea passwordArea;

    public PasswordGeneratorApp() {
        setTitle("Password Generator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        lengthField = new JTextField(10);
        includeUpperCase = new JCheckBox("Include Uppercase");
        includeLowerCase = new JCheckBox("Include Lowercase");
        includeNumbers = new JCheckBox("Include Numbers");
        includeSpecialChars = new JCheckBox("Include Special Characters");
        generateButton = new JButton("Generate Password");
        passwordArea = new JTextArea(5, 20);
        passwordArea.setEditable(false);

        // Panel to hold components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1));
        mainPanel.add(new JLabel("Password Length:"));
        mainPanel.add(lengthField);
        mainPanel.add(includeUpperCase);
        mainPanel.add(includeLowerCase);
        mainPanel.add(includeNumbers);
        mainPanel.add(includeSpecialChars);
        mainPanel.add(generateButton);

        // Add action listener to generate button
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });

        // Panel for displaying generated password
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(new JLabel("Generated Password:"));
        passwordPanel.add(passwordArea);

        // Add panels to JFrame
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(passwordPanel, BorderLayout.SOUTH);
    }

    private void generatePassword() {
        int length = 12; // default length
        try {
            length = Integer.parseInt(lengthField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for password length. Using default length (12).");
        }

        boolean useUpperCase = includeUpperCase.isSelected();
        boolean useLowerCase = includeLowerCase.isSelected();
        boolean useNumbers = includeNumbers.isSelected();
        boolean useSpecialChars = includeSpecialChars.isSelected();

        String password = generateRandomPassword(length, useUpperCase, useLowerCase, useNumbers, useSpecialChars);
        passwordArea.setText(password);
    }

    private String generateRandomPassword(int length, boolean useUpperCase, boolean useLowerCase, boolean useNumbers, boolean useSpecialChars) {
        StringBuilder password = new StringBuilder();
        String upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numberChars = "0123456789";
        String specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?";

        String validChars = "";
        if (useUpperCase) {
            validChars += upperCaseChars;
        }
        if (useLowerCase) {
            validChars += lowerCaseChars;
        }
        if (useNumbers) {
            validChars += numberChars;
        }
        if (useSpecialChars) {
            validChars += specialChars;
        }

        if (validChars.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one character type.");
            return "";
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * validChars.length());
            password.append(validChars.charAt(randomIndex));
        }

        return password.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PasswordGeneratorApp().setVisible(true);
            }
        });
    }
}
