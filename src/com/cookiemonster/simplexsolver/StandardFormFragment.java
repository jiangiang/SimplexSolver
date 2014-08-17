package com.cookiemonster.simplexsolver;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StandardFormFragment extends MainFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_3_standard_form, container,false);
		final TextView textView5 = (TextView) rootView.findViewById(R.id.textView5);
		
/*		RelativeLayout rl = (RelativeLayout) rootView.findViewById(R.id.relayout);
		final TextView rowTextView = new TextView(getActivity());
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_LEFT, R.id.textView5);
		params.addRule(RelativeLayout.BELOW, R.id.textView5);
		rowTextView.setText("Large Text");
		rowTextView.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
		rowTextView.setPadding(0, 20, 0, 0);
		rl.addView(rowTextView,params);*/
	
		
		
		
		return rootView;
	}
}
