var Service = require('node-windows').Service;
​
// Create a new service object
var svc = new Service({
 name:'HelloWorld',
 description: 'The nodejs.org example web server.',
 script: 'C:\\Users\\sjcuello\\Desktop\\impresion\\App2.js'
});
​
// Listen for the "install" event, which indicates the
// process is available as a service.
svc.on('install',function(){
 console.log(svc.script);
 svc.start();
});
​
svc.install();