package ch3mapping;

import javax.persistence.*;

//JPA가 로딩될 때 JPA가 사용되는 얘임을 알려줌, JPA가 읽고 관리
//@Entity
//매핑될 테이블
//@Table(name ="USER")
public class MemberCh3 {
    //PK
    @Id
    //자동 생성
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //매핑될 컬럼
    @Column(unique=true, length=10)
    private String username;
    private int age;

    public MemberCh3(Long id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public MemberCh3() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
