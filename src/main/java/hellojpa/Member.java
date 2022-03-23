package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {

    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR") // table sequence
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    // 테이블에 맞춰 외래키 자체를 가지고 오는 경우 (객체지향적이지 못하다)
//    @Column(name = "TEAM_ID")
//    private Long teamId;

    // 연관관계를 통해 데이터를 가지고 오는경우(객체 자체를 사용)
    // 연관관계의 주인 ( 보통 N:1일 때 N쪽이 연관관계의 주인이 된다. 프로세스적으로 중요한 것을 말하는 것이 아니다. simple하게 생각하면 foreign key를 가지는 쪽이 연관관계의 주인이라고 생각하면 된다.)
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

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

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        // 객체지향적으로 만들기 위해 양방향 매핑에서 양쪽에 데이터를 넣어줄 경우 아래와 같이 넣어주면 N 쪽에서 1을 set 할 때 자동으로 1(Member)의 값이 들어간다.
        team.getMembers().add(this);
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
