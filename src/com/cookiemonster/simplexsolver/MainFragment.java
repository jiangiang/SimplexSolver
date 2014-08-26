package com.cookiemonster.simplexsolver;

import android.app.Fragment;
import android.widget.Toast;

public class MainFragment extends Fragment {
	static int variable = 0;
	static int constraint = 0;
	static String var = null;
	static String cons = null;
	static String[] strin;
	static int[] intin;
	static int counter;
	static boolean maximize = true;
	
	
	
	
	protected void toast(String in){
		Toast.makeText(getActivity(),in,Toast.LENGTH_SHORT).show();
	}


}
