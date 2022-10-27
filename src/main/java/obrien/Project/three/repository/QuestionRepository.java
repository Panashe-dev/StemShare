package obrien.Project.three.repository;

import obrien.Project.three.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface QuestionRepository  extends JpaRepository<Question,Integer> {


}