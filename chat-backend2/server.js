//Lets require/import the HTTP module
var http = require('http');
var express = require('express');
//Lets define a port we want to listen to
//const PORT=8080;
var Pusher = require('pusher');
var server = express();
var pusher = new Pusher({
  appId: '319347',
  key: 'c2134be56e864f56ef09',
  secret: '681943d30d60c5ad7627',
  // encrypted: true
});

server.post('/messages', function(req, res){
  var message = req.body;
  pusher.trigger('messages', 'new_message', message);
  res.json({success: 200});
});

http.createServer(function (request, response) {

   // Send the HTTP header
   // HTTP Status: 200 : OK
   // Content Type: text/plain
   var textmsg = request.body;
   response.writeHead(200, {'Content-Type': 'text/plain'});
   pusher.trigger('my-channel', 'my-event', {
     "message": textmsg
   });
   // Send the response body as "Hello World"
   response.end('Hello World\n');
}).listen(8000, "192.168.43.56");

// Console will print the message
console.log('Server running at http://127.0.0.1:8081/');
