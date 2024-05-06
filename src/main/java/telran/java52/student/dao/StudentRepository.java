package telran.java52.student.dao;

import java.util.Set;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.java52.student.model.Student;

//DAO/Repository part 5
//delete impl 
public interface StudentRepository extends MongoRepository<Student, Long> {
	Stream<Student> getAllBy();
	
	Stream<Student> findByNameIgnoreCase(String name);
	
	Long countAllByNameInIgnoreCase(Set<String> names);
	
	@Query("{'scores.?0': {$gt: ?1}} ")
	Stream<Student> findByExamAndScoreGreaterThan(String exam, int score);
}
