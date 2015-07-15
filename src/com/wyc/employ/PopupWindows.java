package com.wyc.employ;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;

public class PopupWindows extends PopupWindow {
   
	private Spinner spinner_por;
	private EditText edit_xm;
	private EditText edit_age;
	private Spinner spinner_sex;
	private EditText edit_gz;
	private EditText edit_yw;
	private EditText edit_time;
	private Button btn_qr;
	private Context context;
	private int flage;
	private int id;
	public PopupWindows(Context mContext, View parent,employ person,int flag) {
        this.flage=flag;
		context=mContext;
		View view = View
				.inflate(mContext, R.layout.popwindow, null);
		view.startAnimation(AnimationUtils.loadAnimation(mContext,
				R.anim.fade_ins));
		
		setWidth(LayoutParams.FILL_PARENT);
		setHeight(LayoutParams.FILL_PARENT);
		setBackgroundDrawable(new BitmapDrawable());
		setFocusable(true);
		setOutsideTouchable(true);
		
		setContentView(view);
		showAsDropDown(parent);
		update();
        
		spinner_por=(Spinner)view.findViewById(R.id.spinner_por);
		edit_xm=(EditText)view.findViewById(R.id.edit_xm);;
		edit_age=(EditText)view.findViewById(R.id.edit_age);;
		spinner_sex=(Spinner)view.findViewById(R.id.spinner_sex);;
		edit_gz=(EditText)view.findViewById(R.id.edit_gz);;
		edit_gz.setEnabled(false);
		edit_yw=(EditText)view.findViewById(R.id.edit_yw);;
		edit_time=(EditText)view.findViewById(R.id.edit_time);;
		btn_qr=(Button)view.findViewById(R.id.btn_qr);;
		
//		spinner_por.setOnItemSelectedListener(new OnItemSelectedListener(){
//
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				switch(spinner_por.getSelectedItemPosition()){
//				  case 0:
//					  edit_time.setVisibility(View.GONE);
//					  break;
//				  case 1:
//					  edit_yw.setVisibility(View.GONE);
//					  break;
//				  case 2:
//					  edit_time.setVisibility(View.GONE);
//					  break;
//				  case 3:
//					  edit_time.setVisibility(View.GONE);
//					  break;
//				}
//				
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		});
		
		
		
		if(flag==1){
			spinner_por.setSelection(person.getPor());
			edit_xm.setText(person.getName());
			edit_age.setText(person.getAge()+"");
		    spinner_sex.setSelection(person.getSex());
		    edit_gz.setText(person.getMoney()+"");
		    edit_yw.setText(person.getYwze()+"");
		    edit_time.setText(person.getTime()+"");
		    id=person.getId();
		}
		
		btn_qr.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				add();
				
			}
			
		});

	}
	public void add(){
		String por=spinner_por.getSelectedItemPosition()+"";
		String xm=edit_xm.getText().toString();
		String age=edit_age.getText().toString();
		String sex=spinner_sex.getSelectedItemPosition()+"";
		String gz=edit_gz.getText().toString();
		String yw=edit_yw.getText().toString();
		String time=edit_time.getText().toString();
		employ per=new employ();
		switch(Integer.valueOf(por)){
		  case 0:
			  gz= Double.valueOf(yw)*0.04+"";
			  break;
		  case 1:
			  gz=Integer.valueOf(time)*100+"";
			  break;
		  case 2:
			  gz= (Double.valueOf(yw)*0.005+5000)+"";
			  break;
		  case 3:
			  gz=8000+"";
			  break;
		}
		per.setAge(Integer.valueOf(age));
		per.setName(xm);
		per.setPor(Integer.valueOf(por));
		per.setSex(Integer.valueOf(sex));
		per.setTime(Integer.valueOf(time));
		per.setYwze(Double.valueOf(yw));
		per.setMoney(Double.valueOf(gz));
		DBManger manager= new DBManger(context);
		if(flage==0){
		    manager.add(per);
		}
		else{
			per.setId(id);
			manager.update(per);
		}
		manager.closeDB();
		this.dismiss();
		((MainActivity)context).getData();
	}
	
}