package com.cookiemonster.simplexsolver;

import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class StandardFormFragment extends EquationFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_3_standard_form,
				container, false);
		counter = 0;

		TextView type = (TextView) rootView.findViewById(R.id.type);
		LinearLayout objfunc = (LinearLayout) rootView
				.findViewById(R.id.objfunc);
		TableLayout mainbody = (TableLayout) rootView
				.findViewById(R.id.mainbody);
		Button solve = (Button) rootView.findViewById(R.id.button1);

		if (maximize == true)
			type.setText("Maximization");
		else
			type.setText("Minimization");

		Double tempvalue;
		basicRow.add("z");
		basicColPos.add(0);

		// Display the equation of OBJECTIVE FUNCTION
		for (int i = 0; i < variable; i++) {
			TextView objfunctv = new TextView(getActivity());
			objfunctv.setTextAppearance(getActivity(),
					android.R.style.TextAppearance_Large);

			// Store the Value into matrix
			basicRow.add("x" + (i + 1));

			tempvalue = -1 * objMatrix.get(i);
			// tempvalue = -1*consMatrix.get(0).get(i);

			objfunctv.setText(tempvalue.toString());
			objfunctv.append(Html.fromHtml("x<sub><small>" + (i + 1)
					+ "</small></sub>"));

			// Add + sign infront of each text
			if (i != variable - 1)
				objfunctv.append(" + ");

			objfunctv.setPadding(0, 0, 0, 10);
			objfunc.addView(objfunctv);
		}

		// Display the equation of CONSTRAINT FUNCTION
		for (int i = 0; i < constraint; i++) {
			TableRow row = new TableRow(getActivity());
			row.setPadding(0, 0, 0, 10);
			TableRow.LayoutParams params = new TableRow.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			mainbody.addView(row, params);

			TextView rt = new TextView(getActivity());
			rt.setTextAppearance(getActivity(),
					android.R.style.TextAppearance_Large);
			rt.setText(Html.fromHtml(""));
			rt.setWidth((int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 40, getResources()
							.getDisplayMetrics()));
			row.addView(rt);

			for (int j = 0; j < variable; j++) {
				TextView var = new TextView(getActivity());
				var.setTextAppearance(getActivity(),
						android.R.style.TextAppearance_Large);
				var.setText(Html.fromHtml(consMatrix.get(i).get(j).toString()
						+ "x<sub><small>" + (j + 1) + "</small></sub>"));
				if (j != variable - 1)
					var.append(" + ");
				var.setPadding(0, 0, 0, 10);
				row.addView(var);
			}

			for (int k = 0; k < constraint; k++) {
				TextView slackvar = new TextView(getActivity());
				slackvar.setTextAppearance(getActivity(),
						android.R.style.TextAppearance_Large);

				if (k == i) {
					consMatrix.get(i).add(1.0);
					basicRow.add("s" + (k + 1)); // store the string of the
													// basic row
					basicColPos.add(variable + k + 1); // store the position of
														// the basic col
					slackvar.setText(" + "
							+ Html.fromHtml("s<sub><small>" + (k + 1)
									+ "</small></sub>"));

				}

				else {
					consMatrix.get(i).add(0.0);
					slackvar.setText(Html.fromHtml(""));
				}

				slackvar.setPadding(0, 0, 0, 10);
				row.addView(slackvar);
			}

			TextView eq = new TextView(getActivity());
			eq.setTextAppearance(getActivity(),
					android.R.style.TextAppearance_Large);
			eq.setText(Html.fromHtml(" = "));
			eq.setGravity(Gravity.CENTER);
			eq.setWidth(60);
			row.addView(eq);

			TextView ans = new TextView(getActivity());
			ans.setTextAppearance(getActivity(),
					android.R.style.TextAppearance_Large);
			ans.setText(solMatrix.get(i + 1).toString());
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
