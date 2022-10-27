package obrien.Project.three.mapper;

import obrien.Project.three.dto.StudentDTO;
import obrien.Project.three.dto.TutorDTO;
import obrien.Project.three.entity.Role;
import obrien.Project.three.entity.Student;
import obrien.Project.three.entity.Tutor;
import obrien.Project.three.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static Role roleMapper(){
        Role role=new Role();
        role.setCreatedBy("SYS");
        role.setName("ROLE_STUDENT");
        role.setCreatedDate(LocalDateTime.now());
        role.setLastModifiedBy(null);
        role.setLastModifiedDate(null);
        role.setDeleted(false);
        return role;
    }

    public static Role roleTutorMapper(){
        Role role=new Role();
        role.setCreatedBy("SYS");
        role.setName("ROLE_TUTOR");
        role.setCreatedDate(LocalDateTime.now());
        role.setLastModifiedBy(null);
        role.setLastModifiedDate(null);
        role.setDeleted(false);
        return role;
    }

    public static User userMapper(StudentDTO studentDTO){
        List<Role> roles=new ArrayList<>();
        roles.add(Mapper.roleMapper());
        var user=new User();
        user.setFirstName(studentDTO.getFirstName());
        user.setLastName(studentDTO.getLastName());
        user.setUserName(studentDTO.getUserName());
        user.setEmail(studentDTO.getEmail());
        user.setPhone("+263786258832");
        user.setCreatedBy("System");
        user.setCreatedDate(LocalDateTime.now());
        user.setLastModifiedBy(null);
        user.setLastModifiedDate(null);
        user.setDeleted(false);
        user.setRoles(roles);
        user.setDeleted(false);
        return user;
    }

    public static Student studentMapper(StudentDTO studentDTO){
        Student student=new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setAddress(studentDTO.getAddress());
        student.setLevel(studentDTO.getLevel());
        student.setGender(studentDTO.getGender());
        student.setMonth(studentDTO.getMonth());
        student.setYear(studentDTO.getYear());
        student.setSchoolAddress(studentDTO.getSchoolAddress());
        student.setSchoolName(studentDTO.getSchoolName());
        student.setCreatedBy("System");
        student.setCreatedDate(LocalDateTime.now());
        student.setLastModifiedBy(null);
        student.setLastModifiedDate(null);
        return student;
    }

    public static Tutor tutorMapper(TutorDTO tutorDTO){
        Tutor tutor =new Tutor();
        tutor.setFirstName(tutorDTO.getFirstName());
        tutor.setLastName(tutorDTO.getLastName());
        tutor.setAddress(tutorDTO.getAddress());
        tutor.setLevel(tutorDTO.getLevel());
        tutor.setSubject(tutorDTO.getSubject());
        tutor.setGender(tutorDTO.getGender());
        tutor.setSchoolAddress(tutorDTO.getSchoolAddress());
        tutor.setSchoolName(tutorDTO.getSchoolName());
        tutor.setCreatedBy("System");
        tutor.setCreatedDate(LocalDateTime.now());
        tutor.setLastModifiedBy(null);
        tutor.setLastModifiedDate(null);
        return tutor;
    }

    public static User userTutorMapper(TutorDTO tutorDTO){
        List<Role> roles=new ArrayList<>();
        roles.add(Mapper.roleTutorMapper());
        var user=new User();
        user.setFirstName(tutorDTO.getFirstName());
        user.setLastName(tutorDTO.getLastName());
        user.setUserName(tutorDTO.getUserName());
        user.setEmail(tutorDTO.getEmail());
        user.setPhone("+263786258832");
        user.setCreatedBy("System");
        user.setCreatedDate(LocalDateTime.now());
        user.setLastModifiedBy(null);
        user.setLastModifiedDate(null);
        user.setDeleted(false);
        user.setRoles(roles);
        user.setDeleted(false);
        return user;
    }


}
