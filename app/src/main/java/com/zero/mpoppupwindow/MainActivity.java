package com.zero.mpoppupwindow;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private ImageButton ib_showlist;
    private ListView listView;
    private ArrayList<String> mData;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText= (EditText) findViewById(R.id.et_input);
        ib_showlist= (ImageButton) findViewById(R.id.ib_dropdown);

        ib_showlist.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        showPopupWindow();
    }

    private void showPopupWindow() {
        initListView();

        mPopupWindow=new PopupWindow(listView,editText.getWidth(),200);

        // 设置点击外部区域, 自动隐藏
        mPopupWindow.setOutsideTouchable(true); // 外部可触摸
       mPopupWindow.setBackgroundDrawable(new BitmapDrawable()); // 设置空的背景, 响应点击事件

        mPopupWindow.setFocusable(true); //设置可获取焦点

        mPopupWindow.showAsDropDown(editText,0,3);

    }

    private void initListView() {
        listView=new ListView(this);
        listView.setDividerHeight(0);
        listView.setBackgroundResource(R.drawable.listview_background);

        mData=new ArrayList<String>();
        for (int i=0;i<30;i++){
            mData.add(100102+i+"");

        }

        listView.setAdapter(new MyAdapater());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("listview","onclick");
                editText.setText(mData.get(position));
                mPopupWindow.dismiss();
            }
        });

    }



    class MyAdapater extends BaseAdapter{

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=View.inflate(parent.getContext(),R.layout.listview_item,null);
            }
            TextView textView= (TextView) convertView.findViewById(R.id.tv_show);
            ImageButton ib_delete= (ImageButton) convertView.findViewById(R.id.ib_delete);

            textView.setText(mData.get(position));
            ib_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mData.remove(position);
                    notifyDataSetChanged();
                    if (mData.size()==0){
                        mPopupWindow.dismiss();
                    }
                }
            });

            return convertView;
        }
    }
}
