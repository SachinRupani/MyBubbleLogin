package com.jodhpurtechies.mybubblelogin.view

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.jodhpurtechies.mybubblelogin.R
import com.jodhpurtechies.mybubblelogin.utils.MyAnimations
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        init()

        handleClicks()

        startAnimating(viewBubble1)
    }

    private fun init() {
        etPassword.transformationMethod = PasswordTransformationMethod()
        hideAllViews()
    }

    private fun hideAllViews() {
        inpName.isInvisible = true
        inpEmail.isInvisible = true
        inpPhone.isInvisible = true
        inpPassword.isInvisible = true

        relBtnLogin.isInvisible = true
        relBtnSignUp.isInvisible = true

        viewBubble1.isInvisible = true
        viewBubble2.isInvisible = true
        viewBubble3.isInvisible = true
        viewBubble4.isInvisible = true
        viewBubble5.isInvisible = true
        viewBubble6.isInvisible = true
        relHeader.isInvisible = true
    }

    private fun startAnimating(view: View, durationMillis:Long=200L) {
        try {
            view.isVisible = true
            val anim: Animation?
            anim = when (view.id) {
                R.id.inpName, R.id.inpEmail,
                R.id.inpPhone, R.id.inpPassword,
                R.id.relBtnLogin, R.id.relBtnSignUp -> MyAnimations.scaleAnimate(durationMillis)

                else -> MyAnimations.overshootAnimate(durationMillis)
            }

            view.startAnimation(anim)

            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    when (view.id) {
                        R.id.viewBubble1 -> startAnimating(viewBubble2)
                        R.id.viewBubble2 -> startAnimating(viewBubble3)
                        R.id.viewBubble3 -> startAnimating(viewBubble4)
                        R.id.viewBubble4 -> startAnimating(viewBubble5)
                        R.id.viewBubble5 -> startAnimating(viewBubble6)
                        R.id.viewBubble6 -> startAnimating(inpName)

                        R.id.relBtnLogin->startAnimating(relHeader,600)
                    }
                }

                override fun onAnimationStart(p0: Animation?) {
                    when (view.id) {
                        R.id.inpName -> startAnimating(inpEmail,400)
                        R.id.inpEmail -> startAnimating(inpPhone,500)
                        R.id.inpPhone -> startAnimating(inpPassword,600)
                        R.id.inpPassword -> startAnimating(relBtnSignUp,700)
                        R.id.relBtnSignUp-> startAnimating(relBtnLogin,950)

                    }
                }

            })
        } catch (e: Exception) {
        }

    }

    private fun handleClicks() {
        relBtnLogin.setOnClickListener {
            onBackPressed()
        }

        relBtnSignUp.setOnClickListener {

        }
    }
}
