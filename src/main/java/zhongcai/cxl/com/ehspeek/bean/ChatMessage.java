package zhongcai.cxl.com.ehspeek.bean;

import java.util.Date;

/**
 * Created by chenjun on 16/4/12.
 */
public class ChatMessage {

    private String name;
    private String message;
    private Type type;
    private Date date;

    public enum Type{
        INCOMING,OUTCOMING
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public Type getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
