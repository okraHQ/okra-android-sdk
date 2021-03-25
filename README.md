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

### Usage versions v2.1-beta and below
``` java
ArrayList products = new ArrayList<Enums.Product>();
products.add(Enums.Product.auth);
products.add(Enums.Product.transactions);
OkraOptions okraOptions = new OkraOptions(true, "c81f3e05-7a5c-5727-8d33-1113a3c7a5e4","5d8a35224d8113507c7521ac", products, Enums.Environment.sandbox,"Bassey");
Okra.create(MainActivity.this, okraOptions);
```

### Usage versions v2.2-beta and above
``` java
final Map<String, Object> guarantor = new HashMap<>();
        guarantor.put("status", true);
        guarantor.put("message","hello nurse");
        guarantor.put("number",3);

        Map<String, Object> dataMap  = new HashMap<String, Object>() {{
            put("products", new String[]{"auth", "balance", "identity", "transaction"});
            put("key", "public key");
            put("token", "client token");
            put("env", Enums.Environment.production_sandbox.toString());
            put("clientName", "Chris");
            put("color", "#953ab7");
            put("limit", "24");
            put("corporate", false);
            put("connectMessage", "Which account do you want to connect with?");
            put("guarantors", guarantor);
            put("callback_url", "");
            put("redirect_url", "");
            put("logo", "https://cdn.okra.ng/images/icon.svg");
            //put("filter", new Filter(Enums.IndustryType.all.toString(), banks));
            put("widget_success", "Your account was successfully linked to Okra, Inc");
            put("widget_failed", "Which account do you want to connect with?");
            put("currency", "NGN");
            put("exp", "2020-08-06");
            put("manual", false);
            put("success_title", "it has entered success");
            put("success_message", "this is the success message");
        }};

        Okra.create(MainActivity.this, dataMap);
```

#### OkraOptions

|Name                   | Type           | Required            | Default Value       | Description         |
|-----------------------|----------------|---------------------|---------------------|---------------------|
|  `isWebview `         | `boolean`      | true                |  true               | 
|  `key `               | `String`       | true                |  undefined          | Your public key from Okra.
|  `token`              | `String`       | true                |  undefined          | Your client token from okra.
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

## Data Dictionary

### Auth
Field | Required | Description
---|---|---
**id**<br>`ObjectID` | **Yes** | Unique Auth ID (Unique Okra Identifier)
**validated**<br>`Boolean` | **Yes** | Customer authentication status
**bank**<br>`ObjectID` | **Yes** | Unique Bank ID (Unique Okra Identifier)
**customer**<br>`ObjectID` | **Yes** | Unique Customer ID (Unique Okra Identifier)
**record**<br>`ObjectID` | **Yes** | Unique Record ID (Unique Okra Identifier)
**owner**<br>`ObjectID` | **Yes** | Unique Company ID (Unique Okra Identifier) (Your Client Token)
**created_at**<br>`Object` | **Yes** | Date Auth was fetched
**last_updated**<br>`Object` | **Yes** | Last Date Auth was fetched

### Balance
Field | Required | Description
---|---|---
**id**<br>`ObjectID` | **Yes** | Unique Balance ID (Unique Okra Identifier)
**available_balance**<br>`Integer` | **Yes** | Amount of available funds in account
**ledger_balance**<br>`Integer` | **Yes** | Closing balance of account
**currency**<br>`String` | **Yes** | The currency of the account
**connected**<br>`Boolean` | **Yes** | Customer connection status (Did they choose to connect this account to you)
**env**<br>`String` | **Yes** | Okra API Env the transaction was pulled from **production** or **production-sandbox**
**bank**<br>`ObjectID` | **Yes** | Unique Bank ID (Unique Okra Identifier)
**accounts**<br>`ObjectID` | **Yes** | Unique Account ID (Unique Okra Identifier)
**customer**<br>`ObjectID` | **Yes** | Unique Customer ID (Unique Okra Identifier)
**record**<br>`Array of ObjectID` | **Yes** | Unique Record ID (Unique Okra Identifier)
**created_at**<br>`Object` | **Yes** | Date Balance was fetched
**last_updated**<br>`Object` | **Yes** | Last Date Balance was fetched

