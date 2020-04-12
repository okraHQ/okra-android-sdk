# okra-android-sdk
This is an android library for implementing okra widget
### Get Started
This library would help you add Okra widget to you native android app in no time. All you need to do is ...
### Install
##### gradle

1. Add it in your root build.gradle at the end of repositories:

``` gradle
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
  //e.g implementation 'com.github.okraHQ:okra-android-sdk:v0.6-alpha'
 }
```

### Usage
``` java
ArrayList products = new ArrayList<Enums.Product>();
products.add(Enums.Product.auth);
products.add(Enums.Product.transactions);
OkraOptions okraOptions = new OkraOptions(true, "c81f3e05-7a5c-5727-8d33-1113a3c7a5e4","5d8a35224d8113507c7521ac", products, Enums.Environment.sandbox,"Bassey");
Okra.create(MainActivity.this, okraOptions);
```
#### OkraOptions

|Name                   | Type           | Required            | Default Value       | Description         |
|-----------------------|----------------|---------------------|---------------------|---------------------|
|  `isWebview `         | `boolean`      | true                |  true               | 
|  `key `               | `String`       | true                |  undefined          | Your public key from Okra.
|  `token`              | `String`       | true                |  undefined          | Your pubic Key from okra. Use test key for test mode and live key for live mode
|  `products`           | `ArrayList<Enums.Product>`| true     |  undefined          | The Okra products you want to use with the widget.
|  `env`                | `Enums.Environment`| true            |  undefined          | 
|  `clientName`         | `String`       | true                |  undefined          | Name of the customer using the widget on the application
|  `webhook`            | `String`       | true                |  undefined          | The Url that Okra send the client's data to.

### Getting the okra onSuccess and onError response.
Okra gives provision to access the response data on the mobile device. Okra wraps the response in the `OkraHandler` object and passes it back to the View which called it. 

``` java
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkraHandler okraHandler = (OkraHandler) getIntent().getSerializableExtra("okraHandler");
        String okraData = "";
        if(okraHandler != null){
            if(okraHandler.getIsDone() && (okraHandler.getIsSuccessful() || okraHandler.getHasError()) ) {
                okraData = okraHandler.getData();
            }
        }
    }
```

#### OkraHandler

|Name                   | Type           | Default Value   | Description         |
|-----------------------|----------------|-----------------|---------------------|
|  `isDone `            | `boolean`      |  false          | flag indicates if the okra process has finished
|  `isSuccessful `      | `boolean`      |  false          | flag indicates if the okra process was successful.
|  `hasError`           | `boolean`      |  false          | flag indicates if the okra process has an error.
|  `Data`               | `Json`         |  null           | this is the response that okra provides.

