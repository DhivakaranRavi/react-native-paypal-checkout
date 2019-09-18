package com.paypal;

import android.content.Intent;
import android.content.Context;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.JavaScriptModule;

public class PaypalPackage implements ReactPackage {
 
private PayPal paypalModule;

 @Override
 public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
    List<NativeModule> modules = new ArrayList<>();
    paypalModule = new PayPal(reactContext);
    modules.add(paypalModule);
    return modules;
  }

  @Override
  public List<Class<? extends JavaScriptModule>> createJSModules() {
    return Collections.emptyList();
  }

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    return Collections.emptyList();
  }

  public void handleActivityResult(final int requestCode, final int resultCode, final Intent data) {
    PaypalModule.handleActivityResult(requestCode, resultCode, data);
  }
}
