package com.akmaldev.tutorapp.presentation.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.akmaldev.tutorapp.R
import com.akmaldev.tutorapp.util.constant.AppConstants.EMPTY_STRING
import com.akmaldev.tutorapp.util.extension.dpToPx
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class PieChartView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.BUTT
    }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.getColor(R.color.black_custom)
        textSize = 32.dpToPx()
        textAlign = Paint.Align.CENTER
        typeface = ResourcesCompat.getFont(context, R.font.sf_pro_display_bold)
    }
    private val labelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.getColor(R.color.black_custom)
        textSize = 18.dpToPx()
        textAlign = Paint.Align.CENTER
        typeface = ResourcesCompat.getFont(context, R.font.sf_pro_display_medium)
    }
    private val labelBgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.getColor(R.color.light_gray_second)
        style = Paint.Style.FILL
    }

    private var segmentColors = emptyList<Int>()
    private var segmentValues = emptyList<Int>()
    private var segmentMargin = 0f
    private var centerText = EMPTY_STRING
    private var ringWidth = 56.dpToPx()
    private var cornerRadius = 8.dpToPx()
    private var showLabels = false

    fun setSegmentValues(values: List<Int>, colors: List<Int>) {
        segmentValues = values
        segmentColors = colors
        centerText = segmentValues.sum().toString()
        invalidate()
    }

    fun setShowLabels(show: Boolean) {
        showLabels = show
        invalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val size = min(width, height) * 0.8f
        val rectF = RectF(
            width / 2 - size / 2,
            height / 2 - size / 2,
            width / 2 + size / 2,
            height / 2 + size / 2
        )

        paint.strokeWidth = ringWidth

        val total = segmentValues.sum().toFloat()
        var startAngle = -90f
        val segmentData = mutableListOf<SegmentData>()

        for (i in segmentValues.indices) {
            val sweepAngle = (segmentValues[i] / total) * 360f - segmentMargin
            paint.color = segmentColors.getOrNull(i) ?: Color.GRAY
            paint.pathEffect = CornerPathEffect(cornerRadius)
            canvas.drawArc(rectF, startAngle, sweepAngle, false, paint)

            val midAngle = startAngle + sweepAngle / 2

            val labelRadius = if (sweepAngle < 30) size / 1.8f else size / 2.2f
            val labelX =
                width / 2 + labelRadius * cos(Math.toRadians(midAngle.toDouble())).toFloat()
            val labelY =
                height / 2 + labelRadius * sin(Math.toRadians(midAngle.toDouble())).toFloat()

            segmentData.add(SegmentData(segmentValues[i], labelX, labelY))

            startAngle += sweepAngle + segmentMargin
        }

        if (showLabels) {
            for (data in segmentData) {
                val textWidth = labelPaint.measureText(data.value.toString())
                val textHeight = labelPaint.textSize
                val paddingHorizontal = 16.dpToPx()
                val paddingVertical = 8.dpToPx()

                val bgRect = RectF(
                    data.labelX - textWidth / 2 - paddingHorizontal,
                    data.labelY - textHeight / 2 - paddingVertical,
                    data.labelX + textWidth / 2 + paddingHorizontal,
                    data.labelY + textHeight / 2 + paddingVertical
                )

                val arrowSize = 10.dpToPx()
                val arrowPath = Path().apply {
                    moveTo(data.labelX - arrowSize, data.labelY + textHeight / 2 + paddingVertical)
                    lineTo(data.labelX + arrowSize, data.labelY + textHeight / 2 + paddingVertical)
                    lineTo(data.labelX, data.labelY + textHeight / 2 + paddingVertical + arrowSize)
                    close()
                }

                canvas.drawRoundRect(bgRect, cornerRadius, cornerRadius, labelBgPaint)
                canvas.drawPath(arrowPath, labelBgPaint)
            }
        }

        if (showLabels) {
            for (data in segmentData) {
                canvas.drawText(
                    data.value.toString(),
                    data.labelX,
                    data.labelY + labelPaint.textSize / 3,
                    labelPaint
                )
            }
        }

        canvas.drawText(centerText, width / 2, height / 2 + textPaint.textSize / 3, textPaint)
    }

    data class SegmentData(val value: Int, val labelX: Float, val labelY: Float)
}