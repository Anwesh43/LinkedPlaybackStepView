package com.anwesh.uiprojects.playbackstepview

/**
 * Created by anweshmishra on 21/11/18.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.Color
import android.app.Activity
import android.content.Context

val nodes : Int = 5

val BACK_COLOR : Int = Color.parseColor("#212121")

val COLOR : Int = Color.parseColor("#EEEEEE")

val STROKE_FACTOR : Int = 60

val SIZE_FACTOR : Float = 3f

fun Int.getInverse() : Float = 1f / this

fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.getInverse(), Math.max(0f, this - i * n.getInverse())) * n

fun Canvas.drawPSNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = w / (nodes + 1)
    val size : Float = gap / SIZE_FACTOR
    val sc1 : Float = scale.divideScale(0, 3)
    val sc2 : Float = scale.divideScale(1, 3)
    val sc3 : Float = scale.divideScale(2, 3)
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / STROKE_FACTOR
    paint.color = COLOR
    save()
    translate(gap * (i + 1), h/2)
    rotate(-90f * sc3)
    drawLine(-size * sc1, size, size * sc1, size, paint)
    val x : Float = size * sc2
    val y : Float = -size + 2 * size * sc2
    val path = Path()
    path.moveTo(0f, size)
    path.lineTo(-x, y)
    path.lineTo(x, y)
    drawPath(path, paint)
    restore()
}

class PlaybackStepView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }

    data class State(var scale : Float = 0f, var dir : Float = 0f, var prevScale : Float = 0f) {

        fun update(cb : (Float) -> Unit) {
            scale += 0.05f * dir
            if (Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                cb(prevScale)
            }
        }

        fun startUpdating(cb : () -> Unit) {
            if (dir == 0f) {
                dir = 1f - 2 * prevScale
                cb()
            }
        }
    }
}