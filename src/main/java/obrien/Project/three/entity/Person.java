package obrien.Project.three.entity;



import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@Data
@MappedSuperclass
public class Person  extends BaseEntity{

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

}
