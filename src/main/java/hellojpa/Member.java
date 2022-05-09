package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Member extends BasedEntity{ // BasedEntity를 상속 받아 필드를 직접생성하지 않아도 공통으로 들어갈 필드가 생성된다.

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne
    private Team team;

    @Embedded // 다른 엔티티에서도 많이 사용될 것 같은 객체는 뽑아서 사용할 수 있도록 따로 뽑아낸 후 @Embedded 어노테이션을 붙혀준다.
    private Period workPeriod;

    @Embedded
    private Address workAddress;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }
}
