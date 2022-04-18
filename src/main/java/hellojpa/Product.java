package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product extends BasedEntity{ // BasedEntity를 상속 받아 필드를 직접생성하지 않아도 공통으로 들어갈 필드가 생성된다.

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;

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
}
