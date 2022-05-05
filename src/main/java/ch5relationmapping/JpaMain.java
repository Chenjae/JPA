package ch5relationmapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            //객체를 테이블에 맞추어 데이터 중심으로 모델링하면 협력 관계를 만들 수 없다
            TeamCh5 team = new TeamCh5();
            team.setName("TeamA");
            em.persist(team);

            MemberCh5 member = new MemberCh5();
            member.setUsername("member1");
            member.changeTeam(team); // 연관관계 편의 메서드 *주의 : 한 객체에서만 메서드를 만들 것
            em.persist(member);

            //flush를 하지 않을 경우를 위해 양쪽에 값을 세팅 -> Member의 Team Setter를 변경
            //team.getMembers().add(member);

            //em.flush();
            //em.clear();

            MemberCh5 findMember = em.find(MemberCh5.class, member.getId());
            
            TeamCh5 fineTeam = findMember.getTeam();
            System.out.println("fineTeam.getName() = " + fineTeam.getName());
            
            //양방향 연관 관계
            List<MemberCh5> members = findMember.getTeam().getMembers();
            for(MemberCh5 m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
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
