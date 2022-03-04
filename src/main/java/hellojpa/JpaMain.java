package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // persistence.xml에서 설정해준 unit-name
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
            Member findMember = em.find(Member.class, 1L);
            System.out.println("find member : " + findMember.getName());

            // 삭제
            //em.remove(findMember);

            // db 업데이트 시 굳이 persist()를 안써도 된다.
            // 설계가 컬렉션을 다루는 것 처럼 되어 있다.
            findMember.setName("helloJPA");

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
