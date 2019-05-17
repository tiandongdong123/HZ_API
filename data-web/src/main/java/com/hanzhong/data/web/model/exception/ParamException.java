package com.hanzhong.data.web.model.exception;

/**
 *  
 *  @Description 参数异常类
 *  @Author   luqs   
 *  @Date 2019/4/3 14:05 
 *  @Version  V1.0   
 */
public class ParamException extends Exception {

    /**
     * 异常枚举
     */
    private ExceptionEnum exceptionEnum;
    /**
     * 消息
     */
    private String message;

    /**
     * 异常枚举
     */
    public enum ExceptionEnum {
        /**
         * 必填项为空
         */
        BLANK("必填项为空"),
        /**
         * 格式不正确
         */
        FORMAT_ERROR("格式不正确");

        private String value;

        ExceptionEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ParamException{" +
                "exceptionEnum=" + exceptionEnum +
                ", message='" + message + '\'' +
                '}';
    }
}
