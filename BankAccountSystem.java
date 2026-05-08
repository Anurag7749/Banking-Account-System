import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;

/**
 * Bank Account Management System - Single File Swing Application
 * Demonstrates method overloading with different deposit types
 */
public class BankAccountSystem extends JFrame {
    private BankAccount account;
    
    // UI Components
    private JLabel lblAccountInfo;
    private JLabel lblBalance;
    private JTextArea txtOutput;
    private JTabbedPane tabbedPane;
    
    // Cash Deposit Components
    private JTextField txtCashAmount;
    private JButton btnCashDeposit;
    
    // Cheque Deposit Components
    private JTextField txtChequeAmount;
    private JTextField txtChequeNumber;
    private JButton btnChequeDeposit;
    
    // Online Transfer Components
    private JTextField txtOnlineAmount;
    private JTextField txtTransactionId;
    private JTextField txtBankName;
    private JButton btnOnlineDeposit;
    
    // Withdraw Components
    private JTextField txtWithdrawAmount;
    private JTextField txtWithdrawReason;
    private JButton btnWithdraw;
    
    public BankAccountSystem() {
        // Initialize account with sample data
        account = new BankAccount("409128056", "Anurag", 1000.00);
        
        // Setup frame
        setTitle("Bank Account Management System");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Create UI components
        createHeaderPanel();
        createDepositTabs();
        createOutputPanel();
        
        setVisible(true);
    }
    
    private void createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.setBackground(new Color(41, 128, 185));
        
        JLabel lblTitle = new JLabel("Bank Account Management System");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblAccountInfo = new JLabel(account.getAccountInfo().replace("\n", " | "));
        lblAccountInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAccountInfo.setForeground(Color.WHITE);
        lblAccountInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblBalance = new JLabel(String.format("Current Balance: $%.2f", account.getBalance()));
        lblBalance.setFont(new Font("Arial", Font.BOLD, 18));
        lblBalance.setForeground(new Color(46, 204, 113));
        lblBalance.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        headerPanel.add(lblTitle);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(lblAccountInfo);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(lblBalance);
        
