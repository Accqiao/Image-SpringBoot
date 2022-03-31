package org.project.image.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName record
 */
public class Record implements Serializable {
    /**
     * 所归属用户id
     */
    private String uid;

    /**
     * 所归属图片id
     */
    private String hid;

    /**
     * trail足迹 like喜欢 dislike不喜欢
     */
    private String type;

    /**
     * 
     */
    private Date createtime;

    private static final long serialVersionUID = 1L;

    /**
     * 所归属用户id
     */
    public String getUid() {
        return uid;
    }

    /**
     * 所归属用户id
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 所归属图片id
     */
    public String getHid() {
        return hid;
    }

    /**
     * 所归属图片id
     */
    public void setHid(String hid) {
        this.hid = hid;
    }

    /**
     * trail足迹 like喜欢 dislike不喜欢
     */
    public String getType() {
        return type;
    }

    /**
     * trail足迹 like喜欢 dislike不喜欢
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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
        Record other = (Record) that;
        return (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getHid() == null ? other.getHid() == null : this.getHid().equals(other.getHid()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getHid() == null) ? 0 : getHid().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
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
        sb.append(", type=").append(type);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}