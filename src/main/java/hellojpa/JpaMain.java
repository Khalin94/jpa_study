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
            // 양방향 매핑 시 객체에 데이터를 넣어주기 위한 방법2 set 할 때 넣어준다.
            member.changeTeam(team);
            em.persist(member);

            // 객체지향적으로 생각했을 때 team과 member 모두 넣어주는 것이 맞다... ( add를 안해줘도 db에 값은 업데이트 된다.)
            // 또한 flush나 clear를 하지 않을 경우 add 없이 가지고 오면 1차 캐시에만 값이 있으므로 team.getMembers() 에 값이 없게 된다.
            // 양방향 매핑 시 객체에 데이터를 넣어주기 위한 방법1 데이터를 불러와 add 해준다.
            // team.getMembers().add(member);

            // 양방향 매핑 시 객체에 데이터를 넣어주기 위한 방법3 add 메서드를 만들어서 넣어준다.
            //team.addMember(member);

//            em.flush();
//            em.clear();

            //연관관계를 통해 객체를 가지고 오는경우(객체지향적이다.)
            Member findMember = em.find(Member.class, member.getId());
            // 멤버에서 팀을 가지고 올 수도 있고 팀에서 멤버를 가지고 올 수도 있다 (양방향 연관관계)
            List<Member> findTeam = findMember.getTeam().getMembers();

            // flush, clear 를 하지 않고 Members에도 값을 넣지 않으면 1차 캐시에만 값이 있으므로 member 값을 가지고 오지 못한다.
            System.out.println("===========================");
            for (Member m : findTeam) {
                System.out.println("member :: " + m.getName());
            }
            System.out.println("===========================");

            tx.commit(); // 커밋 시 insert 쿼리가 나간다.
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
