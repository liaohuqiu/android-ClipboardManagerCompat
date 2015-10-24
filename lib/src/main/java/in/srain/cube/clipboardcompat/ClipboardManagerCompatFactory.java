package in.srain.cube.clipboardcompat;

import android.content.Context;
import android.os.Build;

public class ClipboardManagerCompatFactory {

    public static ClipboardManagerCompat create(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return new ClipboardManagerCompatImplHC(context);
        } else {
            return new ClipboardManagerCompatImplHCBefore(context);
        }
    }
}
