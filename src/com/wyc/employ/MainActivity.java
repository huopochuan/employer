package com.wyc.employ;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText etSearch = null;
	private ImageView ivDeleteText = null;
	private ListView listview = null;
	private List<employ> data = null;
	private LsitAdapter adapter = null;
	private RelativeLayout rl_view = null;
	private TextView tv_add = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		listview = (ListView) this.findViewById(R.id.listview);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				employ person = data.get(arg2);

				new PopupWindows(MainActivity.this, rl_view, person, 1);
				//getData();
			}
		});
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {

				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);

				builder.setMessage("Do you want Delete?");
				builder.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								int id = data.get(arg2).getId();
								DBManger manager = new DBManger(
										MainActivity.this);
								manager.delete(id);
								data = manager.query("");

								manager.closeDB();
								adapter.refresh(data);
							}
						});

				builder.setNegativeButton("NO",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

							}
						});
				builder.show();
				// Toast.makeText(MainActivity.this, "执行了", 0).show();
				return false;
			}

		});
		etSearch = (EditText) this.findViewById(R.id.etSearch1);
		ivDeleteText = (ImageView) this.findViewById(R.id.ivDeleteText);
		tv_add = (TextView) this.findViewById(R.id.tv_add);

		rl_view = (RelativeLayout) this.findViewById(R.id.rl_view);
		tv_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				new PopupWindows(MainActivity.this, rl_view, null, 0);
				getData();
			}

		});
		DBManger manager = new DBManger(this);

		data = manager.query("");

		manager.closeDB();

		adapter = new LsitAdapter(this, data);
		listview.setAdapter(adapter);

		ivDeleteText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				etSearch.setText("");
			}

		});

		etSearch.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				DBManger manager = new DBManger(MainActivity.this);
				// manager.add(per);
				data = manager.query(s.toString());

				manager.closeDB();

				adapter.refresh(data);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			public void afterTextChanged(Editable s) {
				if (s.length() == 0) {
					ivDeleteText.setVisibility(View.GONE);
				} else {
					ivDeleteText.setVisibility(View.VISIBLE);
				}
			}
		});

	}

	public void getData() {

		//Toast.makeText(this, "执行了", 0).show();
		DBManger manager = new DBManger(MainActivity.this);

		data = manager.query("");

		manager.closeDB();
		adapter.refresh(data);

	}

}
