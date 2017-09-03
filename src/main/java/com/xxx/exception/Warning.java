package com.xxx.exception;


import com.xxx.common.consts.ReCode;

public class Warning extends RuntimeException{
    
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */  
    private static final long serialVersionUID = -530531644437450453L;

    private ReCode reCode;
    
    private Object data;
    
    public Warning(){
        super();
    }
    
    public Warning(String msg, Throwable cause){
        super(msg, cause);
    }
    
    public Warning(ReCode reCode){
        this.reCode = reCode;
    }
    
    public Warning(ReCode reCode,Object data){
        this.reCode = reCode;
        this.data = data;
    }

    public Warning(String msg){
        this.reCode = ReCode.Fail;
        this.data = msg;
    }

    public ReCode getReCode() {
        return reCode;
    }

    public void setReCode(ReCode reCode) {
        this.reCode = reCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        String data = super.toString();
               data+="---->code:"+this.reCode.getCode();
               data+=" msg:"+this.reCode.getMsg();
               data+=" data:"+this.data;
        return data;
    }
}
