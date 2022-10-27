package obrien.Project.three.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Panashe Obrien Mugomba
 *
 *  */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user",uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User extends  Person {

    @Column(name = "username")
    private String userName;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "phone_number",nullable = false)
    private String phone;

    @Column(name = "password",nullable = false)
    private String password;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",referencedColumnName = "id"))

    private Collection<Role> roles;

}
