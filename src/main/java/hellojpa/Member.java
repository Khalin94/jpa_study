package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table("USER") 관례에 따라 클래스 이름과 같은 테이블에 자동으로 매핑이 되지만
// 실제 DB 테이블과 이름이 다를 경우 @Table Annotation을 사용하면 된다.
public class Member {

    @Id
    private Long id;

    // 실제 DB컬럼의 이름과 class의 필드 이름이 다를 경우 name 속성을 사용한다.
    // 실제 DB컬럼의 이름이 name일 경우
    @Column(name = "name")
    private String username;

    // int가 아닌 Integer여도 JPA가 알아서 적절한 DB타입으로 선택한다.
    private Integer age;

    // enum 타입일 경우
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // TemporalType의 경우 DATE, TIME, TIMESTAMP 3가지가 있는데 이는 DB타입이 나눠져 있어서 그렇다.
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // blob이나 clob 처럼 varchar를 넘어서는 데이터가 들어가야 할 때 해당 annotation을 사용한다.
    @Lob
    private String description;

    // 실제 DB에는 없고 프로그램 내에서 사용할 때
    @Transient
    private String temp;



}
