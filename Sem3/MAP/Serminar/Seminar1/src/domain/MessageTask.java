package domain;

import java.time.LocalDateTime;

import utils.Constants;

public class MessageTask extends Task {

    private String mesaj;
    private String from;
    private String to;
    private LocalDateTime date;

    public MessageTask(String taskId, String desc, String mesaj, String from, String to, LocalDateTime date) {
        super(taskId, desc);
        this.mesaj = mesaj;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString() + " " + mesaj + " " + to + " " + from + " " + date.format(Constants.DATE_TIME_FORMATTER);
    }

    @Override
    public void execute() {
        System.out.println(toString());
    }
}
