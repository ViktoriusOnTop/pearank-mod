package viktorius.pearank;

import java.util.ArrayList;
import java.util.List;

public class Config {

    public List<Rank> ranks = new ArrayList<>();

    private transient Rank rank = new Rank();

    private transient String testPerm = "test.perm";

    private transient String testPerm2 = "test.perm2";

    private transient String testName = "TestRank";

    private transient String testRankMember = "Viktorius_";

    private transient List<String> rankMembers = new ArrayList<>();

    private transient List<String> perms = new ArrayList<>();

    public List<Rank> getRanks() {
        return ranks;
    }

    public void setRanks(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public void setDefaultRank() {
        perms.add(testPerm);
        perms.add(testPerm2);
        rankMembers.add(testRankMember);
        rank.setRankMembers(rankMembers);
        rank.setRankName(testName);
        rank.setPermissions(perms);
        ranks.add(rank);
    }

    //member
    //perms: dungeon.enter, dungeon.start, rtp.teleport

    //developer
    //perms: member.rank, dungeon.debug, dungeon.open, dungeon.close


    //enums
    //dungeon, member,
}
