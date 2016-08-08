package com.jacob.dota2.dota2Application.data.history;

import com.google.gson.annotations.SerializedName;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MHMatch {

    private final PrettyTime mPrettyTime = new PrettyTime();
    private final Date mStartTime = new Date();

    @SerializedName("match_id")
    private String matchid;
    private List<MHPlayer> players;
    @SerializedName("match_seq_num")
    private String matchSeqNum;
    @SerializedName("start_time")
    private long startTime;
    @SerializedName("lobby_type")
    private String lobbyType;
    @SerializedName("radiant_team_id")
    private String radiantTeamId;
    @SerializedName("dire_team_id")
    private String direTeamId;

    public String getMatchid() {
        return matchid;
    }
    public List<MHPlayer> getPlayers() {
        return players;
    }

    public String getMatchSeqNum() {
        return matchSeqNum;
    }

    public String getStartTime() {
        mStartTime.setTime(startTime * 1000);
        return mPrettyTime.format(mStartTime);
    }

    public long getRawStartTime() {
        return startTime;
    }

    public String getLobbyType() {
        return lobbyType;
    }

    public String getRadiantTeamId() {
        return radiantTeamId;
    }

    public String getDireTeamId() {
        return direTeamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MHMatch mhMatch = (MHMatch) o;
        return startTime == mhMatch.startTime &&
                Objects.equals(mPrettyTime, mhMatch.mPrettyTime) &&
                Objects.equals(mStartTime, mhMatch.mStartTime) &&
                Objects.equals(players, mhMatch.players) &&
                Objects.equals(matchSeqNum, mhMatch.matchSeqNum) &&
                Objects.equals(lobbyType, mhMatch.lobbyType) &&
                Objects.equals(radiantTeamId, mhMatch.radiantTeamId) &&
                Objects.equals(direTeamId, mhMatch.direTeamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mPrettyTime, mStartTime,
                            players, matchSeqNum, startTime, lobbyType, radiantTeamId, direTeamId);
    }

}
