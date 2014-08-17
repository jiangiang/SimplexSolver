package com.cookiemonster.simplexsolver;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ParameterFragment extends MainFragment{
	int status = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_1_parameter, container, false);

		final EditText edittext1 = (EditText) rootView.findViewById(R.id.editText1);
		final EditText edittext2 = (EditText) rootView.findViewById(R.id.editText2);
		final Button next = (Button) rootView.findViewById(R.id.button1);
		
		next.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
    			var = edittext1.getText().toString();
    			cons = edittext2.getText().toString();
    			
    			if(var.matches(""))
    				status = 1;
    			else if (cons.matches(""))
    				status = 2;
    			else if(var.matches("0"))
    				status = 3;
    			else if(cons.matches("0"))
    				status = 4;
    			else
    				status = 0;
    			
    			switch(status){
    			case 0:
	            	getFragmentManager().beginTransaction()
	            	.replace(R.id.container, new EquationFragment())
	            	.addToBackStack(null).commit();break;
    			case 1:
    				toast("Variables cannnot be empty!");break;
    			case 2:
    				toast("Constraints cannot be empty!");break;
    			case 3:
    				toast("Number of variables must be greater than 0!");break;
    			case 4:
    				toast("Please insert at least 1 constraint.");break;
    			}

        	}

        }); 
		
		
		return rootView;
	}
}
