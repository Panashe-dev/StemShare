package obrien.Project.three.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "student")
public class Student  extends  Person{

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "level")
    private String level;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_address")
    private String schoolAddress;

    @Column(name = "birth_month")
    private String month;

    @Column(name = "birth_year")
    private String year;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
