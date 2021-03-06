package ch1hellojpa;

import javax.persistence.Id;

//JPA가 로딩될 때 JPA가 사용되는 얘임을 알려줌, JPA가 읽고 관리
//@Entity
//매핑될 테이블
//@Table(name ="USER")
public class Member {
    //PK
    @Id
    private Long id;
    //매핑될 컬럼
    //@Column(name ="username")
    private String name;

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
