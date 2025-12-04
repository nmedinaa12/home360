package MicroservicioCasas.infrastructure.repositories.mysql;

import MicroservicioCasas.infrastructure.entities.LocationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<LocationEntity, Long>{
    Optional<LocationEntity> findByDepartmentAndCity(String department, String city);

    @Query("SELECT l FROM LocationEntity l " +
            "WHERE (:searchText IS NULL OR " +
            "(LOWER(l.city) LIKE LOWER(CONCAT('%', :searchText, '%')) " +
            "OR LOWER(l.department) LIKE LOWER(CONCAT('%', :searchText, '%')) " +
            "OR EXISTS (SELECT 1 FROM LocationEntity l2 " +
            "WHERE l2.id = l.id " +
            "AND (LOWER(l2.city) LIKE LOWER(CONCAT('%', :word1, '%')) " +
            "OR LOWER(l2.department) LIKE LOWER(CONCAT('%', :word1, '%'))) " +
            "AND (LOWER(l2.city) LIKE LOWER(CONCAT('%', :word2, '%')) " +
            "OR LOWER(l2.department) LIKE LOWER(CONCAT('%', :word2, '%'))))))")
    Page<LocationEntity> searchByText(
            @Param("searchText") String searchText,
            @Param("word1") String word1,
            @Param("word2") String word2,
            Pageable pageable
    );
}