### Identity
Field | Required | Description
---|---|---
**id**<br>`ObjectID` | **Yes** | Unique Identifier ID (Unique Okra Identifier)
**firstname**<br>`String` | **Yes** | Customer First Name
**middlename**<br>`String` | **Yes** | Customer Middle Name
**lastname**<br>`String` | **Yes** | Customer Last Name
**next_of_kins**<br>`Identity Object` | **Yes** | Customer Next of Kins
**dob**<br>`Date` | **Yes** | Customer Date of Birth
**verified**<br>`String` | **Yes** | BVN Validation status
**score**<br>`String` | **Yes** | Unique Okra Score
**dti**<br>`String` | **Yes** | Customer Debt to Income Score
**fullname**<br>`String` | **Yes** | Customer Fullname
**company_name**<br>`String` | **Yes | Company Name if Corporate Identity
**nin**<br>`String` | **Yes** | Customer NIN Number
**national_id**<br>`String` | **Yes** | Customer National ID Number
**drivers_lisence**<br>`String` | **Yes** | Customer Driver's License Number
**nimc**<br>`String` | **Yes** | Customer National Identity Management Commission (NIMC) Number
**voters_id**<br>`String` | **Yes** | Customer Voter's ID Number
**rc_number**<br>`String` | **Yes** | Company's Registered Company Number if Corporate Identity
**phone**<br>`Array of String` | **Yes** | Customer Phone Number
**last_login**<br>`String` | **Yes** | Customer Last Login via Okra
**email**<br>`Array of String` | **Yes** | Customer Email address
**address**<br>`Array of String` | **Yes** | Customer
**mothers_maiden**<br>`String` | **Yes** | Customer Mother's Maiden Name
**photo_ids**<br>`Array of Object` | **Yes** | Customer's photo ID
**env**<br>`String` | **Yes** | Okra API Env the transaction was pulled from **production** or **production-sandbox**
**bank**<br>`ObjectID` | **Yes** | Unique Bank ID (Unique Okra Identifier)
**accounts**<br>`ObjectID` | **Yes** | Unique Account ID (Unique Okra Identifier)
**customer**<br>`ObjectID` | **Yes** | Unique Customer ID (Unique Okra Identifier)
**record**<br>`Array of ObjectID` | **Yes** | Unique Record ID (Unique Okra Identifier)
**created_at**<br>`Object` | **Yes** | Date Balance was fetched
**last_updated**<br>`Object` | **Yes** | Last Date Balance was fetched

### Transaction
Field | Required | Description
---|---|---
**id**<br>`ObjectID` | **Yes** | Unique Transaction ID (Unique Okra Identifier)
**debit**<br>`Integer` | **No**| Amount deducted from account 
**credit**<br>`Integer` | **No**| Amount credited to account
**trans_date**<br>`Date` | **Yes** | Date transaction occurred
**cleared_date**<br>`Date` | **Yes** | Date transaction cleared at bank
**unformatted_trans_date**<br>`String` | **Yes** | Date transaction occurred (from bank)
**unformatted_cleared_date**<br>`String` | **Yes** | Date transaction cleared (from bank)
**branch**<br>`String` | **No**| Branch transactions occurred
**ref**<br>`String` | **No**| Bank reference ID (from bank)
**env**<br>`String` | **Yes** | Okra API Env the transaction was pulled from **production** or **production-sandbox**
**code**<br>`String` | **No**| Bank Code (from bank)
**benefactor**<br>`ObjectID` | **No**| Customer ID of sender (within Okra)
**code**<br>`String` | **No**| Bank Code (from bank)
**notes**<br>`Object` | **Yes** | Breakdown of Narrative from bank
**bank**<br>`ObjectID` | **Yes** | Unique Bank ID (Unique Okra Identifier)
**account**<br>`ObjectID` | **Yes** | Unique Account ID (Unique Okra Identifier)
**record**<br>`Array of ObjectID` | **Yes** | Unique Record ID (Unique Okra Identifier)
**created_at**<br>`Object` | **Yes** | Date transactions was fetched
**last_updated**<br>`Object` | **Yes** | Last Date transactions was fetched

### Notes Data Dictionary
Field | Required | Description
---|---|---
**desc**<br>`String` | **Yes** | Narrative / Description of transaction (combination of bank and user entered information)
**topics**<br>`Array of String` | **Yes** | Topics within the desc
**places**<br>`Array of String` | **Yes** | Places mentioned within the desc
**people**<br>`Array of String` | **Yes** | People mentioned within the desc
**actions**<br>`Array of String` | **Yes** | Actions mentioned within the desc
**subject**<br>`Array of String` | **Yes** | Subject of the desc
**preposition**<br>`Array of String` | **Yes** | Prepositions within desc to understand intent
