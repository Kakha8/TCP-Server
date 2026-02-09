package kakha.kudava.server;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServerActions {
    private String message;
    public ServerActions(String message) {
        this.message = message;
    }

    public String messageChoices() {
        if(message.equals("TIME")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            return LocalTime.now().format(formatter);
        } else
            return "";
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


}
