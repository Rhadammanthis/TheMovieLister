package me.hugomedina.themovielister.ui;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import me.hugomedina.themovielister.R;

/**
 * @author Hugo
 *
 */
public abstract class BaseActivity extends AppCompatActivity {


	List<WeakReference<Fragment>> fragList = new ArrayList<>();


	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	public void onAttachFragment(Fragment fragment) {
		super.onAttachFragment(fragment);
	}

	/**
	 * @param fragment el fragmento
	 * @param tag el tag
	 */
	protected void addFragment(Fragment fragment, String tag){
		getFragmentManager()
        	.beginTransaction()
        	.add(R.id.container, fragment, tag)
        	.commit();
	}

	/**
	 * @param fragment el fragmento
	 * @param tag el tag
	 */
	protected void replaceFragment(Fragment fragment, String tag){
		getFragmentManager()
        	.beginTransaction()
        	.replace(R.id.container, fragment, tag)
        	.commit();
	}

	/**
	 * @return Devuelve un valor booleano que indica si existe conexi�n a internet
	 *
	 */
	protected boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo netInfo = cm.getActiveNetworkInfo();

		return netInfo != null && netInfo.isConnectedOrConnecting();
	}

    /**
     *
     * @return todas los fragmentos contenidos en el FragmentManager
     */
    public List<Fragment> getActiveFragments() {
        ArrayList<Fragment> ret = new ArrayList<>();
        for(WeakReference<Fragment> ref : fragList) {
            Fragment f = ref.get();
            if(f != null) {
                if(f.isVisible()) {
                    ret.add(f);
                }
            }
        }
        return ret;
    }


}
