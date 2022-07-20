package com.kt.gigagenie.geniesdksample.features

import android.util.Log
import androidx.fragment.app.activityViewModels
import com.kt.gigagenie.geniesdk.GenieSdkEventListener
import com.kt.gigagenie.geniesdk.data.model.Response
import com.kt.gigagenie.geniesdk.service.MediaService
import com.kt.gigagenie.geniesdksample.R
import com.kt.gigagenie.geniesdksample.base.BaseFragment
import com.kt.gigagenie.geniesdksample.databinding.FragmentMediaServiceBinding
import com.kt.gigagenie.geniesdksample.features.main.MainViewModel

class MediaServiceFragment :
    BaseFragment<FragmentMediaServiceBinding>(R.layout.fragment_media_service) {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun initViews() {
        super.initViews()
        mainViewModel.setLogs("[ MediaServiceFragment ] ==> initViews() ")
    }

    override fun initListeners() {
        super.initListeners()
        binding.changeTvChannelButton.setOnClickListener {
            MediaService.instance.changeTvChannel("34", object : GenieSdkEventListener {
                override fun callback(result: Response) {
                    mainViewModel.apply {
                        setLogs("--- ###### [ changeTvChannel ] ###### ---")
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