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
@Table(name = "resourceHub ")
public class ResourceHub extends  BaseEntity{

    @Column(name = "category")
    private String category;

    @Column(length = 512,nullable = false,unique = true)
    private String Document_Name;

    @Column(name = "size")
    private  Long Size;

    @Column(name = "uploadTime")
    private Date UploadTime;

    @Column(name = "content")
    private byte[] Content;

    @Column(name = "subCategory")
    private String subCategory;

    @Column(name = "level")
    private String level;

    public ResourceHub(Integer id, String document_Name, Long size) {
        id = id;
        Document_Name = document_Name;
        Size = size;
    }

}
