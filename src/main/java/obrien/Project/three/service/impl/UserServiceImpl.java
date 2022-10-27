package obrien.Project.three.service.impl;

import groovy.util.logging.Slf4j;
import obrien.Project.three.dto.StudentDTO;
import obrien.Project.three.dto.TutorDTO;
import obrien.Project.three.entity.Role;
import obrien.Project.three.entity.User;
import obrien.Project.three.entity.UserDetail;
import obrien.Project.three.mapper.Mapper;
import obrien.Project.three.repository.StudentRepository;
import obrien.Project.three.repository.TutorRepository;
import obrien.Project.three.repository.UserRepository;
import obrien.Project.three.service.UserService;
import obrien.Project.three.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Panashe Obrien Mugomba
 *
 *  */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private  final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final TutorRepository tutorRepository;

    @Autowired
    private BCryptPasswordEncoder PasswordEncoder;

    public UserServiceImpl(StudentRepository studentRepository, UserRepository userRepository, TutorRepository tutorRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.tutorRepository = tutorRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUserName(username).orElseThrow(
                ()->new IllegalArgumentException("User not found")
        );
    }

    @Override
    @Transactional
    public User createUserStudent(StudentDTO studentDTO) {
        var user = Mapper.userMapper(studentDTO);
        user.setPassword(PasswordEncoder.encode(studentDTO.getPassword()));
        var _user = userRepository.save(user);
        var student = Mapper.studentMapper(studentDTO);
        student.setUser(_user);
        studentRepository.save(student);
        return _user;
    }

    @Override
    public User createUserTutor(TutorDTO tutorDTO) {
        var user = Mapper.userTutorMapper(tutorDTO);
        user.setPassword(PasswordEncoder.encode(tutorDTO.getPassword()));
        var _user = userRepository.save(user);
        var tutor=Mapper.tutorMapper(tutorDTO);
        tutor.setUser(_user);
        this.tutorRepository.save(tutor);
        return _user;
    }

    @Override
    public List<User> findAllUser(String keyword) {
        return null;
    }

    @Override
    public Optional<User> findUserById() {
        Optional<Long> userByID = SecurityUtils.findUserByID();
        if (userByID.isPresent()){
            return userRepository.findUserById(userByID.get());
        }else {
            throw  new IllegalStateException("no such user") ;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetail(userRepository.findUserByUserName(username).orElseThrow(
                ()-> new UsernameNotFoundException("User not fount")));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return	roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}

