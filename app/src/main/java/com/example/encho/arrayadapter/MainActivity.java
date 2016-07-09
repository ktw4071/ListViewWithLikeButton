package com.example.encho.arrayadapter;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView taraList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taraList = (ListView)findViewById(R.id.tara_list);

        ArrayList<TARAValueObject> items = new ArrayList<TARAValueObject>();

        Resources res = getResources();
        items.add(new TARAValueObject("소연",
                res.getDrawable(R.drawable.t_ara_icon_soyeon)));
        items.add(new TARAValueObject("지연",
                res.getDrawable(R.drawable.t_ara_icon_jiyeon)));
        items.add(new TARAValueObject("큐리",
                res.getDrawable(R.drawable.t_ara_icon_qri)));
        items.add(new TARAValueObject("보람",
                res.getDrawable(R.drawable.t_ara_icon_boram)));
        items.add(new TARAValueObject("효민",
                res.getDrawable(R.drawable.t_ara_icon_hyomin)));
        items.add(new TARAValueObject("은정",
                res.getDrawable(R.drawable.t_ara_icon_eunjung)));
        TARAArrayAdapter adapter = new TARAArrayAdapter(this, items);
        taraList.setAdapter(adapter);

        //taraList.setOnItemClickListener(itemListener);
    }
    /*
    아이템행을 터치하면 발생하는 이벤트이며 아이템안에 존재하는 각 위젯에 이벤트를 설정시 itemClick 이벤트가 먼저 발생하도록 이 이벤트는 설정하지 않는 것이 보편적이다
    private AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            LinearLayout itemRoot = (LinearLayout)view;
            String memberName = (TextView)((LinearLayout) view).getChildAt(1).getText().toString();

            TARAValueObject valueObject = (TARAValueObject)parent.getItemAtPosition(position);

            Toast.makeText(MainActivity.this, valueObject.memberName, Toast.LENGTH_SHORT).show();
        }
    };*/



    // 가장 기본이 되는 어댑터, 개발자의 능력에 따라 자료구조를 다양하게 재정의하여 사용 가능
    // 유연성이 좋은 어댑터
    private class TARAArrayAdapter extends ArrayAdapter<TARAValueObject> {

        private Context currentContext;

        public TARAArrayAdapter(Context context, List<TARAValueObject> objects) {
            super(context, 0, objects);
            currentContext = context;
        }
        //ViewHolder viewHolder;
        @Override

        public View getView(final int position, View convertView, ViewGroup parent) {

            final TARAValueObject valueObject = (TARAValueObject)getItem(position);
            final ViewHolder viewHolder;
            //viewHolder.likeButtonWD = (ImageView)convertView.findViewById(R.id.like_Btn);
            //View rootView;
            //convertView인자는 그려질 아이템의 root(보통)값을 의미한다. 아이템에 그려질
            //위젯이 하나라면 그 위젯의 객체가 될 수 있다.
            //안드로이드 시스템에 의해 convertView 객체는 계속 재사용될 수 있음을 명심하라
            if (convertView == null){
                convertView = LayoutInflater.from(currentContext).inflate(R.layout.tara_list_view_item, null);
                viewHolder = new ViewHolder();
                viewHolder.memberImageWD = (ImageView)convertView.findViewById(R.id.member_icon);
                viewHolder.memberNameWD =  (TextView)convertView.findViewById(R.id.member_name);
                viewHolder.likeButtonWD = (ImageView)convertView.findViewById(R.id.like_Btn);
                viewHolder.countNumWD = (TextView)convertView.findViewById(R.id.countLike);
                convertView.setTag(viewHolder);
            }
            else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.memberImageWD.setImageDrawable(valueObject.memberImage);
            viewHolder.memberNameWD.setText(valueObject.memberName);
            viewHolder.likeButtonWD.setImageResource(R.drawable.like_button);
            //viewHolder.countNumWD.setText(valueObject.count.toString());

            viewHolder.memberImageWD.setOnTouchListener(new View.OnTouchListener(){
                public boolean onTouch(View v, MotionEvent event){
                    Toast.makeText(currentContext, valueObject.memberName + "을 선택", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            viewHolder.likeButtonWD.setOnTouchListener(new View.OnTouchListener(){
                public boolean onTouch(View v, MotionEvent event){
                    valueObject.count++;
                    viewHolder.countNumWD.setText(String.valueOf(valueObject.count));
                    return false;
                }
            });

            //지금은 한행을 그릴 때 마다 새로운 위젯들을 생성하고 있음을 명심하세요.

            // 한 행이 그려질 root 레이아웃을 리턴해야 한다.
            return convertView;
        }
        private class ViewHolder {
            public ImageView memberImageWD;
            public TextView  memberNameWD;
            public ImageView likeButtonWD;
            public TextView countNumWD;

        }
    }
}
