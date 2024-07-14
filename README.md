# About this Project

An android layout that support all kinds of shadows

# Core Ability

- support shadow color, blur radius, location offset
- support shadow inner stroke border
- support shadow corners
- support shadow inner fill color

# Steps for Integration

#### 1. Dependency

```kotlin
api("io.github.hellogoogle2000:android-shadow-layout:1.0.0")
```

#### 2. Apply in Xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical">

    <com.android.library.shadowlayout.ShadowLayout
        android:id="@+id/flowLayout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:showShadow="true">

        <View
            android:layout_width="200dp"
            android:layout_height="200dp" />
    </com.android.library.shadowlayout.ShadowLayout>
</LinearLayout>
```

#### 3. Supported Attributes

- showShadow, Boolean
- showBorder, Boolean
- shadowRadius, Dimen
- shadowColor, Color
- shadowDx, Dimen
- shadowDy, Dimen
- cornerRadius, Dimen
- topLeftRadius topRightRadius bottomLeftRadius bottomRightRadius
- borderColor, Color
- borderWidth, Dimen
- fillColor, Color

# 4. Preview

<img src="https://github.com/user-attachments/assets/21343e5d-8f8f-43ef-b9f8-1782b56d8802" height="250"><br>
<img src="https://github.com/user-attachments/assets/5e4de4a1-74e8-4ccd-bc99-01d3002718a9" height="250"><br>
<img src="https://github.com/user-attachments/assets/6867cef0-c8a3-489e-8dbc-31f4a12e9e58" height="250"><br>
<img src="https://github.com/user-attachments/assets/6ae1d652-cbed-47b2-a236-c66628dff6bd" height="250"><br>