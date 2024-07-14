package com.android.library.shadowlayout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import com.android.library.shadowlayout.commons.android.Dimens.dp2Px
import com.android.library.shadowlayout.commons.android.Res.color
import kotlin.math.max

// A layout that support any type of shadow
class ShadowLayout : FrameLayout {

    private var showShadow = false
    private var showBorder = false

    private var shadowColor = color(R.color.black_percent_50)
    private var shadowDx = 0f
    private var shadowDy = 0f
    private var shadowRadius = dp2Px(5)

    private var cornerRadius = 0f
    private var topLeftRadius = 0f
    private var topRightRadius = 0f
    private var bottomLeftRadius = 0f
    private var bottomRightRadius = 0f

    private var borderColor = Color.BLACK
    private var borderWidth = dp2Px(1)

    private var fillColor = color(R.color.transparent)

    private val paint = Paint()
    private val strokePaint = Paint()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : this(context, attributeSet, defStyleAttr, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attributeSet, defStyleAttr, defStyleRes) {
        parseAttribute(attributeSet)
        setLayoutAndPaint()
    }

    private fun parseAttribute(attributeSet: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ShadowLayout)
        showShadow = typedArray.getBoolean(R.styleable.ShadowLayout_showShadow, showShadow)
        showBorder = typedArray.getBoolean(R.styleable.ShadowLayout_showBorder, showBorder)
        shadowColor = typedArray.getColor(R.styleable.ShadowLayout_shadowColor, shadowColor)
        shadowDx = typedArray.getDimension(R.styleable.ShadowLayout_shadowDx, shadowDx)
        shadowDy = typedArray.getDimension(R.styleable.ShadowLayout_shadowDy, shadowDy)
        shadowRadius = typedArray.getDimension(R.styleable.ShadowLayout_shadowRadius, shadowRadius)
        cornerRadius = typedArray.getDimension(R.styleable.ShadowLayout_cornerRadius, cornerRadius)
        topLeftRadius = cornerRadius
        topRightRadius = cornerRadius
        bottomRightRadius = cornerRadius
        bottomLeftRadius = cornerRadius
        borderColor = typedArray.getColor(R.styleable.ShadowLayout_borderColor, borderColor)
        borderWidth = typedArray.getDimension(R.styleable.ShadowLayout_borderWidth, borderWidth)
        fillColor = typedArray.getColor(R.styleable.ShadowLayout_fillColor, fillColor)
        if (typedArray.hasValue(R.styleable.ShadowLayout_topLeftRadius)) {
            topLeftRadius = typedArray.getDimension(R.styleable.ShadowLayout_topLeftRadius, topLeftRadius)
        }
        if (typedArray.hasValue(R.styleable.ShadowLayout_topRightRadius)) {
            topRightRadius = typedArray.getDimension(R.styleable.ShadowLayout_topRightRadius, topRightRadius)
        }
        if (typedArray.hasValue(R.styleable.ShadowLayout_bottomRightRadius)) {
            bottomRightRadius = typedArray.getDimension(R.styleable.ShadowLayout_bottomRightRadius, bottomRightRadius)
        }
        if (typedArray.hasValue(R.styleable.ShadowLayout_bottomLeftRadius)) {
            bottomLeftRadius = typedArray.getDimension(R.styleable.ShadowLayout_bottomLeftRadius, bottomLeftRadius)
        }
        typedArray.recycle()
    }

    private fun setLayoutAndPaint() {
        var paddingLeft = shadowRadius - shadowDx
        var paddingTop = shadowRadius - shadowDy
        var paddingRight = shadowRadius + shadowDx
        var paddingBottom = shadowRadius + shadowDy
        paddingLeft = max(paddingLeft, 0f)
        paddingTop = max(paddingTop, 0f)
        paddingRight = max(paddingRight, 0f)
        paddingBottom = max(paddingBottom, 0f)
        paint.style = Paint.Style.FILL
        paint.color = fillColor
        if (showShadow) {
            setWillNotDraw(false)
            paint.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor)
            setPadding(paddingLeft.toInt(), paddingTop.toInt(), paddingRight.toInt(), paddingBottom.toInt())
        } else {
            setWillNotDraw(true)
            setPadding(0, 0, 0, 0)
        }
        strokePaint.style = Paint.Style.STROKE
        strokePaint.color = borderColor
        strokePaint.strokeWidth = borderWidth
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val parent = parent as? ViewGroup
        parent?.let {
            it.clipChildren = false
            it.clipToPadding = false
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        if (showShadow) {
            drawRadius(canvas)
        }
        if (showBorder) {
            drawBorder(canvas)
        }
    }

    private fun createCornerRadius(): FloatArray {
        return floatArrayOf(
            topLeftRadius, topLeftRadius,
            topRightRadius, topRightRadius,
            bottomRightRadius, bottomRightRadius,
            bottomLeftRadius, bottomLeftRadius
        )
    }

    private fun createContentRect(): RectF {
        return RectF(
            paddingLeft.toFloat(),
            paddingTop.toFloat(),
            measuredWidth - paddingRight.toFloat(),
            measuredHeight - paddingBottom.toFloat()
        )
    }

    private fun drawRadius(canvas: Canvas) {
        val path = Path()
        path.addRoundRect(
            createContentRect(),
            createCornerRadius(),
            Path.Direction.CW
        )
        canvas.drawPath(path, paint)
    }

    private fun drawBorder(canvas: Canvas) {
        val rect = createContentRect()
        rect.inset(-borderWidth / 2, -borderWidth / 2)
        val path = Path()
        path.addRoundRect(rect, createCornerRadius(), Path.Direction.CW)
        canvas.drawPath(path, strokePaint)
    }

    fun showShadow(showShadow: Boolean) {
        this.showShadow = showShadow
        setLayoutAndPaint()
        invalidate()
    }

    fun setShadowRadius(shadowRadius: Number) {
        this.shadowRadius = shadowRadius.toFloat()
        setLayoutAndPaint()
        invalidate()
    }

    fun setCornerRadius(
        topLeftRadius: Number,
        topRightRadius: Number,
        bottomRightRadius: Number,
        bottomLeftRadius: Number
    ) {
        this.topLeftRadius = topLeftRadius.toFloat()
        this.topRightRadius = topRightRadius.toFloat()
        this.bottomRightRadius = bottomRightRadius.toFloat()
        this.bottomLeftRadius = bottomLeftRadius.toFloat()
        setLayoutAndPaint()
        invalidate()
    }

    fun showBorder(showBorder: Boolean) {
        this.showBorder = showBorder
        setLayoutAndPaint()
        invalidate()
    }
}