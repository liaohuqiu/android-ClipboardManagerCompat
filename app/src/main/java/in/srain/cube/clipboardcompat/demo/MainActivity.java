package in.srain.cube.clipboardcompat.demo;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import in.srain.cube.clipboardcompat.ClipboardManagerCompat;
import in.srain.cube.clipboardcompat.ClipboardManagerCompatFactory;
import in.srain.cube.clipboardcompat.OnPrimaryClipChangedListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView apiInfoTextView = (TextView) findViewById(R.id.api_info);
        final TextView resultInfoTextView = (TextView) findViewById(R.id.result_info);

        String apiInfo = String.format("Current API level is: %s", Build.VERSION.SDK_INT);
        apiInfoTextView.setText(apiInfo);

        final ClipboardManagerCompat clipboardManager = ClipboardManagerCompatFactory.create(this);
        clipboardManager.addPrimaryClipChangedListener(new OnPrimaryClipChangedListener() {

            @Override
            public void onPrimaryClipChanged() {

                String patten = getString(R.string.data_changed_tip);
                String msg = String.format(patten, clipboardManager.getText());
                resultInfoTextView.setText(msg);
            }
        });
    }
}