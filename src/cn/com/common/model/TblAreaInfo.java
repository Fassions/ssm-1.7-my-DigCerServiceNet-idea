package cn.com.common.model;

import cn.com.common.agent.BaseEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Horace.zhang on 2019/6/10.
 */
@XmlRootElement(name="tblAreaInfo")
public class TblAreaInfo extends BaseEntity {

    private String parentId;

    private String levelType;

    private String name;

    private String saleManager;

    private String sortLevel;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSaleManager() {
        return saleManager;
    }

    public void setSaleManager(String saleManager) {
        this.saleManager = saleManager;
    }

    public String getSortLevel() {
        return sortLevel;
    }

    public void setSortLevel(String sortLevel) {
        this.sortLevel = sortLevel;
    }
}
