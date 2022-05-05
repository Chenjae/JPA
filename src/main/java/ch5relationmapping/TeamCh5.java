package ch5relationmapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="team")
public class TeamCh5 {
    @Id @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;
    private String name;
    
    //양방향 매핑
    @OneToMany(mappedBy = "team")
    private List<MemberCh5> members = new ArrayList<>();

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

    public List<MemberCh5> getMembers() {
        return members;
    }

    public void setMembers(List<MemberCh5> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "TeamCh5{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", members=" + members +
                '}';
    }
}
