# react-native-paypal-checkout

A React Native interface for the PayPal Payment UI

# Setup

1. Add react-navive-paypal-checkout to your project

```bash
npm install --save react-native-paypal-checkout
```

2. Add the following to android/app/build.gradle

```groovy
dependencies {
    // ...
    compile project(':react-native-paypal-checkout')
}
```

3. Add the following to android/settings.gradle

```groovy
include ':react-native-paypal-checkout'
project(':react-native-paypal-checkout').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-paypal-checkout/android')
```

If using RN 0.60+, edit android/src/.../MainActivity.java

```java
// ...
import com.dk.rn.paypal.PayPalPackage;
import android.content.Intent;

public class MainActivity extends ReactActivity {
    public PayPalPackage payPalPackage;
    // ...

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
       if (requestCode == 619) {
           payPalPackage.handleActivityResult(requestCode, resultCode, data);
       }
    }
}
````

4. Usage example:

```javascript

import { createPayment } from "react-native-paypal-checkout";
createPayment({
  clientId: 'AbyfNDFV53djg6w4yYgiug_JaDfBSUiYI7o6NM9HE1CQ_qk9XxbUX0nwcPXXQHaNAWYtDfphQtWB3q4R',
  environment: PayPal.SANDBOX,
  price: '42.00',
  currency: 'USD',
  description: 'PayPal Test'
}).then((confirm, payment) => console.log('Paid'); /* MUST verify payment in server*/)
.catch((error_code) => console.error('Failed to pay through PayPal'));
```

5. Callback parameters:

If all goes OK with the payment than the paymentRequest promise is resolved with
the following arguments as JSON strings:

- A confirm:

```json
{
  "client": {
    "environment": "mock",
    "paypal_sdk_version": "2.12.4",
    "platform": "Android",
    "product_name": "PayPal-Android-SDK"
  },
  "response": {
    "create_time": "2014-02-12T22:29:49Z",
    "id": "PAY-6RV70583SB702805EKEYSZ6Y",
    "intent": "sale",
    "state": "approved"
  },
  "response_type": "payment"
}
```

- A payment:

```json
{
  "amount": "1.00",
  "currency_code": "USD",
  "short_description": "PayPal Test",
  "intent": "sale"
}
```

Handling callbacks:

```javascript
import { createPayment } from "react-native-paypal-checkout";
createPayment(...).then(function (payment, confirm) {
  sendPaymentToConfirmInServer(payment, confirm);
})
```

If anything fails the promise will be notify an error with a code which will be
one of:

- USER_CANCELLED
- INVALID_CONFIG

Handling failures:

```javascript
import { createPayment } from "react-native-paypal-checkout";
createPayment(...).catch(function (error_code) {
    if (error_code == PayPal.USER_CANCELLED) {
        // User didn't complete the payment
    } else if (error_code == PayPal.INVALID_CONFIG) {
        // Invalid config was sent to PayPal
    }
})
```
