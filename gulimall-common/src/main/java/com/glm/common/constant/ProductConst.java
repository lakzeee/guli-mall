package com.glm.common.constant;

public class ProductConst {
    public enum AttrEnum{
        ATTR_TYPE_BASE(1, "base attribute"), ATTR_TYPE_SALE(0, "sale attribute");
        private int code;
        private String msg;
        AttrEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
