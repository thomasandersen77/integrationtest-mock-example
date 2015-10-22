var express = require('express');
// Constants
var PORT = 8080;
// App
var app = express();

app.get('/', function (req, res) {
    res.send('Hello World from CentOS6 in Docker\n');
});

var server = app.listen(8080, function () {
    var host = server.address().address;
    var port = server.address().port;

    console.log('Simple Node express app listening at http://%s:%s', host, port);
});