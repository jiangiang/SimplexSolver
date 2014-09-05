package com.cookiemonster.simplexsolver;

import java.util.ArrayList;
import java.util.Collections;

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
	int iterationCount = 0;
	TextView resultStatusView, opSol, opVal, MaxMinDis;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_4_result, container,
				false);
		resultStatusView = (TextView) rootView.findViewById(R.id.resultStatus);
		opSol = (TextView) rootView.findViewById(R.id.OpSolVal);
		opVal = (TextView) rootView.findViewById(R.id.OpValVal);
		MaxMinDis = (TextView) rootView.findViewById(R.id.MaxMinDis);

		Collections.copy(consMatrixCpy, consMatrix);
		Collections.copy(objMatrixCpy, objMatrix);
		Collections.copy(solMatrixCpy, solMatrix);

		matrixEnterLeave();
		matrixTable();
		optimalResultCheck();

		// while (special_multiple == false && special_unbound == false
		// && optimalFound == false) {
		while (optimalFound == false && special_unbound == false) {
			// Change the sequence of the BasicCol
			// +1 is due to the z at index 0
			basicColPos.set(leavePos + 1, enterPos + 1);

			// Step 1: initialize all the variables
			initialMatrixOp();

			// Step 2: Inverse B

			b_inv_op();

			// Step 3: All the matrix arithmetic
			matrixMULObj();
			matrixMULObjSlack();
			matrixMULObjSol();
			matrixMULCons();
			matrixMULConsSlack();
			matrixMULConsSol();

			optimalResultCheck();

			matrixEnterLeave();
			matrixTable();

			// debug use
			// showMatrix();
		}

		if (special_unbound == true)
			resultStatusView.setText("Unbounded Solution");

		return rootView;
	}

	protected void optimalResultCheck() {
		for (int i = 0; i < constraint + variable; i++) {
			if (objMatrixCpy.get(i) >= 0)
				optimalFound = true;
			else {
				optimalFound = false;
				i = constraint + variable; // To exit the loop
			}
		}
	}

	protected void matrixTable() {
		TableLayout mainbody = (TableLayout) rootView
				.findViewById(R.id.mainbody);
		//col_title = (TableRow) rootView.findViewById(R.id.col_title);

		String tempStr;
		Double tempDouble;
		int tempPos;
		int consMatrixPos;
		
		TableRow kelefe = new TableRow(getActivity());
		TableRow.LayoutParams param = new TableRow.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		mainbody.addView(kelefe, param);
		TextView kelefes = new TextView(getActivity());
		kelefe.addView(kelefes);
		
		// +2 if for the row title and the objective function
		for (int i = 0; i < (constraint + 2); i++) {

			TableRow row = new TableRow(getActivity());
			TableRow.LayoutParams params = new TableRow.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			mainbody.addView(row, params);
			// Display the Title of row
			if (i == 0) {
				
				for (int j = 0; j <= (constraint + variable); j++) {		
					TextView rowTitle = new TextView(getActivity());
					rowTitle.setTextAppearance(getActivity(),
							android.R.style.TextAppearance_Medium);
					rowTitle.setWidth(100);
					rowTitle.setPadding(0, 20, 0, 10);
					rowTitle.setGravity(Gravity.CENTER);
					rowTitle.setBackgroundResource(R.drawable.back);
					tempStr = basicRow.get(j).toString();
					if (i == 0 && j == 0) {
						TextView lableTitle = new TextView(getActivity());

						// lableTitle.setWidth(90);
						lableTitle.setPadding(2, 20, 2, 10);
						lableTitle.setGravity(Gravity.CENTER);
						lableTitle.setTextAppearance(getActivity(),
								android.R.style.TextAppearance_Medium);
						lableTitle.setText("Iteration " + iterationCount);
						lableTitle.setBackgroundResource(R.drawable.back);

						iterationCount++;
						
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

					lableTitle.setTextAppearance(getActivity(),
							android.R.style.TextAppearance_Medium);

					lableTitle.setWidth(120);
					lableTitle.setPadding(0, 20, 0, 10);
					lableTitle.setGravity(Gravity.CENTER);
					lableTitle.setText("Sol");
					lableTitle.setBackgroundResource(R.drawable.back);

					row.addView(lableTitle);

				}
				{
					TextView lableTitle = new TextView(getActivity());
					lableTitle.setTextAppearance(getActivity(),
							android.R.style.TextAppearance_Medium);

					lableTitle.setWidth(120);
					lableTitle.setPadding(0, 20, 0, 10);
					lableTitle.setGravity(Gravity.CENTER);
					lableTitle.setText("Ratio");
					lableTitle.setBackgroundResource(R.drawable.back);

					row.addView(lableTitle);

				}

			} else {

				TextView rowBasicView = new TextView(getActivity());
				rowBasicView.setTextAppearance(getActivity(),
						android.R.style.TextAppearance_Medium);
				rowBasicView.setGravity(Gravity.CENTER);
				rowBasicView.setPadding(0, 0, 0, 10);
				rowBasicView.setBackgroundResource(R.drawable.back);

				consMatrixPos = i - 1;
				tempPos = basicColPos.get(i - 1);
				tempStr = basicRow.get(tempPos).toString();

				if (consMatrixPos == 0) {
					rowBasicView.setText(tempStr);
					rowBasicView.setBackgroundResource(R.drawable.shape);
					row.addView(rowBasicView);

					// add the first col of matrix which is useless
					{
						TextView rt1 = new TextView(getActivity());
						rt1.setTextAppearance(getActivity(),
								android.R.style.TextAppearance_Medium);
						rt1.setPadding(0, 0, 0, 10);
						rt1.setWidth(60);
						rt1.setGravity(Gravity.CENTER);
						rt1.setText("1");
						rt1.setBackgroundResource(R.drawable.back);

						row.addView(rt1);
					}

					// Display the objective function matrix
					for (int j = 0; j < (constraint + variable); j++) {

						TextView rt1 = new TextView(getActivity());
						rt1.setTextAppearance(getActivity(),
								android.R.style.TextAppearance_Medium);
						rt1.setPadding(0, 0, 0, 10);
						rt1.setWidth(90);
						rt1.setGravity(Gravity.CENTER);
						rt1.setBackgroundResource(R.drawable.shape);

						rt1.setBackgroundResource(R.drawable.back);

						// display matrix
						tempDouble = objMatrixCpy.get(j);
						// tempDouble = consMatrix.get(consMatrixPos).get(j);
						if (tempDouble == 0.0)
							rt1.setText("0.00");
						else
							rt1.setText(String.format("%.2f", tempDouble));

						row.addView(rt1);
					}

				} else {

					// Display the constraint function matrix
					char[] cArray = tempStr.toCharArray();
					rowBasicView.setText(Html.fromHtml(cArray[0]
							+ "<sub><small>" + cArray[1] + "</small></sub>"));
					rowBasicView.setPadding(0, 0, 0, 10);
					rowBasicView.setWidth(60);
					rowBasicView.setGravity(Gravity.CENTER);
					rowBasicView.setBackgroundResource(R.drawable.back);

					row.addView(rowBasicView);

					// add the first col of matrix which is useless
					{
						TextView rt1 = new TextView(getActivity());
						rt1.setTextAppearance(getActivity(),
								android.R.style.TextAppearance_Medium);
						rt1.setPadding(0, 0, 0, 10);
						rt1.setWidth(60);
						rt1.setBackgroundResource(R.drawable.back);
						rt1.setGravity(Gravity.CENTER);
						rt1.setBackgroundResource(R.drawable.shape);

						// display matrix
						rt1.setText("0.00");
						row.addView(rt1);
					}

					for (int j = 0; j < (constraint + variable); j++) {
						TextView rt1 = new TextView(getActivity());
						rt1.setTextAppearance(getActivity(),
								android.R.style.TextAppearance_Medium);
						rt1.setPadding(0, 0, 0, 10);
						rt1.setWidth(60);
						rt1.setGravity(Gravity.CENTER);
						rt1.setBackgroundResource(R.drawable.back);

						// display matrix
						if (special_multiple == false && optimalFound == false
								&& special_unbound == false && enterPos == j
								&& leavePos == consMatrixPos - 1)
							rt1.setTextColor(Color.RED);

						tempDouble = consMatrixCpy.get(consMatrixPos - 1)
								.get(j);
						if (tempDouble == 0.0)
							rt1.setText("0.00");
						else
							rt1.setText(String.format("%.2f", tempDouble));

						row.addView(rt1);
					}
				}
				{
					// To Display the solution matrix
					TextView rt1 = new TextView(getActivity());
					rt1.setTextAppearance(getActivity(),
							android.R.style.TextAppearance_Medium);
					rt1.setPadding(0, 0, 0, 10);
					rt1.setWidth(60);
					rt1.setGravity(Gravity.CENTER);
					rt1.setBackgroundResource(R.drawable.back);

					tempDouble = solMatrixCpy.get(consMatrixPos);
					if (tempDouble == 0.0)
						rt1.setText("0.00");
					else
						rt1.setText(String.format("%.2f", tempDouble));

					row.addView(rt1);
				}
				{
					TextView rt1 = new TextView(getActivity());
					rt1.setTextAppearance(getActivity(),
							android.R.style.TextAppearance_Medium);
					rt1.setPadding(0, 0, 0, 10);
					rt1.setWidth(60);
					rt1.setGravity(Gravity.CENTER);
					rt1.setBackgroundResource(R.drawable.back);

					if (consMatrixPos == 0) {
						rt1.setText("-");
					} else {
						// To Display the ratio matrix
						if (special_multiple == false && optimalFound == false) {
							tempDouble = ratioMatrix.get(consMatrixPos - 1);
							if (tempDouble == 0.0)
								rt1.setText("0.00");
							else
								rt1.setText(String.format("%.2f", tempDouble));

							row.addView(rt1);
						}

					}
				}

			}
		}

	}

	protected void matrixEnterLeave() {
		double enterValue = 0.0, leaveRatio = Double.MAX_VALUE, tmpRatio = 0.0;
		int i = 0;
		int unboundchecker = 0;
		i = 0;
		ratioMatrix.clear();
		if (maximize == true) {
			MaxMinDis.setText("Maximization");
			while (i < constraint + variable) {
				if (objMatrixCpy.get(i) < enterValue) {
					enterValue = objMatrixCpy.get(i);
					enterPos = i;
				}
				i++;
			}
		} else {
			// Minimization here
			MaxMinDis.setText("Minimization");
			while (i < constraint + variable) {
				if (objMatrixCpy.get(i) > enterValue) {
					enterValue = objMatrixCpy.get(i);
					enterPos = i;
				}
				i++;
			}
		}
		i = 0;
		while (i < constraint) {
			if (consMatrixCpy.get(i).get(enterPos) != 0.0) {
				tmpRatio = solMatrixCpy.get(i + 1)
						/ consMatrixCpy.get(i).get(enterPos);
				if (tmpRatio < leaveRatio && tmpRatio >= 0) {
					leavePos = i;
					leaveRatio = tmpRatio;
					special_unbound = false;
					unboundchecker++;
				}
				ratioMatrix.add(tmpRatio);

			} else
				ratioMatrix.add(0.0);
			i++;
		}

		if (unboundchecker == 0)
			special_unbound = true;

		if (optimalFound == true) {

			// Special case - Check for Multiple optimal solution
			// Multiple optimal solution occur when it is in optimal state
			for (int k = 0; k < variable; k++) {
				for (int l = 1; l <= constraint; l++) {
					if (basicColPos.get(l) - 1 == k
							&& basicColPos.get(l) <= variable) {
						special_multiple = false;
						continue;
					} else if (basicColPos.get(l) <= variable) {
						special_multiple = true;
					}
				}
			}// Multile optimal solution ENDED.

			opVal.setText(String.format("%.2f", solMatrixCpy.get(0)));
			for (int l = 0; l < variable; l++) {
				for (int j = 0; j < constraint; j++) {
					if (basicColPos.get(j + 1) - 1 == l) {
						if (l == 0) {
							
							opSol.setText(basicRow.get(basicColPos.get(j + 1))
									+ ":");
							opSol.append(String.format("%.2f",
									solMatrixCpy.get(l + 1)));
						} else {
							opSol.append(", ");
							opSol.append(basicRow.get(basicColPos.get(j + 1))
									+ ":");
							opSol.append(String.format("%.2f",
									solMatrixCpy.get(l + 1)));
						}
						j = constraint;
					} else if (l == 0)
						opSol.setText("0.00");
					/**
					 * else { opSol.append(", "); opSol.append("0"); }
					 * 
					 * }
					 **/

				}
			}
			if (special_multiple == true)
				resultStatusView.setText("Multiple Optimal Solution");
			else
				resultStatusView.setText("Optimal Solution FOUND");
		}

	}

	protected void b_inv_op() {
		// Using Gauss Jordan Elimination
		// Step 3: Inverse B
		double tempVal, tempVal1, tempVal2, tempVal3, tempVal4, kelefe, tem;
		int pivot = 0;
		for (int i = 0; i < constraint; i++) {
			tempVal = B.get(i).get(i);
			if (tempVal < 0)
				tempVal = tempVal * -1;
			pivot = i;

			// Choosing Pivot
			for (int j = i + 1; j < constraint; j++) {
				if (B.get(j).get(i) < 0)
					tem = -1 * B.get(j).get(i);
				else
					tem = B.get(j).get(i);
				if (tempVal < 0)
					tempVal = -1 * tempVal;
				if (tem > tempVal) {
					pivot = j;
					tempVal = B.get(j).get(i);
				}
			}
			// Row Exchange
			for (int j = 0; j < constraint; j++) {
				tempVal1 = B.get(i).get(j);
				B.get(i).set(j, B.get(pivot).get(j));
				B.get(pivot).set(j, tempVal1);
				tempVal2 = B_inv.get(i).get(j);
				B_inv.get(i).set(j, B_inv.get(pivot).get(j));
				B_inv.get(pivot).set(j, tempVal2);
			}

			// dividing the row by a[i][i]
			tempVal3 = B.get(i).get(i);
			for (int j = 0; j < constraint; j++) {
				kelefe = B.get(i).get(j) / tempVal3;
				B.get(i).set(j, kelefe);

				kelefe = B_inv.get(i).get(j) / tempVal3;
				B_inv.get(i).set(j, kelefe);
			}

			// making other elements 0 in order to make the matrix a[][] an
			// indentity matrix and obtaining a inverse b[][] matrix
			for (int q = 0; q < constraint; q++) {
				if (q == i)
					continue;
				tempVal4 = B.get(q).get(i);
				for (int j = 0; j < constraint; j++) {
					kelefe = B.get(q).get(j) - (tempVal4 * B.get(i).get(j));
					B.get(q).set(j, kelefe);

					kelefe = B_inv.get(q).get(j)
							- (tempVal4 * B_inv.get(i).get(j));
					B_inv.get(q).set(j, kelefe);
				}
			}

		}
	}

	protected void matrixMULObj() {
		double temp, sum;

		CbT_mul_B_inv();

		B_temp2.clear();
		B_temp2.add(new ArrayList<Double>());
		for (int j = 0; j < variable; j++) {
			B_temp2.get(0).add(0.0);
		}

		// Matrix Multiplication

		for (int j = 0; j < variable; j++) {
			sum = 0;
			for (int k = 0; k < constraint; k++) {
				sum += B_temp.get(0).get(k) * consMatrix.get(k).get(j);
			}
			B_temp2.get(0).set(j, sum);
		}

		for (int i = 0; i < variable; i++) {
			temp = B_temp2.get(0).get(i) + objMatrix.get(i);
			objMatrixCpy.set(i, temp);
		}
	}

	protected void matrixMULObjSlack() {

		// CbT multiply B inverse
		CbT_mul_B_inv();

		// Update Result
		for (int j = 0; j < variable; j++) {
			objMatrixCpy.set(j + variable, B_temp.get(0).get(j));
		}

	}

	protected void matrixMULObjSol() {
		double sum = 0;
		// CbT multiply B inverse
		CbT_mul_B_inv();

		for (int k = 0; k < constraint; k++) {
			sum += B_temp.get(0).get(k) * solMatrix.get(k + 1);
		}
		solMatrixCpy.set(0, sum);

	}

	// CbT * B inverse
	protected void CbT_mul_B_inv() {
		// Dynamically generate temporary array
		B_temp.clear();
		B_temp.add(new ArrayList<Double>());

		for (int j = 0; j < constraint; j++) {
			B_temp.get(0).add(0.0);
		}

		double sum;
		for (int j = 0; j < variable; j++) {
			sum = 0;
			for (int k = 0; k < constraint; k++) {
				sum += CbT.get(k) * B_inv.get(k).get(j);
			}
			B_temp.get(0).set(j, sum);
		}
	}

	protected void matrixMULCons() {
		double sum = 0;

		// Dynamically generate temporary array
		B_temp.clear();
		for (int i = 0; i < constraint; i++) {
			B_temp.add(new ArrayList<Double>());
			for (int j = 0; j < variable; j++) {
				B_temp.get(i).add(0.0);
			}
		}

		// Matrix Multiplication
		for (int i = 0; i < constraint; i++) {
			for (int j = 0; j < variable; j++) {
				sum = 0;
				for (int k = 0; k < constraint; k++) {
					sum += B_inv.get(i).get(k) * consMatrix.get(k).get(j);
				}
				B_temp.get(i).set(j, sum);
			}
		}

		// Update Result
		for (int i = 0; i < constraint; i++) {
			for (int j = 0; j < variable; j++) {
				consMatrixCpy.get(i).set(j, B_temp.get(i).get(j));
			}
		}
	}

	protected void matrixMULConsSlack() {
		double temp;
		for (int i = 0; i < constraint; i++) {
			for (int j = 0; j < constraint; j++) {
				temp = B_inv.get(i).get(j);
				// Notes: i+variable is where slack value store in consMatrix
				consMatrixCpy.get(i).set(j + variable, temp);
			}
		}
	}

	protected void matrixMULConsSol() {
		double sum = 0;

		// Dynamically generate temporary array
		B_temp.clear();

		B_temp.add(new ArrayList<Double>());
		for (int j = 0; j < constraint; j++) {
			B_temp.get(0).add(0.0);
		}

		// Matrix Multiplication
		for (int i = 0; i < constraint; i++) {

			sum = 0;
			for (int k = 0; k < constraint; k++) {
				sum += B_inv.get(i).get(k) * solMatrix.get(k + 1);
			}
			B_temp.get(0).set(i, sum);

		}

		// Update Result
		for (int i = 0; i < constraint; i++) {
			solMatrixCpy.set(i + 1, B_temp.get(0).get(i));
		}
	}

	protected void initialMatrixOp() {
		CbT.clear();
		B.clear();
		B_inv.clear();
		B_temp.clear();
		B_temp2.clear();

		consMatrixCpy.clear();
		objMatrixCpy.clear();
		solMatrixCpy.clear();

		// Clear and regenerate the temporary array
		for (int i = 0; i < constraint; i++) {
			consMatrixCpy.add(new ArrayList<Double>());
			for (int j = 0; j < variable + constraint; j++) {
				consMatrixCpy.get(i).add(0.0);
				if (i == 0)
					objMatrixCpy.add(0.0);
			}
		}

		// Step 1: Generate CbT: obj. Coeff in order of Xb
		// dynamic allocate the solMatrixCpy Matrix
		solMatrixCpy.add(0.0);
		for (int i = 0; i < constraint; i++) {
			solMatrixCpy.add(0.0);
			CbT.add((-1) * objMatrix.get(basicColPos.get(i + 1) - 1));
		}

		// Step 2: Generate B: LHS Constraint coeff in order of Xb
		for (int i = 0; i < constraint; i++) {
			B.add(new ArrayList<Double>());
			B_inv.add(new ArrayList<Double>());

			for (int j = 0; j < constraint; j++) {
				int colPos = basicColPos.get(j + 1) - 1;
				B.get(i).add(consMatrix.get(i).get(colPos));
				if (i == j)
					B_inv.get(i).add(1.0);
				else
					B_inv.get(i).add(0.0);
			}
		}
	}
}
