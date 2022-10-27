package obrien.Project.three.entity;


import lombok.*;

import javax.persistence.*;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "question")
public class Question extends BaseEntity {

    @Column(name = "question")
    private String question;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "Question_id")
    @OrderBy("createdDate")
    private List<Answer> answers=new ArrayList<>();

    public void addAnswer(Answer answer) {
        if (answer.isNew()) {
            getAnswers().add(answer);
        }
    }

}
