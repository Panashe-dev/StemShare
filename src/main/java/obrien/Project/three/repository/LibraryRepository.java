package obrien.Project.three.repository;

import obrien.Project.three.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LibraryRepository extends JpaRepository<Library,Integer> {
    List<Library> findAllBySubject(String subject);
}
