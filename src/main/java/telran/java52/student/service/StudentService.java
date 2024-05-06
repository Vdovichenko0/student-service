package telran.java52.student.service;
//interface part 2
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import telran.java52.student.dto.ScoreDto;
import telran.java52.student.dto.StudentAddDto;
import telran.java52.student.dto.StudentDto;
import telran.java52.student.dto.StudentUpdateDto;

@Component
public interface StudentService {
	Boolean addStudent(StudentAddDto studentAddDto);
	
	StudentDto findStudent(Long id);
	
	StudentDto removeStudent(Long id);
	
	StudentAddDto updateStudent(Long id, StudentUpdateDto studentUpdateDto);
	
	Boolean addScore(Long id, ScoreDto scoreDto);
	
	List<StudentDto> findStudentsByName(String name);
	
	Long getStudentsNamesQuantity(Set<String> names);
	
	List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore);
}
