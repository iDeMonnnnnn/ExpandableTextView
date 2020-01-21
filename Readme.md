### ExpandableTextView

按钮视图在右下方的可折叠TextView。


[文档](https://demon.blog.csdn.net/article/details/104060142)

### 效果
<img src="https://raw.githubusercontent.com/DeMonLiu623/ExpandableTextView/master/img/device-2020-01-21-105005.png" alt="xxx" height="500" width="300"><img src="https://raw.githubusercontent.com/DeMonLiu623/ExpandableTextView/master/img/device-2020-01-21-101650.png" alt="xxx" height="500" width="300">

### 使用

#### 添加依赖
```java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


```

```java
	dependencies {
	        implementation 'com.github.DeMonLiu623:ExpandableTextView:1.0'
	}
```

#### xml使用

```xml
<com.demon.expandablelibrary.ExpandableTextView
        android:id="@+id/expandTextView2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:padding="30dp"
        app:isExpand="true"
        app:showLines="4"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/expandTextView1"
        app:textColor="@color/colorPrimary"
        app:textSize="15sp" />
```


#### 属性说明

|属性|说明|
|--|--|
|text|文本内容|
|textColor|字体颜色|
|textSize|字体大小|
|expandDrawable|向下展开时的图标|
|collapseDrawable|向上折叠时的图标|
|showLines|折叠时显示的文本行数，默认3|
|isExpand|初始是否展开，默认false|


### 已知缺陷

>最后一行的内容刚好完全填满，而tvSecond由于右边drawableRight和drawablePadding的存在所以可能显示不全，只能加多一行显示。
>这时视觉上折叠按钮在最后两行的中间，而不是在最后一行。


### Bugs or Questions

1. issues
2. email:757454343@qq.com

### MIT License

```
MIT License

Copyright (c) 2020 DeMon

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```