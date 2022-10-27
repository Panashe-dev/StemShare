package obrien.Project.three.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AnswerDTO {

    @NotEmpty
    private String questionId;

    @NotEmpty
    private String answer;

}
