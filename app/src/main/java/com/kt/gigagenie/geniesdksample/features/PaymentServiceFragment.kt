package com.kt.gigagenie.geniesdksample.features

import androidx.fragment.app.activityViewModels
import com.kt.gigagenie.geniesdk.GenieSdkEventListener
import com.kt.gigagenie.geniesdk.data.model.Response
import com.kt.gigagenie.geniesdk.service.PaymentService
import com.kt.gigagenie.geniesdksample.base.BaseFragment
import com.kt.gigagenie.geniesdksample.R
import com.kt.gigagenie.geniesdksample.databinding.FragmentPaymentServiceBinding
import com.kt.gigagenie.geniesdksample.features.main.MainViewModel

class PaymentServiceFragment :
    BaseFragment<FragmentPaymentServiceBinding>(R.layout.fragment_payment_service) {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun initViews() {
        super.initViews()
        mainViewModel.setLogs("[ PaymentServiceFragment ] ==> initViews() ")
    }

    override fun initListeners() {
        super.initListeners()
        binding.requestTotalPayButton.setOnClickListener {
            PaymentService.instance.requestTotalPay(
                "appSvcId",
                "txid",
                "productInfo",
                "bill|geniePay",
                "geniePay",
                object : GenieSdkEventListener {
                    override fun callback(result: Response) {
                        mainViewModel.apply {
                            setLogs("--- ###### [ requestTotalPay ] ###### ---")
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