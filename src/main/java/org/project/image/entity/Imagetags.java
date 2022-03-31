package org.project.image.entity;

import java.io.Serializable;

/**
 * 
 * @TableName imagetags
 */
public class Imagetags implements Serializable {
    /**
     * 
     */
    private String hid;

    /**
     * 
     */
    private String tag;

    /**
     * 纠正值 ，不显示时积累到一定程度添加，显示时积累数量删除
     */
    private Integer changenum;

    /**
     * no 为不显示，yes显示
     */
    private String state;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public String getHid() {
        return hid;
    }

    /**
     * 
     */
    public void setHid(String hid) {
        this.hid = hid;
    }

    /**
     * 
     */
    public String getTag() {
        return tag;
    }

    /**
     * 
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 纠正值 ，不显示时积累到一定程度添加，显示时积累数量删除
     */
    public Integer getChangenum() {
        return changenum;
    }

    /**
     * 纠正值 ，不显示时积累到一定程度添加，显示时积累数量删除
     */
    public void setChangenum(Integer changenum) {
        this.changenum = changenum;
    }

    /**
     * no 为不显示，yes显示
     */
    public String getState() {
        return state;
    }

    /**
     * no 为不显示，yes显示
     */
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Imagetags other = (Imagetags) that;
        return (this.getHid() == null ? other.getHid() == null : this.getHid().equals(other.getHid()))
            && (this.getTag() == null ? other.getTag() == null : this.getTag().equals(other.getTag()))
            && (this.getChangenum() == null ? other.getChangenum() == null : this.getChangenum().equals(other.getChangenum()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHid() == null) ? 0 : getHid().hashCode());
        result = prime * result + ((getTag() == null) ? 0 : getTag().hashCode());
        result = prime * result + ((getChangenum() == null) ? 0 : getChangenum().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hid=").append(hid);
        sb.append(", tag=").append(tag);
        sb.append(", changenum=").append(changenum);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}