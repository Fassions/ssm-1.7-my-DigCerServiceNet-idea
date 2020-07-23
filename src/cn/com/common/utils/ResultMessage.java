package cn.com.common.utils;

/**
 * Created by Horace.zhang on 2019/6/20.
 */
public class ResultMessage {
    private  Result result;
    private  String errorMsg;
    private Object message;
    public static enum Result{
        /**
         * 成功
         */
        SUCCESS,
        /**
         * 成功
         */
        OUTSUCCESS,
        /**
         * 失败
         */
        ERROR,
        /**
         * 失败
         */
        OUTERROR;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
