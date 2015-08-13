package scionoftech.snackbarandbottomsheer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import scionoftech.snackbar.R;

public class Snackbar extends AppCompatActivity {

    RelativeLayout snackbar;
    Button clickme;
    boolean show_snackbar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        Toolbar toolbar=(Toolbar)findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        snackbar = (RelativeLayout) findViewById(R.id.snackbar);
        RelativeLayout close = (RelativeLayout) findViewById(R.id.close);
        clickme = (Button) findViewById(R.id.clickme);

        //this will open Snackbar
        clickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar_open_close();
                if (show_snackbar) {
                    clickme.setText("HIDE");
                } else {
                    clickme.setText("SHOW");
                }
            }
        });

        //this will close Snackbar
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar_open_close();
            }
        });
    }

    //this is animation to show and hide Snackbar
    private void snackbar_open_close() {
        if (show_snackbar == false) {
            Animation bottomUp = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_up);

            snackbar.startAnimation(bottomUp);
            snackbar.setVisibility(View.VISIBLE);
            show_snackbar = true;
        } else {
            Animation bottomUp = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_down);

            snackbar.startAnimation(bottomUp);
            snackbar.setVisibility(View.GONE);
            show_snackbar = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_snackbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==android.R.id.home)
        {
            Intent i=new Intent(Snackbar.this,MainActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
