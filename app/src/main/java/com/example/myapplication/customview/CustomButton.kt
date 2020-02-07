package com.example.myapplication.customview

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.myapplication.R

class CustomButton(context: Context, attributes: AttributeSet) : Button(context, attributes) {
    private var borderDrawable: ShapeDrawable
    private var backgroundShape: ShapeDrawable

    init {
        val theme = context.theme.obtainStyledAttributes(attributes, R.styleable.CustomButton, 0, 0)
        val btnRadius =
            theme.getDimensionPixelSize(R.styleable.CustomButton_border_radius, 0).toFloat()
        val radius = floatArrayOf(
            btnRadius,
            btnRadius,
            btnRadius,
            btnRadius,
            btnRadius,
            btnRadius,
            btnRadius,
            btnRadius
        )

        borderDrawable = ShapeDrawable(RoundRectShape(radius, null, radius))
        val borderPadding = theme.getDimensionPixelSize(R.styleable.CustomButton_border_width, 0)
        val strBorderColor = theme.getString(R.styleable.CustomButton_border_color)
        if (strBorderColor != null) {
            val borderColor = strBorderColor
            borderDrawable.paint.apply {
                color = Color.parseColor(borderColor)
                style = Paint.Style.FILL
            }
        } else {
            val borderColor =
                theme.getInteger(R.styleable.CustomButton_border_color, R.color.colorPrimary)
            borderDrawable.paint.apply {
                color = ContextCompat.getColor(context, borderColor)
                style = Paint.Style.FILL
            }
        }

        borderDrawable.setPadding(borderPadding, borderPadding, borderPadding, borderPadding)

        backgroundShape = ShapeDrawable(RoundRectShape(radius, null, radius))
        val strBackgroundColor = theme.getString(R.styleable.CustomButton_background_color)

        if (strBackgroundColor != null) {
            val bgColor = Color.parseColor(strBackgroundColor)
            backgroundShape.paint.apply {
                color = bgColor
                style = Paint.Style.FILL
                isAntiAlias = true
            }
        } else {
            val bgColor = ContextCompat.getColor(
                context,
                theme.getInteger(R.styleable.CustomButton_background_color, R.color.colorPrimary)
            )
            backgroundShape.paint.apply {
                color = bgColor
                style = Paint.Style.FILL
                isAntiAlias = true
            }
        }

        val drawables = arrayOf(borderDrawable, backgroundShape)

        val backgroundPadding = resources.getDimensionPixelSize(R.dimen.btn_padding)

        backgroundShape.setPadding(
            backgroundPadding,
            backgroundPadding,
            backgroundPadding,
            backgroundPadding
        )

        val layerDrawable = LayerDrawable(drawables)

        background = layerDrawable
        setTextColor(ContextCompat.getColor(context, R.color.white))
    }

    fun setBorderColor(newColor: String) {
        borderDrawable.paint.apply {
            color = Color.parseColor(newColor)
        }
    }

    fun setBorderColor(newColor: Int) {
        borderDrawable.paint.apply {
            color = ContextCompat.getColor(context, newColor)
        }
    }

    fun setBorderWidth(width: Int) {
        try {
            val widthToPixel = resources.getDimensionPixelSize(width)

            borderDrawable.setPadding(widthToPixel, widthToPixel, widthToPixel, widthToPixel)
        } catch (e: Resources.NotFoundException) {
            val widthToPixel = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width.toFloat(), resources.displayMetrics).toInt()
            borderDrawable.setPadding(widthToPixel, widthToPixel, widthToPixel, widthToPixel)
        }
    }

    fun setBackgroundColor(newColor: String) {
        backgroundShape.paint.apply {
            color = Color.parseColor(newColor)
        }
    }

    override fun setBackgroundColor(newColor: Int) {
        backgroundShape.paint.apply {
            color = newColor
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
    }

}