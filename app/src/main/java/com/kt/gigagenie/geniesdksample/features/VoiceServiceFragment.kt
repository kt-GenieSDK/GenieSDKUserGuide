package com.kt.gigagenie.geniesdksample.features

import androidx.fragment.app.activityViewModels
import com.kt.gigagenie.geniesdk.GenieSdkEventListener
import com.kt.gigagenie.geniesdk.data.model.Response
import com.kt.gigagenie.geniesdk.service.VoiceService
import com.kt.gigagenie.geniesdksample.base.BaseFragment
import com.kt.gigagenie.geniesdksample.R
import com.kt.gigagenie.geniesdksample.databinding.FragmentVoiceServiceBinding
import com.kt.gigagenie.geniesdksample.features.main.MainViewModel

class VoiceServiceFragment :
    BaseFragment<FragmentVoiceServiceBinding>(R.layout.fragment_voice_service) {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun initViews() {
        super.initViews()
        mainViewModel.setLogs("[ VoiceServiceFragment ] ==> initViews() ")
    }

    override fun initListeners() {
        super.initListeners()
        binding.getVoiceTextButton.setOnClickListener {
            VoiceService.instance.getVoiceText(listener = object : GenieSdkEventListener {
                override fun callback(result: Response) {
                    mainViewModel.apply {
                        setLogs("--- ###### [ getVoiceText ] ###### ---")
                        setLogs("* resultCode: ${result.resultCode}\n* resultMsg: ${result.resultMsg}\n* extra: ${result.extra}")
                        setLogs("==========================================")
                    }
                    it.post {
                        it.requestFocus()
                    }
                }
            })
        }
        binding.stopVoiceRecognitionButton.setOnClickListener {
            VoiceService.instance.stopVoiceRecognition(object : GenieSdkEventListener {
                override fun callback(result: Response) {
                    mainViewModel.apply {
                        setLogs("--- ###### [ stopVoiceRecognition ] ###### ---")
                        setLogs("* resultCode: ${result.resultCode}\n* resultMsg: ${result.resultMsg}\n* extra: ${result.extra}")
                        setLogs("==========================================")
                    }
                    it.post {
                        it.requestFocus()
                    }
                }
            })
        }
        binding.showConfirmDialogWithTTSButton.setOnClickListener {
            VoiceService.instance.showConfirmDialog(
                "띄어쓰기 포함 144자까지 지원합니다. 보여지는 메시지와 재생되는 메시지 개별 설정 가능합니다. 확인/취소 응답을 전달받을 수 있습니다.",
                "띄어쓰기 포함 144자까지 지원합니다.",
                object : GenieSdkEventListener {
                    override fun callback(result: Response) {
                        mainViewModel.apply {
                            setLogs("--- ###### [ showConfirmDialog with TTS ] ###### ---")
                            setLogs("* resultCode: ${result.resultCode}\n* resultMsg: ${result.resultMsg}\n* extra: ${result.extra}")
                            setLogs("==========================================")
                        }
                        it.post {
                            it.requestFocus()
                        }
                    }
                })
        }
        binding.showConfirmDialogWithoutTTSButton.setOnClickListener {
            VoiceService.instance.showConfirmDialog(
                "ttsText에 empty string을 넣으면 TTS가 재생되지 않고 팝업만 설정할 수 있습니다.",
                "",
                object : GenieSdkEventListener {
                    override fun callback(result: Response) {
                        mainViewModel.apply {
                            setLogs("--- #### [ showConfirmDialog without TTS ] #### ---")
                            setLogs("* resultCode: ${result.resultCode}\n* resultMsg: ${result.resultMsg}\n* extra: ${result.extra}")
                            setLogs("==========================================")
                        }
                        it.post {
                            it.requestFocus()
                        }
                    }
                })
        }
        binding.setVoiceFilterButton.setOnClickListener {
            val keys = ArrayList<String>()
            keys.add("정답")
            keys.add("힌트")
            VoiceService.instance.setVoiceFilter(keys, object : GenieSdkEventListener {
                override fun callback(result: Response) {
                    mainViewModel.apply {
                        setLogs("--- #### [ setVoiceFilter request ] #### ---")
                        setLogs("* resultCode: ${result.resultCode}\n* resultMsg: ${result.resultMsg}\n* extra: ${result.extra}")
                        setLogs("==========================================")
                    }
                    it.post {
                        it.requestFocus()
                    }
                }
            }).observe(this) {
                mainViewModel.apply {
                    setLogs("--- ###### [ setVoiceFilter result ] ###### ---")
                    setLogs("* 전달된 STT: $it")
                    setLogs("==========================================")
                }
            }
        }
        binding.resetVoiceFilterButton.setOnClickListener {
            VoiceService.instance.resetVoiceFilter(object : GenieSdkEventListener {
                override fun callback(result: Response) {
                    mainViewModel.apply {
                        setLogs("--- ###### [ resetVoiceFilter ] ###### ---")
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