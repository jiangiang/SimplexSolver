package com.cookiemonster.simplexsolver;

import java.util.ArrayList;

import android.app.Fragment;
import android.widget.Toast;

public class MainFragment extends Fragment {
	static int variable = 0;
	static int constraint = 0;
	static String var = null;
	static String cons = null;
	static String[] strin;
	static int[] intin;
	static int counter;
	static int enterPos;
	static int leavePos;

	static boolean optimalFound = false;
	static boolean maximize = true;
	static boolean special_unbound = false;
	static boolean special_multiple = false;
	// static boolean special_cycling = false;
	// static boolean special_redundant = false;

	static ArrayList<ArrayList<Double>> consMatrix = new ArrayList<ArrayList<Double>>();
	static ArrayList<Double> objMatrix = new ArrayList<Double>();
	static ArrayList<Double> solMatrix = new ArrayList<Double>();
	static ArrayList<String> basicRow = new ArrayList<String>();
	static ArrayList<Integer> basicColPos = new ArrayList<Integer>();
	static ArrayList<Double> ratioMatrix = new ArrayList<Double>();

	static ArrayList<Double> CbT = new ArrayList<Double>();
	static ArrayList<ArrayList<Double>> B = new ArrayList<ArrayList<Double>>();
	static ArrayList<ArrayList<Double>> B_inv = new ArrayList<ArrayList<Double>>();
	static ArrayList<ArrayList<Double>> B_temp = new ArrayList<ArrayList<Double>>();
	static ArrayList<ArrayList<Double>> B_temp2 = new ArrayList<ArrayList<Double>>();

	protected void toast(String in) {
		Toast.makeText(getActivity(), in, Toast.LENGTH_SHORT).show();
	}

}
