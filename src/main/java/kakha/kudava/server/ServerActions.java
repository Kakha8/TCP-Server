package kakha.kudava.server;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServerActions {
    private String message;
    public ServerActions(String message) {
        this.message = message;
    }

    public String messageChoices() {
        String [] parts = message.split(" ", 2);
        if(parts[0].equals("TIME")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            return LocalTime.now().format(formatter);
        } else if (parts[0].equals("UPPER")) {
            return parts[1].toUpperCase();
        } else
            return "ECHO: " + parts[0];
    }
    private String upperCommand(String content) {
        return content.toUpperCase();
    }
    private String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


}
