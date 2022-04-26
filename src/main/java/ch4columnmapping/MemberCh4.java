package ch4columnmapping;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
//Unique 제약 조건
//@Table(uniqueConstraints = )
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 50) //allocationSize : DB의 SEQUENCE에 50개 번호를 미리 땡겨놓고 사용
public class MemberCh4 {
    @Id
    //AUTO, IDENTITY : TABLE에 위임, SEQUENCE : SEQUENCE OBJECT로 부터 만듬, TABLE : 키 생성 전용 테이블을 하나 만들어서 시퀀스 흉내
    //IDENTITY 전략을 사용하면 persist() 시점에 INSERT 발생
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    //데이터 컬럼명을 별도로 지정 "name"
    @Column(name = "name") private String username;
    private Integer age;
    //enum
    //EnumType.ORDINAL: enum 순서(번호)를 DB에 저장, enum 순서가 바뀔 경우 반영안됨 -> 오류 야기
    @Enumerated(EnumType.STRING) private RoleType roleType;
    //DATE, TIME, TIMESTAMP
    @Temporal(TemporalType.TIMESTAMP) private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP) private Date lastModifiedDate;
    //최신버전의 Hibernate에서는 자동으로 Date 타입 지정해줌
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;
    //CLOB, BLOB
    //문자면 CLOB, byte면 BLOB으로 타입 지정된다
    @Lob
    private String description; //Getter, Setter…
    //매핑하지 않음
    @Transient
    private String address;

    public MemberCh4() {
    }

    public MemberCh4(Long id, String username, Integer age, RoleType roleType, Date createdDate, Date lastModifiedDate, String description, String address) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.roleType = roleType;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.description = description;
        this.address = address;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}