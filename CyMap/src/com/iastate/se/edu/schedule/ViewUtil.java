package com.iastate.se.edu.schedule;

import java.util.Calendar;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
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
				updateScheduleView(context, view, day);
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
			private void updateScheduleView(Context context, View view,
					String day) {
				if (day.toUpperCase().equals(Day.MONDAY.name())) {
					ViewUtil.initClassView(context,
							SchedListActivity.classListView, "Menu", 5,
							android.R.layout.simple_list_item_1, Day.MONDAY);
				} else if (day.toUpperCase().equals(Day.TUESDAY.name())) {
					ViewUtil.initClassView(context,
							SchedListActivity.classListView, "Menu", 5,
							android.R.layout.simple_list_item_1, Day.TUESDAY);
				} else if (day.toUpperCase().equals(Day.WEDNESDAY.name())) {
					ViewUtil.initClassView(context,
							SchedListActivity.classListView, "Menu", 5,
							android.R.layout.simple_list_item_1, Day.WEDNESDAY);
				} else if (day.toUpperCase().equals(Day.THURSDAY.name())) {
					ViewUtil.initClassView(context,
							SchedListActivity.classListView, "Menu", 5,
							android.R.layout.simple_list_item_1, Day.THURSDAY);
				} else if (day.toUpperCase().equals(Day.FRIDAY.name())) {
					ViewUtil.initClassView(context,
							SchedListActivity.classListView, "Menu", 5,
							android.R.layout.simple_list_item_1, Day.FRIDAY);
				} else if (day.equals("Add Class +")) {

				} else {

				}
			}

		});
	}

	public static void initClassView(Context context, ListView listView,
			String prefix, int numItems, int layout, Day day) {
		/*
		 * By using SetAdapter method in ListView we can add a String Array to a
		 * List
		 */
		String[] stringArray = null;

		Calendar calendar = Calendar.getInstance();
		int calDay = 0;
		if (day == Day.EMPTY) {
			calDay = calendar.get(Calendar.DAY_OF_WEEK);
			stringArray = determineToday(stringArray, calDay);
		} else {
			stringArray = determineToday(stringArray, day.getById(day));
		}

		listView.setAdapter(new MyTypeFaceAdapter(context, layout, stringArray));

		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long is) {
				Context context = view.getContext();
				String msg = "Menu[" + pos + "]: "
						+ parent.getItemAtPosition(pos);
				Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
				System.out.println(msg);

				String building = parent.getItemAtPosition(pos).toString();
				viewMapFromClass(view, building);
			}

			/**
			 * Helps view the map from clicking on a class.
			 * 
			 * @param view
			 */
			private void viewMapFromClass(View view, String building) {
				if (building.contains("Carver")) {
					System.out.println("TEST");
				} else if (building.equals("")) {

				} else if (building.equals("")) {

				} else if (building.equals("")) {

				} else if (building.equals("")) {

				} else if (building.equals("")) {

				} else {

				}
			}

		});
	}

	/**
	 * @param stringArray
	 * @param day
	 * @return
	 */
	private static String[] determineToday(String[] stringArray, int day) {
		switch (day) {
		case 1:
			stringArray = new String[] { "Today is Sunday!" };
			break;
		case 2:
			stringArray = new String[] { "Today is Monday!", "Carver" };
			break;
		case 3:
			stringArray = new String[] { "Today is Tuesday!" };
			break;
		case 4:
			stringArray = new String[] { "Today is Wednesday!" };
			break;
		case 5:
			stringArray = new String[] { "Today is Thursday!" };
			break;
		case 6:
			stringArray = new String[] { "Today is Friday!" };
			break;
		case 7:
			stringArray = new String[] { "Today is Saturday!" };
			break;
		default:
			break;
		}
		return stringArray;
	}
}
