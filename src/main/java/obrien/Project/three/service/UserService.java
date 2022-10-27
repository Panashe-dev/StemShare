package obrien.Project.three.service;

import obrien.Project.three.dto.StudentDTO;
import obrien.Project.three.dto.TutorDTO;
import obrien.Project.three.dto.UserDTO;
import obrien.Project.three.entity.Tutor;
import obrien.Project.three.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

/**
 * @author Panashe Obrien Mugomba
 *
 *  */

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    User createUserStudent(StudentDTO studentDTO);

    User createUserTutor(TutorDTO tutorDTO);
    List<User> findAllUser(String keyword);
    Optional<User> findUserById();
}
