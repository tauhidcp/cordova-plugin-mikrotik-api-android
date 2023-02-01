# cordova-plugin-mikrotik-api-android

this plugin adapted from mikrotik java api writen by GideonLeGrange (https://github.com/GideonLeGrange/mikrotik-java)

simply, this plugin can help you execute command to RouterOS a.k.a Mikrotik from android app build with cordova  

### Tested on:

- NodeJS  	      : 19.4.0
- Cordova 	      : 11.1.0
- cordova-android : 11.0 
- Java 11  

### How to use

install from github repository using this command
```
cordova plugin add https://github.com/tauhidcp/cordova-plugin-mikrotik-api-android.git
```
or install from npmjs package using this command 
```
cordova plugin add id.my.tauhidslab.mikrotik.api
```

uninstall using this command
```
cordova plugin rm id.my.tauhidslab.mikrotik.api
```

### Login process / connect to mikrotik

first of all, you need login to your mikrotik device  

``` 
function Login(){
	
	var host = document.getElementById("host").value;
	var user = document.getElementById("user").value;
	var pass = document.getElementById("pass").value;
	var port = 8728; // default port 
	
	cordova.plugins.MikrotikApi.Login(host, user, pass, port, onSuccess, onError);
	
	function onSuccess(s){
        alert(s);
    }

	function onError(e){
        alert(e);
    }
	
}
```

### Retrieve data form routeros / mikrotik

to retrieve data form mikrotik, mainly to handle print command   

```
function getData(){
	
	var cmd = "/interface/print";
	
	cordova.plugins.MikrotikApi.getCommand(cmd, onSuccess, onError);
	
	function onSuccess(s){
		
		var rs = JSON.parse(s); // parse output to JSON
		
		rs.forEach(function(obj){
			document.getElementById("iface_list").innerHTML += obj['.id']+". "+obj['name']+" | "+obj['mac-address']+"</br></br>"; // get by name key 
		});
    }

	function onError(e){
        alert(e);
    }
	
}
```

### Execute command to mikrotik / routeros

to execute routeros command like add, edit, remove

```
function AddUser(){
	
	var cmd = "/user/add name=budi group=full";
	
	cordova.plugins.MikrotikApi.execCommand(cmd, onSuccess, onError);
	
	function onSuccess(s){
        alert(s);
    }

	function onError(e){
        alert(e);
    }
	
}
```
