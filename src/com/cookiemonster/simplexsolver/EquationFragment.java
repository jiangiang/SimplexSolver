package com.cookiemonster.simplexsolver;

import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.util.TypedValue;
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
import android.widget.ToggleButton;

public class EquationFragment extends MainFragment{
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_2_equation, container,false);
		counter = 0;
		
		final ToggleButton toggle = (ToggleButton) rootView.findViewById(R.id.toggleButton1);
		LinearLayout objfunc = (LinearLayout) rootView.findViewById(R.id.objfunc);
		LinearLayout mainbody = (LinearLayout) rootView.findViewById(R.id.mainbody);
		Button standard = (Button) rootView.findViewById(R.id.button1);
		variable = Integer.parseInt(var);
		constraint = Integer.parseInt(cons);
		
		for(int i=0; i<variable; i++){
			EditText et = new EditText(getActivity());
			et.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
			et.setGravity(Gravity.CENTER);
			et.setImeOptions(EditorInfo.IME_ACTION_NEXT);
			et.setSelectAllOnFocus(true);
			counter++;
			et.setId(counter);
			
			//to show the id of each textbox. MUZ remove the line afterward
			et.setText(String.valueOf(counter));
			
			if(i==0)
				et.requestFocus();	
			objfunc.addView(et);
			
			TextView tv = new TextView(getActivity());
			tv.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			tv.setText(Html.fromHtml("x<sub><small>"+ (i+1) +"</small></sub>"));
			if(i!=variable-1)
				tv.append(" + ");
			tv.setPadding(0, 0, 0, 10);
			objfunc.addView(tv);
		}
		for(int i=0; i<constraint; i++){
			LinearLayout constraints = new LinearLayout(getActivity());
			constraints.setPadding(0, 0, 0, 10);
			constraints.setOrientation(LinearLayout.HORIZONTAL);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			mainbody.addView(constraints,params);
			create_row(constraints,i);
		}
		
		toggle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
 				if (toggle.isChecked())
 					maximize = false; 
			    else
 					maximize = true;
			}
		});
		
		standard.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		
        		//to read from the textbox using the ID
        		
/*        		TextView temp;
				for(int i=1; i<=variable; i++){
        			temp = (TextView) rootView.findViewById(i);
					String text = temp.getText().toString();
					send text to array??
				}*/
        		
        		getFragmentManager().beginTransaction()
            	.replace(R.id.container, new StandardFormFragment())
            	.addToBackStack(null).commit();        		
        	}
		});		
		return rootView;
	}
	protected void create_row(LinearLayout ll, int a){
		TextView ds = new TextView(getActivity());
		ds.setText("");
		ds.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getResources().getDisplayMetrics()));
		ll.addView(ds);

		for(int i=0; i<variable; i++){
			EditText et = new EditText(getActivity());
			et.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
			et.setGravity(Gravity.CENTER);
			et.setImeOptions(EditorInfo.IME_ACTION_NEXT);
			et.setSelectAllOnFocus(true);
			counter++;
			et.setId(counter);
			
			//to show the id of each textbox. MUZ remove the line afterward
			et.setText(String.valueOf(counter));

			ll.addView(et);
			
			TextView tv = new TextView(getActivity());
			tv.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			tv.setText(Html.fromHtml("x<sub><small>"+ (i+1) +"</small></sub>"));
			if(i!=variable-1)
				tv.append(" + ");
			tv.setPadding(0, 0, 0, 10);
			ll.addView(tv);
		}

		TextView eq = new TextView(getActivity());
		eq.setWidth(50);
		eq.setGravity(Gravity.CENTER);
		eq.setText(Html.fromHtml("<big>¡Ü</big>"));
		ll.addView(eq);

		EditText et = new EditText(getActivity());
		et.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
		et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
		et.setGravity(Gravity.CENTER);
		if(a<constraint-1)
			et.setImeOptions(EditorInfo.IME_ACTION_NEXT);
		else
			et.setImeOptions(EditorInfo.IME_ACTION_DONE);
		et.setSelectAllOnFocus(true);
		counter++;
		et.setId(counter);
		
		//to show the id of each textbox. MUZ remove the line afterward
		et.setText(String.valueOf(counter));
		
		ll.addView(et);
	}
}
