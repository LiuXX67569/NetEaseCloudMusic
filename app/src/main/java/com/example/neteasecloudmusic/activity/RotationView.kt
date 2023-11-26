package com.example.neteasecloudmusic.activity

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.os.Bundle
import com.example.neteasecloudmusic.R

class RotationView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {
    private var rotationDrawable: Drawable? = null
    private var rotateAnimation: RotateAnimation? = null
    private var rotationDegrees = 0f
    private var isAnimationPaused = false
    private var currentAnimationProgress = 0f

    init {
        rotationDrawable = context.getDrawable(R.drawable.testimg) // Replace with your rotation image resource
    }

    override fun setImageResource(resId: Int) {
        rotationDrawable = context.getDrawable(resId)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        val centerX = width / 2
        val centerY = height / 2
        //val radius = centerX.coerceAtMost(centerY).toFloat()
        val radius = Math.min(centerX, centerY).toFloat()

        val path = Path()
        path.addCircle(centerX.toFloat(), centerY.toFloat(), radius, Path.Direction.CW)
        canvas.clipPath(path)

        rotationDrawable?.let {
            it.setBounds(0, 0, width, height)
            it.draw(canvas)
        }
    }

    fun startRotation() {
         if (isAnimationPaused) {
            rotateAnimation = RotateAnimation(rotationDegrees, rotationDegrees + 360f, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f)
        } else {
            rotateAnimation = RotateAnimation(0f, 360f, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f)
        }
        rotateAnimation?.duration = 6000
        rotateAnimation?.repeatCount = RotateAnimation.INFINITE
        rotateAnimation?.interpolator = LinearInterpolator()
        startAnimation(rotateAnimation)
        isAnimationPaused = false
    }

    fun pauseRotation() {
        if (rotateAnimation != null) {
            clearAnimation()
            currentAnimationProgress = rotationDegrees / 360
            isAnimationPaused = true
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        pauseRotation()
    }

    // Save and restore instance state for rotationDegrees and isAnimationPaused
    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        bundle.putParcelable("superState", super.onSaveInstanceState())
        bundle.putFloat("rotationDegrees", rotationDegrees)
        bundle.putBoolean("isAnimationPaused", isAnimationPaused)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state is Bundle) {
            rotationDegrees = state.getFloat("rotationDegrees")
            isAnimationPaused = state.getBoolean("isAnimationPaused")
            super.onRestoreInstanceState(state.getParcelable("superState"))
        } else {
            super.onRestoreInstanceState(state)
        }
    }
}
