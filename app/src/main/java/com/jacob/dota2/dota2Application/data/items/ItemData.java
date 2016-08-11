package com.jacob.dota2.dota2Application.data.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemData  {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("dname")
    @Expose
    private String dname;
    @SerializedName("qual")
    @Expose
    private String qual;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("attrib")
    @Expose
    private String attrib;
    @SerializedName("mc")
    @Expose
    private Object mc;
    @SerializedName("cd")
    @Expose
    private Object cd;
    @SerializedName("lore")
    @Expose
    private String lore;
    @SerializedName("components")
    @Expose
    private List components;
    @SerializedName("created")
    @Expose
    private Boolean created;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The img
     */
    public String getImg() {
        return img;
    }

    /**
     *
     * @param img
     * The img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     *
     * @return
     * The dname
     */
    public String getDname() {
        return dname;
    }

    /**
     *
     * @param dname
     * The dname
     */
    public void setDname(String dname) {
        this.dname = dname;
    }

    /**
     *
     * @return
     * The qual
     */
    public String getQual() {
        return qual;
    }

    /**
     *
     * @param qual
     * The qual
     */
    public void setQual(String qual) {
        this.qual = qual;
    }

    /**
     *
     * @return
     * The cost
     */
    public Integer getCost() {
        return cost;
    }

    /**
     *
     * @param cost
     * The cost
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    /**
     *
     * @return
     * The desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     *
     * @param desc
     * The desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     *
     * @return
     * The notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     *
     * @param notes
     * The notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     *
     * @return
     * The attrib
     */
    public String getAttrib() {
        return attrib;
    }

    /**
     *
     * @param attrib
     * The attrib
     */
    public void setAttrib(String attrib) {
        this.attrib = attrib;
    }

    /**
     *
     * @return
     * The mc
     */
    public Object getMc() {
        return mc;
    }

    /**
     *
     * @param mc
     * The mc
     */
    public void setMc(Boolean mc) {
        this.mc = mc;
    }

    /**
     *
     * @return
     * The cd
     */
    public Object getCd() {
        return cd;
    }

    /**
     *
     * @param cd
     * The cd
     */
    public void setCd(Boolean cd) {
        this.cd = cd;
    }

    /**
     *
     * @return
     * The lore
     */
    public String getLore() {
        return lore;
    }

    /**
     *
     * @param lore
     * The lore
     */
    public void setLore(String lore) {
        this.lore = lore;
    }

    /**
     *
     * @return
     * The components
     */
    public List getComponents() {
        return components;
    }

    /**
     *
     * @param components
     * The components
     */
    public void setComponents(List components) {
        this.components = components;
    }

    /**
     *
     * @return
     * The created
     */
    public Boolean getCreated() {
        return created;
    }

    /**
     *
     * @param created
     * The created
     */
    public void setCreated(Boolean created) {
        this.created = created;
    }

}
