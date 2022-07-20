package com.kt.gigagenie.geniesdksample.features

import androidx.fragment.app.activityViewModels
import com.kt.gigagenie.geniesdk.service.AppInfoService
import com.kt.gigagenie.geniesdksample.base.BaseFragment
import com.kt.gigagenie.geniesdksample.R
import com.kt.gigagenie.geniesdksample.databinding.FragmentAppinfoServiceBinding
import com.kt.gigagenie.geniesdksample.features.main.MainViewModel

class AppInfoServiceFragment :
    BaseFragment<FragmentAppinfoServiceBinding>(R.layout.fragment_appinfo_service) {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun initViews() {
        super.initViews()
        mainViewModel.setLogs("[ AppInfoServiceFragment ] ==> initViews() ")
    }

    override fun initListeners() {
        super.initListeners()
        binding.allValuesButton.setOnClickListener {
            with(binding) {
                userNicknameButton.callOnClick()
                isPinButton.callOnClick()
                kwsIdButton.callOnClick()
                regSpeaker.callOnClick()
                isRegistWithApp.callOnClick()
                telAvailable.callOnClick()
                otvAvailable.callOnClick()
            }
            binding.allValuesButton.post {
                it.requestFocus()
            }
        }
        binding.userNicknameButton.setOnClickListener {
            mainViewModel.setLogs("userNickname: ${AppInfoService.instance.userNickname}")
            binding.userNicknameButton.post {
                it.requestFocus()
            }
        }
        binding.isPinButton.setOnClickListener {
            mainViewModel.setLogs("isPin: ${AppInfoService.instance.isPin}")
            binding.isPinButton.post {
                it.requestFocus()
            }
        }
        binding.kwsIdButton.setOnClickListener {
            mainViewModel.setLogs("kwsId: ${AppInfoService.instance.kwsId}")
            binding.kwsIdButton.post {
                it.requestFocus()
            }
        }
        binding.regSpeaker.setOnClickListener {
            mainViewModel.setLogs("regSpeaker: ${AppInfoService.instance.regSpeaker}")
            binding.regSpeaker.post {
                it.requestFocus()
            }
        }
        binding.isRegistWithApp.setOnClickListener {
            mainViewModel.setLogs("isRegistWithApp: ${AppInfoService.instance.isRegistWithApp}")
            binding.isRegistWithApp.post {
                it.requestFocus()
            }
        }
        binding.telAvailable.setOnClickListener {
            mainViewModel.setLogs("telAvailable: ${AppInfoService.instance.telAvailable}")
            binding.telAvailable.post {
                it.requestFocus()
            }
        }
        binding.otvAvailable.setOnClickListener {
            mainViewModel.setLogs("otvAvailable: ${AppInfoService.instance.otvAvailable}")
            binding.otvAvailable.post {
                it.requestFocus()
            }
        }
    }
}