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

    public void changeTeam(TeamCh5 team) {
        this.team = team;
        //1차 캐시 메모리에만 있을 경우를 위해 team의 Member List에도 객체를 세팅해줌
        team.getMembers().add(this);
    }

    //*Member의 toString과 Team의 toString에서 서로를 호출 해 무한 루프에 빠짐
    @Override
    public String toString() {
        return "MemberCh5{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", team=" + team +
                '}';
    }
}
