package com.demon.expandablelibrary

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView


/**
 * @author DeMon
 * Created on 2020/1/20.
 * Desc: drawableRight可点击的TextView
 */
class ClickRightTextView : AppCompatTextView {

    constructor(context: Context?) : super(context) {
        ClickRightTextView(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    interface OnClickRightClickListener {
        fun onClickRightClick()
    }

    private var onClickRightListener: OnClickRightClickListener? = null

    fun setOnClickRightListener(onClickRightListener: OnClickRightClickListener?) {
        this.onClickRightListener = onClickRightListener
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            val drawableRight: Drawable = compoundDrawables[2]
            //本次点击事件的x轴坐标，如果>当前控件宽度-控件右间距-drawable实际展示大小
            if (event.x >= width - paddingRight - drawableRight.intrinsicWidth) {
                //设置点击EditText右侧图标
                if (onClickRightListener != null) {
                    onClickRightListener!!.onClickRightClick()
                }
            }
        }
        return super.onTouchEvent(event)
    }
}