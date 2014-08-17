package com.cookiemonster.simplexsolver;

import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EquationFragment extends MainFragment{
	LinearLayout objfunc;
	LinearLayout mainbody;
	LinearLayout constraints;
	EditText et;
	TextView tv;
	TextView ds;
	TextView eq;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_2_equation, container,false);
		
		objfunc = (LinearLayout) rootView.findViewById(R.id.objfunc);
		mainbody = (LinearLayout) rootView.findViewById(R.id.mainbody);
		Button solve = (Button) rootView.findViewById(R.id.button1);
		variable = Integer.parseInt(var);
		constraint = Integer.parseInt(cons);
		


			for(int i=0; i<constraint; i++){
				constraints = new LinearLayout(getActivity());
				constraints.setPadding(5, 5, 5, 20);
				constraints.setOrientation(LinearLayout.HORIZONTAL);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				mainbody.addView(constraints,params);
				create_space(constraints);
				create_var(variable,constraints);
				create_equal(constraints);
				create_et(constraints);
				
			}
		
		
		create_var(variable,objfunc);

		
		solve.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		
        		
        	}
		});
		
		

		return rootView;
	}
	protected void create_space(LinearLayout ll){
		ds = new TextView(getActivity());
		ds.setText("");
		ds.setWidth(77);
		ll.addView(ds);
	}
	protected void create_equal(LinearLayout ll){
		eq = new TextView(getActivity());
		eq.setWidth(50);
		eq.setGravity(Gravity.CENTER);
		eq.setText(Html.fromHtml("<big>¡Ü</big>"));
		ll.addView(eq);
	}
	
	protected void create_var(int variable, LinearLayout ll){
		for(int i=0; i<variable; i++){
			et = new EditText(getActivity());
			et.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
			et.setGravity(Gravity.CENTER);
			et.setImeOptions(EditorInfo.IME_ACTION_NEXT);
			if(i==0)
				et.requestFocus();
			ll.addView(et);
			
			tv = new TextView(getActivity());
			tv.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			tv.setText(Html.fromHtml("x<sub><small>"+ (i+1) +"</small></sub>"));
			if(i!=variable-1)
				tv.append(" + ");
			tv.setPadding(0, 0, 0, 20);
			ll.addView(tv);
		}
	}
	protected void create_et(LinearLayout ll){
		et = new EditText(getActivity());
		et.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
		et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
		et.setGravity(Gravity.CENTER);
		et.getNextFocusDownId();
		et.requestFocus();
		ll.addView(et);
	}
}
