package in.srain.cube.clipboardcompat;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * For API level before HoneyComb(11)
 */
class ClipboardManagerCompatImplHCBefore extends ClipboardManagerCompatImplBase implements Runnable {

    /**
     * It's better to check clipboard data for a static thread
     */
    private static Handler sHandler;

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
        mWorking = true;
        sHandler.postDelayed(this, 10000);
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
            Log.d("uc-toast", "run: " + data);
            check(data);
            sHandler.postDelayed(this, 1000);
        }
    }

    private void check(CharSequence data) {
        if (TextUtils.isEmpty(mLastData) && TextUtils.isEmpty(data)) {
            return;
        }

        if (!TextUtils.isEmpty(mLastData) && mLastData.equals(data)) {
            return;
        }
        mLastData = data;
        notifyPrimaryClipChanged();
    }
}
