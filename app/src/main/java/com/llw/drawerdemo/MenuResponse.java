package com.llw.drawerdemo;

import java.util.List;

/**
 * 菜单返回数据
 * @author lonel
 */
public class MenuResponse {


    /**
     * code : 200
     * data : [{"id":0,"name":"朋友"},{"id":1,"name":"钱包"},{"id":2,"name":"位置"},{"id":3,"name":"电话"},{"id":4,"name":"邮箱"},{"id":5,"name":"分享"},{"id":6,"name":"发送"}]
     * msg : Success
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 0
         * name : 朋友
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
