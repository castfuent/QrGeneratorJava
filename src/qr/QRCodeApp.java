package qr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class QRCodeApp extends JFrame {

    private JTextField[] fields;
   private  ResourceBundle bundle;
    public QRCodeApp(Locale locale) {



  // Cargar el ResourceBundle según el locale
        bundle = ResourceBundle.getBundle("resources.Texts", locale);
    
        // Configuración básica del JFrame
        setTitle("QR Code Generator");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Usamos un layout flexible: GridBagLayout
        setLayout(new BorderLayout());

        // Instanciar la clase GenerateTest
       
       String[] texts = generate();

        // Título
        JLabel titleLabel = new JLabel(bundle.getString("title")); // El primer elemento es el título
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Estilo y tamaño grande
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrado
        titleLabel.setForeground(new Color(30, 144, 255)); // Color del texto
        

        // Panel donde agregamos los campos de texto
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.WEST;

        gbc.fill = GridBagConstraints.NONE;  // No forzar a ocupar todo el espacio
        gbc.weightx = 0;                     // No expandir horizontalmente
        gbc.gridwidth = 1;

        // Fuentes y colores
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        Color bgColor = new Color(240, 240, 240);
        Color fieldColor = new Color(255, 255, 255);

        // Crear los JTextField y almacenarlos en un array
        fields = new JTextField[15];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = createTextField(fieldFont, fieldColor);
        }

        // Añadir los componentes al layout usando GridBagLayou
        int row=0;
        int col=0;
        for (int i=0;i<texts.length;i++){
            addLabelAndField(formPanel, gbc, texts[i], fields[i],row,col);
            col++;
            if (col==2){ row++;col=0;}

        }
        // Botón de generar QR
        JButton generateButton = new JButton("Generate QR");
        generateButton.setFont(new Font("Arial", Font.BOLD, 16));
        generateButton.setBackground(new Color(30, 144, 255));
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateQRCode();
            }
        });

        // Crear el JScrollPane con el panel de formulario
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setPreferredSize(new Dimension(600, 600)); // Tamaño para el scroll
        add(titleLabel, BorderLayout.NORTH);
        // Agregar el scrollPane al JFrame
        add(scrollPane, BorderLayout.CENTER);


        // 2. Crea el botón (puede ser antes o después, eso da igual)
generateButton = new JButton(bundle.getString("button.generate"));
generateButton.setFont(new Font("Arial", Font.BOLD, 16));
generateButton.setBackground(new Color(30, 144, 255));
generateButton.setForeground(Color.WHITE);
generateButton.setFocusPainted(false);
generateButton.addActionListener(e -> generateQRCode());

// 3. Y lo añades al sur:
add(generateButton, BorderLayout.SOUTH);

        // Barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu(bundle.getString("menu.file"));
        JMenu helpMenu = new JMenu(bundle.getString("menu.help"));

        JMenuItem newMenuItem = new JMenuItem(bundle.getString("menu.new"));
        newMenuItem.addActionListener(e -> clearFields());
        JMenuItem exitMenuItem = new JMenuItem(bundle.getString("menu.exit"));
        exitMenuItem.addActionListener(e -> System.exit(0));

        JMenuItem aboutMenuItem = new JMenuItem(bundle.getString("menu.about"));
        aboutMenuItem.addActionListener(e -> showAboutDialog());


        fileMenu.add(newMenuItem);
        fileMenu.add(exitMenuItem);
        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    private JTextField createTextField(Font font, Color color) {
        JTextField field = new JTextField(20);
        field.setFont(font);
        field.setBackground(color);
        field.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        return field;
    }

    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, String labelText, JTextField field, int row, int col) {
        // Añadir etiqueta
        gbc.gridx = col * 2;
        gbc.gridy = row;
        panel.add(new JLabel(labelText, JLabel.RIGHT), gbc);

        // Añadir campo de texto
        gbc.gridx = col * 2 + 1;
        panel.add(field, gbc);
    }

    private void generateQRCode() {
        // Aquí va tu código de generación del QR
    }

    private void clearFields() {
        // Limpiar los campos usando el array
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this, bundle.getString("title"));
    }



    private String[] generate() {
        ArrayList<String> texts = new ArrayList<String>();
        try {
            
            texts.add(bundle.getString("label.firstName"));
            texts.add(bundle.getString("label.middleName"));
            texts.add(bundle.getString("label.lastName"));
            texts.add(bundle.getString("label.organization"));
            texts.add(bundle.getString("label.title"));
            texts.add(bundle.getString("label.email"));
            texts.add(bundle.getString("label.phone"));
            texts.add(bundle.getString("label.url"));
            texts.add(bundle.getString("label.address"));
            texts.add(bundle.getString("label.city"));
            texts.add(bundle.getString("label.state"));
            texts.add(bundle.getString("label.country"));
            texts.add(bundle.getString("label.birthday"));
            texts.add(bundle.getString("label.nickname"));
            texts.add(bundle.getString("label.note"));
            

        } catch (Exception e) {
            e.printStackTrace(); // Imprimir detalles del error si algo falla
        }
        String [] te=new String[texts.size()];
        for (int i=0;i<texts.size();i++) te[i]=texts.get(i);
        return te;
    }




    public static void main(String[] args) {
        Locale locale = new Locale("es"); // Cambia el idioma aquí, por ejemplo, a "en" para inglés
        SwingUtilities.invokeLater(() -> new QRCodeApp(locale).setVisible(true));
    }
}
