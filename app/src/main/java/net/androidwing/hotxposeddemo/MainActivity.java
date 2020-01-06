package net.androidwing.hotxposeddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    protected Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        ShellUtil.execCommand("am force-stop " + Constants.TARGET_PACKAGE_NAME, true);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
    }

    protected void initView() {
        btnSettings = (Button) findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(MainActivity.this);
    }
}
