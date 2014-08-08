package OAuth4Sina.com;

import java.util.SortedSet;

import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author 水的右边
 * @blog http://www.cnblogs.com/hll2008
 */


public class AuthActivity extends Activity{
	CommonsHttpOAuthConsumer httpOauthConsumer;
	OAuthProvider httpOauthprovider;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oauthlayout);
        
        String consumerKey="填上你自己的";
        String consumerSecret="填上你自己的";
        String callBackUrl="myapp://AuthActivity";
        
      //  Button btn2=(Button)findViewById(R.id.btn2);
       // btn2.
        
        try{
        	httpOauthConsumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
    		httpOauthprovider = new DefaultOAuthProvider("http://api.t.sina.com.cn/oauth/request_token","http://api.t.sina.com.cn/oauth/access_token","http://api.t.sina.com.cn/oauth/authorize");
    		String authUrl = httpOauthprovider.retrieveRequestToken(httpOauthConsumer, callBackUrl);
    		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(authUrl)));
    		
    		int b=4;
    		
    	}catch(Exception e){
    		String s= e.getMessage();
    	}
    }
	
	@Override
    protected void onNewIntent(Intent intent) {
    	super.onNewIntent(intent);
    	Uri uri = intent.getData();
    	String verifier = uri.getQueryParameter(oauth.signpost.OAuth.OAUTH_VERIFIER);
    	try {
            httpOauthprovider.setOAuth10a(true); 
            httpOauthprovider.retrieveAccessToken(httpOauthConsumer,verifier);
        } catch (OAuthMessageSignerException ex) {
            ex.printStackTrace();
        } catch (OAuthNotAuthorizedException ex) {
            ex.printStackTrace();
        } catch (OAuthExpectationFailedException ex) {
            ex.printStackTrace();
        } catch (OAuthCommunicationException ex) {
            ex.printStackTrace();
        }
        SortedSet<String> user_id= httpOauthprovider.getResponseParameters().get("user_id");
        String userId=user_id.first();
        String userKey = httpOauthConsumer.getToken();
        String userSecret = httpOauthConsumer.getTokenSecret();
        
        TextView text=(TextView)findViewById(R.id.text);
        text.setText("suerId:"+userId+"/userKey:"+userKey+"/userSecret:"+userSecret);
    }
}
