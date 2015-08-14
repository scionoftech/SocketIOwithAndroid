#SocketIO with Android

A small project to explain how to use SocketIO with Android(Client) and Node.js(Server).

This project uses three jar files which are,

* [socket.io-client](http://mvnrepository.com/artifact/com.github.nkzawa/socket.io-client/0.6.0)
+ [engine.io-client](http://mvnrepository.com/artifact/com.github.nkzawa/engine.io-client/0.6.0)
- [Java-WebSocket](http://mvnrepository.com/artifact/org.java-websocket/Java-WebSocket/1.3.0)

##How to use in Android
  ```java
 	
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
```
##How to use in Node.js
 ```js
var express=require('express');
var app=express();
var http= require('http').createServer(app);
var io=require('socket.io').listen(http);

<!-- app.get('/',function(req,res)
{
    res.send("hello world");
}); -->

//socket.io listen for events
io.on('connection', function(socket){

//this what you sent from Client(Android)
socket.on('test',function(data)
{

console.log(data);
//this what you sent to client(Android)
socket.emit('test_response',"i got you");

});
});

//port
http.listen(3000, function(){
console.log('listening on *:3000');

});
 ```
## License

  [ISC](LICENSE)