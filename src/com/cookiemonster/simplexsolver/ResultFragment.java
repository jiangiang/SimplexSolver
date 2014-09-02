package com.cookiemonster.simplexsolver;

import java.lang.Object;

import android.graphics.Color;
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
	View rootView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_4_result, container,
				false);

		matrixEnterLeave();
		matrixTable();
		return rootView;
	}

	protected void matrixTable() {
		TableLayout mainbody = (TableLayout) rootView
				.findViewById(R.id.mainbody);
		col_title = (TableRow) rootView.findViewById(R.id.col_title);

		String tempStr;
		Double tempDouble;
		int tempPos;
		int consMatrixPos;

		// +2 if for the row title and the objective function
		for (int i = 0; i < (constraint + 2); i++) {

			TableRow row = new TableRow(getActivity());
			row.setPadding(0, 0, 0, 10);
			TableRow.LayoutParams params = new TableRow.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			mainbody.addView(row, params);

			// Display the Title of row
			if (i == 0) {
				for (int j = 0; j <= (constraint + variable); j++) {

					TextView rowTitle = new TextView(getActivity());
					// rowTitle.setTextAppearance(getActivity(),
					// android.R.style.TextAppearance_Large);
					rowTitle.setWidth(60);
					rowTitle.setGravity(Gravity.CENTER);
					tempStr = basicRow.get(j).toString();
					if (i == 0 && j == 0) {
						TextView lableTitle = new TextView(getActivity());
						lableTitle.setTextAppearance(getActivity(),
								android.R.style.TextAppearance_Large);
						lableTitle.setWidth(60);
						lableTitle.setGravity(Gravity.CENTER);
						lableTitle.setText("Basic");
						row.addView(lableTitle);
						rowTitle.setText(tempStr);
					} else {
						char[] cArray = tempStr.toCharArray();
						rowTitle.setText(Html
								.fromHtml(cArray[0] + "<sub><small>"
										+ cArray[1] + "</small></sub>"));
					}
					row.addView(rowTitle);
				}
				{
					TextView lableTitle = new TextView(getActivity());
					lableTitle.setWidth(60);
					lableTitle.setGravity(Gravity.CENTER);
					lableTitle.setText("Sol");
					row.addView(lableTitle);

				}
				{
					TextView lableTitle = new TextView(getActivity());
					lableTitle.setWidth(60);
					lableTitle.setGravity(Gravity.CENTER);
					lableTitle.setText("Ratio");
					row.addView(lableTitle);

				}

			} else {

				TextView rowBasicView = new TextView(getActivity());
				rowBasicView.setTextAppearance(getActivity(),
						android.R.style.TextAppearance_Large);

				consMatrixPos = i - 1;
				tempPos = basicColPos.get(i - 1);
				tempStr = basicRow.get(tempPos).toString();

				if (consMatrixPos == 0) {
					rowBasicView.setText(tempStr);
					row.addView(rowBasicView);

					// add the first col of matrix which is useless
					{
						TextView rt1 = new TextView(getActivity());
						rt1.setTextAppearance(getActivity(),
								android.R.style.TextAppearance_Large);
						rt1.setPadding(0, 0, 0, 10);
						rt1.setWidth(60);
						rt1.setGravity(Gravity.CENTER);
						rt1.setText("0");

						row.addView(rt1);
					}

					// Display the objective function matrix
					for (int j = 0; j < (constraint + variable); j++) {

						TextView rt1 = new TextView(getActivity());
						rt1.setTextAppearance(getActivity(),
								android.R.style.TextAppearance_Large);
						rt1.setPadding(0, 0, 0, 10);
						rt1.setWidth(60);
						rt1.setGravity(Gravity.CENTER);

						// display matrix
						tempDouble = consMatrix.get(consMatrixPos).get(j);
						if (tempDouble == 0.0)
							rt1.setText("0");
						else
							rt1.setText(tempDouble.toString());

						row.addView(rt1);
					}
					
				} else {

					// Display the constraint function matrix
					char[] cArray = tempStr.toCharArray();
					rowBasicView.setText(Html.fromHtml(cArray[0]
							+ "<sub><small>" + cArray[1] + "</small></sub>"));
					rowBasicView.append("0");
					rowBasicView.setPadding(0, 0, 0, 10);
					rowBasicView.setWidth(60);
					rowBasicView.setGravity(Gravity.CENTER);
					row.addView(rowBasicView);

					// add the first col of matrix which is useless
					{
						TextView rt1 = new TextView(getActivity());
						rt1.setTextAppearance(getActivity(),
								android.R.style.TextAppearance_Large);
						rt1.setPadding(0, 0, 0, 10);
						rt1.setWidth(60);
						rt1.setGravity(Gravity.CENTER);

						// display matrix
						rt1.setText("1");
						row.addView(rt1);
					}

					for (int j = 0; j < (constraint + variable); j++) {
						TextView rt1 = new TextView(getActivity());
						rt1.setTextAppearance(getActivity(),
								android.R.style.TextAppearance_Large);
						rt1.setPadding(0, 0, 0, 10);
						rt1.setWidth(60);
						rt1.setGravity(Gravity.CENTER);

						// display matrix
						if (special_multiple == false
								&& special_unbound == false && enterPos == j
								&& leavePos == consMatrixPos)
							rt1.setTextColor(Color.RED);

						tempDouble = consMatrix.get(consMatrixPos).get(j);
						if (tempDouble == 0.0)
							rt1.setText("0");
						else
							rt1.setText(tempDouble.toString());

						row.addView(rt1);
					}
				}
				{
					// To Display the solution matrix
					TextView rt1 = new TextView(getActivity());
					rt1.setTextAppearance(getActivity(),
							android.R.style.TextAppearance_Large);
					rt1.setPadding(0, 0, 0, 10);
					rt1.setWidth(60);
					rt1.setGravity(Gravity.CENTER);

					tempDouble = solMatrix.get(consMatrixPos);
					if (tempDouble == 0.0)
						rt1.setText("0");
					else
						rt1.setText(tempDouble.toString());

					row.addView(rt1);
				}
				{
					// To Display the ratio matrix
					TextView rt1 = new TextView(getActivity());
					rt1.setTextAppearance(getActivity(),
							android.R.style.TextAppearance_Large);
					rt1.setPadding(0, 0, 0, 10);
					rt1.setWidth(60);
					rt1.setGravity(Gravity.CENTER);

					tempDouble = ratioMatrix.get(consMatrixPos);
					if (tempDouble == 0.0)
						rt1.setText("0");
					else
						rt1.setText(tempDouble.toString());

					row.addView(rt1);
				}
				
			}
		}

	}

	protected void matrixEnterLeave() {
		double enterValue = 0.0, leaveRatio = Double.MAX_VALUE, tmpRatio = 0.0;
		int i = 0;

		// If special case one of the position will remain -1
		enterPos = -1;
		leavePos = -1;

		// Special case - Multiple optimal solution
		for (int j = 0; j < variable; j++) {
			if (consMatrix.get(0).get(j) == 0)
				special_multiple = true;
			else
				special_multiple = false;
		}
		// IF multiple optimal detected than the following iteration can be skipped
		if (special_multiple == false) {
			while (i < consMatrix.size()) {
				if (consMatrix.get(0).get(i) < enterValue) {
					enterValue = consMatrix.get(0).get(i);
					enterPos = i;
				}
				i++;
			}

			i = 0;
			while (i < consMatrix.size()) {
				if (consMatrix.get(i).get(enterPos) != 0.0) {
					tmpRatio = solMatrix.get(i) / consMatrix.get(i).get(enterPos);
					if (tmpRatio < leaveRatio && tmpRatio > 0) {
						leavePos = i;
						ratioMatrix.add(tmpRatio);
					}else{
						ratioMatrix.add(tmpRatio);
					}
				}
				else
					ratioMatrix.add(0.0);
				i++;
			}

			// Special case - unbounded
			if (enterPos == -1 || leavePos == -1) {
				special_unbound = false;
			}
		}

	}

	protected void matrixMUL() {

	}

}
