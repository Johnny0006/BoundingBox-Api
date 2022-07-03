package api.repository;

import api.entity.BoundingBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoundingBoxRepository extends JpaRepository<BoundingBox, Long> {
}
