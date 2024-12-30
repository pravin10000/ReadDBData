package DB.export.demo1.repository;

import DB.export.demo1.entity.Table1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Table1Repository extends JpaRepository<Table1, Integer> {
}
