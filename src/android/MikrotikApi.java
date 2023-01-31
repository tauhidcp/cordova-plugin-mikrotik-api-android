package id.my.tauhidslab.mikrotik.api;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * This class echoes a string called from JavaScript.
 * @author Ahmad Tauhid, S.Kom (https://github.com/tauhidcp)
 * 
 * http://www.tauhidslab.my.id/
 *
 * https://github.com/tauhidcp/cordova-plugin-mikrotik-api-android
 *
 */
 
public class MikrotikApi extends CordovaPlugin {

	private ApiConnection con = null;
	private List<Map<String, String>> data = null;
	private String reply = "";
	private String arr = "";
	
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
       	
		if (action.equals("getCMD")) {
            String message = args.getString(0);
            this.getCommand(message, callbackContext);
            return true;
        }
		if (action.equals("execCMD")) {
            String message = args.getString(0);
            this.execCommand(message, callbackContext);
            return true;
        }
		if (action.equals("login")) {
            String host = args.getString(0);
            String user = args.getString(1);
            String pass = args.getString(2);
			this.Login(host, user, pass, callbackContext);
            return true;
        }
		
        return false;
    }

    private void getCommand(String message, CallbackContext callbackContext){
		
		if (this.con != null){
			
			if (message != null && message.length() > 0) {
			
				this.data = this.getCMD(message);
				
				if (this.data.size()>=1){
					for (int i=0; i<this.data.size(); i++) {

						this.arr += this.data.get(i).entrySet().toString();

					}
					String replacement = "["+removeLastChar(this.arr.replace("[", "{'").replace("]", "'},").replace("=", "':'").replace(", ", "','"))+"]";
					callbackContext.success(replacement.replace("'", "\""));
				} else {
					callbackContext.success(this.arr);
				}
			} else {
				
				callbackContext.error("don't pass empty string!");
				
			}
		
		} else {
			
            callbackContext.error("you are not connected, login first!");
        }
    }
	
	public static String removeLastChar(String s) {
    return (s == null || s.length() == 0)
      ? null 
      : (s.substring(0, s.length() - 1));
}
	
	private void execCommand(String message, CallbackContext callbackContext) {
		
		if (this.con != null){
			
			if (message != null && message.length() > 0) {
			
				this.reply = this.execCMD(message);
				
				callbackContext.success(this.reply);
				
			} else {
				
				callbackContext.error("don't pass empty string!");
				
			}
		
		} else {
			
            callbackContext.error("you are not connected, login first!");
        }
		
    }
	
	private void Login(String host, String user, String pass, CallbackContext callbackContext) {
		
        if (host != null && host.length() > 0 && user != null && user.length() > 0) {	
		
			this.con = getCon(host, user, pass);
			
			if (this.con != null){
				
				callbackContext.success("login success!");
				
			} else {
				
				callbackContext.error("failed! you are not connected!");
				
			}
		
		} else {
			
            callbackContext.error("don't pass empty string!");
        }
    }
	
	public List<Map<String, String>> getCMD(String command){
		
		List<Map<String, String>> rs = null;
		
		try{
		  
			rs = this.con.execute(command);
		
		} catch(Exception e){
		
			System.out.print(e);
	   
		}
		
		return rs;
	}

	public String execCMD(String command){

		String cmd = "";
		
		try{
			
		  this.con.execute(command);
		  
		  cmd = "Done!";
		  
		} catch(Exception e){
			
		  cmd = e.toString();
		}
	   

		return cmd;
	}
	
	public ApiConnection getCon(String host, String user, String pass){
    
		ApiConnection conn = null;
    
		try {
			
			conn = ApiConnection.connect(host); 
		
            try {
			
				conn.login(user,pass); 
		
				} catch (Exception e){
			
					conn = null;
		
				}
                         
			} catch (Exception e){
			
			conn = null;
			
			}
			
		return conn;
	
	}
}
