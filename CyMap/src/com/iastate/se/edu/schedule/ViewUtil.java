package com.iastate.se.edu.schedule;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Utility method for the Views in the application
 * 
 * @author John Shelley
 * 
 */
public class ViewUtil {

	public static void setViewWidths(View view, View[] views) {
		int width = view.getWidth();
		int height = view.getHeight();
		for (int i = 0; i < views.length; i++) {
			View v = views[i];
			v.layout((i + 1) * width, 0, (i + 2) * width, height);
			printView("view[" + i + "]", v);
		}
	}

	public static void printView(String msg, View v) {
		System.out.println(msg + "=" + v);
		if (v == null) {
			System.out.println("View is null!");
			return;
		}
		System.out.print("[" + v.getLeft());
		System.out.print("," + v.getTop());
		System.out.print(", width: " + v.getWidth());
		System.out.println(", height: " + v.getHeight() + "]");
		System.out.println("MeasuredWidth: " + v.getMeasuredWidth());
		System.out.print("MeasuredHeight: " + v.getMeasuredHeight());
		System.out.print("scroll [" + v.getScrollX() + "," + v.getScrollY()
				+ "]");
	}

	public static void initListView(Context context, ListView listView,
			String prefix, int numItems, int layout) {
		/*
		 * By using SetAdapter method in ListView we can add a String Array to
		 * List
		 */
		String[] stringArray = new String[numItems];
		/* Adds the days to the menu items */
		for (int i = 0; i < stringArray.length; i++) {
			switch (i) {
			case 0:
				stringArray[i] = "Monday";
				break;
			case 1:
				stringArray[i] = "Tuesday";
				break;
			case 2:
				stringArray[i] = "Wednesday";
				break;
			case 3:
				stringArray[i] = "Thursday";
				break;
			case 4:
				stringArray[i] = "Friday";
				break;
			default:
				stringArray[i] = "Today";
				break;
			}
		}
		listView.setAdapter(new MyTypeFaceAdapter(context, layout, stringArray));
//		listView.setAdapter(new ArrayAdapter<String>(context, layout,
//				stringArray));
		listView.setBackgroundColor(Color.rgb(130, 36, 51));
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				Context context = view.getContext();
				String msg = "item[" + pos + "]: "
						+ parent.getItemAtPosition(pos);
				Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
				System.out.println(msg);
			}

		});
	}
}
