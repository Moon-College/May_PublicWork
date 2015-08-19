package cn.ysh.json;

import java.util.ArrayList;
import java.util.List;
import cn.ysh.bean.Person;
import cn.ysh.bean.Student;
import cn.ysh.bean.Teacher;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void objToString(View v){
		Student studet = new Student("ysh", 24, 0, 1);
		String str = objectToString(studet);
		Log.i("INFO", str);
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
	
	public void jsonToObj(View v){
		String json_teacher = "{\"age\":20,\"name\":\"zdy\",\"duty\":\"����\",\"students\":[{\"age\":18,\"name\":\"Danny\"},{\"age\":19,\"name\":\"kenny\"}]}";
		Teacher teacher = (Teacher) jsonToObject(json_teacher);
		List<Student> list = teacher.getStudents();
		for(Student s:list){
			Log.i("INFO", s.getName()+"---"+s.getAge());
		}
	}
	
	public void arrayToString(View v){
		List<Student> students = new ArrayList<Student>();
		Student ysh = new Student("ysh", 25, 0, 1);
		Student zdy = new Student("zdy", 24, 1, 2);
		students.add(ysh);
		students.add(zdy);
		String json = arrayToJson(students);
		Log.i("INFO", json);
	}
	
	public void jsonToArrayObj(View v){
			String str = "[{\"age\":25,\"gender\":0,\"name\":\"ysh\",\"sid\":1},{\"age\":24,\"gender\":1,\"name\":\"zdy\",\"sid\":2}]";
			List<Student> students = jsonToArrayObject(str);
			for(Student s : students){
				Log.i("INFO", s.getName()+"---"+s.getAge());
			}
	}
	
	/**
	 * ����ת�ַ���
	 * @param person
	 * @return
	 */
	public String objectToString(Person person){
		String str = com.alibaba.fastjson.JSON.toJSONString(person);
		return str;
	}
	
	/**
	 * json�ַ���ת����
	 * @param json
	 * @return
	 */
	public Object jsonToObject(String json){
		Object person = com.alibaba.fastjson.JSON.parseObject(json,Teacher.class);
		return person;
	}
	
	/**
	 * ��������תjson�ַ���
	 * @param students
	 * @return
	 */
	public String arrayToJson(List<Student> students){
		String jsonString = com.alibaba.fastjson.JSON.toJSONString(students);
		return jsonString;
	}
	
	/**
	 * json�ַ���ת��������
	 * @param json
	 * @return
	 */
	public List<Student> jsonToArrayObject(String json){
		List<Student> list = com.alibaba.fastjson.JSONArray.parseArray(json,Student.class);
		return list;
	}
}
