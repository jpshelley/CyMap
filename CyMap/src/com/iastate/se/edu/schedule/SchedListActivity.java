package com.iastate.se.edu.schedule;

/**
 * CyMap - SE 319
 * @Author John Shelley
 * Copyright (c) 2012
 * CyMap Schedule implementation
 * -Uses slide out tab to choose the day.
 * -Default view depends on the day of the week
 *  
 */
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.iastate.se.edu.R;
import com.iastate.se.edu.schedule.MyHorizontalScrollView.SizeCallback;

/**
 * Main activity to determine what the users schedule is for the day.
 * 
 * @author johnshelley
 * 
 */
public class SchedListActivity extends Activity {
	static MyHorizontalScrollView scrollView;
	static View menu;
	static ListView dayListView;
	static ListView classListView;
	View app;
	ImageView buttonSlide;
	static boolean menuVisible = false;
	Handler handler = new Handler();
	int buttonWidth;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Thin.ttf");

		LayoutInflater inflater = LayoutInflater.from(this);
		scrollView = (MyHorizontalScrollView) inflater.inflate(
				R.layout.schedule_menu_scroll, null);
		setContentView(scrollView);

		/* The pop out view */
		menu = inflater.inflate(R.layout.schedule_menu, null);
		/* Cyclone Cardinal Shade */
		menu.setBackgroundColor(Color.rgb(130, 36, 51));

		/* The actual schedule shown */
		app = inflater.inflate(R.layout.schedule_menu_app, null);
		/* Cyclone Gold Highlight */
		app.setBackgroundColor(Color.rgb(250, 218, 99));

		/* Set menu title font detail */
		TextView menuTitle = (TextView) app.findViewById(R.id.mainTitle);
		createFontDetail(tf, menuTitle);

		/* Creates the header for the app */
		ViewGroup tabBar = (ViewGroup) app.findViewById(R.id.tabBar);
		/* Cyclone Cardinal Shade */
		tabBar.setBackgroundColor(Color.rgb(130, 36, 51));

		/* Creates the menu list */
		dayListView = (ListView) menu.findViewById(R.id.list);
		ViewUtil.initListView(this, dayListView, "Item ", 5,
				android.R.layout.simple_list_item_1);
		/* Helps sets the fonts and size of menu header */
		TextView headerType = (TextView) menu.findViewById(R.id.menu_title);
		createFontDetail(tf, headerType);

		/* Creates the students schedule */
		classListView = (ListView) app.findViewById(R.id.classList);
		ViewUtil.initClassView(this, classListView, "Menu ", 5,
				android.R.layout.simple_list_item_1, Day.EMPTY);

		buttonSlide = (ImageView) tabBar.findViewById(R.id.buttonSlide);
		buttonSlide.setOnClickListener(new ClickListenerForScrolling(
				scrollView, menu));

		final View[] children = new View[] { menu, app };

		/* Scroll to app (view[i]) when layout is finished */
		int scrollToViewIndex = 1;
		scrollView.initView(children, scrollToViewIndex, new SizeCallbackMenu(
				buttonSlide));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_schedule, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.actionBarSched:
			Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * Helps create the side menu
	 * 
	 * @param tf
	 * @param view
	 */
	private void createFontDetail(Typeface tf, TextView view) {
		view.setTypeface(tf);
		view.setTextSize(24);
		view.setTextColor(Color.WHITE);
	}

	/**
	 * Helper for scrolling
	 * 
	 * @author johnshelley
	 * 
	 */
	static class ClickListenerForScrolling implements OnClickListener {

		HorizontalScrollView scrollView;
		View menu;

		// static boolean menuVisible = false;

		public ClickListenerForScrolling(HorizontalScrollView scrollView,
				View menu) {
			super();
			this.scrollView = scrollView;
			this.menu = menu;
		}

		public void onClick(View v) {
			Context context = menu.getContext();
			String msg = "Slide " + new Date();
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
			System.out.println(msg);

			int menuWidth = menu.getMeasuredWidth();

			/* Ensure the menu is visible */
			menu.setVisibility(View.VISIBLE);

			if (!menuVisible) {
				/* Scroll to 0 to reveal menu */
				int left = 0;
				scrollView.smoothScrollTo(left, 0);
			} else {
				/* Scroll to menuWidth to disappear */
				scrollView.smoothScrollTo(menuWidth, 0);
			}
			menuVisible = !menuVisible;
		}
	}

	static class SizeCallbackMenu implements SizeCallback {
		View buttonSlide;

		public SizeCallbackMenu(View buttonSlide) {
			super();
			this.buttonSlide = buttonSlide;
		}

		public void onGlobalLayout() {
			System.out
					.println("ButtonWidth: " + buttonSlide.getMeasuredWidth());
		}

		public void getViewSize(int idx, int w, int h, int[] dims) {
			dims[0] = w;
			dims[1] = h;
			final int menuIndex = 0;
			if (idx == menuIndex) {
				dims[0] = w - buttonSlide.getMeasuredWidth();
			}
		}

	}
}
