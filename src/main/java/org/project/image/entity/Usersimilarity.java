package org.project.image.entity;

import java.io.Serializable;

/**
 * 
 * @TableName usersimilarity
 */
public class Usersimilarity implements Serializable {
    /**
     * 
     */
    private String userb;

    /**
     * 
     */
    private String usera;

    /**
     * 
     */
    private Double similarity;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public String getUserb() {
        return userb;
    }

    /**
     * 
     */
    public void setUserb(String userb) {
        this.userb = userb;
    }

    /**
     * 
     */
    public String getUsera() {
        return usera;
    }

    /**
     * 
     */
    public void setUsera(String usera) {
        this.usera = usera;
    }

    /**
     * 
     */
    public Double getSimilarity() {
        return similarity;
    }

    /**
     * 
     */
    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
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
        Usersimilarity other = (Usersimilarity) that;
        return (this.getUserb() == null ? other.getUserb() == null : this.getUserb().equals(other.getUserb()))
            && (this.getUsera() == null ? other.getUsera() == null : this.getUsera().equals(other.getUsera()))
            && (this.getSimilarity() == null ? other.getSimilarity() == null : this.getSimilarity().equals(other.getSimilarity()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserb() == null) ? 0 : getUserb().hashCode());
        result = prime * result + ((getUsera() == null) ? 0 : getUsera().hashCode());
        result = prime * result + ((getSimilarity() == null) ? 0 : getSimilarity().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userb=").append(userb);
        sb.append(", usera=").append(usera);
        sb.append(", similarity=").append(similarity);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}