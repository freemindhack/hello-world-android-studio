package com.unvired.sample.activity;

import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.unvired.database.DBException;
import com.unvired.exception.ApplicationException;
import com.unvired.sample.R;
import com.unvired.logger.Logger;
import com.unvired.login.AuthenticationService;
import com.unvired.login.LoginListener;
import com.unvired.login.LoginParameters;
import com.unvired.login.LoginParameters.LOGIN_TYPE;
import com.unvired.model.ApplicationVersion;
import com.unvired.utils.FrameworkHelper;
import com.unvired.utils.UITheme;
import com.unvired.utils.UITheme.THEME;

public class StartUpActivity extends Activity implements LoginListener {

	private static final String URL = "live.unvired.io/UNI";

	private static Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startup);
		context = this;
	}

	public void onResume() {
		super.onResume();
		initialize();
	}

	private void initialize() {

		InputStream metaDataXmlStream = getResources().openRawResource(R.raw.metadata);

		String metaDataXml = null;
		try {
			metaDataXml = FrameworkHelper.getString(metaDataXmlStream);
		} catch (ApplicationException e) {
			Logger.log(Logger.LEVEL_ERROR, getClass().getName(), "navigateToLoginActivity", "ApplicationException: " + e.getMessage());
		}

        //Authenticate with Unvired Mobile Platform
        //Set required Login Parameters for Authentication
		LoginParameters.setUrl(URL);
		LoginParameters.setAppTitle(getResources().getText(R.string.app_name).toString());
		LoginParameters.setMetaDataXml(metaDataXml);
		LoginParameters.setLoginListener(this);
		LoginParameters.setDemoModeRequired(false);
		LoginParameters.setContext(context);
		LoginParameters.setCompany("UNVIRED");
		LoginParameters.setAppName("HELLO_WORLD");
		LoginParameters.showCompanyField(true);

		LoginParameters.setLoginTypes(new LOGIN_TYPE[] { LOGIN_TYPE.UNVIRED_ID, LOGIN_TYPE.ADS });

		UITheme.setTheme(THEME.THEME_LIGHT_BACKGROUND);

		ApplicationVersion.setBUILD_NUMBER("1");

		try {
			AuthenticationService.login(this.getApplicationContext());
		} catch (ApplicationException e) {
			Logger.log(Logger.LEVEL_ERROR, this.getClass().getName(), "initialize", e.getMessage());
		} catch (DBException e) {
			Logger.log(Logger.LEVEL_ERROR, this.getClass().getName(), "initialize", e.getMessage());
		} catch (Exception e) {
			Logger.log(Logger.LEVEL_ERROR, this.getClass().getName(), "initialize", "Exception caught: " + e.getMessage());
		}

		this.finish();
	}

	private void navigateToMainActivity() {
		
		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		this.finish();
	}

	@Override
	public void loginSuccessful() {
		navigateToMainActivity();
	}

	@Override
	public void loginFailure(String errorMessage) {

	}

	@Override
	public void authenticateAndActivationSuccessful() {
		navigateToMainActivity();
	}

	@Override
	public void authenticateAndActivationFailure(String errorMessage) {

	}

	@Override
	public void loginCancelled() {

	}

	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
}