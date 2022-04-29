package ch5relationmapping;

import javax.persistence.*;

@Entity
@Table(name = "member")
public class MemberCh5 {
    @Id @GeneratedValue
    @Column(name= "member_id")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    //@Column(name = "TEAM_ID")
    //private Long teamId;

    //멤버 N : 팀 1
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private TeamCh5 team;



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

    /*public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }*/

    public TeamCh5 getTeam() {
        return team;
    }

    public void setTeam(TeamCh5 team) {
        this.team = team;
    }
}
