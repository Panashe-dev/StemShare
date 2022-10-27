package obrien.Project.three.repository;

import obrien.Project.three.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author Panashe Obrien Mugomba
 *
 *  */

@Repository
@Transactional
public interface TutorRepository  extends JpaRepository<Tutor,Long> {


}
