package com.cookiemonster.simplexsolver;


import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class StandardFormFragment extends MainFragment {
	LinearLayout objfunc;
	TableLayout mainbody;
	TableRow row;
	TextView rt;
	TextView tv;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_3_standard_form, container,false);
		
		objfunc = (LinearLayout) rootView.findViewById(R.id.objfunc);
		mainbody = (TableLayout) rootView.findViewById(R.id.mainbody);
		Button solve = (Button) rootView.findViewById(R.id.button1);
		
		for(int i=0; i<6; i++){
			tv = new TextView(getActivity());
			tv.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			tv.setText(Html.fromHtml("x<sub><small>"+ (i+1) +"</small></sub>"));			
			if(i!=6-1)
				tv.append(" + ");
			tv.setPadding(0, 0, 0, 10);
			objfunc.addView(tv);
		}
		
		for(int i=0; i<constraint; i++){
			row = new TableRow(getActivity());
			row.setPadding(0, 0, 0, 10);
			TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			mainbody.addView(row,params);
			
			rt = new TextView(getActivity());
			rt.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			rt.setText(Html.fromHtml(""));
			rt.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics()));
			row.addView(rt);
			
			for(int j=0; j<variable; j++){
				TextView rt1 = new TextView(getActivity());
				rt1.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
				rt1.setText(Html.fromHtml("x<sub><small>"+ (j+1) +"</small></sub>"));			
				if(j!=variable-1)
					rt1.append(" + ");
				rt1.setPadding(0, 0, 0, 10);
				row.addView(rt1);
			}
			for(int k=0; k<constraint; k++){
				TextView ds = new TextView(getActivity());
				ds.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
				if(k==i)
					ds.setText(" + " + Html.fromHtml("s<sub><small>"+ (k+1) +"</small></sub>"));
				else
					ds.setText(Html.fromHtml(""));
				ds.setPadding(0, 0, 0, 10);
				row.addView(ds);
			
			}
			TextView eq = new TextView(getActivity());
			eq.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			eq.setText(Html.fromHtml(" = "));
			eq.setGravity(Gravity.CENTER);
			eq.setWidth(60);
			row.addView(eq);
			
			TextView ans = new TextView(getActivity());
			ans.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			ans.setText(Html.fromHtml("20"));
			row.addView(ans);
						
		}

		solve.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
       		
        		getFragmentManager().beginTransaction()
            	.replace(R.id.container, new ResultFragment())
            	.addToBackStack(null).commit();        		
        	}
		});		
		return rootView;
	}
}
