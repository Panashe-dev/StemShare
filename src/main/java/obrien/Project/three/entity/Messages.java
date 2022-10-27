package obrien.Project.three.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "message")
public class Messages extends   BaseEntity{

    @Column(name = "message_id")
    private String messageId;

    @Column(name = "content")
    private String content;

    @Column(name = "sender")
    private String sender;

}
