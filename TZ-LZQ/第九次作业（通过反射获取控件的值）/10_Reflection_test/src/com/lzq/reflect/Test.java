package com.lzq.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

//	����û�е���Student  ��˵��  Test �� Studentû�����
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ʵ����һ������
		// --> �����ڴ� JVM ���ٵ�
		// Student s = new Student();

		try {
			// ��û�и����ǵ�Student����������
			Class cls = Class.forName("com.lzq.reflect.bean.Student");
			// ���Լ�����������б�
			// Field[] declaredFields = cls.getDeclaredFields();
			//
			// for (Field f : declaredFields) {
			// System.out.println(f.getName());
			// }

			/**
			 * ����һ��
			 */
			/*Field field = cls.getDeclaredField("name");
			// ���Ǹ�����ģ�� ����ʵ���������ڵ���Ĭ�ϵĹ��췽�� ��
			Object obj = cls.newInstance();
			// ǿ���ƽ� ��ȡprivate
			field.setAccessible(true);
			// �����Ҿ���Ҫ�����һ�����������
			// ����ǻ�ö�Ӧ���Ե�ֵ �����Ǿ�����Ҫȥ�����һ������ ���صľ���������Ե�ֵ
			String value = (String) field.get(obj);
			*/

			/**
			 * ��������
			 */
			Object obj = cls.newInstance();
			// ��ø�Name�����������
			Method method = cls.getDeclaredMethod("getName", new Class[]{});
			// ���е���
			Object value2 = method.invoke(obj, null);
			
			System.out.println(value2);
			// ��̳е����Ե��б�
			// Field[] fields = cls.getFields();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
