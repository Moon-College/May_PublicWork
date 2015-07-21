package com.decent.broadcast.util;

import java.util.List;

import com.decent.decentutil.DecentLogUtil;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

public class MemoryMangerUtil {

	private static final String TAG = "MemoryMangerUtil";

	/**
	 * ɱ�����к�̨���еĽ���
	 * 
	 * @param context
	 *            ������
	 * @param levelRestrict kill������xxxxlevel�Ľ���,�����<0�����֣���ʹ��Ĭ�ϡ���ɱ������RunningAppProcessInfo.IMPORTANCE_VISIBLE��
	 *              
	 */
	public static void killAllRunningProcess(Context context,int levelRestrict) {
		//���levelRestrictС��0��ʹ��Ĭ��IMPORTANCE_VISIBLE
		int level = levelRestrict<0?RunningAppProcessInfo.IMPORTANCE_VISIBLE:levelRestrict;
		// 1����ȡactivityManager
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		// 2��ͨ��am��ȡ��ǰ�������еĽ���
		List<RunningAppProcessInfo> allRunningProcList = am
				.getRunningAppProcesses();

		// 3������allRunningProcList��kill
		for (RunningAppProcessInfo rapInfo : allRunningProcList) {
			if (rapInfo.importance > level) {
				// 3.1�� ��ȡ���process��ص�package����
				String[] relativePaks = rapInfo.pkgList;
				// 3.2��ʹ��killBackgroundProcessesȥkill��Ӧ��package
				// kill��ҪȨ��
				// android.Manifest.permission.KILL_BACKGROUND_PROCESSES
				for (String pakName : relativePaks) {
					am.killBackgroundProcesses(pakName);
					DecentLogUtil.d(TAG, "package:" + pakName
							+ " has been killed");
				}
			}
		}
	}

	/**
	 * ��õ�ǰϵͳʣ����ڴ�����λ��byte
	 * 
	 * @param context
	 *            ������
	 * @return ��ǰϵͳʣ����ڴ�����λ��byte
	 */
	public static long getAvailMemorySize(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo mi = new MemoryInfo();
		am.getMemoryInfo(mi);
		return mi.availMem;
	}
}
