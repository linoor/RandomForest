package RandomForestHOG.NotifyingThread;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by ericchiu on 1/25/15.
 */
public abstract class NotifyingThread implements Runnable {

    private final Set<ThreadCompleteListener> listeners = new CopyOnWriteArraySet<ThreadCompleteListener>();
    private int threadId;

    public final void addListener(final ThreadCompleteListener listener) {
        listeners.add(listener);
    }
    public final void removeListener(final ThreadCompleteListener listener) {
        listeners.remove(listener);
    }
    public final void setThreadId(int id) {
        threadId = id;
    }
    public int getThreadId() {
        return threadId;
    }

    private final void notifyListeners() {
        for (ThreadCompleteListener listener : listeners) {
            listener.notifyOfThreadComplete(this);
        }
    }

    @Override
    public final void run() {
        try {
            doRun();
        } finally {
            notifyListeners();
        }
    }
    public abstract void doRun();
}
