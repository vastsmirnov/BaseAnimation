package com.example.baseanimation

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.example.baseanimation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var translateOA: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            alphaMb.setOnClickListener(onAlphaClickListener())
            scaleMb.setOnClickListener(onScaleClickListener())
            translateMb.setOnClickListener(onTranslateClickListener())
            rotateMb.setOnClickListener(onRotateClickListener())
        }

        setContentView(binding.root)
    }

    private fun onAlphaClickListener(): View.OnClickListener {
        return View.OnClickListener {
            when (AnimationState.isAlphaStarted) {
                false -> {
                    val animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
                        .apply {
                            repeatMode = Animation.REVERSE
                            duration = 1000
                            repeatCount = Animation.INFINITE
                        }

                    binding.apply {
                        alphaMb.setBackgroundColor(resources.getColor(R.color.purple_700))
                        alphaMb.text = resources.getString(R.string.finish_alpha)
                        alphaIv.startAnimation(animation)
                    }

                    AnimationState.isAlphaStarted = true
                }
                true -> {
                    binding.apply {
                        alphaMb.text = resources.getString(R.string.start_alpha)
                        alphaMb.setBackgroundColor(resources.getColor(R.color.purple_500))
                        alphaIv.clearAnimation()
                    }

                    AnimationState.isAlphaStarted = false
                }
            }
        }
    }

    private fun onScaleClickListener(): View.OnClickListener {
        return View.OnClickListener {
            when (AnimationState.isScaleStarted) {
                false -> {
                    binding.apply {
                        scaleMb.setBackgroundColor(resources.getColor(R.color.purple_700))
                        scaleMb.text = resources.getString(R.string.finish_scale)
                        scaleIv.animate().apply {
                            scaleX(1.1F)
                            scaleY(1.1F)
                            duration = 1500
                        }
                    }

                    AnimationState.isScaleStarted = true
                }
                true -> {
                    binding.apply {
                        scaleMb.text = resources.getString(R.string.start_scale)
                        scaleMb.setBackgroundColor(resources.getColor(R.color.purple_500))
                        scaleIv.animate().apply {
                            scaleX(1.0F)
                            scaleY(1.0F)
                            duration = 1500
                        }
                    }

                    AnimationState.isScaleStarted = false
                }
            }
        }
    }

    private fun onTranslateClickListener(): View.OnClickListener {
        return View.OnClickListener {
            when (AnimationState.isTranslateStarted) {
                false -> {
                    binding.translateMb.apply {
                        text = resources.getString(R.string.pause_translate)
                        setBackgroundColor(resources.getColor(R.color.purple_700))
                    }

                    if (translateOA == null) {
                        translateOA =
                            ObjectAnimator.ofFloat(binding.translateIv, "translationX", 50.0F)
                                .apply {
                                    duration = 500
                                    repeatCount = ValueAnimator.INFINITE
                                    repeatMode = ValueAnimator.REVERSE
                                    start()
                                }

                        return@OnClickListener
                    }

                    translateOA?.resume()

                    AnimationState.isTranslateStarted = true
                }
                true -> {
                    binding.translateMb.apply {
                        text = resources.getString(R.string.start_translate)
                        setBackgroundColor(resources.getColor(R.color.purple_500))
                    }
                    translateOA?.pause()

                    AnimationState.isTranslateStarted = false
                }
            }

        }
    }

    private fun onRotateClickListener(): View.OnClickListener {
        return View.OnClickListener {
            when (AnimationState.isRotateStarted) {
                false -> {
                    binding.rotateMb.apply {
                        text = resources.getString(R.string.finish_rotate)
                        setBackgroundColor(resources.getColor(R.color.purple_700))
                    }

                    val animation = RotateAnimation(
                        0f,
                        360f,
                        Animation.RELATIVE_TO_SELF,
                        .5f,
                        Animation.RELATIVE_TO_SELF,
                        .5f
                    )

                    animation.repeatMode = Animation.RESTART
                    animation.repeatCount = Animation.INFINITE
                    animation.interpolator = LinearInterpolator()
                    animation.duration = 1000L

                    binding.rotateIv.startAnimation(animation)
                    AnimationState.isRotateStarted = true
                }
                true -> {
                    binding.rotateMb.apply {
                        text = resources.getString(R.string.start_rotate)
                        setBackgroundColor(resources.getColor(R.color.purple_500))
                    }

                    binding.rotateIv.clearAnimation()

                    AnimationState.isRotateStarted = false
                }
            }
        }
    }
}