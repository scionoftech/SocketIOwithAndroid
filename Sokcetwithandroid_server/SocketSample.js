var express=require('express');
var app=express();
var http= require('http').createServer(app);
var io=require('socket.io').listen(http);

app.get('/',function(req,res)
{
	res.send("hello");
});

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
