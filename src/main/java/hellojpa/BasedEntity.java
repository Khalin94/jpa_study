package hellojpa;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// MappedSuperclass 어노테이션이 있는 클래스를 상속받으면 상속받은 객체는 해당 필드를 생성하지 않아도 자동으로 생성된다.
// 재사용성? 측면에서 좋다.
@MappedSuperclass
public class BasedEntity {

    @Column(name = "CREATED_NAME")
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "LAST_MODIFIED_NAME")
    private String lastModifiedBy;

    @Column(name = "LAST_MODIFIED_DATE")
    private String lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
