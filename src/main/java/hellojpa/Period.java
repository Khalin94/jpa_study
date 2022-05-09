package hellojpa;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable // 임베디드 값타입 사용을 위해서 해당 어노테이션이 필요하다.
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
