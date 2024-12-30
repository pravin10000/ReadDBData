package DB.export.demo1.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Table2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
