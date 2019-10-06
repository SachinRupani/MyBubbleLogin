package com.jodhpurtechies.mylogin.view

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.jodhpurtechies.mylogin.R
import com.jodhpurtechies.mylogin.utils.MyAnimations
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()

        handleClicks()

        startAnimating(viewBubble1)
    }

    private fun init(){
        etPassword.transformationMethod=PasswordTransformationMethod()
        hideAllViews()
    }

    private fun hideAllViews() {
        inpUsername.isInvisible = true
        inpPassword.isInvisible = true
        relBtnLogin.isInvisible = true
        relBtnLoginGoogle.isInvisible = true
        relBtnLoginFacebook.isInvisible = true
        txtSignUp.isInvisible = true
        txtForgotPass.isInvisible = true

        viewBubble1.isInvisible = true
        viewBubble2.isInvisible = true
        viewBubble3.isInvisible = true
        viewBubble4.isInvisible = true
        viewBubble5.isInvisible = true
        viewBubble6.isInvisible = true
        relHeader.isInvisible = true

        viewSeparator.isInvisible = true
    }

    private fun handleClicks() {
        txtSignUp.setOnClickListener {
            startActivity(Intent(applicationContext, SignUpActivity::class.java))
        }
    }

    private fun startAnimating(viewBubble: View) {
        try {
            viewBubble.isVisible = true
            val anim: Animation?
            when (viewBubble.id) {
                R.id.relHeader -> anim = AnimationUtils.loadAnimation(
                    applicationContext,
                    R.anim.fade_in
                )
                else -> anim = MyAnimations.overshootAnimate(viewBubble)
            }

            viewBubble.startAnimation(anim)

            anim?.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    when (viewBubble.id) {
                        R.id.viewBubble1 -> startAnimating(viewBubble2)
                        R.id.viewBubble2 -> startAnimating(viewBubble3)
                        R.id.viewBubble3 -> startAnimating(viewBubble4)
                        R.id.viewBubble4 -> startAnimating(viewBubble5)
                        R.id.viewBubble5 -> startAnimating(viewBubble6)
                        R.id.viewBubble6 -> startAnimatingOthers(inpUsername,400)
                    }
                }

                override fun onAnimationStart(p0: Animation?) {

                }

            })
        } catch (e: Exception) {
        }

    }

    private fun startAnimatingOthers(view: View,durationMillis:Long=200L) {
        try {
            view.isVisible = true
            var anim: Animation? = null

            when (view.id) {
                R.id.inpUsername, R.id.inpPassword,
                R.id.txtForgotPass, R.id.relBtnLogin,
                R.id.relBtnLoginGoogle, R.id.relBtnLoginFacebook ->
                    anim = MyAnimations.scaleAnimate(view,durationMillis)

                R.id.txtSignUp, R.id.viewSeparator ->
                    anim = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)

                R.id.relHeader ->
                    anim=MyAnimations.overshootAnimate(view,durationMillis)

            }

            view.startAnimation(anim)

            anim?.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    when (view.id) {
                        R.id.relBtnLogin -> startAnimatingOthers(txtSignUp,500)
                        R.id.txtSignUp -> startAnimatingOthers(viewSeparator,400)

                        R.id.relBtnLoginFacebook -> startAnimatingOthers(relHeader,600)
                    }
                }

                override fun onAnimationStart(p0: Animation?) {
                    when (view.id) {
                        R.id.inpUsername -> startAnimatingOthers(inpPassword,500)
                        R.id.inpPassword -> startAnimatingOthers(txtForgotPass,600)
                        R.id.txtForgotPass -> startAnimatingOthers(relBtnLogin,700)

                        R.id.viewSeparator -> startAnimatingOthers(relBtnLoginGoogle,700)
                        R.id.relBtnLoginGoogle -> startAnimatingOthers(relBtnLoginFacebook,800)
                    }
                }

            })
        } catch (e: Exception) {
        }
    }


}
