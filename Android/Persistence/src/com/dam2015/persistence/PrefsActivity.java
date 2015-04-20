package com.dam2015.persistence;

import java.util.List;

import android.preference.PreferenceActivity;

public class PrefsActivity extends PreferenceActivity {

	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.xml.userpreferenceheaders, target);
	}
	
	@Override
	protected boolean isValidFragment (String fragmentName) {
	  if(PrefsFragment.class.getName().equals(fragmentName)) return true;
	  return false;
	}
}
