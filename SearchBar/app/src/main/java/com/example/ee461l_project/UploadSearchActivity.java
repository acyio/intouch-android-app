package com.example.ee461l_project;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UploadSearchActivity extends AppCompatActivity {
	public final static String EXTRA_MESSAGE = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_search);
		handleIntent(getIntent());
	}

	public void uploadContact(View view) {
		Intent intent = new Intent(this, UploadInformationActivity.class);
		startActivity(intent);
	}

	/*
	 * public void viewContacts(View view) { Intent intent = new Intent(this,
	 * ViewContactActivity.class); startActivity(intent); }
	 */

	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
	}

	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			searchData(query);
		}
	}

	private void searchData(String query) {
		Intent intent = new Intent(this, SearchResultsActivity.class);
		intent.putExtra(EXTRA_MESSAGE, query);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.search_menu, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

		return true;
	}
}
