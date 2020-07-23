package cn.com.common.page;

import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * Created by Horace.zhang on 2019/7/1.
 */
public class Limit implements Serializable {

    private static final long serialVersionUID = 4499481037916352022L;

    private int offset;

    private int count;


    public Limit(){super();}

    public Limit(int offset, int count) {
        this.offset = offset;
        this.count = count;
    }


    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
