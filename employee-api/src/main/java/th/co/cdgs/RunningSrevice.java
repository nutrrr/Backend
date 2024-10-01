package th.co.cdgs;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

@ApplicationScoped
public class RunningSrevice {
    @Inject
    EntityManager entityManager;
    public Integer next(String entityClassName) {
        RunningSeq entity = entityManager
            .createQuery(" from RunningSeq where entityClass = :entityClass", RunningSeq.class)
            .setParameter("entityClass", entityClassName) .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .setHint("javax.persistence.lock.timeout", 5000) // timeout 5 วินาที
            .getSingleResult();
        return entity.incrementSequence();
    }
}