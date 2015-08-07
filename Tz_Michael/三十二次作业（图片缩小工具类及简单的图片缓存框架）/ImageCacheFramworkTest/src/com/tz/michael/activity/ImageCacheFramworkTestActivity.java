package com.tz.michael.activity;

import com.tz.michael.utils.CacheImageLoader;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class ImageCacheFramworkTestActivity extends Activity {
	
	private String[] arr_paths=new String[]{
		"http://pica.nipic.com/2007-11-09/2007119124513598_2.jpg",
		"http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=324d313a233fb80e0c8469d106e10316/21a4462309f79052ab867a350ef3d7ca7bcbd51b.jpg",
		"http://pic2.ooopic.com/01/26/61/83bOOOPIC72.jpg",
		"http://pic1.ooopic.com/uploadfilepic/sheji/2009-08-09/OOOPIC_SHIJUNHONG_20090809ad6104071d324dda.jpg",
		"http://pic1.nipic.com/2008-08-12/200881211331729_2.jpg",
		"http://pic.nipic.com/2007-11-09/2007119122519868_2.jpg",
		"http://pic.nipic.com/2007-11-09/2007119121849495_2.jpg",
		"http://img5.imgtn.bdimg.com/it/u=2651666712,3423349209&fm=21&gp=0.jpg",
		"http://pic1a.nipic.com/2008-12-04/2008124215522671_2.jpg",
		"http://pica.nipic.com/2007-12-18/200712189215503_2.jpg",
		"http://pic1.ooopic.com/uploadfilepic/sheji/2009-05-05/OOOPIC_vip4_20090505079ae095187332ea.jpg",
		"http://pica.nipic.com/2007-11-09/2007119124513598_2.jpg",
		"http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=324d313a233fb80e0c8469d106e10316/21a4462309f79052ab867a350ef3d7ca7bcbd51b.jpg",
		"http://pic2.ooopic.com/01/26/61/83bOOOPIC72.jpg",
		"http://pic.nipic.com/2007-11-09/2007119121849495_2.jpg",
		"http://img5.imgtn.bdimg.com/it/u=2651666712,3423349209&fm=21&gp=0.jpg"
	};
	
	private ListView lv;
	private CacheImageLoader caLoader;
	private MyAdapter adapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        lv=(ListView) findViewById(R.id.lv);
        caLoader=CacheImageLoader.getInstance(this);
        adapter=new MyAdapter(this);
        lv.setAdapter(adapter);
    }
    
    class MyAdapter extends BaseAdapter{

    	private LayoutInflater inflater;
    	private Context mContext;
    	
    	public MyAdapter(Context context) {
    		this.mContext=context;
    		inflater=LayoutInflater.from(mContext);
		}
    	
		public int getCount() {
			return arr_paths.length;
		}

		public Object getItem(int position) {
			return arr_paths[position];
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder vh=null;
			if(convertView==null){
				vh=new ViewHolder();
				convertView=inflater.inflate(R.layout.lv_item, parent, false);
				vh.img=(ImageView) convertView.findViewById(R.id.img);
				convertView.setTag(vh);
			}else{
				vh=(ViewHolder) convertView.getTag();
			}
			
			caLoader.loadImage(arr_paths[position], vh.img);
			return convertView;
		}
    	
		class ViewHolder{
			private ImageView img;
		}
		
    }
}