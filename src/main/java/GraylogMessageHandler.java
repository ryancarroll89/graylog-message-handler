import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.log4j.Logger;

public class GraylogMessageHandler {
    static Logger log = Logger.getLogger(GraylogMessageHandler.class.getName());

    public static String graylogHost;
    public static int graylogPort;
    public static String inputFilePath;

    public static void main(String[] args) {
        try {
            graylogHost = args[0];
            graylogPort = Integer.parseInt(args[1]);
            inputFilePath = args[2];
        } catch (Exception e) {
            System.out.println("Invalid arguments!");
            System.out.println("Please provide: host port inputFilePath");
            log.info("Invalid commandline arguments provided");
            return;
        }

        Injector ingestorInjector = Guice.createInjector(new IngestorModule());
        IngestService ingestService = ingestorInjector.getInstance(IngestService.class);
        MessageGroup messageGroup = ingestService.ingest(inputFilePath);

        Injector formatterInjector = Guice.createInjector(new FormatterModule());
        FormatService formatService = formatterInjector.getInstance(FormatService.class);
        formatService.format(messageGroup);

        Injector senderInjector = Guice.createInjector(new SenderModule());
        SenderService senderService = senderInjector.getInstance(SenderService.class);
        senderService.send(messageGroup, graylogHost, graylogPort);
    }
}