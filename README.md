## TG Messenger for Android

[Telegram](https://telegram.org) is a messaging app with a focus on speed and security. It’s superfast, simple and free.

This is an unofficial GMS-Free fork of the [Telegram App for Android](https://play.google.com/store/apps/details?id=org.telegram.messenger).

### Changes

_Replacement of GMS and proprietary components_

* Location sharing and streaming with Google maps is replaced by OSMDroid
* Firebase messaging replaced with telegram foreground push service
* Document scanning (Mrz) via GMS vision is replaced with Zxing PDF
* Stripe payments are moved outside the app - to WebView

_Removal of GMS and proprietary components_

* Google SafetyNet and Integrity API removed
* GMS Billing, GMS Wallet and GMS wearable removed without keeping their functionality
* GMS Auth removed, so it can't detect SMS received
* GMS Cast framework and its functionality fully removed
* GMS Vision and MLkit image are removed, functionality with emoji clipping not preserved
* Mlkit language, language detection and translation are removed due to privacy concerns, they are sending data to google translate
* Google captcha removed, as its not possible to replace it

_Changes related to other dependencies_

* Outdated and unsupported googlecode.m4parser.isoparser is replaced with with org.m4parser
* Tonsite DNS is disabled due to untrusted DNS server in the middle
* Huawei, AppHockey and Standalone modules removed, crash reporting disabled

_Other changes_

* App_id is changed to app.tgmessenger.gmsfree
* Fixed known ui bugs in the forked telegram version
* Default cache size is limited to 5GB*
* Media file auto-download is disabled by default*
* Auto-play is disabled for gif's and videos*
* Applied patch #30513 TLS Fingerprint fixes in MTProxy ClientHello 

\* can be changed in settings

### API, Protocol documentation

Telegram API manuals: https://core.telegram.org/api

MTproto protocol manuals: https://core.telegram.org/mtproto

### Compilation Guide

You will require Android Studio 3.4, Android NDK rev. 20 and Android SDK 8.1

1. Copy your release.keystore into TMessagesProj/config
2. Fill out RELEASE_KEY_PASSWORD, RELEASE_KEY_ALIAS, RELEASE_STORE_PASSWORD in gradle.properties to access your  release.keystore
3. Open the project in the Studio (note that it should be opened, NOT imported).
4. Create an API_KEYS in project root and fill out your credentials
    ```agsl
    APP_ID = 12345
    APP_HASH= aaaaaabbbbbbccccccdddd1234
    ```
5. You are ready to compile Telegram.

### Localization

We moved all translations to https://translations.telegram.org/en/android/. Please use it.
