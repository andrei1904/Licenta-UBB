package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageTask extends Task {

    private String message;
    private  String from;
    private String to;
    private LocalDateTime date;

    public MessageTask() {
        this("","","","","",LocalDateTime.now());

    }

    public static DateTimeFormatter getDateFormatter() {
        return dateFormatter;
    }
    private static DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public MessageTask(String taskId, String desc,String message, String from, String to, LocalDateTime d) {
        super(taskId, desc);
        this.message=message;
        this.from=from;
        this.to=to;
        this.date= d;
    }

    public String getMessage()
    {
        return message;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return getDateAsString();
    }

    @Override
    public void execute() {
        try{
            Thread.sleep(5000);
        }catch(InterruptedException ex){
            throw new TaskExecutionException(ex);
        }

        //SmtpEmailService.getInstance().sendMail(message.getFrom(), message.getTo(), message.getSubject(), message.getBody());
    }

    @Override
    public String toString() {
        return super.toString()+"|"+ "message="+message + "|" +"from="+from +"|"+"to="+to+"|"+"date="+getDateAsString();
    }

    public String getDateAsString(){return date.format(dateFormatter);}

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public static void setDateFormatter(DateTimeFormatter dateFormatter) {
        MessageTask.dateFormatter = dateFormatter;
    }
}

//public class MessageTask implements Task {
//
//    private final Message message;
//
//
//    public MessageTask(final Message message) {
//        this.message = message;
//    }
//
//    @Override
//    public void execute() {
//        SmtpEmailService.getInstance().sendMail(message.getFrom(), message.getTo(), message.getSubject(), message.getBody());
//    }
//
//    @Override
//    public String getInfo() {
//        return message.toString();
//    }
//}