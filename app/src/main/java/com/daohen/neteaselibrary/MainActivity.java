package com.daohen.neteaselibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.daohen.netease.library.NimLogin;
import com.daohen.netease.library.callback.NeteaseCallback;
import com.daohen.personal.toolbox.library.util.Logs;
import com.daohen.personal.toolbox.library.util.Toasts;
import com.netease.nimlib.sdk.auth.LoginInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TextView) findViewById(R.id.tv1)).setText("当前状态："+ NimLogin.get().getCurrentStatus().getValue());

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NimLogin.get().login(new LoginInfo("daohen", "123456"), new NeteaseCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo info) {
                        Logs.d("success");
                        Toasts.showLong("success");
                    }

                    @Override
                    public void onFail(Throwable t) {
                        Logs.d(t.getMessage());
                        Toasts.showLong(t.getMessage());
                    }
                });
            }
        });

    }
}
