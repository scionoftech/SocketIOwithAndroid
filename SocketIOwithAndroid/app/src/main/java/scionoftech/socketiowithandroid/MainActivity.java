package scionoftech.socketiowithandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import scionoftech.socketiowithandroid.socketio.SingeltonSocket;

public class MainActivity extends AppCompatActivity implements SingeltonSocket.oneventlitsener {

    //this Singleton class listen for data push and sends data to server
    SingeltonSocket socket = SingeltonSocket.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //run socket first
        socket.run();
        //pass context to receive reponse in this Activity
        socket.setProperties(this);

        Toolbar toolbar=(Toolbar)findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        setTitle("SocketIO With Android");

        final EditText input = (EditText) findViewById(R.id.input);
        Button send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //emmit data to server with key
                socket.emit("test", input.getText().toString());
            }
        });

    }

        //response can be received here
    @Override
    public void setData(String data, String tag) {
        final String message=data;
        if(tag.equals("test_response"))
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }
            });
        }else {

        }

    }
}
