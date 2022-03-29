package org.project.image.entity;

public class ResultObject {

    private Boolean result;
    private String message;
    private Object data;
    private String taken;
    public ResultObject(){

    }
    public ResultObject(Boolean result, String message, Object data, String taken){
        this.result = result;
        this.message = message;
        this.data = data;
        this.taken = taken;
    }


    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTanken() {
        return taken;
    }

    public void setTanken(String tanken) {
        this.taken = tanken;
    }

    @Override
    public String toString() {
        return "ResultObj{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", tanken='" + taken + '\'' +
                '}';
    }
}
