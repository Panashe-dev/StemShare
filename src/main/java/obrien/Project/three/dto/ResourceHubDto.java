package obrien.Project.three.dto;

import lombok.Data;

import javax.persistence.Column;


@Data
public class ResourceHubDto {
    private String category;
    private String documentName;
    private String subCategory;
    private String level;
}
