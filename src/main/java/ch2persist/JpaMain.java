package ch2persist;

import ch1hellojpa.Member;

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
            //엔티티를 생성한 상태 : 비영속 상태
            Member member = new Member();
            member.setId(150L);
            member.setName("HelloJPA");
            //아직 DB에 저장되지 않음
            System.out.println("=== BEFORE ===");

            //persist : 엔티티를 영속
            //엔티티를 영속성 컨텐츠에 영속상태로 저장
            //1차 캐시와 쓰기 지연 SQL 저장소에 저장됨     *1차 캐시 : 한 트랜잭션 내에서 사용되는 캐시
            em.persist(member);

            //데이터 변경 감지
            member.setName("CCC");

            //데이터 삭제
            em.remove(150L);

            //준영속상태
            //엔티티를 영속성 컨텐츠에서 비영속상태로 분리 : 준영속 상태
            //1. 특정 엔티티만 준영속상태로 변경
            em.detach(member);
            //2. 영속성 컨텍스트를 통으로 초기화
            em.clear();
            //3. 영속성 컨텍스트를 종료
            em.close();

            //1차 캐시에서 조회, 1차 캐시에 없을 경우, DB에서 조회 후 1차 캐시에 저장한 뒤 객체를 반환
            Member findMember = em.find(Member.class, 150L);

            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());

            System.out.println("=== AFTER ===");

            //영속 엔티티의 동일성 보장
            Member a = em.find(Member.class, 100L);
            Member b = em.find(Member.class, 100L);

            //flush
            //영속성 컨텍스트의 변경내용을 데이터베이스에 동기화, 영속성 컨텍스트를 비우는 의미가 아니다.
            //트랜잭션이라는 작업 단위가 중요
            Member newMember = new Member(200L, "member200");
            em.persist(newMember);

            //1. flush() 직접 호출
            em.flush();
            //2. JPQL 실행전 자동 flush
            // JPQL 실행 시 엔티티의 변경 값이 반영이 안될 수 있기 때문에 실행전에 변경 사항을 flush
            //3. 트랜잭션 커밋시 자동 flush
            tx.commit();
            //1. 커밋 후 flush 자동 실행
            //2. 엔티티와 스냅샷 비교       *스냅샷 : DB에서 최초로 1차캐시로 읽어온 시점의 상태
            //3. 바뀐 것이 있으면 쓰기 지연 SQL 저장소에 UPDATE 쿼리를 생성
            //4. flush       *flush : 영속성 컨텍스트의 변경내용을 데이터베이스에 반영
        }catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
