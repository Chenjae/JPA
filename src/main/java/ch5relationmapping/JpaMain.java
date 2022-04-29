package ch5relationmapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            //member.setTeamId(team.getId());
            member.setTeam(team);
            em.persist(member);

            MemberCh5 findMember = em.find(MemberCh5.class, member.getId());
            //Long findTeamId = findMember.getTeamId();
            //TeamCh5 findTeam = em.find(TeamCh5.class, member.getTeamId());
            TeamCh5 fineTeam = findMember.getTeam();
            System.out.println("fineTeam.getName() = " + fineTeam.getName());

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
