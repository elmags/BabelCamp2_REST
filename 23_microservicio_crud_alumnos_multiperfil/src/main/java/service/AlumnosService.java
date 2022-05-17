package service;

import java.util.List;

import model.Alumno;

public interface AlumnosService {
	void altaAlumno(Alumno alumno);
	List<Alumno> alumnos();
	Alumno buscarAlumno(int idAlumno);
	boolean eliminarAlumno(int idAlumno);
	boolean actualizarAlumno(int idAlumno, String curso);
	List<String> cursos();
}
