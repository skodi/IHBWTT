package uk.co.lnssolutions.ihbwtt.rest.operations;




import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import uk.co.lnssolutions.ihbwtt.control.Fortune;
import uk.co.lnssolutions.ihbwtt.rest.json.util.JsonResponseHandler;

public class HttpUtil {

    private final String HTTP_HEADER_X_APPLICATION = "X-Application";
    private final String HTTP_HEADER_X_AUTHENTICATION = "X-Authentication";
    private final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
    private final String HTTP_HEADER_ACCEPT = "Accept";
    private final String HTTP_HEADER_ACCEPT_CHARSET = "Accept-Charset";

    public HttpUtil() {
        super();
    }

    private String sendPostRequest(String param, String operation, String appKey, String ssoToken, String URL, ResponseHandler<String> reqHandler){
        String jsonRequest = param;

        HttpPost post = new HttpPost(URL);
        String resp = null;
        try {
            post.setHeader(HTTP_HEADER_CONTENT_TYPE, Fortune.getProp().getProperty("APPLICATION_JSON"));
            post.setHeader(HTTP_HEADER_ACCEPT, Fortune.getProp().getProperty("APPLICATION_JSON"));
            post.setHeader(HTTP_HEADER_ACCEPT_CHARSET, Fortune.getProp().getProperty("ENCODING_UTF8"));
            post.setHeader(HTTP_HEADER_X_APPLICATION, appKey);
            post.setHeader(HTTP_HEADER_X_AUTHENTICATION, ssoToken);

            post.setEntity(new StringEntity(jsonRequest, Fortune.getProp().getProperty("ENCODING_UTF8")));

            HttpClient httpClient = new DefaultHttpClient();

            HttpParams httpParams = httpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, new Integer(Fortune.getProp().getProperty("TIMEOUT")).intValue());
            HttpConnectionParams.setSoTimeout(httpParams, new Integer(Fortune.getProp().getProperty("TIMEOUT")).intValue());

            resp = httpClient.execute(post, reqHandler);

        } catch (UnsupportedEncodingException e1) {
            //Do something

        } catch (ClientProtocolException e) {
            //Do something

        } catch (IOException ioE){
            //Do something

        }

        return resp;

    }


    public String sendPostRequestJsonRpc(String param, String operation, String appKey, String ssoToken) {
        String apiNgURL = Fortune.getProp().getProperty("APING_URL") + Fortune.getProp().getProperty("JSON_RPC_SUFFIX");

        return sendPostRequest(param, operation, appKey, ssoToken, apiNgURL, new JsonResponseHandler());

    }
    
    public String sendPostAccountRequestJsonRpc(String param, String operation, String appKey, String ssoToken) {
        String apiNgURL = Fortune.getProp().getProperty("ACCOUNT_URL") + Fortune.getProp().getProperty("JSON_RPC_SUFFIX");

        return sendPostRequest(param, operation, appKey, ssoToken, apiNgURL, new JsonResponseHandler());

    }

}
