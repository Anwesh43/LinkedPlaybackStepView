package com.anwesh.uiprojects.linkedplaybackstepview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.playbackstepview.PlaybackStepView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PlaybackStepView.create(this)
    }
}
