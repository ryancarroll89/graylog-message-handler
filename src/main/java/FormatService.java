import com.google.inject.Inject;

public class FormatService {
    private Formatter formatter;

    @Inject
    public FormatService(Formatter formatter) {
        this.formatter = formatter;
    }

    public void format(Message message) {
        formatter.format(message);
    }
}