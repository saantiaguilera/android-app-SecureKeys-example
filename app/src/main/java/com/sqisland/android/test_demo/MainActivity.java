package com.sqisland.android.test_demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.u.securekeys.SecureEnvironment;
import com.u.securekeys.annotation.SecureConfigurations;
import com.u.securekeys.annotation.SecureKey;
import com.u.securekeys.annotation.SecureKeys;
import junit.framework.Assert;
import org.joda.time.DateTime;

import javax.inject.Inject;

@SecureKey(key = "Activity", value = "Value")
@SecureConfigurations(
    useAesRandomly = true
)
public class MainActivity extends Activity {
  public static final String KEY_MILLIS = "millis";

  @Inject
  Clock clock;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ((DemoApplication) getApplication()).component().inject(this);

    TextView todayView = (TextView) findViewById(R.id.date);

    long millis = getIntent().getLongExtra(KEY_MILLIS, -1);
    DateTime dateTime = (millis > 0) ? new DateTime(millis) : clock.getNow();
    todayView.setText(DateUtils.format(dateTime));

    Assert.assertFalse(SecureEnvironment.getString("Activity").isEmpty());
    Assert.assertFalse(SecureEnvironment.getString("One").isEmpty());
    Assert.assertFalse(SecureEnvironment.getString("Two").isEmpty());
    Assert.assertFalse(SecureEnvironment.getString("Three").isEmpty());

  }
}