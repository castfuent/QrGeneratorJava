package qr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class QRCodeApp extends JFrame {

    private JTextField nameField, orgField, titleField, emailField, phoneField, urlField, addressField;
    private ResourceBundle bundle;

    public QRCodeApp(Locale locale) {
        bundle = ResourceBundle.getBundle("resources.Texts", locale);
        setTitle(bundle.getString("title"));
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameField = new JTextField();
        orgField = new JTextField();
        titleField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();
        urlField = new JTextField();
        addressField = new JTextField();

        formPanel.add(new JLabel(bundle.getString("label.name")));
        formPanel.add(nameField);
        formPanel.add(new JLabel(bundle.getString("label.organization")));
        formPanel.add(orgField);
        formPanel.add(new JLabel(bundle.getString("label.title")));
        formPanel.add(titleField);
        formPanel.add(new JLabel(bundle.getString("label.email")));
        formPanel.add(emailField);
        formPanel.add(new JLabel(bundle.getString("label.phone")));
        formPanel.add(phoneField);
        formPanel.add(new JLabel(bundle.getString("label.url")));
        formPanel.add(urlField);
        formPanel.add(new JLabel(bundle.getString("label.address")));
        formPanel.add(addressField);

        JButton generateButton = new JButton(bundle.getString("button.generate"));
        generateButton.setBackground(new Color(30, 144, 255));
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        generateButton.addActionListener(e -> generateQRCode());

        add(formPanel, BorderLayout.CENTER);
        add(generateButton, BorderLayout.SOUTH);

        setupMenuBar();
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu(bundle.getString("menu.file"));
        JMenuItem newItem = new JMenuItem(bundle.getString("menu.new"));
        JMenuItem exitItem = new JMenuItem(bundle.getString("menu.exit"));

        newItem.addActionListener(e -> clearFields());
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu(bundle.getString("menu.help"));
        JMenuItem aboutItem = new JMenuItem(bundle.getString("menu.about"));
        aboutItem.addActionListener(e -> showAboutDialog());

        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }

    private void clearFields() {
        nameField.setText("");
        orgField.setText("");
        titleField.setText("");
        emailField.setText("");
        phoneField.setText("");
        urlField.setText("");
        addressField.setText("");
    }

    private void generateQRCode() {
        String vCard = "BEGIN:VCARD\n" +
                "VERSION:3.0\n" +
                "N:" + nameField.getText() + "\n" +
                "ORG:" + orgField.getText() + "\n" +
                "TITLE:" + titleField.getText() + "\n" +
                "EMAIL:" + emailField.getText() + "\n" +
                "TEL;CELL:" + phoneField.getText() + "\n" +
                "URL:" + urlField.getText() + "\n" +
                "ADR;WORK:;;" + addressField.getText() + "\n" +
                "END:VCARD";

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(bundle.getString("dialog.saveTitle"));
        fileChooser.setSelectedFile(new File("qrcode.png"));

        int result = fileChooser.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) return;

        try {
            QRCodeGenerator.generateQRCode(vCard, fileChooser.getSelectedFile().getAbsolutePath());
            JOptionPane.showMessageDialog(this, bundle.getString("message.success"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, bundle.getString("message.error") + ex.getMessage());
        }
    }

    private void showAboutDialog() {
        String message = bundle.getString("about.title") + "\nCasfutent Software\nJesus C. de la Fuente\nwww.castfuent.es";
        JOptionPane.showMessageDialog(this, message, bundle.getString("menu.about"), JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        Locale locale = new Locale("es");
        SwingUtilities.invokeLater(() -> new QRCodeApp(locale).setVisible(true));
    }
}
