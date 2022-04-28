package org.project.image.entity;

import java.io.Serializable;

/**
 * 
 * @TableName userrecommend
 */
public class Userrecommend implements Serializable {
    /**
     * 
     */
    private String uid;

    /**
     * 
     */
    private String hid;

    /**
     * 
     */
    private Double recommend;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public String getUid() {
        return uid;
    }

    /**
     * 
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

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
    public Double getRecommend() {
        return recommend;
    }

    /**
     * 
     */
    public void setRecommend(Double recommend) {
        this.recommend = recommend;
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
        Userrecommend other = (Userrecommend) that;
        return (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getHid() == null ? other.getHid() == null : this.getHid().equals(other.getHid()))
            && (this.getRecommend() == null ? other.getRecommend() == null : this.getRecommend().equals(other.getRecommend()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getHid() == null) ? 0 : getHid().hashCode());
        result = prime * result + ((getRecommend() == null) ? 0 : getRecommend().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", hid=").append(hid);
        sb.append(", recommend=").append(recommend);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}