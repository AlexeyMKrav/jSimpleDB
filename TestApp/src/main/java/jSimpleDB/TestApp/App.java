package jSimpleDB.TestApp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private EventLogger eventLogger;

    public App() {
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
//        App app = new App();

//        app.client = new Client("1", "John Smith");
//        app.eventLogger = new ConsoleEventLogger();

//        app.logEvent("Some event for user 1");

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = context.getBean(App.class);

        Event event = context.getBean(Event.class);
        event.msg = "Some event for user 1";
        app.logEvent(event);

        context.close();
    }

    private void logEvent(Event event){
        event.msg = event.msg.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(event);
    }
}
