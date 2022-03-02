import com.google.inject.Inject;

/**
 * Service class to format a MessageGroup to new specifications.
 */
public class FormatService {
    private Formatter formatter;

    @Inject
    public FormatService(Formatter formatter) {
        this.formatter = formatter;
    }

    /**
     * Formats the messages in a message group to correct specifications.
     *
     * @param messageGroup - The group of messages to be formatted.
     */
    public void format(MessageGroup messageGroup) {
        formatter.format(messageGroup);
    }
}