package org.project.image.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName image
 */
public class Image implements Serializable {
    /**
     * 图片hash id
     */
    private String hid;

    /**
     * 上传该图片用户id
     */
    private String uid;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片地址链接
     */
    private String href;

    /**
     * 描述
     */
    private String description;

    /**
     * 
     */
    private Integer width;

    /**
     * 
     */
    private Integer height;

    /**
     * 浏览次数
     */
    private Integer trailnum;

    /**
     * 喜欢次数
     */
    private Integer likenum;

    /**
     * 审核状态
     */
    private String state;

    /**
     * 
     */
    private Date updatetime;

    /**
     * 
     */
    private Date createtime;

    private static final long serialVersionUID = 1L;

    /**
     * 图片hash id
     */
    public String getHid() {
        return hid;
    }

    /**
     * 图片hash id
     */
    public void setHid(String hid) {
        this.hid = hid;
    }

    /**
     * 上传该图片用户id
     */
    public String getUid() {
        return uid;
    }

    /**
     * 上传该图片用户id
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 图片地址链接
     */
    public String getHref() {
        return href;
    }

    /**
     * 图片地址链接
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * 
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 浏览次数
     */
    public Integer getTrailnum() {
        return trailnum;
    }

    /**
     * 浏览次数
     */
    public void setTrailnum(Integer trailnum) {
        this.trailnum = trailnum;
    }

    /**
     * 喜欢次数
     */
    public Integer getLikenum() {
        return likenum;
    }

    /**
     * 喜欢次数
     */
    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    /**
     * 审核状态
     */
    public String getState() {
        return state;
    }

    /**
     * 审核状态
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
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
        Image other = (Image) that;
        return (this.getHid() == null ? other.getHid() == null : this.getHid().equals(other.getHid()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getHref() == null ? other.getHref() == null : this.getHref().equals(other.getHref()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getWidth() == null ? other.getWidth() == null : this.getWidth().equals(other.getWidth()))
            && (this.getHeight() == null ? other.getHeight() == null : this.getHeight().equals(other.getHeight()))
            && (this.getTrailnum() == null ? other.getTrailnum() == null : this.getTrailnum().equals(other.getTrailnum()))
            && (this.getLikenum() == null ? other.getLikenum() == null : this.getLikenum().equals(other.getLikenum()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHid() == null) ? 0 : getHid().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getHref() == null) ? 0 : getHref().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getWidth() == null) ? 0 : getWidth().hashCode());
        result = prime * result + ((getHeight() == null) ? 0 : getHeight().hashCode());
        result = prime * result + ((getTrailnum() == null) ? 0 : getTrailnum().hashCode());
        result = prime * result + ((getLikenum() == null) ? 0 : getLikenum().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hid=").append(hid);
        sb.append(", uid=").append(uid);
        sb.append(", title=").append(title);
        sb.append(", href=").append(href);
        sb.append(", description=").append(description);
        sb.append(", width=").append(width);
        sb.append(", height=").append(height);
        sb.append(", trailnum=").append(trailnum);
        sb.append(", likenum=").append(likenum);
        sb.append(", state=").append(state);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}