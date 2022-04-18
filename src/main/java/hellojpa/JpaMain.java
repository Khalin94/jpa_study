package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
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

            Member member1 = new Member();
            member1.setName("member1");

            em.persist(member1);

            Member member2 = new Member();
            member2.setName("member2");

            em.persist(member2);

            em.flush();
            em.clear();

            Member findMember1 = em.getReference(Member.class, member1.getId());
            // 아직 영속성 컨택스트에 안 올려놨으므로 false
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(findMember1));

            // 프록시 강제 초기화
            findMember1.getName();

            // 영속성 컨택스트에 올라갔으므로 true
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(findMember1));


            Member findMember2 = em.getReference(Member.class, member2.getId());
            // hibernate에서 지원하는 프록시 강제초기화 방법(jpa 표준엔 없음, 표준에서 초기화하는 방법은 실제 데이터를 불러오는 것 밖에 없음)
            Hibernate.initialize(findMember2);
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(findMember2));


        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

        emf.close();
    }
}
