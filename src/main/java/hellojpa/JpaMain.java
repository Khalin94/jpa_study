package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        /**
         * EntityManagerFactory의 경우 프로젝트 당 하나를 생성해야 한다.
         * META-INF의 unit-name과 맞춰주어야 한다.
         * */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // persistence.xml에서 설정해준 unit-name
        /**
         * EntityManager는 jdbc로 치면 Connection 맺는 것과 비슷하다고 볼 수 있다.
         * 사용후 반납해야 한다.
         * */
        EntityManager em = emf.createEntityManager();

        // jpa에서 insert, update, delete 등 테이블이 변경되는 작업을 하려면 transaction을 생성해야됨. (EntityTransaction)
        EntityTransaction tx = em.getTransaction();
        // transaction 시작
        tx.begin();

        try {
            // 변경감지(Dirty Checking)
            // jpa에서는 따로 update()가 없고 해당 객체의 데이터가 변경되면 commit()시 자동으로 update쿼리를 생성한다.
            // update()가 따로 없는 이유는 jpa는 데이터를 java Collection 과 비슷하게 객체를 컨트롤하는 것이 컨셉이기 때문이다.(객체 지향적?)
//            Member member = em.find(Member.class, 100L);
//            member.setName("MemberC");
            Member member = new Member();
            member.setName("jpa");
            member.setAge(20);

            em.persist(member);

            tx.commit(); // 커밋 시 insert 쿼리가 나간다.
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
