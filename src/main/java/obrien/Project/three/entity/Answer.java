package obrien.Project.three.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "answers")
public class Answer extends  BaseEntity{

    public String answer;

}
