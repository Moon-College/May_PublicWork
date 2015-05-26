package com.ztt.ch4listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity
{
    private List<Person> list = new ArrayList<MainActivity.Person>();
    
    private ListView lv;

    private myAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();
        
    }
    
    private void initDatas()
    {
        Person person1 = new Person("ÕÅ·É", "ÄÐ", "´ò¼Ü", "165cm");
        Person person2 = new Person("¹ØÓð", "ÄÐ", "³Ô·¹", "166cm");
        Person person3 = new Person("ÕÔ·ÉÑà", "Å®", "ÌøÎè", "155cm");
        Person person4 = new Person("ÀîÊ¦Ê¦", "Å®", "Âô³ª", "165cm");
        Person person5 = new Person("ÀîÊÀÃñ", "ÄÐ", "»ÊµÛ", "175cm");
        Person person6 = new Person("ÕÅ·É", "ÄÐ", "´ò¼Ü", "165cm");
        Person person7 = new Person("¹ØÓð", "ÄÐ", "³Ô·¹", "166cm");
        Person person8 = new Person("ÕÔ·ÉÑà", "Å®", "ÌøÎè", "155cm");
        Person person9 = new Person("ÀîÊ¦Ê¦", "Å®", "Âô³ª", "165cm");
        Person person10 = new Person("ÀîÊÀÃñ", "ÄÐ", "»ÊµÛ", "175cm");
        
        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        list.add(person5);
        list.add(person6);
        list.add(person7);
        list.add(person8);
        list.add(person9);
        list.add(person10);
    }
    
    private void initViews()
    {
        lv = (ListView) findViewById(R.id.lv);
        adapter = new myAdapter(getApplicationContext(), list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id)
            {
                list.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
    
    /**
     * ÊÊÅäÆ÷
     * @author zhangtengteng
     *
     */
    class myAdapter extends BaseAdapter
    {
        LayoutInflater inflater;
        
        Context context;
        
        List data;
        
        public myAdapter(Context context, List data)
        {
            super();
            this.context = context;
            this.data = data;
            inflater = inflater.from(context);
        }
        
        @Override
        public int getCount()
        {
            return data.size();
        }
        
        @Override
        public Object getItem(int position)
        {
            return data.get(position);
        }
        
        @Override
        public long getItemId(int position)
        {
            return 0;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            Person person = list.get(position);
            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.lv_item, null);
            }
            
            LinearLayout group = (LinearLayout) convertView.findViewById(R.id.ll_group);
            TextView name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView sex = (TextView) convertView.findViewById(R.id.tv_sex);
            if(person.getSex().equals("ÄÐ")){
                group.setBackgroundColor(getResources().getColor(R.color.primary));
            }else{
                group.setBackgroundColor(getResources().getColor(R.color.warningtext));
            }
            
            TextView speciality =
                (TextView) convertView.findViewById(R.id.tv_speciality);
            TextView height =
                (TextView) convertView.findViewById(R.id.tv_height);
            
            name.setText(person.getName());
            sex.setText(person.getSex());
            speciality.setText(person.getSpeciality());
            height.setText(person.getHeight());
            
            return convertView;
        }
    }
    
    class Person
    {
        private String name;
        
        private String sex;
        
        private String speciality;
        
        private String height;
        
        public Person()
        {
            super();
        }
        
        public Person(String name, String sex, String speciality, String height)
        {
            super();
            this.name = name;
            this.sex = sex;
            this.speciality = speciality;
            this.height = height;
        }
        
        public String getName()
        {
            return name;
        }
        
        public void setName(String name)
        {
            this.name = name;
        }
        
        public String getSex()
        {
            return sex;
        }
        
        public void setSex(String sex)
        {
            this.sex = sex;
        }
        
        public String getSpeciality()
        {
            return speciality;
        }
        
        public void setSpeciality(String speciality)
        {
            this.speciality = speciality;
        }
        
        public String getHeight()
        {
            return height;
        }
        
        public void setHeight(String height)
        {
            this.height = height;
        }
    }
}
