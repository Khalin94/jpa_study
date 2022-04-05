package hellojpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // @inheritance를 사용하지 않으면 ITEM 컬럼에 다 들어가게 되는 Single Table 전략이 사용된다.
@DiscriminatorColumn // 상속받은 엔티티가 들어갈 수 있는 DTYPE(default) 컬럼이 생성된다 name 속성으로 변경가능
public class Item {

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
