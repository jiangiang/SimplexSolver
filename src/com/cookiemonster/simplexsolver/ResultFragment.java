package com.cookiemonster.simplexsolver;

import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ResultFragment extends MainFragment {
	TableRow col_title;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_4_result, container,false);
		
		TableLayout mainbody = (TableLayout) rootView.findViewById(R.id.mainbody);
		col_title = (TableRow) rootView.findViewById(R.id.col_title);
		
		create_col_title();

		for(int i=0; i<variable; i++){
			TableRow row = new TableRow(getActivity());
			row.setPadding(0, 0, 0, 10);
			TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			mainbody.addView(row,params);
			//for(int i=0; i<(constraint); i++){
			TextView rt = new TextView(getActivity());
			rt.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			rt.setText(Html.fromHtml("x<sub><small>"+ (i+1) +"</small></sub>"));
			rt.setPadding(0, 0, 0, 10);
			rt.setWidth(60);
			rt.setGravity(Gravity.CENTER);
			row.addView(rt);
			
			TextView rt1 = new TextView(getActivity());
			rt1.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			rt1.setText(Html.fromHtml("0"));
			rt1.setPadding(0, 0, 0, 10);
			rt1.setWidth(60);
			rt1.setGravity(Gravity.CENTER);
			row.addView(rt1);
				
			//}			
		}
		for(int i=0; i<variable; i++){
			TextView x = new TextView(getActivity());
			x.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			x.setText(Html.fromHtml("x<sub><small>"+ (i+1) +"</small></sub>"));
			x.setPadding(0, 0, 0, 10);
			x.setWidth(60);
			x.setGravity(Gravity.CENTER);
			col_title.addView(x);
		}
		for(int i=0; i<constraint; i++){
			TextView s = new TextView(getActivity());
			s.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			s.setText(Html.fromHtml("s<sub><small>"+ (i+1) +"</small></sub>"));
			s.setPadding(0, 0, 0, 10);
			s.setWidth(60);
			s.setGravity(Gravity.CENTER);
			col_title.addView(s);
		}

	
		
		
		
		return rootView;
	}
	protected void create_row_title(){
		for(int i=0; i<constraint; i++){
			TextView tv = new TextView(getActivity());
			tv.setWidth(60);
			tv.setGravity(Gravity.CENTER);
			tv.setText(Html.fromHtml("x<sub><small>"+ (i+1) +"</small></sub>"));
			col_title.addView(tv);
		}
	}
	protected void create_col_title(){
		for(int i=0; i<variable; i++){
			TextView x = new TextView(getActivity());
			x.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			x.setText(Html.fromHtml("x<sub><small>"+ (i+1) +"</small></sub>"));
			x.setPadding(0, 0, 0, 10);
			x.setWidth(60);
			x.setGravity(Gravity.CENTER);
			col_title.addView(x);
		}
		for(int i=0; i<constraint; i++){
			TextView s = new TextView(getActivity());
			s.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
			s.setText(Html.fromHtml("s<sub><small>"+ (i+1) +"</small></sub>"));
			s.setPadding(0, 0, 0, 10);
			s.setWidth(60);
			s.setGravity(Gravity.CENTER);
			col_title.addView(s);
		}
	}

}
