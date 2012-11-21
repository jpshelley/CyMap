package com.iastate.se.edu.schedule;

import java.util.Date;

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
		 * By using SetAdapter method in ListView we can add a String Array to a
		 * List
		 */
		String[] stringArray = new String[] { "Monday", "Tuesday", "Wednesday",
				"Thursday", "Friday", "Add Class +" };

		listView.setAdapter(new MyTypeFaceAdapter(context, layout, stringArray));
		listView.setBackgroundColor(Color.rgb(130, 36, 51));
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				Context context = view.getContext();
				String msg = "item[" + pos + "]: "
						+ parent.getItemAtPosition(pos);
				Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
				System.out.println(msg);

				String day = parent.getItemAtPosition(pos).toString();
				slideMenuOnClick(view);
				setMenuTitleOnClick(view, day);
				updateScheduleView(view, day);
			}

			/**
			 * Slides the menu back on the multiple day views
			 * 
			 * @param view
			 */
			private void slideMenuOnClick(View view) {
				int menuWidth = view.getMeasuredWidth();

				/* Ensure the menu is visible */
				view.setVisibility(View.VISIBLE);

				if (SchedListActivity.menuVisible) {
					/* Scroll to menuWidth to disappear */
					SchedListActivity.scrollView.smoothScrollTo(menuWidth, 0);
				} else {
					/* Scroll to 0 to reveal menu */
					int left = 0;
					SchedListActivity.scrollView.smoothScrollTo(left, 0);
				}
				SchedListActivity.menuVisible = !SchedListActivity.menuVisible;
			}

			/**
			 * Sets the menu title on the main schedule page to match whats been
			 * clicked.
			 * 
			 * @param view
			 */
			private void setMenuTitleOnClick(View view, String day) {
				if (day.equals(Day.MONDAY)) {

				} else if (day.equals(Day.TUESDAY)) {

				} else if (day.equals(Day.WEDNESDAY)) {

				} else if (day.equals(Day.THURSDAY)) {

				} else if (day.equals(Day.FRIDAY)) {

				} else if (day.equals("Add Class +")) {

				} else {
					
				}
			}

			/**
			 * Helps update the list view on the main schedule page.
			 * 
			 * @param view
			 */
			private void updateScheduleView(View view, String day) {
				if (day.equals(Day.MONDAY)) {

				} else if (day.equals(Day.TUESDAY)) {

				} else if (day.equals(Day.WEDNESDAY)) {

				} else if (day.equals(Day.THURSDAY)) {

				} else if (day.equals(Day.FRIDAY)) {

				} else if (day.equals("Add Class +")) {

				} else {
					
				}
			}

		});
	}
}
