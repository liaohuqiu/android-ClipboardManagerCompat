package in.srain.cube.clipboardcompat;

import android.annotation.TargetApi;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;

/**
 * For API level >= 11
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
final class ClipboardManagerCompatImplHC extends ClipboardManagerCompatImplBase {

    ClipboardManager.OnPrimaryClipChangedListener mOnPrimaryClipChangedListener = new ClipboardManager.OnPrimaryClipChangedListener() {
        @Override
        public void onPrimaryClipChanged() {
            notifyPrimaryClipChanged();
        }
    };
    private ClipboardManager mClipboardManager;

    public ClipboardManagerCompatImplHC(Context context) {
        mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    public void addPrimaryClipChangedListener(OnPrimaryClipChangedListener listener) {
        super.addPrimaryClipChangedListener(listener);
        synchronized (mPrimaryClipChangedListeners) {
            if (mPrimaryClipChangedListeners.size() == 1) {
                mClipboardManager.addPrimaryClipChangedListener(mOnPrimaryClipChangedListener);
            }
        }
    }

    @Override
    public void removePrimaryClipChangedListener(OnPrimaryClipChangedListener listener) {
        super.removePrimaryClipChangedListener(listener);
        synchronized (mPrimaryClipChangedListeners) {
            if (mPrimaryClipChangedListeners.size() == 0) {
                mClipboardManager.removePrimaryClipChangedListener(mOnPrimaryClipChangedListener);
            }
        }
    }

    @Override
    public CharSequence getText() {
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
}
