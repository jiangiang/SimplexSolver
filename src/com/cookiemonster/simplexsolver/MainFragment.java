package com.cookiemonster.simplexsolver;

import android.app.Fragment;
import android.text.Html;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment {
	static int variable = 0;
	static int constraint = 0;
	static String var = null;
	static String cons = null;
	
	
	
	
	protected void toast(String in){
		Toast.makeText(getActivity(),in,Toast.LENGTH_SHORT).show();
	}


}
