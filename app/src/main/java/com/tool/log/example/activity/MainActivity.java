package com.tool.log.example.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.tool.common.log.QLog;
import com.tool.log.example.R;
import com.tool.log.example.helper.DataHelper;

/**
 * 测试
 */
public class MainActivity extends AppCompatActivity {

    //Default Log
    private Button btnDefaultLog = null;
    //Json
    private Button btnJsonLog = null;
    //XML
    private Button btnXMLLog = null;
    //超大文本
    private Button btnBigLog = null;

    //异常测试
    private Button btnCrash = null;

    //悬浮窗测试
    private Button btnDisplay = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Log测试
        this.logTest();
        //Crash测试
        this.crashTest();
        //悬浮窗测试
        this.displayTest();
    }

    /**
     * Log测试
     */
    private void logTest() {
        btnDefaultLog = (Button) findViewById(R.id.btn_default_log);
        btnJsonLog = (Button) findViewById(R.id.btn_json_log);
        btnXMLLog = (Button) findViewById(R.id.btn_xml_log);
        btnBigLog = (Button) findViewById(R.id.btn_big_log);

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_default_log:
                        // 基本数据类型 byte short int long float double char boolean
                        QLog.v(3.1415926);
                        // 数组
                        QLog.d(DataHelper.getArray());
                        // Map
                        QLog.i(DataHelper.getMap());
                        // List
                        QLog.w(DataHelper.getList());
                        // Intent
                        QLog.e(DataHelper.getIntent());
                        break;
                    case R.id.btn_json_log:
                        QLog.json(DataHelper.getJson());
                        break;
                    case R.id.btn_xml_log:
                        QLog.xml(DataHelper.getXml());
                        break;
                    case R.id.btn_big_log://超大文本
                        QLog.d(DataHelper.getBigString(MainActivity.this));
                        break;
                    default:
                        break;
                }
            }
        };

        btnDefaultLog.setOnClickListener(onClickListener);
        btnJsonLog.setOnClickListener(onClickListener);
        btnXMLLog.setOnClickListener(onClickListener);
        btnBigLog.setOnClickListener(onClickListener);
    }

    /**
     * Crash测试
     */
    private void crashTest() {
        btnCrash = (Button) findViewById(R.id.btn_crash);

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_crash:
                        throw new RuntimeException("RuntimeException Test");
                    default:
                        break;
                }
            }
        };

        btnCrash.setOnClickListener(onClickListener);
    }

    // 测试
    int i = 0;

    /**
     * 消息处理器
     */
    private Handler handler = new Handler();

    /**
     * 线程定时器
     */
    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            QLog.e("Test " + i++);

            handler.postDelayed(this, 500);
        }
    };

    /**
     * 悬浮窗测试
     */
    private void displayTest() {
        btnDisplay = (Button) findViewById(R.id.btn_display);

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_display:
                        QLog.display(MainActivity.this, true);

                        // 测试代码 模拟Log在logcat输出
                        handler.postDelayed(runnable, 0);
                    default:
                        break;
                }
            }
        };

        btnDisplay.setOnClickListener(onClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_github:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/DesignQu")));
                break;
            case R.id.action_qlog:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/DesignQu/Android-Q-Log")));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //关闭悬浮窗口
        QLog.display(MainActivity.this, false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}