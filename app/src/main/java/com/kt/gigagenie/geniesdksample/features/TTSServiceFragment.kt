package com.kt.gigagenie.geniesdksample.features

import androidx.fragment.app.activityViewModels
import com.kt.gigagenie.geniesdk.GenieSdkEventListener
import com.kt.gigagenie.geniesdk.data.model.Response
import com.kt.gigagenie.geniesdk.service.TTSService
import com.kt.gigagenie.geniesdksample.base.BaseFragment
import com.kt.gigagenie.geniesdksample.R
import com.kt.gigagenie.geniesdksample.databinding.FragmentTtsServiceBinding
import com.kt.gigagenie.geniesdksample.features.main.MainViewModel

class TTSServiceFragment :
    BaseFragment<FragmentTtsServiceBinding>(R.layout.fragment_tts_service) {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun initViews() {
        super.initViews()
        mainViewModel.setLogs("[ TTSServiceFragment ] ==> initViews() ")
    }

    override fun initListeners() {
        super.initListeners()
        binding.sendTTSButton.setOnClickListener {
            TTSService.instance.sendTTS(
                "TTS를 재생할 수 있습니다. 중복 요청은 두 건까지 허용되며, 초과된 요청은 에러 코드 502번과 함께 무시됩니다.",
                TTSService.LANGUAGE.KOREAN,
                object : GenieSdkEventListener {
                    override fun callback(result: Response) {
                        mainViewModel.apply {
                            setLogs("--- ###### [ sendTTS ] ###### ---")
                            setLogs("* resultCode: ${result.resultCode}\n* resultMsg: ${result.resultMsg}\n* extra: ${result.extra}")
                            setLogs("==========================================")
                        }
                        it.post {
                            it.requestFocus()
                        }
                    }
                })
        }
        binding.stopTTSButton.setOnClickListener {
            TTSService.instance.stopTTS(listener = object : GenieSdkEventListener {
                override fun callback(result: Response) {
                    mainViewModel.apply {
                        setLogs("--- ###### [ stopTTS ] ###### ---")
                        setLogs("* resultCode: ${result.resultCode}\n* resultMsg: ${result.resultMsg}\n* extra: ${result.extra}")
                        setLogs("==========================================")
                    }
                    it.post {
                        it.requestFocus()
                    }
                }
            })
        }
    }
}