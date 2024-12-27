import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator extends JFrame {

    private JTextField[] marksFields;
    private JLabel resultLabel;

    public StudentGradeCalculator() {
        setTitle("Student Grade Calculator");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header Label
        JLabel headerLabel = new JLabel("Student Grade Calculator", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(0, 102, 204));
        add(headerLabel, BorderLayout.NORTH);

        // Main Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setBackground(new Color(240, 248, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Input Fields for Marks
        marksFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            JLabel subjectLabel = new JLabel("Subject " + (i + 1) + " Marks (out of 100):");
            subjectLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            inputPanel.add(subjectLabel, gbc);

            gbc.gridx = 1;
            marksFields[i] = new JTextField();
            marksFields[i].setPreferredSize(new Dimension(150, 25)); // Set visible size
            marksFields[i].setToolTipText("Enter marks for Subject " + (i + 1));
            inputPanel.add(marksFields[i], gbc);
        }

        // Add the Input Panel to Frame
        add(inputPanel, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Result Label
        resultLabel = new JLabel("Result: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(new Color(34, 139, 34));
        footerPanel.add(resultLabel, BorderLayout.NORTH);

        // Calculate Button
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(new Color(50, 205, 50));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateButton.setToolTipText("Click to calculate total marks, average percentage, and grade");
        calculateButton.addActionListener(new CalculateListener());
        footerPanel.add(calculateButton, BorderLayout.SOUTH);

        // Add the Footer Panel to Frame
        add(footerPanel, BorderLayout.SOUTH);
    }

    private class CalculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int totalMarks = 0;
                for (JTextField field : marksFields) {
                    int marks = Integer.parseInt(field.getText());
                    if (marks < 0 || marks > 100) {
                        throw new NumberFormatException("Marks should be between 0 and 100.");
                    }
                    totalMarks += marks;
                }

                double averagePercentage = totalMarks / 5.0;
                String grade = calculateGrade(averagePercentage);

                resultLabel.setText("<html><center>Total Marks: " + totalMarks + "<br>" +
                        "Average Percentage: " + String.format("%.2f", averagePercentage) + "%<br>" +
                        "Grade: <b>" + grade + "</b></center></html>");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(StudentGradeCalculator.this,
                        "Please enter valid numbers for all fields (0-100).",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        private String calculateGrade(double percentage) {
            if (percentage >= 90) return "A";
            else if (percentage >= 80) return "B";
            else if (percentage >= 70) return "C";
            else if (percentage >= 60) return "D";
            else return "F";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentGradeCalculator frame = new StudentGradeCalculator();
            frame.setVisible(true);
        });
    }
}
