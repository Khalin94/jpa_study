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
            Member memberA = new Member(10L, "MemberA");
            em.persist(memberA); // 영속성 컨텍스트에 먼저 저장한다..

            // 1차 캐시를 사용하여 데이터를 가지고 오기 때문에 findMemberA와 findMemberA2는 같다 (findMemberA == findMemberB)
            // 같은 id로 가지고 온 데이터에 대한 동일성이 보장된다.
            Member findMemberA = em.find(Member.class, 10L);
            Member findMemberA2 = em.find(Member.class, 10L);

            System.out.println("is true? " + (findMemberA == findMemberA2));

            tx.commit(); // 커밋 시 insert 쿼리가 나간다.
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
