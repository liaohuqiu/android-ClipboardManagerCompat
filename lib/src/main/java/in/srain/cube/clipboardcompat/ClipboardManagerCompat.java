package in.srain.cube.clipboardcompat;

public interface ClipboardManagerCompat {

    void addPrimaryClipChangedListener(OnPrimaryClipChangedListener listener);

    void removePrimaryClipChangedListener(OnPrimaryClipChangedListener listener);

    /**
     * Returns the text on the clipboard.  It will eventually be possible
     * to store types other than text too, in which case this will return
     * null if the type cannot be coerced to text.
     */
    CharSequence getText();

    /**
     * Sets the contents of the clipboard to the specified text.
     */
    void setText(CharSequence text);

    /**
     * Returns true if the clipboard contains text; false otherwise.
     */
    boolean hasText();

}
