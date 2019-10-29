# okra-android-sdk
This is an android library for implementing okra widget
### Get Started
This library would help you add Okra widget to you native android app in no time. All you need to do is ...
### Install
##### gradle

1. Add it in your root build.gradle at the end of repositories:

```
allprojects {
  repositories {
   ...
   maven { url 'https://jitpack.io' }
  }
}

```

2. Add the dependency:

```
dependencies {
  implementation 'com.github.okraHQ:okra-android-sdk:Tag'
 }
```

### Usage
```html
<!-- The text attribute can also take custom html, but does not compile directives yet-->
<!-- The beforepopup attribute is not required. But, if it's added then it must return true for the payment dialog to open-->
<paystack-pay-button
        class="yellow"
        text="<small><b>Pay</b> Me Now!</small>"
        email="$scope.email"
        amount="$scope.amount"
        reference="$scope.reference"
        beforepopup="$scope.beforePopUp"
        metadata="$scope.metadata"
        callback="$scope.callback"
        close="$scope.close">
</paystack-pay-button>
```
