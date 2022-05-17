package service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dtos.UserPwdDto;
import model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	RestTemplate template;
	String urlBase="http://localhost:8000/crud";
	@Value("${user}")
	String user;
	@Value("${pwd}")
	String pwd;
	
	String token;

	public StudentServiceImpl(@Autowired RestTemplate template) {
		super();
		this.template = template;
	}
	
	private void getToken() {
		UserPwdDto userPwdDto = new UserPwdDto(user, pwd);
		token = template.postForObject(urlBase + "/login", userPwdDto, String.class);
	}
	
	private void checkToken() {
		if(token==null||token.equals("")) {
			getToken();
		}
	}
	
	private HttpHeaders getHeaders() {
		HttpHeaders headers=new HttpHeaders();
		headers.add("Authorization", "Bearer "+ token);		// Entre Bearer y el tokon debe haber un espacio en blanco
		return headers;
	}

	@Override
	public void altaStudent(Student student) {
		checkToken();
		template.exchange(urlBase + "/Alumno", HttpMethod.POST, new HttpEntity<Student>(student, getHeaders()), Void.class);	
	}

	@Override
	public List<Student> studentPorPuntuacionMinima(double puntuacion) {
		checkToken();
		Student[] estudiantes = template.exchange(urlBase + "/Alumnos", HttpMethod.GET,
				  								  new HttpEntity<>(getHeaders()), Student[].class).getBody();		// Recuperar todos los estuadiantes en un array
		return Arrays.stream(estudiantes).filter(s -> s.getPuntuacion() > puntuacion).collect(Collectors.toList());		// Se filtra y se transforma en una lista
	}

}
