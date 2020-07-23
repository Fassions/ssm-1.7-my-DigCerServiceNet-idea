package cn.com.common.model.search;

import cn.com.common.agent.BaseEntity;

import java.io.Serializable;

/**
 * Created by Horace.zhang on 2019/7/16.
 */
public class TblTemplateExcelSearcher extends BaseEntity implements Serializable  {
    private static final long serialVersionUID = -4892097133875563207L;
    //每页显示总页数
    private int pageCount;
    //当前页数
    private int offset;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 自定义模板名称
     */
    private String templateName;

    /**
     * 配置的excel 字段
     */
    private String excelField;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getExcelField() {
        return excelField;
    }

    public void setExcelField(String excelField) {
        this.excelField = excelField;
    }
}
