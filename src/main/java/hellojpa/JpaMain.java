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
           /*
            트랜젝션 이용시 꼭 begin()을 해주자!
            tx.begin();
            Member member = new Member(1L, "helloA");
            // member에 저장 (persist)
            em.persist(member);
            */

            /*
            // 비영속(new)
            Member findMember = em.find(Member.class, 1L);
            System.out.println("find member : " + findMember.getName());

            // 영속(persist)
            Member member = new Member(10L, "newMember");
            em.persist(member);

            // 준영속(detach)
            em.detach(member);

            // 삭제(remove)
            em.remove(member);
            */

            /**
             * JPQL
             * jpql은 객체지향적으로 쿼리를 만든다. (객체지향 쿼리 SQL)
             * jpql은 엔티티를 대상으로 쿼리(Member.class), 테이블 대상 X
             * */
            List<Member> memberList = em.createQuery("select m from Member as m", Member.class)
                    // 페이징을 처리할 때 많이 사용..
                    // 이렇게 사용하면 db가 바뀌어도 해당 db에 맞춰서 쿼리를 만든다.
                    // ex) oracle 이라면 rownum으로 만든다.
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : memberList) {
                System.out.println(member.getName());
            }

            // 삭제
            //em.remove(findMember);

            // db 업데이트 시 굳이 persist()를 안써도 된다.
            // 설계가 컬렉션을 다루는 것 처럼 되어 있다.
//            findMember.setName("helloJPA");

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
