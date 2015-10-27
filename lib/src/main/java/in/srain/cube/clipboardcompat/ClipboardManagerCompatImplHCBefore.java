package in.srain.cube.clipboardcompat;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.ClipboardManager;
import android.text.TextUtils;

/**
 * For API level before HoneyComb(11)
 * Just check the clipboard in every {@link #CHECK_CLIPBOARD_INTERVAL} ms.
 */
class ClipboardManagerCompatImplHCBefore extends ClipboardManagerCompatImplBase implements Runnable {

    /**
     * It's better to check clipboard data for a static thread
     */
    private static Handler sHandler;

    public static int CHECK_CLIPBOARD_INTERVAL = 500;

    static {
        sHandler = new Handler(Looper.getMainLooper());
    }

    ClipboardManager mClipboardManager;
    private CharSequence mLastData;
    private boolean mWorking = false;

    public ClipboardManagerCompatImplHCBefore(Context context) {
        mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    public void addPrimaryClipChangedListener(OnPrimaryClipChangedListener listener) {
        super.addPrimaryClipChangedListener(listener);
        synchronized (mPrimaryClipChangedListeners) {
            if (mPrimaryClipChangedListeners.size() == 1) {
                startListen();
            }
        }
    }

    private void startListen() {
        mLastData = getText();
        mWorking = true;
        sHandler.post(this);
    }

    private void stopListen() {
        mWorking = false;
        sHandler.removeCallbacks(this);
    }

    @Override
    public void removePrimaryClipChangedListener(OnPrimaryClipChangedListener listener) {
        super.removePrimaryClipChangedListener(listener);
        synchronized (mPrimaryClipChangedListeners) {
            if (mPrimaryClipChangedListeners.size() == 0) {
                stopListen();
            }
        }
    }

    @Override
    public CharSequence getText() {
        if (mClipboardManager == null) {
            return null;
        }
        return mClipboardManager.getText();
    }

    @Override
    public void setText(CharSequence text) {
        if (mClipboardManager != null) {
            mClipboardManager.setText(text);
        }
    }

    @Override
    public boolean hasText() {
        return mClipboardManager != null && mClipboardManager.hasText();
    }

    @Override
    public void run() {
        if (mWorking) {
            CharSequence data = getText();
            check(data);
            sHandler.postDelayed(this, CHECK_CLIPBOARD_INTERVAL);
        }
    }

    private void check(CharSequence data) {
        if (TextUtils.isEmpty(mLastData) && TextUtils.isEmpty(data)) {
            return;
        }

        if (!TextUtils.isEmpty(mLastData) && data != null && mLastData.toString().equals(data.toString())) {
            return;
        }
        mLastData = data;
        notifyPrimaryClipChanged();
    }
}
