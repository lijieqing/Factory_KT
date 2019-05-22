package com.fengmi.factory_kt.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.fengmi.factory_kt.R
import com.fengmi.factory_kt.utils.DisplayUtils

class InfoTagView : View {
    private val TAG = "InfoTagView"
    /**
     * Tag Name Text Size
     */
    private var mTagNameSize = DisplayUtils.sp2px(context, 10.toFloat())
    /**
     * Tag Value Text Size
     */
    private var mTagValueSize = DisplayUtils.sp2px(context, 10.toFloat())
    /**
     * Tag Name Text Color
     */
    private var mTagNameColor = Color.BLACK
    /**
     * Tag Value Text Color
     */
    private var mTagValueColor = Color.BLACK
    /**
     * Tag Background Color
     */
    private var mTagBackgroundColor = Color.LTGRAY
    /**
     * Tag Name Text
     */
    private var mTagName: String? = null
    /**
     * Tag Value Text
     */
    private var mTagValue: String? = null

    private var mWidth: Int = 0
    private var mHeight: Int = 0

    private var mTXPaint: Paint? = null
    private var mBGPaint: Paint? = null

    private var mMargin: Int = 3
    private var mTXMargin: Int = 3

    private var mConner: Float = 8f


    constructor(context: Context) : super(context) {
        initTag(context, null, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initTag(context, attrs, null)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initTag(context, attrs, defStyleAttr)
    }

    fun initTag(context: Context, attrs: AttributeSet?, defStyleAttr: Int?) {
        mBGPaint = Paint()
        mTXPaint = Paint()
        mTXPaint!!.isAntiAlias = true
        if (attrs != null) {
            val typeArray = context.obtainStyledAttributes(attrs, R.styleable.InfoTagView)
            mTagName = typeArray.getString(R.styleable.InfoTagView_info_tag_name)
            mTagNameSize = typeArray.getDimension(
                R.styleable.InfoTagView_info_tag_name_size,
                DisplayUtils.sp2px(context, 10.toFloat())
            )
            mTagNameColor = typeArray.getColor(R.styleable.InfoTagView_info_tag_name_color, Color.BLACK)
            mTagValue = typeArray.getString(R.styleable.InfoTagView_info_tag_value)
            mTagValueColor = typeArray.getColor(R.styleable.InfoTagView_info_tag_value_color, Color.BLACK)
            mTagValueSize = typeArray.getDimension(
                R.styleable.InfoTagView_info_tag_value_size,
                DisplayUtils.sp2px(context, 10.toFloat())
            )
            mConner = typeArray.getFloat(R.styleable.InfoTagView_info_tag_bg_conner, 3.toFloat())
            typeArray.recycle()
        } else {
            mTagNameSize = 10.toFloat()
            mTagValueSize = 10.toFloat()
            mTagNameColor = Color.BLACK
            mTagValueColor = Color.BLACK
            mConner = 3.toFloat()
        }

        if (mTagName == null) {
            mTagName = "Version SDK"
        }
        if (mTagValue == null) {
            mTagValue = "V-ENG-2018-9"
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measureW(widthMeasureSpec), measureH(heightMeasureSpec))
    }
    override fun onDraw(canvas: Canvas) {

        val coreWidth = mWidth - (2 * mMargin)
        val coreHeight = mHeight - (2 * mMargin)

        val tagNameWidth = coreWidth * 0.39
        var tagValueWidth = coreWidth * 0.6
        val tagSplitWidth = coreWidth * 0.01

        mBGPaint!!.color = mTagBackgroundColor
        canvas.drawRoundRect(
            mMargin.toFloat(), mMargin.toFloat(),
            (mMargin + tagNameWidth).toFloat(),
            (mMargin + coreHeight).toFloat(),
            mConner, mConner,
            mBGPaint
        )
        canvas.drawRoundRect(
            (mMargin + tagNameWidth + tagSplitWidth).toFloat(),
            mMargin.toFloat(),
            (mWidth - mMargin).toFloat(),
            (mMargin + coreHeight).toFloat(),
            mConner, mConner,
            mBGPaint
        )
        //draw Tag Name Text
        mTXPaint!!.textSize = mTagNameSize
        var temp = Rect()
        mTXPaint!!.getTextBounds(mTagName, 0, mTagName!!.length, temp)
        var tagNameW = temp.width()
        var tagNameH = temp.height()
        mTXPaint!!.color = mTagNameColor
        var tagNameX = mMargin + (Math.abs(tagNameWidth - tagNameW) / 2)
        var tagNameY = (mHeight.toFloat() / 2) + (tagNameH.toFloat() / 2)
        Log.d(TAG, "Text x=$tagNameX, y=$tagNameY")
        canvas.drawText(
            mTagName,
            tagNameX.toFloat(),
            tagNameY.toFloat(),
            mTXPaint
        )
        //draw Tag Value Text
        mTXPaint!!.textSize = mTagValueSize
        mTXPaint!!.getTextBounds(mTagValue, 0, mTagValue!!.length, temp)
        tagNameW = temp.width()
        tagNameH = temp.height()
        tagNameX = mMargin + tagNameWidth + tagSplitWidth + (Math.abs(tagValueWidth - tagNameW) / 2)
        tagNameY = (mHeight.toFloat() / 2) + (tagNameH.toFloat() / 2)
        Log.d(TAG, "Text x=$tagNameX, y=$tagNameY")
        canvas.drawText(
            mTagValue,
            tagNameX.toFloat(),
            tagNameY.toFloat(),
            mTXPaint
        )
    }

    fun setTagName(name: String) {
        mTagName = name
    }

    fun setTagValue(value: String?) {
        if (value == null) {
            mTagValue = "null"
        } else {
            mTagValue = value
        }
    }

    private fun measureW(widthMeasureSpec: Int): Int {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val defaultWidth: Float
        defaultWidth = when (widthMode) {
            MeasureSpec.AT_MOST -> {
                Log.d(TAG, "Mode is AT_MOST,Size is $widthSize")
                DisplayUtils.sp2px(context, 150.toFloat())
            }
            MeasureSpec.EXACTLY -> {
                Log.d(TAG, "Mode is EXACTLY,Size is $widthSize")
                widthSize.toFloat()
            }
            else -> {
                Log.d(TAG, "Mode is $widthMode,Size is $widthSize")
                150.toFloat()
            }
        }

        return defaultWidth.toInt()
    }

    private fun measureH(heightMeasureSpec: Int): Int {
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val defaultHeight: Float
        defaultHeight = when (heightMode) {
            MeasureSpec.AT_MOST -> {
                Log.d(TAG, "Mode is AT_MOST,Size is $heightSize")
                DisplayUtils.sp2px(context, 25.toFloat())
            }
            MeasureSpec.EXACTLY -> {
                Log.d(TAG, "Mode is EXACTLY,Size is $heightSize")
                heightSize.toFloat()
            }
            else -> {
                Log.d(TAG, "Mode is ELSE,Size is $heightSize")
                DisplayUtils.sp2px(context, 25.toFloat())
            }
        }

        return defaultHeight.toInt()
    }

}
