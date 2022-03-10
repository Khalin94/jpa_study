package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR", // SequenceGenerator 이름
        sequenceName = "MEMBER_SEQ", // 매핑할 시퀀스 이름
        initialValue = 1, allocationSize = 1
)
public class Member {

    // GeneratedValue가 없으면 id를 직접 할당해야됨.
    //@GeneratedValue(strategy = GenerationType.AUTO)  // GenerationType.AUTO는 db에 맞게 auto_increase, sequence 등등 맞는 인덱스를 넣어준다.
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increase h2 2.x 버전에서 버그가 있어서 자동으로 생성이 안된다...
//    @GeneratedValue(strategy = GenerationType.SEQUENCE) // sequenceGenerator를 주지 않으면 시퀀스가 자동생성 된다. (이름을 정할 수 없다.)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR" // SequenceGenerator(매핑) 이름을 준다.
    )
    private Long id;

    private String name;

    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
