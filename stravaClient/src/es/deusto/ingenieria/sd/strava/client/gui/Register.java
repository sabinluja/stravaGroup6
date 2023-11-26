package es.deusto.ingenieria.sd.strava.client.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {
	private JTextField emailField, nombreField, birthDateField, weightField, heightField, maxHeartField, restHeartField;
	private boolean dataProcessed = false;
    private String email, nombre, birthDate, provider, password;
    private float weight;
    private int height, maxHeart, restHeart;
    private JTextField contrasenyaField;
    private JComboBox<String> providerCmbx;

    public Register() {
        // Configurar el JFrame
        setTitle("Datos Personales");
        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));
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

        providerCmbx = new JComboBox<String>();
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
        getContentPane().add(panel);
        
        JButton processButton = new JButton("Process Data");
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processData();
            }
        });
        
        getContentPane().add(processButton, BorderLayout.SOUTH);

        // Hacer visible el JFrame
        setVisible(true);
    }

    private void processData() {
    	// Obtener datos de los campos de texto
        email = emailField.getText();
        password = contrasenyaField.getText();
        nombre = nombreField.getText();
        birthDate = birthDateField.getText();
        provider = (String) providerCmbx.getSelectedItem();

        // Convertir datos opcionales
        weight = parseTextField(weightField.getText());
        height = parseTextField(heightField.getText());
        maxHeart = parseTextField(maxHeartField.getText());
        restHeart = parseTextField(restHeartField.getText());

        // Indicar que los datos han sido procesados
        dataProcessed = true;

        // Cierra la ventana después de procesar los datos (puedes ajustar esto según tus necesidades)
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
    public boolean dataProcessed() {
        return dataProcessed;
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

	public String getProvider() {
		return provider;
	}

	public String getPassword() {
		return password;
	} 
}