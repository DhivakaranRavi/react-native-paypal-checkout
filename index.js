("use strict");

var { PayPalModule } = require("react-native").NativeModules;

var constants = {};
var constantNames = Object.keys(PayPalModule).filter(p => p == p.toUpperCase());
constantNames.forEach(c => (constants[c] = PayPalModule[c]));

var functions = {
  paymentRequest(payPalParameters) {
    return new Promise(function(resolve, reject) {
      PayPalModule.paymentRequest(payPalParameters, resolve, reject);
    });
  }
};

var exported = {};
Object.assign(exported, constants, functions);

module.exports = exported;
