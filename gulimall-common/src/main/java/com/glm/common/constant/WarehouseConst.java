package com.glm.common.constant;

public class WarehouseConst {

    public enum PurchaseStatusEnum{
        CREATED(0, "newly created"),
        ASSIGNED(1, "assigned"),
        RECEIVED(2, "received"),
        FINISHED(3, "finished"),
        HASERROR(4, "something went wrong");

        private int code;
        private String msg;
        PurchaseStatusEnum(int code, String msg) {
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
    public enum PurchaseDetailStatusEnum{
        CREATED(0, "newly created"),
        ASSIGNED(1, "assigned"),
        PURCHASING(2, "purchasing"),
        FINISHED(3, "finished"),
        HASERROR(4, "something went wrong");

        private int code;
        private String msg;
        PurchaseDetailStatusEnum(int code, String msg) {
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