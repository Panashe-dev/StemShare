package obrien.Project.three.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Data
public class TutorDTO {


    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    private String gender;
    @NotEmpty
    private String address;

    private String level;
    @NotEmpty
    private String schoolName;
    @NotEmpty
    private String schoolAddress;

    @NotEmpty
    private String subject;
}
