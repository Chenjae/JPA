package ch1hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //persistence.xml에 설정한 UnitName
        //EntityManagerFactory는 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //EntityManager는 쓰레드간에 공유하지 X (사용하고 버려야한다)
        EntityManager em = emf.createEntityManager();

        //JPA에서 데이터를 변경하는 모든 작업은 트랜잭션 단위 안에서 실행 해야함
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //INSERT
            /*Member member = new Member();

            member.setId(1L);
            member.setName("HelloA");
            
            em.persist(member);*/

            Member findMember = em.find(Member.class, 1L);
            //SELECT
            /*
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());*/

            //DELETE
            /*em.remove(findMember);*/

            //UPDATE
            //persist를 안해도 적용 -> JAVA Collection과 같이 사용하도록 설계
            //JPA를 통해서 값을 가져오면 값이 바뀔 때마다 체크하고 UPDATE 쿼리를 만들어 트랜잭션 날리고 커밋한다.
            /*findMember.setName("HelloJPA");*/

            //JPQL : 테이블이 아닌 엔티티 객체를 대상으로 검색
            //SQL을 추상화해서 특정 DB SQL에 종속적이지 않은 쿼리 설계 가능
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    //pagination
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
