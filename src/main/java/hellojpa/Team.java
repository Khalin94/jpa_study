package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    // 일대다 연관관계
    // foreign key가 없는 쪽에 @OneToMany로 연관관계를 맺어준다.
    // persist 시 값을 업데이트 시켜주기 때문에 사용이 권장되지는 않음.. DB 테이블이 많을 시 헷갈린다.
    // update를 한번 더 날려야되기 때문에 비용도 증가함.
    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    List<Member> members = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
