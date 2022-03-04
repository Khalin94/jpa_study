package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table("USER") 관례에 따라 클래스 이름과 같은 테이블에 자동으로 매핑이 되지만
// 실제 DB 테이블과 이름이 다를 경우 @Table Annotation을 사용하면 된다.
public class Member {

    @Id
    private Long id;

    //@Column("user_name") Table과 같이 관례에 따름 db 컬럼 이름이 다를경우 @Column annotaion을 사용한다.
    private String name;

    public Member() {

    }
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
