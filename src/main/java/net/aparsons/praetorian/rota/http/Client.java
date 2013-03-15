package net.aparsons.praetorian.rota.http;

import net.aparsons.praetorian.rota.model.Response;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpProtocolParams;

public class Client {
    
    private final HttpClient client;
    private final ResponseHandler<String> rh;
    
    private final HttpHost proxy = new HttpHost("localhost", 8080);
    
    private Gson gson = new Gson();
    
    public Client() {
        
        final BasicHttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, "UTF-8");
        HttpProtocolParams.setUserAgent(params, "Adam Parsons (adam@aparsons.net)");
        
        client = new DefaultHttpClient(new PoolingClientConnectionManager(), params);
        client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        
        rh = new BasicResponseHandler();
    }
    
    public Response request(String url) {
        HttpGet get = new HttpGet(url);
        
        String response = null;
        try {
            Logger.getLogger(Client.class.getName()).log(Level.INFO, get.getRequestLine().toString());
            response = client.execute(get, rh);
        } catch (SocketTimeoutException ste) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, ste.getMessage(), ste);
        } catch (ClientProtocolException cpe) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, cpe.getMessage(), cpe);
        } catch (IOException ioe) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, ioe.getMessage(), ioe);
        } 
        
        return gson.fromJson(response, Response.class);
    }
    
}
