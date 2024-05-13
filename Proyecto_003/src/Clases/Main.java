package Clases;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import Clases.Schedules;
import DAO.SchedulesDAOImp;
import DAO.StudentDAOImp;
import DAO.SubjectsDAOImp;
import DAO.TeacherDAOImp;
import Interfaces.SchedulesDAO;
import Interfaces.StudentDAO;
import Interfaces.SubjectsDAO;
import Interfaces.TeacherDAO;

public class Main {
	public static void main(String[] args) {

		// -----------------------------------STUDENTS-----------------------------------------------------

		StudentDAO studentDAO = new StudentDAOImp();
		Student nuevoEstudiante = new Student();
		int idEstudianteAEliminar = 4; // Id estudiante

		// Para reiniciar la secuencia de la columna id: ALTER SEQUENCE student_id_seq RESTART WITH 1;

		nuevoEstudiante.setName("Paulino");
		nuevoEstudiante.setLastname("Loya");
		nuevoEstudiante.setAge(16); // Edad del estudiante

		// Insertar estudiante
		try {
			boolean creado = studentDAO.crear(nuevoEstudiante);
			if (creado) {
				System.out.println("Estudiante creado correctamente.");
			} else {
				System.out.println("Error al crear el estudiante.");
			}
		} catch (SQLException e) {
			System.out.println("Error al interactuar con la base de datos: " + e.getMessage());
		}

		// eliminar
		/*try {
			boolean eliminado = studentDAO.eliminar(idEstudianteAEliminar);
			if (eliminado) {
				System.out.println("Estudiante eliminado correctamente.");
			} else {
				System.out.println("No se pudo eliminar el estudiante.");
			}
		} catch (SQLException e) {
			System.out.println("Error al interactuar con la base de datos: " + e.getMessage());
		}*/

		// Leer un estudiante por su ID
		/*try {
			Student estudianteLeido = studentDAO.leer(1);
			System.out.println("Estudiante leído: " + estudianteLeido);
		} catch (SQLException e) {
			System.out.println("Error al leer el estudiante: " + e.getMessage());
		}*/

		// Actualizar un estudiante
		/*try {
			Student estudianteActualizado = new Student();
			estudianteActualizado.setId(1); // ID del estudiante a actualizar
			estudianteActualizado.setName("Nuevo Nombre");
			estudianteActualizado.setLastname("Nuevo Apellido");
			estudianteActualizado.setAge(30);

			boolean actualizado = studentDAO.actualizar(1, estudianteActualizado);
			if (actualizado) {
				System.out.println("Estudiante actualizado correctamente.");
			} else {
				System.out.println("Error al actualizar el estudiante.");
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar el estudiante: " + e.getMessage());
		}*/

		// Listar todos los estudiantes
		/*try {
			List<Student> listaEstudiantes = studentDAO.listar();
			System.out.println("Lista de Estudiantes:");
			for (Student s : listaEstudiantes) {
				System.out.println(s);
			}
		} catch (SQLException e) {
			System.out.println("Error al listar los estudiantes: " + e.getMessage());
		}*/

//-----------------------------------TEACHER-----------------------------------------------------

		TeacherDAO teacherDAO = new TeacherDAOImp();
		Teacher nuevoProfesor = new Teacher();
		int idProfesorAEliminar = 4; // Id estudiante

		// ALTER SEQUENCE teacher_id_seq RESTART WITH 1;

		nuevoProfesor.setName("Mateo");
		nuevoProfesor.setLastname("Loya");
		nuevoProfesor.setAge(19); // Edad del estudiante

		// Insertar profesor
		/*try {
			boolean creado = teacherDAO.crear(nuevoProfesor);
			if (creado) {
				System.out.println("Profesor creado correctamente.");
			} else {
				System.out.println("Error al crear el profesor.");
			}
		} catch (SQLException e) {
			System.out.println("Error al interactuar con la base de datos: " + e.getMessage());
		}*/

		// eliminar profesor
		/*try {
			boolean eliminado = teacherDAO.eliminar(idProfesorAEliminar);
			if (eliminado) {
				System.out.println("Profesor eliminado correctamente.");
			} else {
				System.out.println("No se pudo eliminar el profesor.");
			}
		} catch (SQLException e) {
			System.out.println("Error al interactuar con la base de datos: " + e.getMessage());
		}*/

		// Leer un profesor por su ID
		/*try {
			Teacher profesorLeido = teacherDAO.leer(1);
			System.out.println("Profesor leído: " + profesorLeido);
		} catch (SQLException e) {
			System.out.println("Error al leer el profesor: " + e.getMessage());
		}*/

		// Actualizar un profesor
		/*try {
			Teacher profesorActualizado = new Teacher();
			profesorActualizado.setId(1); // ID del profesor a actualizar
			profesorActualizado.setName("Nuevo Nombre");
			profesorActualizado.setLastname("Nuevo Apellido");
			profesorActualizado.setAge(40);

			boolean actualizado = teacherDAO.actualizar(1, profesorActualizado);
			if (actualizado) {
				System.out.println("Profesor actualizado correctamente.");
			} else {
				System.out.println("Error al actualizar el profesor.");
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar el profesor: " + e.getMessage());
		}*/

		// Listar todos los profesores
		/*try {
			List<Teacher> listaProfesores = teacherDAO.listar();
			System.out.println("Lista de Profesores:");
			for (Teacher t : listaProfesores) {
				System.out.println(t);
			}
		} catch (SQLException e) {
			System.out.println("Error al listar los profesores: " + e.getMessage());
		}*/

		// --------------------------------SUBJECTS--------------------------------------

		// Insertar
		SubjectsDAO subjectsDAO = new SubjectsDAOImp();
		Subjects nuevoSubject = new Subjects();
		int idSubjectEliminar = 1;

		nuevoSubject.setName("Matematicas Dsicretas");
		nuevoSubject.setDescription("Es una rama de las matemáticas que estudia estructuras discretas");
		nuevoSubject.setLevel("Cuarto Semestre");

		// ALTER SEQUENCE subjects_id_seq RESTART WITH 1;

		// Insertar
		/*try {
			boolean creado = subjectsDAO.crear(nuevoSubject);
			if (creado) {
				System.out.println("Subject creado correctamente.");
			} else {
				System.out.println("Error al crear el subject.");
			}
		} catch (SQLException e) {
			System.out.println("Error al interactuar con la base de datos: " + e.getMessage());
		}*/

		// Eliminar

		/*try {
			boolean eliminado = subjectsDAO.eliminar(idSubjectEliminar);
			if (eliminado) {
				System.out.println("Subject eliminado correctamente.");
			} else {
				System.out.println("Error al eliminar el subject.");
			}
		} catch (SQLException e) {
			System.out.println("Error al interactuar con la base de datos: " + e.getMessage());
		}*/

		// Leer una asignatura por su ID
		/*try {
			Subjects asignaturaLeida = subjectsDAO.leer(1);
			System.out.println("Asignatura leída: " + asignaturaLeida);
		} catch (SQLException e) {
			System.out.println("Error al leer la asignatura: " + e.getMessage());
		}*/

		// Actualizar una asignatura
		/*try {
			Subjects asignaturaActualizada = new Subjects();
			asignaturaActualizada.setId(1); // ID de la asignatura a actualizar
			asignaturaActualizada.setName("Nuevo Nombre");
			asignaturaActualizada.setDescription("Nueva Descripción");
			asignaturaActualizada.setLevel("Nuevo Nivel");

			boolean actualizada = subjectsDAO.actualizar(1, asignaturaActualizada);
			if (actualizada) {
				System.out.println("Asignatura actualizada correctamente.");
			} else {
				System.out.println("Error al actualizar la asignatura.");
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar la asignatura: " + e.getMessage());
		}*/

		// Listar todas las asignaturas
		/*try {
			List<Subjects> listaAsignaturas = subjectsDAO.listar();
			System.out.println("Lista de Asignaturas:");
			for (Subjects s : listaAsignaturas) {
				System.out.println(s);
			}
		} catch (SQLException e) {
			System.out.println("Error al listar las asignaturas: " + e.getMessage());
		}*/

		// -------------------------------SCHEDULES-----------------------------------------------

		SchedulesDAO schedulesDAO = new SchedulesDAOImp();
		Schedules nuevoHorario = new Schedules();
		int idScheduleEliminar = 1;

		nuevoHorario.setId(1); // Id del Horario
		nuevoHorario.setId_sub(1); // Id de la asignatura
		nuevoHorario.setId_stu(1); // Id del estudiante
		nuevoHorario.setId_tea(1); // Id del profesor
		nuevoHorario.setStart_time(Time.valueOf("08:00:00")); // Hora de inicio del horario
		nuevoHorario.setEnd_time(Time.valueOf("10:00:00")); // Hora de fin del horario
		nuevoHorario.setDay("Lunes"); // Día del horario

		// Insertar
		/*try {
			boolean creado = schedulesDAO.crear(nuevoHorario);
			if (creado) {
				System.out.println("Horario creado correctamente.");
			} else {
				System.out.println("Error al crear el horario.");
			}
		} catch (SQLException e) {
			System.out.println("Error al interactuar con la base de datos: " + e.getMessage());
		}*/

		// Eliminar
		/*try {
			boolean eliminado = schedulesDAO.eliminar(idScheduleEliminar);
			if (eliminado) {
				System.out.println("Horario eliminado correctamente.");
			} else {
				System.out.println("Error al eliminar el horario.");
			}
		} catch (SQLException e) {
			System.out.println("Error al interactuar con la base de datos: " + e.getMessage());
		}*/

		// Leer un horario por su ID
		/*try {
			Schedules horarioLeido = schedulesDAO.leer(0);
			System.out.println("Horario leído: " + horarioLeido);
		} catch (SQLException e) {
			System.out.println("Error al leer el horario: " + e.getMessage());
		}*/

		// Actualizar un horario
		/*try {
			Schedules horarioActualizado = new Schedules();
			horarioActualizado.setId(1); // ID del horario a actualizar
			horarioActualizado.setId_sub(1); // ID de la asignatura
			horarioActualizado.setId_stu(1); // ID del estudiante
			horarioActualizado.setId_tea(1); // ID del profesor
			horarioActualizado.setStart_time(Time.valueOf("10:00:00")); // Hora de inicio del horario
			horarioActualizado.setEnd_time(Time.valueOf("12:00:00")); // Hora de fin del horario
			horarioActualizado.setDay("Miércoles"); // Día del horario

			boolean actualizado = schedulesDAO.actualizar(1, horarioActualizado);
			if (actualizado) {
				System.out.println("Horario actualizado correctamente.");
			} else {
				System.out.println("Error al actualizar el horario.");
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar el horario: " + e.getMessage());
		}*/

		// Listar todos los horarios
		/*try {
			List<Schedules> listaHorarios = schedulesDAO.listar();
			System.out.println("Lista de Horarios:");
			for (Schedules h : listaHorarios) {
				System.out.println(h);
			}
		} catch (SQLException e) {
			System.out.println("Error al listar los horarios: " + e.getMessage());
		}*/

	}
}
