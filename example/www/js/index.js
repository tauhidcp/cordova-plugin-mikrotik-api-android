/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

// Wait for the deviceready event before using any of Cordova's device APIs.
// See https://cordova.apache.org/docs/en/latest/cordova/events/events.html#deviceready
document.addEventListener('deviceready', onDeviceReady, false);
document.getElementById("login").addEventListener("click", Login);
document.getElementById("get_cmd").addEventListener("click", getData);
document.getElementById("write_cmd").addEventListener("click", AddUser);

function onDeviceReady() {
    // Cordova is now initialized. Have fun!

    console.log('Running cordova-' + cordova.platformId + '@' + cordova.version);
    document.getElementById('deviceready').classList.add('ready');
}


function Login(){
	
	var host = document.getElementById("host").value;
	var user = document.getElementById("user").value;
	var pass = document.getElementById("pass").value;
	
	cordova.plugins.MikrotikApi.Login(host, user, pass, onSuccess, onError);
	
	function onSuccess(s){
        alert(s);
    }

	function onError(e){
        alert(e);
    }
	
}

function getData(){
	
	var cmd = "/interface/print";
	
	cordova.plugins.MikrotikApi.getCommand(cmd, onSuccess, onError);
	
	function onSuccess(s){
		
		var rs = JSON.parse(s);
		
		rs.forEach(function(obj){
			document.getElementById("iface_list").innerHTML += obj['.id']+". "+obj['name']+" | "+obj['mac-address']+"</br></br>"; // get by name key 
		});
    }

	function onError(e){
        alert(e);
    }
	
}

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
