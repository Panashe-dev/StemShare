package obrien.Project.three.repository;

import obrien.Project.three.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Panashe Obrien Mugomba
 *
 *  */

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByUserName(String name);
    Optional<User> findUserById(long Id);
}
