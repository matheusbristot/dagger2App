package com.bristot.presentation.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bristot.presentation.R
import com.bristot.presentation.core.base.BaseActivity
import com.bristot.presentation.databinding.ActivityMainBinding
import com.bristot.presentation.extensions.load
import com.bristot.presentation.extensions.observe
import javax.inject.Inject

class MainActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    @Inject
    protected lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.run {
            lifecycle.addObserver(this)
            viewModel.onResumeLifeCycle.observe(this@MainActivity, ::onResumeText)
            viewModel.icon.observe(this@MainActivity, ::onGetDrawable)
        }
    }

    private fun onResumeText(text: String?) {
        text?.let { binding.firstTextView.text = text }
    }

    private fun onGetDrawable(drawable: Int?) {
        drawable?.let {
            binding.iconImageView.load(url = it, placeholderRes = R.drawable.ic_white_placeholder_24dp)
        }
    }
}
