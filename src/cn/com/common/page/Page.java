package cn.com.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Horace.zhang on 2019/7/1.
 */
public class Page<T>  implements Serializable {

    private static final long serialVersionUID = 5934950194640816570L;

    private long total;

    private Limit limit;

    private List<T> data;

    public long getTotal() {
        return total;
    }

    public Page<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public Limit getLimit() {
        return limit;
    }

    public Page<T> setLimit(Limit limit) {
        this.limit = limit;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public Page<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

}

