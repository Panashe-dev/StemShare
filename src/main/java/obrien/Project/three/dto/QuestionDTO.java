package obrien.Project.three.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class QuestionDTO {

    @NotEmpty
    private String question;
}
