package in.srain.cube.clipboardcompat;

import android.content.Context;
import android.os.Build;

public class ClipboardManagerCompatFactory {

    public static ClipboardManagerCompat create(Context context) {

        // Thanks: http://weibo.com/2830785152
        // http://weibo.com/1503535883/D12tzfMlN
        final String aplLevelString = Build.VERSION.SDK;
        int apiLevel = 0;
        try {
            apiLevel = Integer.parseInt(aplLevelString);
        } catch (Exception any) {
        }

        if (apiLevel >= Build.VERSION_CODES.HONEYCOMB) {
            return new ClipboardManagerCompatImplHC(context);
        } else {
            return new ClipboardManagerCompatImplHCBefore(context);
        }
    }
}
