package com.android.library.shadowlayout.commons.android

import android.content.Context
import android.view.View

object Res {

    inline fun Context.color(colorResId: Int) = resources.getColor(colorResId, null)

    inline fun Context.drawable(drawableResId: Int) = resources.getDrawable(drawableResId, null)

    inline fun Context.dimen(dimenResId: Int) = resources.getDimension(dimenResId)

    inline fun Context.string(stringResId: Int) = resources.getString(stringResId)

    inline fun View.color(colorResId: Int) = context.color(colorResId)

    inline fun View.drawable(drawableResId: Int) = context.drawable(drawableResId)

    inline fun View.dimen(dimenResId: Int) = context.dimen(dimenResId)

    inline fun View.string(stringResId: Int) = context.string(stringResId)
}