package andres_sjsu.mid;

import com.activeandroid.ActiveAndroid;

import android.app.Application;

/**
 * Created by andres on 7/6/15.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        //Notice this initialization code here
        ActiveAndroid.initialize(this);


    }
}