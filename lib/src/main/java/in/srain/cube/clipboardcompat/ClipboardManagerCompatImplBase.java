package in.srain.cube.clipboardcompat;

import java.util.ArrayList;

abstract class ClipboardManagerCompatImplBase implements ClipboardManagerCompat {

    protected final ArrayList<OnPrimaryClipChangedListener> mPrimaryClipChangedListeners
            = new ArrayList<OnPrimaryClipChangedListener>();

    @Override
    public void addPrimaryClipChangedListener(OnPrimaryClipChangedListener listener) {
        synchronized (mPrimaryClipChangedListeners) {
            mPrimaryClipChangedListeners.add(listener);
        }
    }

    /**
     * Make sure to call this method when the data in clipboard has been changed
     */
    protected final void notifyPrimaryClipChanged() {
        synchronized (mPrimaryClipChangedListeners) {
            for (int i = 0; i < mPrimaryClipChangedListeners.size(); i++) {
                mPrimaryClipChangedListeners.get(i).onPrimaryClipChanged();
            }
        }
    }

    @Override
    public void removePrimaryClipChangedListener(OnPrimaryClipChangedListener listener) {
        synchronized (mPrimaryClipChangedListeners) {
            mPrimaryClipChangedListeners.remove(listener);
        }
    }
}
