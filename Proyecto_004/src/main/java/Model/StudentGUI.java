package Model;

import java.awt.Color;

import javax.swing.*;
import org.hibernate.SessionFactory;

public class StudentGUI {
    private final JFrame frame;
    private final JTextField ciField;
    private final JTextField idField;
    private final JTextField nameField;
    private final JTextField lastnameField;
    private final JTextField ageField;
    private final StudentRepository studentRepository;

    // Constructor principal que recibe un repositorio de estudiantes
    public StudentGUI(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;

        // Configuración de la ventana principal
        frame = new JFrame("Gestión de Estudiantes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        
    	// Creación del panel principal con layout vertical
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Layout vertical
		frame.add(panel);

        // Creación de los campos de texto para CI, ID, Nombre, Apellido y Edad
        ciField = createTextField(panel, "CI:");
        idField = createTextField(panel, "ID:");
        nameField = createTextField(panel, "Name:");
        lastnameField = createTextField(panel, "Lastname:");
        ageField = createTextField(panel, "Age:");

        // Creación de los botones de agregar, eliminar y buscar
        createButton(panel, "Agregar Estudiante", this::addStudent);
        createButton(panel, "Eliminar Estudiante", this::deleteStudent);
        createButton(panel, "Buscar por CI", this::searchByCI);

        // Botón Refrescar: Limpia los campos CI, Nombre, Apellido y Edad, y actualiza
        // el campo ID al siguiente del último ingresado en la base de datos
        JButton refreshButton = new JButton("Refrescar");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        buttonPanel.add(refreshButton);
        refreshButton.addActionListener(e -> {
            ciField.setText(""); // Limpiar campo CI
            idField.setText(String.valueOf(studentRepository.getLastId() + 1)); // Actualizar ID
            nameField.setText(""); // Limpiar campo Nombre
            lastnameField.setText(""); // Limpiar campo Apellido
            ageField.setText(""); // Limpiar campo Edad
        });
        panel.add(buttonPanel);

        frame.setVisible(true);
    }

    // Método privado para crear un campo de texto con su respectivo panel y
    // etiqueta
    private JTextField createTextField(JPanel panel, String labelText) {
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        JLabel label = new JLabel(labelText);
        textFieldPanel.add(label);
        JTextField textField = new JTextField(20);
        textFieldPanel.add(textField);
        panel.add(textFieldPanel);
        return textField;
    }

    // Método privado para crear un botón con su respectivo panel y acción
    private void createButton(JPanel panel, String buttonText, Runnable action) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        JButton button = new JButton(buttonText);
        buttonPanel.add(button);
        button.addActionListener(e -> action.run());
        panel.add(buttonPanel);
    }

    // Método para agregar un estudiante
    private void addStudent() {
        long CI = Long.parseLong(ciField.getText());
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String lastname = lastnameField.getText();
        int age = Integer.parseInt(ageField.getText());
        Student student = new Student(CI, id, name, lastname, age);
        studentRepository.save(student);
        JOptionPane.showMessageDialog(frame, "Estudiante agregado correctamente.");
    }

    // Método para eliminar un estudiante
    private void deleteStudent() {
        long ci = Long.parseLong(ciField.getText());
        studentRepository.delete(ci);
        JOptionPane.showMessageDialog(frame, "Estudiante eliminado correctamente.");
    }

    // Método para buscar un estudiante por CI
    private void searchByCI() {
        long CI = Long.parseLong(ciField.getText());
        Student student = studentRepository.findByCI(CI);
        if (student != null) {
            idField.setText(String.valueOf(student.getId()));
            nameField.setText(student.getName());
            lastnameField.setText(student.getLastname());
            ageField.setText(String.valueOf(student.getAge()));
        } else {
            JOptionPane.showMessageDialog(frame, "No se encontró ningún estudiante con el CI proporcionado.");
        }
    }

    // Agrega un nuevo constructor que acepte un int para inicializar idField
    public StudentGUI(StudentRepository studentRepository, int lastId) {
        this(studentRepository); // Llama al constructor base
        idField.setText(String.valueOf(lastId)); // Inicializa idField con el valor proporcionado
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        StudentRepository studentRepository = new HibernateStudentRepository();
        SwingUtilities.invokeLater(() -> new StudentGUI(studentRepository));
    }
}