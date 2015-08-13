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

public class BottomSheet extends AppCompatActivity {

    RelativeLayout sheet;
    Button clickme;
    boolean show_sheet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sheet = (RelativeLayout) findViewById(R.id.sheet);
        RelativeLayout back = (RelativeLayout) findViewById(R.id.back);
        clickme = (Button) findViewById(R.id.clickme);

        //this will open bottom sheet
        clickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheet_open_close();
            }
        });

        //this will close bottom sheet
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheet_open_close();
            }
        });
    }

    //this is animation to show and hide Bottom sheet
    private void sheet_open_close() {
        if (show_sheet == false) {
            Animation bottomUp = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_up);
            sheet.setVisibility(View.VISIBLE);
            sheet.startAnimation(bottomUp);
            show_sheet = true;
        } else {
            Animation bottomUp = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_down);

            sheet.startAnimation(bottomUp);
            sheet.setVisibility(View.GONE);
            show_sheet = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bottom_sheet, menu);
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
        if (id == android.R.id.home) {
            Intent i = new Intent(BottomSheet.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
