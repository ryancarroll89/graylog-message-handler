/**
 * Interface to implement a MessageGroup formatter.
 */
public interface Formatter {

    /** Takes a messageGroup and formats it to new specifications.
     *
     * @param messageGroup - The messageGroup to be formatted.
     */
    void format(MessageGroup messageGroup);
}
