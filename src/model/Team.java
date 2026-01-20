package model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private int id;
    private String teamName;
    private Shinobi leader;
    private List<Shinobi> members;

    public Team(int id, String teamName, Shinobi leader) {
        this.id = id;
        this.teamName = teamName;
        this.leader = leader;
        this.members = new ArrayList<>();
    }

    public Shinobi getLeader() { return leader; }
    public void setLeader(Shinobi leader) { this.leader = leader; }

    public void addMember(Shinobi member) {
        this.members.add(member);
    }
}