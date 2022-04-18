package hellojpa;

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


            // getReference() 는 프록시 객체를 가지고 온다.
            Member findMember1 = em.getReference(Member.class, member1.getId());
            System.out.println("findMember1 : " + findMember1.getClass());
            System.out.println(findMember1.getName());

            // find() 실제 원본 엔티티를 가지고온다
            Member findMember2 = em.find(Member.class, member2.getId());
            System.out.println("findMember2 : " + findMember2.getClass());
            System.out.println(findMember2.getName());

            Member findSameMember = em.getReference(Member.class, member2.getId());
            System.out.println("same Member : " + findSameMember.getClass());

            System.out.println("==============================================");

            // 실제 엔티티와 프록시 객체를 비교하므로 당연히 false;
            System.out.println("findMember1 == findMember2 : " + (findMember1 == findMember2));

            System.out.println(" instanceof 비교 : " + ((findMember1 instanceof Member) && (findMember2 instanceof Member)));

            // 같은 멤버에 대해서 jpa가 서로 같음을 보장해준다.
            System.out.println("findMember2 == findSamemember : " + (findMember2 == findSameMember));


            tx.commit(); // 커밋 시 insert 쿼리가 나간다.
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
