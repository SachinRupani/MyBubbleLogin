package com.jodhpurtechies.mylogin.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.OvershootInterpolator
import android.view.animation.ScaleAnimation


object MyAnimations {
    fun scaleAnimate(view: View, durationMillis:Long=400L):ScaleAnimation{
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

    fun overshootAnimate(view: View, durationMillis:Long=200L):ScaleAnimation{
        return scaleAnimate(view,durationMillis).also {
            it.interpolator=OvershootInterpolator()
        }
    }


}