/**
 * Project Name:lsn13_animation
 * File Name:TweenActivity.java
 * Package Name:com.zht.animation
 * Date:2015-6-15����3:40:45
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * ClassName:TweenActivity <br/>
 * Function: ���䶯��. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-15 ����3:40:45 <br/>
 * 
 * @author hongtao
 * @version
 * @since JDK 1.6
 * @see
 */
public class TweenActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tween);
		// ���䶯����4�֣�����ɫ����ת��λ�ơ�����
		ImageView iv = (ImageView) findViewById(R.id.image);
        //����ɫ
//		Animation alpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
//		iv.startAnimation(alpha);
		//��ת
//		Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
//		iv.startAnimation(rotate);
		//λ��
//		Animation translate = AnimationUtils.loadAnimation(this, R.anim.translate);
//		iv.startAnimation(translate);
		//����
//		Animation scale = AnimationUtils.loadAnimation(this, R.anim.scale);
//		iv.startAnimation(scale);
	}
}
