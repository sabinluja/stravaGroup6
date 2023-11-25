package es.deusto.ingenieria.sd.strava.client.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {
	private JTextField emailField, nombreField, birthDateField, weightField, heightField, maxHeartField, restHeartField;
	private boolean datosProcesados = false;
    private String email, nombre, birthDate;
    private float weight;
    private int height, maxHeart, restHeart;
    private JTextField contrasenyaField;
    
    public Register() {
        // Configurar el JFrame
        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2));

        // Componentes de la interfaz gráfica
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel passLabel = new JLabel("Password: ");
        contrasenyaField = new JTextField();
        JLabel nombreLabel = new JLabel("Name: ");
        nombreField = new JTextField();
        JLabel birthDateLabel = new JLabel("Birthdate:");
        birthDateField = new JTextField();
        JLabel weightLabel = new JLabel("Weight:");
        weightField = new JTextField();
        JLabel heightLabel = new JLabel("Height:");
        heightField = new JTextField();
        JLabel maxHeartLabel = new JLabel("Maximum heartrate freq:");
        maxHeartField = new JTextField();
        JLabel restHeartLabel = new JLabel("Maximum heartrate freq (rest): ");
        restHeartField = new JTextField();
        
        JComboBox<String> providerCmbx = new JComboBox<String>();
        providerCmbx.addItem("Google");
        providerCmbx.addItem("Facebook");
        providerCmbx.setSelectedIndex(0);

        JLabel providerLabel = new JLabel("Provider");

        // Agregar componentes al panel
        panel.add(providerLabel);
        panel.add(providerCmbx);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passLabel);
        panel.add(contrasenyaField);
        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(birthDateLabel);
        panel.add(birthDateField);
        panel.add(weightLabel);
        panel.add(weightField);
        panel.add(heightLabel);
        panel.add(heightField);
        panel.add(maxHeartLabel);
        panel.add(maxHeartField);
        panel.add(restHeartLabel);
        panel.add(restHeartField);

        // Agregar panel al JFrame
        getContentPane().add(panel);
        
        JButton processrButton = new JButton("Procesar Datos");
        getContentPane().add(processrButton, BorderLayout.SOUTH);
        
        processrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processData();
            }
        });

        // Hacer visible el JFrame
        setVisible(true);
    }
    
    private void processData() {
        // Get data from text fields
        // username = usernameField.getText();
        // password = new String(passwordField.getPassword());

        // Indicate that data has been processed
        // dataProcessed = true;

        // Close the window after processing data (adjust as needed)
        dispose();
    }

    private int parseTextField(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0; // Puedes manejar el error de conversión de manera adecuada según tus necesidades
        }
    }



    // Métodos para obtener los datos procesados
    public boolean datosProcesados() {
        return datosProcesados;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public float getWeight() {
        return weight;
    }

    public int getMaxHeart() {
        return maxHeart;
    }

    public int getRestHeart() {
        return restHeart;
    }
    
}
