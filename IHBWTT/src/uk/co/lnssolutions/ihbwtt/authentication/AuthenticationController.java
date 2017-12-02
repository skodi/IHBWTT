package uk.co.lnssolutions.ihbwtt.control;

import uk.co.lnssolutions.ihbwtt.login.Authentication;

/* Singleton controller class 
 * 
 */
public class AuthenticationController {

	private Fortune fortune;
	private String token;
	private String appKey;
	private Authentication a;
	private boolean connected;
	
	private static AuthenticationController instance;
	
	
    public static synchronized AuthenticationController getInstance(){
        if(instance == null){
            instance = new AuthenticationController();
        }
        return instance;
    }
	
	private AuthenticationController(){
		fortune = new Fortune();
		a = new Authentication();
	}
	
	public String getToken(){
		return token;
	}
	
	public boolean isLoggedIn(){
		return connected;
	}
	
	public boolean login(){
		// System.out.println(getHttpResponseWithSSL("http://www.here.com"));

		
		// Login
		token = a.login(fortune.getProp().getProperty("USERNAME"), fortune.getProp().getProperty("PASSWORD"));
		//System.out.println("token: [" + token + "]");
		appKey = fortune.getProp().getProperty("APPKEY");
		//System.out.println("appkey:[" + appKey + "]");	
		
		if (token != null) 
			{
			   connected = true;
			   return true;
			}
		return false;
	}
	
	public boolean logout(){
		token = a.logout();
		if (token != null)
			{
			   connected = false;
			   return true;
			}
		return false;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
