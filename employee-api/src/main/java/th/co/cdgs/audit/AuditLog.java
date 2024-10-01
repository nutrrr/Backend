package th.co.cdgs.audit;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;



@Entity
@Table(name = "audit_log")
public class AuditLog{

	@Id
	@Column(name = "tx_date")
    private Date txDate;

    @Column(name = "action")
    private String action;
    
    @Column(name = "data")
    private String data;   
    
   @PrePersist
    protected void onCreate() {
        this.txDate = new Date(System.currentTimeMillis());
    }

    public AuditLog(){

    }

    public AuditLog(String action , String date){
        this.action = action;
        this.data = date;
    }



}
