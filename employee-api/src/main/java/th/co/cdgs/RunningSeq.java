package th.co.cdgs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "running_seq",
uniqueConstraints = {@UniqueConstraint (columnNames = {"entity_class", "current_sequence"})}) 
public class RunningSeq
{
    @Id
    @Column(name = "entity_class")
    private String entityClass;
    @Column(name = "current_sequence", nullable = false)
    private int currentSequence;
    public Integer incrementSequence() {
        return ++this.currentSequence;
    }
    public String getEntityClass() {
        return entityClass;
    }
    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }
    public int getCurrentSequence() {
        return currentSequence;
    }
    public void setCurrentSequence(int currentSequence) {
        this.currentSequence = currentSequence;
    }
}