package OAuth4Sina.com;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * @author Ë®µÄÓÒ±ß
 * @blog http://www.cnblogs.com/hll2008
 */

public class TestActivity extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(TestActivity.this,AuthActivity.class);
	        	startActivity(intent);
			}
        	
        });
    }
    
}