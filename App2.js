var express = require('express');
var fs = require('fs-extra');
var http = require('http');
var exec = require('child_process').exec;
var download = require('download-pdf');
var app = express();
var child;

app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

app.get('/', function (req, res) {
  console.log(console.log(unescape(req.query.direccion)));
  asincrona(req, res);
});

async function asincrona(req, res){
  var descarga = await imprime(req.query.id, res);
  
  res.send('OK');
}

function imprime(id, res){
  var dir = 'http://scas01.tarjetasucredito.com.ar:8080/reports/rwservlet?userid=scr$sl/sl@sucredito+report=PF$CRE$LCLI0100_RES_SCR+destype=CACHE+desformat=PDF+p_id=';
  var pdf = dir + id;
   
  var options = {
      //directory: "C:\Users\sjcuello\Desktop\prueba",
      directory: "",
      filename: "ejemplo.pdf"
  };
   
  download(pdf, options, function(err){
      if (err) throw err;
      console.log("Bajo el archivo");
  });

  /*Elimino el archivo*/
  setTimeout(()=>{
  //fs.unlinkSync('UserssjcuelloDesktopprueba/ejemplo2.pdf');
  console.log("Por Imprimir");
  	child = exec('java PrintPS',
	  function (error, stdout, stderr){
		console.log('stdout: ' + stdout);
		console.log('stderr: ' + stderr);

		if(error !== null){
		  console.log('exec error: ' + error);
		  res.send('Error');
		}
	  });
  }, 10000);
  console.log("Imprimio");
  /*Ejecuta el archivo en Java*/
  
}

app.listen(3000, function () {

});

