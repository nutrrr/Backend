package th.co.cdgs.audit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;;


@ApplicationScoped
public class AuditLogService {
    @Inject
    EntityManager entityManager;

    @Transactional(TxType.REQUIRES_NEW)
    public void logCreation(AuditLog auditLog){
        entityManager.persist(auditLog);
    }
}
