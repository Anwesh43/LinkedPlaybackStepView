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

fun Int.getInverse() : Float = 1f / this

fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.getInverse(), Math.max(0f, this - i * n.getInverse())) * n