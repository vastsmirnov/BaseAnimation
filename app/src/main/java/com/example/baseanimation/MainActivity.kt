package com.example.baseanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.baseanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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
            Toast.makeText(this, "Alpha", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onScaleClickListener(): View.OnClickListener {
        return View.OnClickListener {
            Toast.makeText(this, "Scale", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onTranslateClickListener(): View.OnClickListener {
        return View.OnClickListener {
            Toast.makeText(this, "Translate", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onRotateClickListener(): View.OnClickListener {
        return View.OnClickListener {
            Toast.makeText(this, "Rotate", Toast.LENGTH_SHORT).show()
        }
    }
}