import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMICalculator {

    private JFrame mainFrame;
    private JPanel firstPanel;
    private JPanel secondPanel;
    private CardLayout cardLayout;

    public BMICalculator() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("BMI Calculator");
        mainFrame.setSize(500, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new CardLayout());

        cardLayout = new CardLayout();
        mainFrame.setLayout(cardLayout);

        createFirstPanel();
        createSecondPanel();

        mainFrame.add(firstPanel, "1");
        mainFrame.add(secondPanel, "2");

        mainFrame.setVisible(true);
    }

    private void createFirstPanel() {
        firstPanel = new JPanel();
        firstPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("BMI Calculator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        firstPanel.add(titleLabel, BorderLayout.NORTH);

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setText("This application calculates your Body Mass Index (BMI).\n\n" +
                "BMI is a simple index of weight-for-height that is commonly used to classify underweight, " +
                "overweight and obesity in adults.\n\n" +
                "Created by: [Your Name Here]");
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionArea.setBackground(firstPanel.getBackground());
        firstPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

        JButton nextButton = new JButton("Next →");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainFrame.getContentPane(), "2");
            }
        });
        firstPanel.add(nextButton, BorderLayout.SOUTH);
    }

    private void createSecondPanel() {
        secondPanel = new JPanel();
        secondPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("BMI Calculator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        secondPanel.add(titleLabel, gbc);

        JLabel heightLabel = new JLabel("Height (cm):");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        secondPanel.add(heightLabel, gbc);

        JTextField heightField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        secondPanel.add(heightField, gbc);

        JLabel weightLabel = new JLabel("Weight (kg):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        secondPanel.add(weightLabel, gbc);

        JTextField weightField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        secondPanel.add(weightField, gbc);

        JButton calculateButton = new JButton("Calculate BMI");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        secondPanel.add(calculateButton, gbc);

        JLabel resultLabel = new JLabel(" ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        secondPanel.add(resultLabel, gbc);

        JButton backButton = new JButton("← Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainFrame.getContentPane(), "1");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        secondPanel.add(backButton, gbc);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heightField.setText("");
                weightField.setText("");
                resultLabel.setText(" ");
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 5;
        secondPanel.add(resetButton, gbc);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double height = Double.parseDouble(heightField.getText()) / 100.0;
                    double weight = Double.parseDouble(weightField.getText());
                    double bmi = weight / (height * height);
                    String category = getBMICategory(bmi);
                    resultLabel.setText(String.format("Your BMI: %.2f (%s)", bmi, category));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Please enter valid numbers for height and weight.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal weight";
        else if (bmi < 30) return "Overweight";
        else return "Obesity";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BMICalculator();
            }
        });
    }
}