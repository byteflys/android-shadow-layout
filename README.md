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

```
# Preview

<img src="https://github.com/user-attachments/assets/4928f382-8180-47d4-af93-1697335bd197" height="250"><br>
<img src="https://github.com/user-attachments/assets/8d07cdc9-79da-43f5-8c38-59fba53cf0a5" height="250"><br>
<img src="https://github.com/user-attachments/assets/07d80c3d-aad8-42f5-ab16-f88e853c7fad" height="250">
