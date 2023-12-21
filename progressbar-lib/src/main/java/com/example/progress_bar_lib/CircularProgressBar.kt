package com.example.progress_bar_lib

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 *  Created by sushant shrestha on 2023/12/21
 **/

class CircularProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint()
    private val oval = RectF()
    private var progress : Float
    private var progressTint : Int
    private var maxProgress : Float
    private var progressDirection : Int
    private var sweepAngle : Float = 0f
    private var strokeWidth : Float = 0f
    private var strokeColor : Int

    init {

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CircularProgressBar,
            0, 0).apply {

            try {
                progress = getFloat(R.styleable.CircularProgressBar_progress, 0f)
                progressTint = getColor(R.styleable.CircularProgressBar_progressTint,Color.BLUE)
                strokeColor = getColor(R.styleable.CircularProgressBar_circleWidthTint,Color.GRAY)
                maxProgress = getFloat(R.styleable.CircularProgressBar_maxProgress,100f)
                progressDirection = getInteger(R.styleable.CircularProgressBar_progressDirection, CLOCK_WISE)
                strokeWidth = getFloat(R.styleable.CircularProgressBar_circleWidth,20f)
            } finally {
                recycle()
            }
        }

        initPaint()

    }

    private fun initPaint(){
        paint.color = progressTint
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = centerX.coerceAtMost(centerY) - paint.strokeWidth / 2

        // Reuse the RectF object
        oval.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)

        if (progressDirection == ANTI_CLOCK){
             sweepAngle = 360f - (360f * progress / maxProgress)
        }else if (progressDirection == CLOCK_WISE){
             sweepAngle = 360f * progress / maxProgress
        }


        // Draw the background circle
        paint.color = strokeColor
        paint.strokeWidth = strokeWidth
        canvas.drawCircle(centerX, centerY, radius, paint)

        // Draw the progress arc
        paint.color = progressTint
        canvas.drawArc(oval, -90f, sweepAngle, false, paint)
    }

    /**
     * Sets the tint color for the progress indicator.
     *
     * @param color The color to be applied as the tint for the progress indicator.
     *
     * The default color is Blue.
     */
    fun setProgressTint(color: Int){
        progressTint = color
        invalidate()
    }

    /**
     * Sets the progress of the custom circle view.
     *
     * @param progress The current progress value as a float.
     *
     * The default value is 0f.
     */
    fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
    }

    /**
     * Sets the tint color for the circle's stroke width.
     *
     * @param color The color to be applied as the tint for the circle's stroke width.
     *
     * The default color is Gray.
     */
    fun setCircleWidthTint(color: Int) {
        this.strokeColor = color
        invalidate()
    }

    /**
     * Sets the width of the circle's stroke.
     *
     * @param width The width of the circle's stroke as a float.
     *
     * The default value is 20f
     */
    fun setCircleWidth(width: Float) {
        this.strokeWidth = width
        invalidate()
    }

    /**
     * Sets the maximum progress value for the custom circle view.
     *
     * @param maxProgress The maximum progress value as a float.
     *
     * The default value is 100f.
     */
    fun setMaxProgress(maxProgress : Float) {
        this.maxProgress = maxProgress
        invalidate()
    }

    /**
     * Sets the direction of progress for the custom circle view.
     *
     * @param direction The direction of progress. Use one of the constants:
     *                  [CLOCK_WISE] for clockwise direction,
     *                  [ANTI_CLOCK] for anticlockwise direction.
     *
     * The default direction is clockwise.
     */
    fun setProgressDirection(direction : Int) {
        this.progressDirection = direction
        invalidate()
    }

    companion object{
        /**
         * Constant indicating a clockwise direction for progress.
         * Use this value with [setProgressDirection].
         */
        const val CLOCK_WISE = 0

        /**
         * Constant indicating an anticlockwise direction for progress.
         * Use this value with [setProgressDirection].
         */
        const val ANTI_CLOCK = 1
    }


}