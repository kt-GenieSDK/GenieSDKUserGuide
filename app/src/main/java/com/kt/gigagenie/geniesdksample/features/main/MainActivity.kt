package com.kt.gigagenie.geniesdksample.features.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.gson.JsonObject
import com.kt.gigagenie.geniesdk.GenieSdk
import com.kt.gigagenie.geniesdk.GenieSdkEventListener
import com.kt.gigagenie.geniesdk.data.model.Response
import com.kt.gigagenie.geniesdksample.R
import com.kt.gigagenie.geniesdksample.databinding.ActivityMainBinding
import com.kt.gigagenie.geniesdksample.features.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initGenieSdk()
        initSpinner()
        initListener()
        initObserver()
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_ESCAPE -> {
                finish()
            }
        }
        return super.onKeyUp(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy")
        GenieSdk.deinit()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        val uri = intent?.data
        when (uri?.path) {
            "/ShowAppinfo" -> {
                // AppInfoService
                binding.spinner.setSelection(0)
            }
            "/ShowVoice" -> {
                // AppInfoService
                binding.spinner.setSelection(1)
            }
            "/ShowTTS" -> {
                // AppInfoService
                binding.spinner.setSelection(2)
            }
            "/ShowPayment" -> {
                // PaymentService
                binding.spinner.setSelection(3)
            }
            "/ShowMedia" -> {
                // PaymentService
                binding.spinner.setSelection(4)
            }
            "/SearchIntent" -> {
                mainViewModel.setLogs("uri.appInfo: ${intent.getStringExtra("appInfo")}")
                mainViewModel.setLogs("result: ${parseSearchIntent(intent)}")
            }
        }
    }

    private fun initGenieSdk() {
        GenieSdk.init(
            applicationContext,
            "N5004402",
            "TjUwMDQ0MDJ8R0JPWERFVk18MTY0MzAxMDQxMDA1NQ==",
            true,
            object : GenieSdkEventListener {
                override fun callback(result: Response) {
                    Log.i("[GenieSDKSample]", "result: $result")
                }
            }
        )
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.function_array,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }
    }

    private fun initListener() {
        binding.spinner.onItemSelectedListener = this
        binding.clearButton.setOnClickListener {
            binding.logView.text = ""
        }
    }

    private fun initObserver() {
        mainViewModel.getLogs().observe(this) {
            binding.logView.append(it)
            binding.scrollView.post {
                binding.scrollView.fullScroll(View.FOCUS_DOWN)
                binding.scrollView.clearFocus()
            }
        }
    }

    private fun parseSearchIntent(intent: Intent): JsonObject {
        var targetString = intent.getStringExtra("appInfo")
        val splitString: List<String>
        val result = JsonObject()
        if (!targetString.isNullOrEmpty()) {
            targetString = targetString.replace("[{}]".toRegex(), "")
            splitString = targetString.split(":")
            result.addProperty(splitString[0], splitString[1])
        }
        return result
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        when (pos) {
            0 -> {
                // AppInfoService
                transaction.replace(binding.fragmentContainer.id, AppInfoServiceFragment())
                transaction.commit()
            }
            1 -> {
                // VoiceService
                transaction.replace(binding.fragmentContainer.id, VoiceServiceFragment())
                transaction.commit()
            }
            2 -> {
                // TTSService
                transaction.replace(binding.fragmentContainer.id, TTSServiceFragment())
                transaction.commit()
            }
            3 -> {
                // PaymentService
                transaction.replace(binding.fragmentContainer.id, PaymentServiceFragment())
                transaction.commit()
            }
            4 -> {
                // MediaService
                transaction.replace(binding.fragmentContainer.id, MediaServiceFragment())
                transaction.commit()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
    }
}