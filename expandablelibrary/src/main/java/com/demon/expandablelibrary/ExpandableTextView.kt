package com.demon.expandablelibrary

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.widget_expand_textview.view.*

/**
 * @author DeMon
 * Created on 2020/1/20.
 * Desc: 可折叠的TextView
 */
class ExpandableTextView : ConstraintLayout {


    private lateinit var mContext: Context
    private var textLines = 0
    private var secondTextLineStr = ""
    private var lastLineStr = ""  //最后一行的内容

    private var showLines = 3
    private var text: String = ""
    private var textColor = 0
    private var textSize = 0f
    private var isExpand = false
    private var expandDrawable: Drawable? = null
    private var collapseDrawable: Drawable? = null


    private var listener: OnExpandListener? = null

    constructor(context: Context) : super(context) {
        ExpandableTextView(context, null)
    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        this.mContext = context
        LayoutInflater.from(context).inflate(R.layout.widget_expand_textview, this)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView)

        text = typedArray.getString(R.styleable.ExpandableTextView_text) ?: ""
        textColor = typedArray.getColor(R.styleable.ExpandableTextView_textColor, ContextCompat.getColor(mContext, R.color.color666666))
        setTextColor(textColor)
        textSize = typedArray.getDimension(R.styleable.ExpandableTextView_textSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16f, resources.displayMetrics))
        setTextSize(textSize)
        showLines = typedArray.getInteger(R.styleable.ExpandableTextView_showLines, 3)
        if (showLines < 1) {
            showLines = 1
        }
        isExpand = typedArray.getBoolean(R.styleable.ExpandableTextView_isExpand, false)

        expandDrawable = typedArray.getDrawable(R.styleable.ExpandableTextView_expandDrawable)
        collapseDrawable = typedArray.getDrawable(R.styleable.ExpandableTextView_collapseDrawable)

        if (expandDrawable == null) {
            expandDrawable = ContextCompat.getDrawable(context, R.mipmap.icon_expand)
        }
        expandDrawable?.run {
            setBounds(0, 0, expandDrawable!!.intrinsicWidth, expandDrawable!!.intrinsicHeight)
        }
        if (collapseDrawable == null) {
            collapseDrawable = ContextCompat.getDrawable(context, R.mipmap.icon_collapse)
        }
        collapseDrawable?.run {
            setBounds(0, 0, collapseDrawable!!.intrinsicWidth, collapseDrawable!!.intrinsicHeight)
        }

        setText(text)
        setExpand(isExpand)

        tvSecond.setOnClickRightListener(object : ClickRightTextView.OnClickRightClickListener {
            override fun onClickRightClick() {
                setExpand(!isExpand)
            }
        })


        typedArray.recycle()

    }


    /**
     * 计算行数
     */
    private fun initLines() {
        //将文本内容全部给tvFirst，方便后面计算行数，获取每行的文本内容
        tvFirst.text = text
        //tvFirst绘制时监听
        tvFirst.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                tvFirst.viewTreeObserver.removeOnPreDrawListener(this)
                //绘制时获取tvFirst实际总行数，直接获取返回的是0
                textLines = tvFirst.lineCount
                if (textLines <= showLines) {
                    //tvSecond隐藏，只显示tvFirst
                    tvSecond.visibility = View.GONE
                    tvFirst.setLines(tvFirst.lineCount)
                } else {
                    //获取tvFirst显示布局，可根据改布局获取文本中每行的开始位置和结束位置
                    val layout = tvFirst.layout
                    if (isExpand) {
                        //获取展开最后一行的内容，展开时给tvSecond显示
                        lastLineStr = text.substring(layout.getLineStart(textLines - 1), layout.getLineEnd(textLines - 1))
                        //设置tvFirst的行数=最大行数-1
                        tvFirst.setLines(textLines - 1)
                        //设置tvSecond的行数控制其显示内容，此处设置为maxLines=2是因为可能存在：
                        //最后一行的内容刚好完全填满，而tvSecond由于右边drawableRight和drawablePadding的存在所以可能显示不全，只能加多一行显示。
                        tvSecond.maxLines = 2
                        tvSecond.text = lastLineStr
                    } else {
                        //设置tvFirst的行数=折叠时显示的行数-1
                        tvFirst.setLines(showLines - 1)
                        val start = layout.getLineStart(showLines - 1)
                        val end = layout.getLineEnd(showLines - 1)
                        secondTextLineStr = text.substring(start, end)
                        //获取折叠时最后一行的内容，给tvSecond显示
                        tvSecond.maxLines = 1
                        tvSecond.text = secondTextLineStr
                    }
                }
                return false
            }
        })
    }


    fun setTextColor(textColor: Int) {
        this.textColor = textColor
        tvFirst.setTextColor(textColor)
        tvSecond.setTextColor(textColor)
    }

    fun setTextSize(textSize: Float) {
        this.textSize = textSize
        tvFirst.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        tvSecond.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
    }


    fun setText(string: String) {
        this.text = string
        initLines()
    }

    fun setShowLines(lines: Int) {
        if (lines < 1) {
            return
        }
        this.showLines = lines
        initLines()
    }


    fun setExpand(isExpand: Boolean) {
        if (isExpand) {
            tvSecond.setCompoundDrawables(null, null, collapseDrawable, null)
        } else {
            tvSecond.setCompoundDrawables(null, null, expandDrawable, null)
        }
        if (this.isExpand == isExpand || textLines <= showLines) return
        listener?.onExpandChange(isExpand)
        this.isExpand = isExpand
        initLines()
    }


    interface OnExpandListener {
        fun onExpandChange(isExpand: Boolean)
    }


}