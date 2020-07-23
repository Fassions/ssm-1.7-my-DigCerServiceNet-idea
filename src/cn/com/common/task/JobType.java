package cn.com.common.task;

/**
 * Created by Horace.zhang on 2019/7/4.
 */
public enum JobType {
    CONFIRMRECEIPT(1,"订单系统自动确认收货"),
    REPORTFILE(2,"报表下载生成文件");
    private int VALUE;
    private String DESC;


    JobType(int VALUE, String DESC) {
        this.VALUE = VALUE;
        this.DESC = DESC;
    }

    public int getVALUE() {
        return VALUE;
    }

    public void setVALUE(int VALUE) {
        this.VALUE = VALUE;
    }

    public String getDESC() {
        return DESC;
    }

    public void setDESC(String DESC) {
        this.DESC = DESC;
    }
}
