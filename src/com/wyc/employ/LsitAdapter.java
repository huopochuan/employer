package com.wyc.employ;

import java.util.List;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LsitAdapter extends BaseAdapter{

	private List<employ> data=null;
	private LayoutInflater inflater=null;
	public LsitAdapter(Context context, List<employ> data){
		inflater=LayoutInflater.from(context);
		inflater.inflate(R.layout.item_lisveiw, null);
		this.data=data;
	}
	@Override
	public int getCount() {
		
		return data!=null?data.size():0;
	}
    public void refresh(List<employ> data){
    	this.data=data;
    	this.notifyDataSetChanged();
    }
	@Override
	public Object getItem(int arg0) {
		
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		ViewHolder holder=null;
		if(view==null){
			
			view=inflater.inflate(R.layout.item_lisveiw,null);
			holder=new ViewHolder();
			holder.tv_xm=(TextView)view.findViewById(R.id.tv_xm);
			holder.tv_xx=(TextView)view.findViewById(R.id.tv_xx);
			view.setTag(holder);
		}
		holder=(ViewHolder)view.getTag();
		holder.tv_xm.setText(data.get(position).getName());
		
		holder.tv_xx.setText("±àºÅ "+data.get(position).getId()+"  ÄêÁä "+data.get(position).getAge()
				+"¹¤×Ê"+data.get(position).getMoney()
				);
		
		return view;
	}
	
	class ViewHolder{
		public TextView tv_xm;
		public TextView tv_xx;
		
	}
	

}
