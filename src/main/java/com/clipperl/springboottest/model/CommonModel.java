package com.clipperl.springboottest.model;


import com.clipperl.springboottest.util.ConstantUtil;

public class CommonModel {
    //code,0:接口请求成功，有数据返回；1：接口请求成功，无数据返回
    private int code;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setSuccess(){
        setCode(ConstantUtil.CODE_SUCCESS);
        setMsg(ConstantUtil.MSG_SUCCESS);
    }

    public void setFail(){
        setCode(ConstantUtil.CODE_FAIL);
        setMsg(ConstantUtil.MSG_FAIL);
    }
}
