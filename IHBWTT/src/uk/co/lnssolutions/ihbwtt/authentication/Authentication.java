package uk.co.lnssolutions.ihbwtt.login;




import java.net.*;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;


public class Authentication
	{
		private static String loginTarget = "https://identitysso.betfair.com/api/login";
		private static String keepAliveTarget = "https://identitysso.betfair.com/api/keepAlive";
		private static String logoutTarget = "https://identitysso.betfair.com/api/logout";
		private static String appKey = "3BeMTTmMUnuxoH6N";
		private static String delayKey = "NOhtpIAW0vs8Bath";
		
		public Authentication()
			{
				;
			}
			
		public static String login(String username, String password)
			{
				String token = "PROBLEM";
				
				String s = "";
				
				try
					{
						s = "&username=" + URLEncoder.encode(username, "UTF-8");
						s+= "&password=" + URLEncoder.encode(password, "UTF-8");
						s+= "&login=" + URLEncoder.encode("true", "UTF-8");
						s+= "&redirectMethod=" + URLEncoder.encode("POST", "UTF-8");
						s+= "&product=" + URLEncoder.encode("home.betfair.int", "UTF-8");
						s+= "&url=" + URLEncoder.encode("https://www.betfair.com/", "UTF-8");
					}
				catch(Exception ex)
					{
						ex.printStackTrace();
					} 
				
				if(!s.equals(""))
					token = makeCall(0, loginTarget, s);
				
				return token;
			}
			
		public static String keepAlive()
			{
				String token = "PROBLEM";
				String s = "";
				
				try
					{
						s = "&" + "product=" + URLEncoder.encode(appKey, "UTF-8");
						s+= "&url=" + URLEncoder.encode("https://www.betfair.com/", "UTF-8");
					}
					
				catch(Exception ex)
					{
						ex.printStackTrace();
					} 
					
				if(!s.equals(""))
					token = makeCall(1, keepAliveTarget, s);
				
				return token;
			}
			
		public static String logout()
			{
				String token = "PROBLEM";
				String s = "";
				
				try
					{
						s = "&" + "product=" + URLEncoder.encode(appKey, "UTF-8");
						s+= "&url=" + URLEncoder.encode("https://www.betfair.com/", "UTF-8");
					}
					
				catch(Exception ex)
					{
						ex.printStackTrace();
					} 
					
				if(!s.equals(""))
					token = makeCall(2, logoutTarget, s);
				
				return token;
			}
			
		private static String makeCall(int call, String target, String query)
			{
				URL url = null;
				HttpsURLConnection conn = null;
				DataOutputStream outStream = null;
				String outString = "PROBLEM";
				
				try
					{
						url = new URL(target);
						conn = (HttpsURLConnection)url.openConnection();
						
						conn.setRequestMethod("POST");
						conn.setDoOutput(true);
						conn.setDoInput(true);
						conn.setFollowRedirects( true );
						
						if(conn != null)
							{
								outStream = new DataOutputStream(conn.getOutputStream());
						
								outStream.writeBytes(query);
								outStream.flush();
								outStream.close();
								conn.disconnect();
						 
								int responseCode = conn.getResponseCode();
						
								if(call == 0)
									{
										if(responseCode == 200)
											{
												String headerName = null;
						 		
												for (int i = 1; (headerName = conn.getHeaderFieldKey(i))!=null; i++) 
													{
														if (headerName.equals("Set-Cookie")) 
															{                  
																String cookie = conn.getHeaderField(i);
														
																if(cookie.indexOf("ssoid") > -1)
																	outString = cookie.substring(cookie.indexOf("=") + 1, cookie.indexOf(";"));
															}
													}
											}
									}
						 	
								else if(responseCode == 200)
									outString = "" + responseCode;
							}//if(conn != null)
					}
					
				catch(Exception ex)
					{
						ex.printStackTrace();
					}
					
				return outString;
			}
	}