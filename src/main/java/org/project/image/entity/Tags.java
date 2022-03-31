package org.project.image.entity;

import java.io.Serializable;

/**
 * 
 * @TableName tags
 */
public class Tags implements Serializable {
    /**
     * 
     */
    private String tag;

    /**
     * 标签的层级/类型
     */
    private String level;

    /**
     * 
     */
    private String description;

    /**
     * 审核
     */
    private String state;

    private static final long serialVersionUID = 1L;

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
     * 标签的层级/类型
     */
    public String getLevel() {
        return level;
    }

    /**
     * 标签的层级/类型
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 审核
     */
    public String getState() {
        return state;
    }

    /**
     * 审核
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
        Tags other = (Tags) that;
        return (this.getTag() == null ? other.getTag() == null : this.getTag().equals(other.getTag()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTag() == null) ? 0 : getTag().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tag=").append(tag);
        sb.append(", level=").append(level);
        sb.append(", description=").append(description);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}