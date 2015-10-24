package in.srain.cube.clipboardcompat.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import in.srain.cube.clipboardcompat.ClipboardManagerCompatFactory;
import in.srain.cube.clipboardcompat.OnPrimaryClipChangedListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClipboardManagerCompatFactory.create(this).addPrimaryClipChangedListener(new OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                Toast.makeText(MainActivity.this, R.string.data_changed_tip, Toast.LENGTH_SHORT).show();
            }
        });
    }
}