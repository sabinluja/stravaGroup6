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
    
    public Register() {
        // Configurar el JFrame
        setTitle("Datos Personales");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        // Componentes de la interfaz gráfica
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();
        JLabel birthDateLabel = new JLabel("Fecha de Nacimiento:");
        birthDateField = new JTextField();
        JLabel weightLabel = new JLabel("Peso:");
        weightField = new JTextField();
        JLabel heightLabel = new JLabel("Altura:");
        heightField = new JTextField();
        JLabel maxHeartLabel = new JLabel("Frecuencia Cardíaca Máxima:");
        maxHeartField = new JTextField();
        JLabel restHeartLabel = new JLabel("Frecuencia Cardíaca en Reposo:");
        restHeartField = new JTextField();

        // Botón para procesar los datos
        JButton procesarButton = new JButton("Procesar Datos");
        procesarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarDatos();
            }
        });

        // Agregar componentes al panel
        panel.add(emailLabel);
        panel.add(emailField);
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
        panel.add(procesarButton);

        // Agregar panel al JFrame
        add(panel);

        // Hacer visible el JFrame
        setVisible(true);
    }

    private void procesarDatos() {
        // Obtener datos de los campos de texto
        email = emailField.getText();
        nombre = nombreField.getText();
        birthDate = birthDateField.getText();

        // Convertir datos opcionales
        weight = parseTextField(weightField.getText());
        height = parseTextField(heightField.getText());
        maxHeart = parseTextField(maxHeartField.getText());
        restHeart = parseTextField(restHeartField.getText());

        // Indicar que los datos han sido procesados
        datosProcesados = true;

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
