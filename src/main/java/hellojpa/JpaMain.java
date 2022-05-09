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


        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

        emf.close();
    }
}
