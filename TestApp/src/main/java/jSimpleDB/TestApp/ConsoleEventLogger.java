package jSimpleDB.TestApp;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event){
        System.out.println("Console event logger: " + event.toString());
    }
}
