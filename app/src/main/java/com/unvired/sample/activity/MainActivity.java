package com.unvired.sample.activity;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.app.ActionBar;

import com.unvired.sample.R;
import com.unvired.ui.Home;
import com.unvired.utils.UITheme;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getSupportActionBar().setIcon(R.drawable.launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
		setContentView(R.layout.activity_main);
	}

	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.clear();
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			navigateToSettingsScreen();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void navigateToSettingsScreen() {
		Intent intent = new Intent(this, Home.class);
		startActivity(intent);
	}
}