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
            Member memberA = new Member(100L, "MemberA");
            Member memberB = new Member(101L, "MemberB");

            // 트랜잭션을 지원하는 쓰기 지연
            // persist()시 db로 insert Query가 나가지 않고 persistence context에 저장된다.
            // commit()시 db로 insert Query를 모아서 한번에 보낸다.
            em.persist(memberA);
            em.persist(memberB);
            System.out.println("===============================");

            tx.commit(); // 커밋 시 insert 쿼리가 나간다.
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
