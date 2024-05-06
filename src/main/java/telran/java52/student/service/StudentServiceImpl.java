package telran.java52.student.service;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java52.student.dao.StudentRepository;
import telran.java52.student.dto.ScoreDto;
import telran.java52.student.dto.StudentAddDto;
import telran.java52.student.dto.StudentDto;
import telran.java52.student.dto.StudentUpdateDto;
import telran.java52.student.dto.exception.StudentNotFoundException;
import telran.java52.student.model.Student;

//service part 7 end
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	
	final ModelMapper modelMapper;
	
//	@Autowired
	final StudentRepository studentRepository; // part 6

	@Override
    public Boolean addStudent(StudentAddDto studentAddDto) {
        // Check if student with the provided ID already exists
        if(studentRepository.existsById(studentAddDto.getId())) {
            return false;
        }
        // Create a new Student object
//        Student student = new Student(studentAddDto.getId(), studentAddDto.getName(), studentAddDto.getPassword());
        Student student = modelMapper.map(studentAddDto, Student.class);
        // Save the student to the repository
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(Long id) {
        // Find the student by ID or throw StudentNotFoundException if not found
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        // Convert the Student entity to StudentDto and return
//        return new StudentDto(id, student.getName(), student.getScores());
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto removeStudent(Long id) {
        // Find the student by ID or throw StudentNotFoundException if not found
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        // Delete the student from the repository
        studentRepository.deleteById(id);
        // Convert the deleted Student entity to StudentDto and return
//        return new StudentDto(id, student.getName(), student.getScores());
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentAddDto updateStudent(Long id, StudentUpdateDto studentUpdateDto) {
        // Find the student by ID or throw StudentNotFoundException if not found
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        // If a new name is specified in the DTO, update it
        if (studentUpdateDto.getName() != null) {
            student.setName(studentUpdateDto.getName());
        }
        // If a new password is specified in the DTO, update it
        if (studentUpdateDto.getPassword() != null) {
            student.setPassword(studentUpdateDto.getPassword());
        }
        studentRepository.save(student);
        // Convert the updated Student entity to StudentAddDto and return
//        return new StudentAddDto(student.getId(), student.getName(), student.getPassword());
        return modelMapper.map(student, StudentAddDto.class);
    }

    @Override
    public Boolean addScore(Long id, ScoreDto scoreDto) {
        // Find the student by ID or throw StudentNotFoundException if not found
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        boolean res = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
        // Add the score to the student
        studentRepository.save(student);
        return res;
    }

    @Override
    public List<StudentDto> findStudentsByName(String name) {
    	// Retrieve all students from the repository
        // Filter students by name ignoring case
        // Convert filtered students to StudentDto objects
        // Return the list of StudentDto objects
        return studentRepository.findAll()
                .stream()
//                .filter(s -> name.equalsIgnoreCase(s.getName())) //filter in db
                .map(s -> modelMapper.map(s, StudentDto.class))
                .toList();
    }

    @Override
    public Long getStudentsNamesQuantity(Set<String> names) {
    	 // Retrieve all students from the repository
        // Filter students by names set
        // Count the occurrences of filtered students
//        return studentRepository.findAll()
//                .stream()
//                .filter(s -> names.contains(s.getName()))
//                .count();  
    	return studentRepository.countAllByNameInIgnoreCase(names);
    }

    @Override
    public List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore) {
    	// Retrieve all students from the repository
        // Filter students by exam and minimum score criteria
        // Convert filtered students to StudentDto objects
        // Return the list of StudentDto objects
//        return studentRepository.findAll()
//                .stream()
//                .filter(s -> s.getScores().containsKey(exam) && s.getScores().get(exam) > minScore)
//                .map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
//                .toList();
    	return studentRepository.findByExamAndScoreGreaterThan(exam, minScore)
    			.map(s -> modelMapper.map(s, StudentDto.class))
                .toList();
    }

}
