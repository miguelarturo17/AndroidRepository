
package sbs.milpo.view;

import android.content.Intent;
import greendroid.app.GDApplication;

public class CatalogApplication extends GDApplication {

    @Override
    public Class<?> getHomeActivityClass() {
        return MilpoLogin.class;
    }
    
    @Override
    public Intent getMainApplicationIntent() {
    	//new Intent(Intent.ACTION_VIEW, Uri.parse("www.google.com"));
        return null;
    }

}
