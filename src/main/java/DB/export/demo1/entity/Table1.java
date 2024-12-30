package DB.export.demo1.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
public class Table1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table1 table1 = (Table1) o;
        return Objects.equals(id, table1.id) &&
                Objects.equals(name, table1.name) &&
                Objects.equals(updatedAt, table1.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, updatedAt);
    }

    @Override
    public String toString() {
        return "Table1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", updateAt=" + updatedAt +
                '}';
    }
}
