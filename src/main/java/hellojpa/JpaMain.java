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

            System.out.println("start >> ");

            //저장
            Team team = new Team();
            team.setName("teamA");
            em.persist(team); // persist()를 하면 영속성 컨텍스트에서 관리하여 pk를 만드므로 밑에서 처럼 바로 사용가능하다.

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            // 테이블을 기준으로 객체를 생성하면 해당 객체를 가지고오기위해 외래키를 가지고와 찾아야한다.(객체 자체를 참조하는 것이 아니다.)
//            Member findMember = em.find(Member.class, member.getId());
//            Long teamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, teamId);


            //연관관계를 통해 객체를 가지고 오는경우(객체지향적이다.)
            Member findMember = em.find(Member.class, member.getId());
            // 멤버에서 팀을 가지고 올 수도 있고 팀에서 멤버를 가지고 올 수도 있다 (양방향 연관관계)
            List<Member> findTeam = findMember.getTeam().getMembers();

            for (Member m : findTeam) {
                System.out.println("member :: " + m.getName());
            }




            tx.commit(); // 커밋 시 insert 쿼리가 나간다.
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
