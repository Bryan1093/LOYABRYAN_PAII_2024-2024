package ec.edu.uce.Proyecto_004;

import javax.swing.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Model.HibernateStudentRepository;
import Model.Student;
import Model.StudentRepository;

public class App {
	
	private final StudentRepository studentRepository;

    public App(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    public static void main(String[] args) {

        StudentRepository studentRepository = new HibernateStudentRepository();

        // Crear y configurar la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de Estudiantes");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

            // Panel principal
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Layout vertical
            frame.add(panel);

            // Campo de texto para el CI del estudiante
            JPanel ciPanel = new JPanel();
            ciPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            JLabel ciLabel = new JLabel("CI:");
            ciPanel.add(ciLabel);
            JTextField ciField = new JTextField(10);
            ciPanel.add(ciField);
            panel.add(ciPanel);

            // Campo de texto para el ID del estudiante
            JPanel idPanel = new JPanel();
            idPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            JLabel idLabel = new JLabel("ID:");
            idPanel.add(idLabel);
            JTextField idField = new JTextField(10);
            idPanel.add(idField);
            panel.add(idPanel);

            // Campo de texto para el nombre del estudiante
            JPanel namePanel = new JPanel();
            namePanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            JLabel nameLabel = new JLabel("Name:");
            namePanel.add(nameLabel);
            JTextField nameField = new JTextField(20);
            namePanel.add(nameField);
            panel.add(namePanel);

            // Campo de texto para el apellido del estudiante
            JPanel lastnamePanel = new JPanel();
            lastnamePanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            JLabel lastnameLabel = new JLabel("Lastname:");
            lastnamePanel.add(lastnameLabel);
            JTextField lastnameField = new JTextField(20);
            lastnamePanel.add(lastnameField);
            panel.add(lastnamePanel);

            // Campo de texto para la edad del estudiante
            JPanel agePanel = new JPanel();
            agePanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            JLabel ageLabel = new JLabel("Age:");
            agePanel.add(ageLabel);
            JTextField ageField = new JTextField(3);
            agePanel.add(ageField);
            panel.add(agePanel);

            // Botones
            JPanel buttonPanel = new JPanel();
            buttonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

            // Botón para agregar estudiante
            JButton addButton = new JButton("Agregar Estudiante");
            buttonPanel.add(addButton);

            // Configurar la acción del botón de agregar
            addButton.addActionListener(e -> {
                long CI = Long.parseLong(ciField.getText()); // Convertir a long
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String lastname = lastnameField.getText();
                int age = Integer.parseInt(ageField.getText());
                Student student = new Student(CI, id, name, lastname, age); // Crear objeto Student
                studentRepository.save(student);
                JOptionPane.showMessageDialog(frame, "Estudiante agregado correctamente.");
            });

            // Botón para eliminar estudiante
            JButton deleteButton = new JButton("Eliminar Estudiante");
            buttonPanel.add(deleteButton);

            // Configurar la acción del botón de eliminar
            deleteButton.addActionListener(e -> {
                long ci = Long.parseLong(ciField.getText()); // Convertir a long
                studentRepository.delete(ci);
                JOptionPane.showMessageDialog(frame, "Estudiante eliminado correctamente.");
            });

            // Botón para buscar estudiante por CI
            JButton searchButton = new JButton("Buscar por CI");
            buttonPanel.add(searchButton);

            // Configurar la acción del botón de buscar
            searchButton.addActionListener(e -> {
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
            });

            panel.add(buttonPanel);

            frame.setVisible(true);
        });
    }
}