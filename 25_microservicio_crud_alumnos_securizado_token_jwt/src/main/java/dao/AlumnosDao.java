package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Alumno;

public interface AlumnosDao extends JpaRepository<Alumno, Integer> {
//	@Query("SELECT distinct(a.curso) FROM Alumno a")
//	List<String> findCursos();
}
