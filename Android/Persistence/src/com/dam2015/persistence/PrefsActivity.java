package com.dam2015.persistence;

import java.util.List;
import android.preference.PreferenceActivity;

public class PrefsActivity extends PreferenceActivity {

	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.xml.userpreferenceheaders, target);
	}
}
