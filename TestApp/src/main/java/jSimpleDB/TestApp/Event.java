package jSimpleDB.TestApp;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    int id = new Random().nextInt();
    String msg;
    Date date;
    DateFormat dateFormat;

    public Event() {
    }

    public Event(Date date, DateFormat dateFormat) {
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("id: %d, msg: %s, date: %s", id, msg, dateFormat.format(date));
    }
}
