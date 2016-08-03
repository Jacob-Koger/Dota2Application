package com.example.jacobkoger.dota2application.data.accountinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("steamid")
    @Expose
    private String steamid;
    @SerializedName("communityvisibilitystate")
    @Expose
    private Integer communityvisibilitystate;
    @SerializedName("profilestate")
    @Expose
    private Integer profilestate;
    @SerializedName("personaname")
    @Expose
    private String personaname;
    @SerializedName("lastlogoff")
    @Expose
    private Integer lastlogoff;
    @SerializedName("profileurl")
    @Expose
    private String profileurl;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("avatarmedium")
    @Expose
    private String avatarmedium;
    @SerializedName("avatarfull")
    @Expose
    private String avatarfull;
    @SerializedName("personastate")
    @Expose
    private Integer personastate;
    @SerializedName("realname")
    @Expose
    private String realname;
    @SerializedName("primaryclanid")
    @Expose
    private String primaryclanid;
    @SerializedName("timecreated")
    @Expose
    private Integer timecreated;
    @SerializedName("personastateflags")
    @Expose
    private Integer personastateflags;
    @SerializedName("loccountrycode")
    @Expose
    private String loccountrycode;
    @SerializedName("locstatecode")
    @Expose
    private String locstatecode;
    @SerializedName("loccityid")
    @Expose
    private Integer loccityid;


    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    public Integer getCommunityvisibilitystate() {
        return communityvisibilitystate;
    }

    public void setCommunityvisibilitystate(Integer communityvisibilitystate) {
        this.communityvisibilitystate = communityvisibilitystate;
    }

    public Integer getProfilestate() {
        return profilestate;
    }

    public void setProfilestate(Integer profilestate) {
        this.profilestate = profilestate;
    }

    public String getPersonaname() {
        return personaname;
    }

    public void setPersonaname(String personaname) {
        this.personaname = personaname;
    }

    public Integer getLastlogoff() {
        return lastlogoff;
    }

    public void setLastlogoff(Integer lastlogoff) {
        this.lastlogoff = lastlogoff;
    }

    public String getProfileurl() {
        return profileurl;
    }

    public void setProfileurl(String profileurl) {
        this.profileurl = profileurl;
    }

    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar The avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return The avatarmedium
     */
    public String getAvatarmedium() {
        return avatarmedium;
    }

    /**
     * @param avatarmedium The avatarmedium
     */
    public void setAvatarmedium(String avatarmedium) {
        this.avatarmedium = avatarmedium;
    }

    /**
     * @return The avatarfull
     */
    public String getAvatarfull() {
        return avatarfull;
    }

    /**
     * @param avatarfull The avatarfull
     */
    public void setAvatarfull(String avatarfull) {
        this.avatarfull = avatarfull;
    }

    /**
     * @return The personastate
     */
    public Integer getPersonastate() {
        return personastate;
    }

    /**
     * @param personastate The personastate
     */
    public void setPersonastate(Integer personastate) {
        this.personastate = personastate;
    }

    /**
     * @return The realname
     */
    public String getRealname() {
        return realname;
    }

    /**
     * @param realname The realname
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * @return The primaryclanid
     */
    public String getPrimaryclanid() {
        return primaryclanid;
    }

    /**
     * @param primaryclanid The primaryclanid
     */
    public void setPrimaryclanid(String primaryclanid) {
        this.primaryclanid = primaryclanid;
    }

    /**
     * @return The timecreated
     */
    public Integer getTimecreated() {
        return timecreated;
    }

    /**
     * @param timecreated The timecreated
     */
    public void setTimecreated(Integer timecreated) {
        this.timecreated = timecreated;
    }

    /**
     * @return The personastateflags
     */
    public Integer getPersonastateflags() {
        return personastateflags;
    }

    /**
     * @param personastateflags The personastateflags
     */
    public void setPersonastateflags(Integer personastateflags) {
        this.personastateflags = personastateflags;
    }

    /**
     * @return The loccountrycode
     */
    public String getLoccountrycode() {
        return loccountrycode;
    }

    /**
     * @param loccountrycode The loccountrycode
     */
    public void setLoccountrycode(String loccountrycode) {
        this.loccountrycode = loccountrycode;
    }

    /**
     * @return The locstatecode
     */
    public String getLocstatecode() {
        return locstatecode;
    }

    /**
     * @param locstatecode The locstatecode
     */
    public void setLocstatecode(String locstatecode) {
        this.locstatecode = locstatecode;
    }

    /**
     * @return The loccityid
     */
    public Integer getLoccityid() {
        return loccityid;
    }

    /**
     * @param loccityid The loccityid
     */
    public void setLoccityid(Integer loccityid) {
        this.loccityid = loccityid;
    }

}