package com.tangcheng.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FileUiActivity extends Activity implements OnItemClickListener,
		OnClickListener, android.content.DialogInterface.OnClickListener {

	private static final int NEWDIR = 0;
	private static final int NEWFILE = 1;
	private static final int RENAME = 2;
	public static final int DIALOG_PROGRESS = 3;
	private static final int DIALOG_PROGRESS_COPY = 4;
	private static final String TAG = "aa";
	// private static final String TPYE = "type";
	// private static final String SUFFIX = "suffix";

	private LinearLayout layoutToolbar;
	private Button buttonSlide;
	private Button buttonSelete;
	private Button buttonSearch;
	private Button buttonApp;

	private ListView listView;
	private GridView gridView;
	private TextView textView;
	private EditText editText_newname;
	private EditText editText_newfilename;

	private ArrayList<FileInfo> data;
	private FileInfoAdapter adapter;
	private ArrayList<File> copyFile;

	private boolean isSlideDown = true;
	private boolean dianji = false;
	private boolean iscopy = false;

	private File[] files;
	private File currentPath;
	private File sdPath;
	private File srcFile;

	private AlertDialog newAdir;
	private AlertDialog newAfile;
	private AlertDialog reAname;
	private EditText editText_rename;
	private int onItemPosition;
	private ProgressDialog dialog;
	private int nf;
	// private int n = 0;
	private String value;
	private boolean isDelete;
	// private boolean isCheckedMode = false;
	private boolean isListMode = true;
	private boolean isDeletes = false;
	private boolean isCheckedMode;
	private boolean ispaste;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		loadData();
		initView();
		listView.setOnItemClickListener(this);
		gridView.setOnItemClickListener(this);
		textView.setText(sdPath.toString());
		registerForContextMenu(listView);
		registerForContextMenu(gridView);
	}

	/**
	 * 声明
	 */
	private void initView() {
		textView = (TextView) findViewById(R.id.textView1);
		listView = (ListView) findViewById(R.id.file_listView);
		gridView = (GridView) findViewById(R.id.file_gridView);
		adapter = new FileInfoAdapter(this, data);
		listView.setAdapter(adapter);
		gridView.setAdapter(adapter);
		textView.setTextColor(Color.BLACK);
		layoutToolbar = (LinearLayout) findViewById(R.id.linearLayout_toolbar);
		buttonSlide = (Button) findViewById(R.id.button_slide);
		buttonSelete = (Button) findViewById(R.id.Button_select);
		buttonApp = (Button) findViewById(R.id.Button_application);
		buttonSearch = (Button) findViewById(R.id.button_search);
	}

	/**
	 * 加载数据
	 */
	private void loadData() {
		copyFile = new ArrayList<File>();
		data = new ArrayList<FileInfo>();
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			sdPath = Environment.getExternalStorageDirectory();// 获得路径
			// 加载目录下的内容
			loadDir(sdPath);
		} else {
			// SD卡不可用
		}
	}

	/*
	 * 创建上下文菜单 (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu,
	 * android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) menuInfo;
		onItemPosition = contextMenuInfo.position;
		value = data.get(onItemPosition).getFile().getName();
		srcFile = data.get(onItemPosition).getFile();
		if (v == listView) {
			menu.setHeaderIcon(android.R.drawable.ic_menu_manage);
			menu.setHeaderTitle("操作" + value);
			getMenuInflater().inflate(R.menu.context_menu, menu);
		}
		countnf(srcFile);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	/**
	 * 计算文件个数
	 * 
	 * @param files
	 */
	private void countnf(File files) {
		if (files.isFile()) {
			nf++;
		} else if (files.isDirectory()) {
			File[] file = files.listFiles();
			for (File f : file) {
				if (f.isFile()) {
					nf++;
				} else if (f.isDirectory()) {
					countnf(f);
					// 文件夹++；
				}
			}
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_rename:
			showDialog(RENAME);
			break;
		case R.id.item_copy:
			break;
		case R.id.item_delete:
			// doDelete();
			// //异步
			isDelete = true;
			MyTask task = new MyTask();
			task.execute(srcFile);
			break;

		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onContextMenuClosed(Menu menu) {
		super.onContextMenuClosed(menu);
	}

	/*
	 * 创建选项菜单
	 * 
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.option_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/*
	 * 预处理选项菜单
	 * 
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		return super.onPrepareOptionsMenu(menu);
	}

	/*
	 * 选项菜单的监听事件
	 * 
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_newfile_file:
			showDialog(NEWFILE);
			break;
		case R.id.item_newfile_folder:
			showDialog(NEWDIR);
			break;
		case R.id.item_fileback:
			isDelete = false;
			MyTask task = new MyTask();
			task.execute(srcFile);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/*
	 * 选项菜单关闭
	 * 
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsMenuClosed(android.view.Menu)
	 */
	@Override
	public void onOptionsMenuClosed(Menu menu) {
		super.onOptionsMenuClosed(menu);
	}

	/*
	 * 对话框
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case NEWDIR:
			return createItem_newdir();
		case NEWFILE:
			return createItem_newfile();
		case RENAME:
			return createItem_rename();
		case DIALOG_PROGRESS:
			return createDialogProgress();
		case DIALOG_PROGRESS_COPY:
			return createDialogProgress();
		default:
			break;
		}
		return super.onCreateDialog(id);
	}

	private Dialog createDialogProgress() {
		dialog = new ProgressDialog(this);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setTitle("进度对话框");
		if (isDelete) {
			dialog.setMessage("删除");
		} else {
			dialog.setMessage("复制");
		}

		// 进度的最大值
		// dialog.setMax(nf);
		return dialog;
	}

	private Dialog createItem_rename() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_input_add);
		builder.setTitle(R.string.rename);
		editText_rename = new EditText(this);
		editText_rename.setSingleLine();
		builder.setView(editText_rename);
		builder.setPositiveButton("确定", this);
		builder.setNegativeButton("取消", null);
		reAname = builder.show();
		return reAname;
	}

	private Dialog createItem_newfile() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_menu_add);
		builder.setTitle("新建文件");
		editText_newfilename = new EditText(this);
		builder.setView(editText_newfilename);
		builder.setPositiveButton("确定", this);
		builder.setNegativeButton("取消", null);
		newAfile = builder.show();
		return newAfile;
	}

	private Dialog createItem_newdir() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_menu_add);
		builder.setTitle("新建文件夹");
		editText_newname = new EditText(this);
		builder.setView(editText_newname);
		builder.setPositiveButton("确定", this);
		builder.setNegativeButton("取消", null);
		newAdir = builder.show();
		return newAdir;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case NEWDIR:
			editText_newname.setText("");
			break;
		case NEWFILE:
			editText_newfilename.setText("");
			break;
		case RENAME:
			String filename = srcFile.getName().toString();
			int stop = filename.lastIndexOf(".");
			editText_rename.setText(filename);
			editText_rename.setSelection(0,
					stop > -1 ? stop : filename.length());
			break;
		case DIALOG_PROGRESS:
			// ((ProgressDialog) dialog).setMax(nf);
			break;
		default:
			break;
		}
		super.onPrepareDialog(id, dialog);
	}

	/**
	 * 加载目录下的文件给DATA
	 * 
	 * @param Dir
	 */
	private void loadDir(File Dir) {
		data.clear();
		currentPath = Dir;
		if (Dir == null) {
			Toast.makeText(this, "没卡", 1000).show();
		} else {
			files = Dir.listFiles();
			if (files.length > 0) {//
				for (File file : files) {
					FileInfo info = new FileInfo();
					info.setFile(file);
					info.setFilePath(file.getAbsolutePath());
					info.setName(file.getName());
					if (file.isDirectory()) {// 文件夹
						info.setBitmap(BitmapFactory.decodeResource(
								getResources(), R.drawable.folder));
					} else if (file.getName().toLowerCase().endsWith(".png")
							|| file.getName().toLowerCase().endsWith(".jpg")) {
						// 是图片文件
						info.setBitmap(null);
					} else {// 是普通文件
						info.setBitmap(BitmapFactory.decodeResource(
								getResources(), R.drawable.file));
					}
					data.add(info);
				}
			}
		}
		// Collections.sort(data);
	}

	/**
	 * 监听BUTTON事件的 监听器
	 * 
	 */
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_slide:
			if (isSlideDown) {
				isSlideDown = false;
				layoutToolbar.setVisibility(View.GONE);
				buttonSlide
						.setBackgroundResource(R.drawable.toolbar_slide_down);
			} else {
				isSlideDown = true;
				layoutToolbar.setVisibility(View.VISIBLE);
				buttonSlide.setBackgroundResource(R.drawable.toolbar_slide_up);
			}
			break;
		case R.id.Button_set:

			if (currentPath.getName().equals("sdcard")) {
				if (!dianji) {
					showToast("退出请再点一次！");
					dianji = true;
				} else {
					FileUiActivity.this.finish();
				}
			} else {
				loadDir(currentPath.getParentFile());// 返回上级目录
				textView.setText(currentPath.toString());
				adapter.notifyDataSetChanged();
			}
			break;
		case R.id.button_mode:

			if (isListMode) {
				gridView.setVisibility(View.VISIBLE);
				listView.setVisibility(View.GONE);
				isListMode = false;
			} else {
				gridView.setVisibility(View.GONE);
				listView.setVisibility(View.VISIBLE);
				isListMode = true;
			}

			break;

		case R.id.Button_select:
			isCheckedMode = !isCheckedMode;
			// Log.d(TAG, isCheckedMode+"");
			if (isCheckedMode) {
				buttonSelete.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.toolbar_select_focus, 0, 0);
				buttonSearch.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.history_delete, 0, 0);
				buttonSearch.setText("删除");
				buttonApp.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.toolbar_copy, 0, 0);
				buttonApp.setText("复制");
			}

			if (!isCheckedMode) {
				buttonSelete.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.toolbar_select, 0, 0);
				if (!iscopy) {
					buttonApp.setCompoundDrawablesWithIntrinsicBounds(0,
							R.drawable.toolbar_applications, 0, 0);
					buttonApp.setText("应用");
					buttonSearch.setCompoundDrawablesWithIntrinsicBounds(0,
							R.drawable.toolbar_search, 0, 0);
					buttonSearch.setText("搜索");
				}
				for (FileInfo info : data) {
					info.setChecked(false);
				}
				iscopy = false;
			}
			adapter.notifyDataSetChanged();
			break;

		case R.id.button_search:
			if (isCheckedMode) {
				if (!isDeletes) {
					isDelete = true;
					for (File file : copyFile) {

						Log.d(TAG, file.getName());
					}
					// for (File file : copyFile) {
					// MyTask myTask = new MyTask();
					// myTask.execute(file);
					// }
					// isDeletes = true;
				}
			} else {
				isDelete = false;
				isDeletes = false;
			}
			break;

		case R.id.Button_application:
			if (iscopy) {
				buttonApp.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.toolbar_paste, 0, 0);
				buttonApp.setText("粘贴");
				iscopy = !iscopy;
				// isCheckedMode = false;
			} else {
				buttonApp.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.toolbar_applications, 0, 0);
				buttonApp.setText("应用");
			}
			if (isCheckedMode) {
				iscopy = !iscopy;
				if (iscopy) {
					buttonApp.setCompoundDrawablesWithIntrinsicBounds(0,
							R.drawable.toolbar_paste, 0, 0);
					buttonApp.setText("粘贴");
					ispaste = true;
				} else {
					buttonApp.setCompoundDrawablesWithIntrinsicBounds(0,
							R.drawable.toolbar_copy, 0, 0);
					buttonApp.setText("复制");
					if (ispaste) {
						for (File file : copyFile) {
							Log.d(TAG, file.getName());
							try {
								FileUtil.docopy(file, currentPath);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						copyFile.clear();
						ispaste = false;
					}
					isCheckedMode = false;
				}
			}
			Log.d(TAG, iscopy + "");
			Log.d(TAG, isCheckedMode + "");
			break;

		case R.id.dialog_file:
			showDialog(NEWFILE);
			break;
		case R.id.dialog_dir:
			showDialog(NEWDIR);
			break;

		default:
			break;
		}
	}

	/*
	 * 列表内容点击的监听器 (non-Javadoc)
	 * 
	 * @see
	 * android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget
	 * .AdapterView, android.view.View, int, long)
	 */
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long id) {

		String path = data.get(position).getFilePath();// 获取被点击到的文件的路径
		currentPath = data.get(position).getFile();// 被点击到的条目的文件对象
		if (currentPath.isDirectory()) {
			loadAndNotityData();
		}

		// currentPath = data.get(position).getFile();
		// HashMap<String, String> types = readMap();
		// if (isCheckedMode) {
		// // 多选模式
		// FileInfo info = adapter.getItem(position);
		//
		// if (info.isChecked()) {
		// info.setChecked(false);
		// } else {
		// info.setChecked(true);
		// }
		// copyFile.clear();
		// for (FileInfo fileInfo : data) {
		// if (fileInfo.isChecked()) {
		// copyFile.add(fileInfo.getFile());
		// }
		// }
		// adapter.notifyDataSetChanged();
		// } else {
		// if (currentPath.isFile()) {
		// String filename = currentPath.getName().toString();
		// if (filename.contains(".")) {
		// String suffix = filename.substring(
		// filename.lastIndexOf(".") + 1, filename.length());
		// Log.d(TAG, suffix);
		// if (types.containsKey(suffix)) {
		// // if (filename.endsWith(".jpg")) {
		// String type = types.get(suffix);
		// Log.d(TAG, type);
		// Uri data = Uri
		// .parse("file://" + currentPath.toString());
		// myIntent(data, type);
		// }
		// }
		// // }
		// showToast("这是文件");
		// } else {
		// loadAndNotityData();
		// }
		// }
	}

	/**
	 * 读取map
	 */
	private HashMap<String, String> readMap() {

		HashMap<String, String> types = new HashMap<String, String>();

		Resources resources = getResources();
		resources.openRawResource(R.raw.mime);
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					resources.openRawResource(R.raw.mime)));
			String str = null;
			while (!("".equals(str = in.readLine()))) {
				String[] str2 = (str.substring(0, str.indexOf("#"))).split(" ");
				// types.put(str.substring(0, str.indexOf("")),
				// str.substring(str.indexOf(""), str.indexOf("#")).trim());
				for (int i = 2; i < str2.length; i++) {
					if (!"".equals(str2[i])) {
						types.put(str2[i], str2[0]);
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return types;
	}

	/**
	 * 
	 */
	private void myIntent(Uri data, String type) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		data = Uri.parse("file://" + currentPath.toString());
		// type = "image/*";
		intent.setDataAndType(data, type);

		startActivity(intent);
	}

	/**
	 * 定义返回键
	 * 
	 * @param string
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (currentPath.getName().equals("sdcard")) {
				if (!dianji) {
					showToast("退出请再点一次！");
					dianji = true;
					return true;
				}
			} else {
				loadDir(currentPath.getParentFile());
				textView.setText(currentPath.toString());
				adapter.notifyDataSetChanged();
				return true;
			}
		} else {

		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 提示
	 * 
	 */
	private void showToast(String string) {
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
	}

	public void onClick(DialogInterface dialog, int which) {
		if (dialog == newAdir) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				try {
					if (FileUtil.doCreatefolder(currentPath, editText_newname
							.getText().toString())) {
						loadAndNotityData();
					} else {
						showToast("已有的文件夹名");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;

			default:
				break;
			}
		}
		if (dialog == newAfile) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:

				try {
					if (FileUtil.doCreatefile(currentPath, editText_newfilename
							.getText().toString())) {
						loadAndNotityData();
					} else {
						showToast("已有的文件名");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;

			default:
				break;
			}
		}
		if (dialog == reAname) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				fileRename();
				loadAndNotityData();
				break;

			default:
				break;
			}
		}
	}

	private void fileRename() {
		try {
			FileUtil.dorename(srcFile, editText_rename.getText().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadAndNotityData() {
		loadDir(currentPath);
		textView.setText(currentPath.toString());
		adapter.notifyDataSetChanged();
	}

	class MyTask extends AsyncTask<File, Integer, String> {

		@Override
		protected void onPreExecute() {
			// TODO 预执行 显示对话框
			super.onPreExecute();

			showDialog(DIALOG_PROGRESS);
			if (isDelete) {
				showDialog(DIALOG_PROGRESS);
			} else {
				showDialog(DIALOG_PROGRESS_COPY);
			}
		}

		@Override
		protected String doInBackground(File... params) {
			// TODO 执行后台任务
			// for (File file : copyFile) {
			// try {
			// FileUtil.docopy(file, currentPath);
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
			// }

			if (isDelete) {
				doDelete(srcFile);
			} else {
				try {
					FileUtil.docopy(srcFile, currentPath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO 进度更新
			super.onProgressUpdate(values);
			// dialog.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO 在UI中显示结果
			if (isDelete) {
				removeDialog(DIALOG_PROGRESS);
				isDelete = false;
			} else {
				removeDialog(DIALOG_PROGRESS_COPY);
			}
			loadAndNotityData();
			super.onPostExecute(result);
		}

		/**
		 * shanchu
		 * 
		 */
		// private void doDelete(File file) {
		// fliedelete(file);
		// loadAndNotityData();
		// }

		private void doDelete(File file) {
			if (file.isFile()) {
				// n++;
				// publishProgress(n);
			}
			if (file.isDirectory()) {
				File[] filedis = file.listFiles();
				for (File f : filedis) {
					if (f.isFile()) {
						// n++;
						// f.delete();
						// publishProgress(n);
					}
					doDelete(f);
				}
			}
			file.delete();
		}

	}

}