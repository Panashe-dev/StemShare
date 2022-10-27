package obrien.Project.three.repository;

import obrien.Project.three.entity.ResourceHub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ResourceHubRepository extends JpaRepository<ResourceHub,Integer> {



    List<ResourceHub> findAllByCategory(String category);

    @Override
    @Query("SELECT new ResourceHub (d.id, d.Document_Name, d.Size) FROM ResourceHub d ORDER BY d.UploadTime DESC")
    List<ResourceHub> findAll();
    @Query("SELECT new ResourceHub (d.id, d.Document_Name, d.Size) FROM ResourceHub d WHERE d.Document_Name= :name")
    Optional<ResourceHub> findByDocument_Name(String name);
}

