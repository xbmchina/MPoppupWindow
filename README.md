# MPoppupWindow
一个下拉选择框，使用PoppupWindow 和ListView, 特别注意两个带有点击事件的控件的布局混合的使用。


* Button或ImageButton等自带按钮功能的控件会抢夺所在Layout的焦点.导致其他区域点击不生效.在所在layout声明一个属性

   	 	android:descendantFocusability="blocksDescendants"
	
* popupwindow获取焦点, 外部可点击

			// 设置点击外部区域, 自动隐藏
		popupWindow.setOutsideTouchable(true); // 外部可触摸
		popupWindow.setBackgroundDrawable(new BitmapDrawable()); // 设置空的背景, 响应点击事件
		
		popupWindow.setFocusable(true); //设置可获取焦点
