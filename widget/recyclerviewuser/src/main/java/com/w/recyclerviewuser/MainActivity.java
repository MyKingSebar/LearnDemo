package com.w.recyclerviewuser;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.w.annotationcompiler.PageRoute;
import com.w.annotationcompiler.Router;
@PageRoute(route = "recyclerviewUser")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewuser_activity_main);

        TextView textView = (TextView) findViewById(R.id.recyclerviewuser_start);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName(MainActivity.this, Router.getInstance().getMap().get("recyclerview"));
                intent.setComponent(componentName);
                intent.putExtra("extra", "test");
                startActivity(intent);
            }
        });
    }
}
