package RandomForestHOG.NotifyingThread;

/**
 * Created by ericchiu on 1/25/15.
 */
public interface ThreadCompleteListener {
    void notifyOfThreadComplete(final Runnable thread);
}
