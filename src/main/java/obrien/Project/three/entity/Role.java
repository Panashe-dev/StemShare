package obrien.Project.three.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Panashe Obrien Mugomba
 *
 *  */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_role")
public class Role  extends BaseEntity{
    private String name;

}
