package obrien.Project.three.dto;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Embedded;

import javax.validation.constraints.NotEmpty;

@Data
public class StudentDTO {

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

    private String month;

    private String year;

}
