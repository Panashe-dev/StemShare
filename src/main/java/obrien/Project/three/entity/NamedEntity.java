package obrien.Project.three.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Panashe Obrien Mugomba
 *
 *  */

@MappedSuperclass
@Data
public class NamedEntity extends BaseEntity {
    @Column(name = "name")
    private String name;
}
