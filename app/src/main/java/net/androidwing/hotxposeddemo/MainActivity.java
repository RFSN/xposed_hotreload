package net.androidwing.hotxposeddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.socks.library.KLog;

import net.androidwing.hotxposed.CommonUtils;
import net.androidwing.hotxposed.PreferencesUtils;
import net.androidwing.hotxposed.ShellUtil;

import static net.androidwing.hotxposed.CommonUtils.restartTargetApp;

public class MainActivity extends Activity implements View.OnClickListener {

    protected Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

        restartTargetApp(Constants.TARGET_PACKAGE_NAME, "HomeActivity");

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