        add(headerPanel, BorderLayout.NORTH);
    }
    
    private void createDepositTabs() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Cash Deposit Tab
        JPanel cashPanel = createCashDepositPanel();
        tabbedPane.addTab("Cash Deposit", new ImageIcon(), cashPanel, "Deposit cash");
        
        // Cheque Deposit Tab
        JPanel chequePanel = createChequeDepositPanel();
        tabbedPane.addTab("Cheque Deposit", new ImageIcon(), chequePanel, "Deposit by cheque");
        
        // Online Transfer Tab
        JPanel onlinePanel = createOnlineTransferPanel();
        tabbedPane.addTab("Online Transfer", new ImageIcon(), onlinePanel, "Deposit via online transfer");
        
        // Withdraw Tab
        JPanel withdrawPanel = createWithdrawPanel();
        tabbedPane.addTab("Withdraw", new ImageIcon(), withdrawPanel, "Withdraw money");
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private JPanel createCashDepositPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel lblTitle = new JLabel("Cash Deposit");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitle, gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        
        JLabel lblAmount = new JLabel("Amount ($):");
        lblAmount.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        panel.add(lblAmount, gbc);
        
        txtCashAmount = new JTextField(25);
        txtCashAmount.setFont(new Font("Arial", Font.PLAIN, 16));
        txtCashAmount.setPreferredSize(new Dimension(300, 35));
        addNumericFilter(txtCashAmount);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        panel.add(txtCashAmount, gbc);
        
        btnCashDeposit = new JButton("Deposit Cash");
        btnCashDeposit.setFont(new Font("Arial", Font.BOLD, 16));
        btnCashDeposit.setBackground(new Color(34, 139, 34));
        btnCashDeposit.setForeground(Color.WHITE);
        btnCashDeposit.setFocusPainted(false);
        btnCashDeposit.setOpaque(true);
        btnCashDeposit.setBorderPainted(false);
        btnCashDeposit.setPreferredSize(new Dimension(200, 45));
        btnCashDeposit.addActionListener(e -> handleCashDeposit());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnCashDeposit, gbc);
        
        return panel;
    }
    
    private JPanel createChequeDepositPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel lblTitle = new JLabel("Cheque Deposit");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitle, gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        
        JLabel lblAmount = new JLabel("Amount ($):");
        lblAmount.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        panel.add(lblAmount, gbc);
        
        txtChequeAmount = new JTextField(25);
        txtChequeAmount.setFont(new Font("Arial", Font.PLAIN, 16));
        txtChequeAmount.setPreferredSize(new Dimension(300, 35));
        addNumericFilter(txtChequeAmount);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        panel.add(txtChequeAmount, gbc);
        
        JLabel lblChequeNumber = new JLabel("Cheque Number:");
        lblChequeNumber.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        panel.add(lblChequeNumber, gbc);
        
        txtChequeNumber = new JTextField(25);
        txtChequeNumber.setFont(new Font("Arial", Font.PLAIN, 16));
        txtChequeNumber.setPreferredSize(new Dimension(300, 35));
        txtChequeNumber.setEditable(true);
        txtChequeNumber.setEnabled(true);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        panel.add(txtChequeNumber, gbc);
        
        btnChequeDeposit = new JButton("Deposit Cheque");
        btnChequeDeposit.setFont(new Font("Arial", Font.BOLD, 16));
        btnChequeDeposit.setBackground(new Color(0, 102, 204));
        btnChequeDeposit.setForeground(Color.WHITE);
        btnChequeDeposit.setFocusPainted(false);
        btnChequeDeposit.setOpaque(true);
        btnChequeDeposit.setBorderPainted(false);
        btnChequeDeposit.setPreferredSize(new Dimension(200, 45));
        btnChequeDeposit.addActionListener(e -> handleChequeDeposit());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnChequeDeposit, gbc);
        
        return panel;
    }
    
    private JPanel createOnlineTransferPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel lblTitle = new JLabel("Online Transfer");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitle, gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        
        JLabel lblAmount = new JLabel("Amount ($):");
        lblAmount.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        panel.add(lblAmount, gbc);
        
        txtOnlineAmount = new JTextField(30);
        txtOnlineAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        txtOnlineAmount.setPreferredSize(new Dimension(500, 40));
        txtOnlineAmount.setMinimumSize(new Dimension(500, 40));
        addNumericFilter(txtOnlineAmount);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        panel.add(txtOnlineAmount, gbc);
        
        JLabel lblTransactionId = new JLabel("Transaction ID:");
        lblTransactionId.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        panel.add(lblTransactionId, gbc);
        
        txtTransactionId = new JTextField(30);
        txtTransactionId.setFont(new Font("Arial", Font.PLAIN, 18));
        txtTransactionId.setPreferredSize(new Dimension(500, 40));
        txtTransactionId.setMinimumSize(new Dimension(500, 40));
        txtTransactionId.setEditable(true);
        txtTransactionId.setEnabled(true);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        panel.add(txtTransactionId, gbc);
        
        JLabel lblBankName = new JLabel("Bank Name:");
        lblBankName.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        panel.add(lblBankName, gbc);
        
        txtBankName = new JTextField(30);
        txtBankName.setFont(new Font("Arial", Font.PLAIN, 18));
        txtBankName.setPreferredSize(new Dimension(500, 40));
        txtBankName.setMinimumSize(new Dimension(500, 40));
        txtBankName.setEditable(true);
        txtBankName.setEnabled(true);
        addLettersOnlyFilter(txtBankName);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        panel.add(txtBankName, gbc);
        
        btnOnlineDeposit = new JButton("Transfer Funds");
        btnOnlineDeposit.setFont(new Font("Arial", Font.BOLD, 16));
        btnOnlineDeposit.setBackground(new Color(138, 43, 226));
        btnOnlineDeposit.setForeground(Color.WHITE);
        btnOnlineDeposit.setFocusPainted(false);
        btnOnlineDeposit.setOpaque(true);
        btnOnlineDeposit.setBorderPainted(false);
        btnOnlineDeposit.setPreferredSize(new Dimension(200, 45));
        btnOnlineDeposit.addActionListener(e -> handleOnlineTransfer());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnOnlineDeposit, gbc);
        
        return panel;
    }
    
    private JPanel createWithdrawPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel lblTitle = new JLabel("Withdraw Money");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitle, gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        
        JLabel lblAmount = new JLabel("Amount ($):");
        lblAmount.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        panel.add(lblAmount, gbc);
        
        txtWithdrawAmount = new JTextField(30);
        txtWithdrawAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        txtWithdrawAmount.setPreferredSize(new Dimension(500, 40));
        txtWithdrawAmount.setMinimumSize(new Dimension(500, 40));
        addNumericFilter(txtWithdrawAmount);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        panel.add(txtWithdrawAmount, gbc);
        
        JLabel lblReason = new JLabel("Reason (Optional):");
        lblReason.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        panel.add(lblReason, gbc);
        
        txtWithdrawReason = new JTextField(30);
        txtWithdrawReason.setFont(new Font("Arial", Font.PLAIN, 18));
        txtWithdrawReason.setPreferredSize(new Dimension(500, 40));
        txtWithdrawReason.setMinimumSize(new Dimension(500, 40));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        panel.add(txtWithdrawReason, gbc);
        
        btnWithdraw = new JButton("Withdraw Money");
        btnWithdraw.setFont(new Font("Arial", Font.BOLD, 16));
        btnWithdraw.setBackground(new Color(220, 20, 60));
        btnWithdraw.setForeground(Color.WHITE);
        btnWithdraw.setFocusPainted(false);
        btnWithdraw.setOpaque(true);
        btnWithdraw.setBorderPainted(false);
        btnWithdraw.setPreferredSize(new Dimension(200, 45));
        btnWithdraw.addActionListener(e -> handleWithdraw());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnWithdraw, gbc);
        
        return panel;
    }
    
    private void createOutputPanel() {
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        
        JLabel lblOutput = new JLabel("Transaction History:");
        lblOutput.setFont(new Font("Arial", Font.BOLD, 14));
        lblOutput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        txtOutput = new JTextArea(8, 50);
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtOutput.setBackground(new Color(245, 245, 245));
        txtOutput.setText("Welcome to Bank Account Management System!\n" +
                         "Select a deposit method from the tabs above.\n" +
                         "----------------------------------------\n");
        
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        outputPanel.add(lblOutput, BorderLayout.NORTH);
        outputPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(outputPanel, BorderLayout.SOUTH);
    }
    
    private void handleCashDeposit() {
        try {
            double amount = Double.parseDouble(txtCashAmount.getText().trim());
            String result = account.deposit(amount);  // Method overloading - 1 parameter
            updateDisplay(result);
            txtCashAmount.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid amount.", 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleChequeDeposit() {
        try {
            double amount = Double.parseDouble(txtChequeAmount.getText().trim());
            String chequeNumber = txtChequeNumber.getText().trim();
            String result = account.deposit(amount, chequeNumber);  // Method overloading - 2 parameters
            updateDisplay(result);
            txtChequeAmount.setText("");
            txtChequeNumber.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid amount.", 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleOnlineTransfer() {
        try {
            double amount = Double.parseDouble(txtOnlineAmount.getText().trim());
            String transactionId = txtTransactionId.getText().trim();
            String bankName = txtBankName.getText().trim();
            String result = account.deposit(amount, transactionId, bankName);  // Method overloading - 3 parameters
            updateDisplay(result);
            txtOnlineAmount.setText("");
            txtTransactionId.setText("");
            txtBankName.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid amount.", 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleWithdraw() {
        try {
            double amount = Double.parseDouble(txtWithdrawAmount.getText().trim());
            String reason = txtWithdrawReason.getText().trim();
            String result;
            
            if (reason.isEmpty()) {
                result = account.withdraw(amount);  // Method overloading - 1 parameter
            } else {
                result = account.withdraw(amount, reason);  // Method overloading - 2 parameters
            }
            
            updateDisplay(result);
            txtWithdrawAmount.setText("");
            txtWithdrawReason.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid amount.", 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateDisplay(String message) {
        txtOutput.append("\n" + message + "\n");
        txtOutput.append("----------------------------------------\n");
        txtOutput.setCaretPosition(txtOutput.getDocument().getLength());
        lblBalance.setText(String.format("Current Balance: $%.2f", account.getBalance()));
    }
    
    // Method to add numeric-only filter to text fields
    private void addNumericFilter(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string == null) return;
                if (isNumeric(string, fb.getDocument(), offset)) {
                    super.insertString(fb, offset, string, attr);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text == null) return;
                if (isNumeric(text, fb.getDocument(), offset)) {
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            private boolean isNumeric(String text, Document doc, int offset) {
                try {
                    String currentText = doc.getText(0, doc.getLength());
                    String beforeOffset = currentText.substring(0, offset);
                    String afterOffset = currentText.substring(offset);
                    String resultText = beforeOffset + text + afterOffset;
                    
                    // Allow empty string
                    if (resultText.isEmpty()) return true;
                    
                    // Check if it matches numeric pattern (digits and at most one decimal point)
                    if (!resultText.matches("^\\d*\\.?\\d*$")) return false;
                    
                    // Prevent multiple decimal points
                    int decimalCount = 0;
                    for (char c : resultText.toCharArray()) {
                        if (c == '.') decimalCount++;
                    }
                    if (decimalCount > 1) return false;
                    
                    return true;
                } catch (BadLocationException e) {
                    return false;
                }
            }
        });
    }
    
    // Method to add letters-only filter to text fields (allows letters and spaces)
    private void addLettersOnlyFilter(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string == null) return;
                if (isLettersOnly(string)) {
                    super.insertString(fb, offset, string, attr);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text == null) return;
                if (isLettersOnly(text)) {
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            private boolean isLettersOnly(String text) {
                // Allow letters (a-z, A-Z) and spaces only
                return text.matches("^[a-zA-Z ]*$");
            }
        });
    }
    
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create and show GUI
        SwingUtilities.invokeLater(() -> new BankAccountSystem());
    }
    
    /**
     * Inner class: BankAccount
     * Demonstrates method overloading with multiple deposit methods
     */
    class BankAccount {
        private String accountNumber;
        private String accountHolderName;
        private double balance;
        
        // Constructor
        public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
            this.accountNumber = accountNumber;
            this.accountHolderName = accountHolderName;
            this.balance = initialBalance;
        }
        
        // Method Overloading - Deposit by Cash (1 parameter)
        public String deposit(double amount) {
            if (amount <= 0) {
                return "Invalid amount. Please enter a positive value.";
            }
            balance += amount;
            return String.format("Cash deposit successful!\nAmount: $%.2f\nNew Balance: $%.2f", 
                               amount, balance);
        }
        
        // Method Overloading - Deposit by Cheque (2 parameters)
        public String deposit(double amount, String chequeNumber) {
            if (amount <= 0) {
                return "Invalid amount. Please enter a positive value.";
            }
            if (chequeNumber == null || chequeNumber.trim().isEmpty()) {
                return "Invalid cheque number.";
            }
            balance += amount;
            return String.format("Cheque deposit successful!\nCheque Number: %s\nAmount: $%.2f\nNew Balance: $%.2f", 
                               chequeNumber, amount, balance);
        }
        
        // Method Overloading - Deposit by Online Transfer (3 parameters)
        public String deposit(double amount, String transactionId, String bankName) {
            if (amount <= 0) {
                return "Invalid amount. Please enter a positive value.";
            }
            if (transactionId == null || transactionId.trim().isEmpty()) {
                return "Invalid transaction ID.";
            }
            if (bankName == null || bankName.trim().isEmpty()) {
                return "Invalid bank name.";
            }
            balance += amount;
            return String.format("Online transfer successful!\nTransaction ID: %s\nBank: %s\nAmount: $%.2f\nNew Balance: $%.2f", 
                               transactionId, bankName, amount, balance);
        }
        
        // Method Overloading - Withdraw (1 parameter)
        public String withdraw(double amount) {
            if (amount <= 0) {
                return "Invalid amount. Please enter a positive value.";
            }
            if (amount > balance) {
                return String.format("Insufficient balance!\nRequested: $%.2f\nAvailable: $%.2f", amount, balance);
            }
            balance -= amount;
            return String.format("Withdrawal successful!\nAmount: $%.2f\nNew Balance: $%.2f", 
                               amount, balance);
        }
        
        // Method Overloading - Withdraw with reason (2 parameters)
        public String withdraw(double amount, String reason) {
            if (amount <= 0) {
                return "Invalid amount. Please enter a positive value.";
            }
            if (amount > balance) {
                return String.format("Insufficient balance!\nRequested: $%.2f\nAvailable: $%.2f", amount, balance);
            }
            if (reason == null || reason.trim().isEmpty()) {
                return "Invalid reason.";
            }
            balance -= amount;
            return String.format("Withdrawal successful!\nReason: %s\nAmount: $%.2f\nNew Balance: $%.2f", 
                               reason, amount, balance);
        }
        
        // Getters
        public String getAccountNumber() {
            return accountNumber;
        }
        
        public String getAccountHolderName() {
            return accountHolderName;
        }
        
        public double getBalance() {
            return balance;
        }
        
        public String getAccountInfo() {
            return String.format("Account Number: %s\nAccount Holder: %s\nCurrent Balance: $%.2f", 
                               accountNumber, accountHolderName, balance);
        }
    }
}
