package com.jack.cn;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class TzTwoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //���� ���� ����
       LinearLayout linearLayout= new LinearLayout(this);
       //�����Բ��� ָ�� Ϊ  ˮƽ����
       linearLayout.setOrientation(LinearLayout.HORIZONTAL); 
       //ָ�� ���Բ��ֵĿ� �͸�
      	LayoutParams params=new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);
        
        EditText editText=new EditText(this);       
       /* editText.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        editText.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);   */
        editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        //���ı� �����  �ŵ�   ���Բ����ļ��У�
        linearLayout.addView(editText, 0);
        
        Button button=new Button(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2));
       /* button.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        button.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);        */
        button.setText("ȷ��");
        linearLayout.addView(button, 1);
        
        setContentView(linearLayout);
    }
}