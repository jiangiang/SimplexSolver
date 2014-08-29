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
			EditText objfuncvar = new EditText(getActivity());
			objfuncvar.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			objfuncvar.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
			objfuncvar.setGravity(Gravity.CENTER);
			objfuncvar.setImeOptions(EditorInfo.IME_ACTION_NEXT);
			objfuncvar.setSelectAllOnFocus(true);
			counter++;
			objfuncvar.setId(counter);
			
			//to show the id of each textbox. MUZ remove the line afterward
			//et.setText(String.valueOf(counter));
			
			if(i==0)
				objfuncvar.requestFocus();	
			objfunc.addView(objfuncvar);
			
			TextView var = new TextView(getActivity());
			var.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			var.setText(Html.fromHtml("x<sub><small>"+ (i+1) +"</small></sub>"));
			if(i!=variable-1)
				var.append(" + ");
			var.setPadding(0, 0, 0, 10);
			objfunc.addView(var);
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
			EditText constvar = new EditText(getActivity());
			constvar.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			constvar.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
			constvar.setGravity(Gravity.CENTER);
			constvar.setImeOptions(EditorInfo.IME_ACTION_NEXT);
			constvar.setSelectAllOnFocus(true);
			counter++;
			constvar.setId(counter);
			
			//to show the id of each textbox. MUZ remove the line afterward
			//et.setText(String.valueOf(counter));

			ll.addView(constvar);
			
			TextView var = new TextView(getActivity());
			var.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			var.setText(Html.fromHtml("x<sub><small>"+ (i+1) +"</small></sub>"));
			if(i!=variable-1)
				var.append(" + ");
			var.setPadding(0, 0, 0, 10);
			ll.addView(var);
		}

		TextView eq = new TextView(getActivity());
		eq.setWidth(50);
		eq.setGravity(Gravity.CENTER);
		eq.setText(Html.fromHtml("<big>¡Ü</big>"));
		ll.addView(eq);

		EditText solvar = new EditText(getActivity());
		solvar.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
		solvar.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
		solvar.setGravity(Gravity.CENTER);
		if(a<constraint-1)
			solvar.setImeOptions(EditorInfo.IME_ACTION_NEXT);
		else
			solvar.setImeOptions(EditorInfo.IME_ACTION_DONE);
		solvar.setSelectAllOnFocus(true);
		counter++;
		solvar.setId(counter);
		
		//to show the id of each textbox. MUZ remove the line afterward
		//et.setText(String.valueOf(counter));
		
		ll.addView(solvar);
	}
}
