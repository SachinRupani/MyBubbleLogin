package com.jodhpurtechies.mybubblelogin.utils

import android.view.animation.Animation
import android.view.animation.OvershootInterpolator
import android.view.animation.ScaleAnimation


object MyAnimations {
    fun scaleAnimate(durationMillis:Long=400L):ScaleAnimation{
        return ScaleAnimation(
            0f,
            1f,
            0f,
            1f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration=durationMillis
            fillAfter =true
        }
    }

    fun overshootAnimate(durationMillis:Long=200L):ScaleAnimation{
        return scaleAnimate(durationMillis).also {
            it.interpolator=OvershootInterpolator()
        }
    }


}