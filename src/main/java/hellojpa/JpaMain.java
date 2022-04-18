package hellojpa;

import javax.persistence.*;
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
            Team team = new Team();
            team.setName("teamA");

            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setCreatedDate(LocalDateTime.now());
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

//            Member findMember = em.find(Member.class, member.getId());
            // em.getReference 로 가지고 올 시 실제 Member 객체를 주는 것이 아님.
            // jpa 가 proxy 객체를 만들어서 보여주는 것
            // 프록시 객체는 origin 객체를 상속받아서 가지고 온다.
            Member findMember = em.getReference(Member.class, member.getId()); // getReference : class hellojpa.Member$HibernateProxy$pdPvfM28
            System.out.println("getReference : " + findMember.getClass());

            // 실제로 값을 가지고 올 때 쿼리가 나간다.
            System.out.println(findMember.getName());
//            System.out.println("============================");
//            Team findTeam = findMember.getTeam();
//            System.out.println(findTeam.getName());

            tx.commit(); // 커밋 시 insert 쿼리가 나간다.
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
