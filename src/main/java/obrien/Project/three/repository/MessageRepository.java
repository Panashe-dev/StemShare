package obrien.Project.three.repository;

import obrien.Project.three.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public interface MessageRepository  extends JpaRepository<Messages,Integer> {

    Collection<Messages> findAllByMessageId(String id);


}
