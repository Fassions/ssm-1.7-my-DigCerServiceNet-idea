package cn.com.common.agent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Horace.zhang on 2019/5/30.
 */
public class BaseEntity {

    private String id;

    private Date dateCreated;

    private Date dateModified;

    private String userCreated;

    private String userModified;

    private Boolean isDel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdUUID() {
        this.id = UUID.randomUUID().toString().replace("-","");
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getUserModified() {
        return userModified;
    }

    public void setUserModified(String userModified) {
        this.userModified = userModified;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }
}
