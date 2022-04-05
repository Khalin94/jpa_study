package hellojpa;

import javax.persistence.*;

@Entity
// strategy = InheritanceType.TABLE_PER_CLASS 는 사용하지 않는게 제일 좋다.
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 구현클래스 전략 - ITEM을 상속하는 클래스(엔티티) 테이블안에 item의 속성이 다 들어간다.(item 테이블은 만들어지지 않음), 부모클래스를 추상 클래스로 만들어야함)
//@DiscriminatorColumn // 구현클래스 전략에서는 필요하지 않다.
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private int price;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
