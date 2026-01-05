package viktorius.pearank;

import java.util.ArrayList;
import java.util.List;

public class Rank {

    private String rankName;

    private List<String> permissions = new ArrayList<>();

    public List<String> getRankMembers() {
        return rankMembers;
    }

    public void setRankMembers(List<String> rankMembers) {
        this.rankMembers = rankMembers;
    }

    private List<String> rankMembers = new ArrayList<>();

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

}
