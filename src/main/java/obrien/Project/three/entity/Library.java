package obrien.Project.three.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "library")
public class Library extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String  bookName;

    @Column(name = "author")
    private String  author;

    @Column(name = "size")
    private  Long Size;

    @Column(name = "uploadTime")
    private Date UploadTime;

    @Column(name = "content")
    private byte[] Content;

    @Column(name = "subject")
    private String subject;

    @Column(name = "level")
    private String level;

    @Column(name = "Publish_Date")
    private String datePublish;

    public Library(Integer id, String bookName, Long size) {
        id = id;
        bookName=bookName;
        Size = size;
    }
}
