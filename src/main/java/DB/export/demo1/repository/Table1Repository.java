package DB.export.demo1.repository;

import DB.export.demo1.entity.Table1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface Table1Repository extends JpaRepository<Table1, Integer> {
    List<Table1> findAllByOrderByUpdatedAtDesc();

    @Query("SELECT e FROM Table1 e WHERE e.updatedAt > :lastProcessedAt ORDER BY e.updatedAt")
    List<Table1> findUpdatedData(LocalDateTime lastProcessedAt);
}
