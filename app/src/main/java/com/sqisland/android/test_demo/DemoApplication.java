package com.sqisland.android.test_demo;

import android.app.Application;

import com.u.securekeys.SecureEnvironment;
import com.u.securekeys.annotation.SecureKey;
import javax.inject.Singleton;

import dagger.Component;

@SecureKey(
    key = "One",
    value = "Uno"
)
public class DemoApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    SecureEnvironment.initialize(this);
  }

  @Singleton
  @Component(modules = ClockModule.class)
  public interface ApplicationComponent extends DemoComponent {
  }

  @SecureKey(
      key = "Two",
      value = "Dos"
  )
  private final DemoComponent component = createComponent();

  protected DemoComponent createComponent() {
    return DaggerDemoComponent.builder().build();
  }

  @SecureKey(
      key = "Three",
      value = "Tres"
  )
  public DemoComponent component() {
    return component;
  }
}