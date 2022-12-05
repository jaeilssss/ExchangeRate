package com.example.exchangeratecal

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.exchangeratecal.databinding.MainFragmentBinding
import com.example.exchangeratecal.model.DataX
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeoutException

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: MainFragmentBinding
    private val scope = MainScope()

    private lateinit var data: DataX
    private var check: Int = 0
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.model = viewModel


        var adapter =
                context?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.receiver_nation)) }
        binding.receiverSpinner.adapter = adapter

        setNotTouch()
        setLoading()
        scope.launch {
            try{
                data = Repository.getExchangeRateData("KRW")!!
                setTouch()
            }catch (e : TimeoutException){

            }

        }

        binding.receiverSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                check = position
                display()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        viewModel.money.observe(viewLifecycleOwner, Observer {

            display()

        })

        // TODO: Use the ViewModel
    }


    fun display() {
        var date = Date()
        var fotmat = SimpleDateFormat("yyyy-MM-dd hh:mm")

        if (check == 1) { // 한국
            if (viewModel.money.value!!.length != 0 &&
                    (viewModel.money.value!!.toInt() in 1..9999) ) {
                var sum = data.quotes!!.uSDKRW?.times(viewModel.money.value!!.toInt())


                var arr = sum.toString().split(".")
                var str = "수취금액은 ${arr[0]}."
                var carr = arr[1].toCharArray()

                for (i in 1..2) {
                    if (arr[1].length >= i) {
                        str = "${str}${carr[i - 1]}"
                        
                    } else {
                        str = "${str}0"
                    }
                }
                str = "${str} KRW 입니다"

                binding.result.text = str
                binding.searchTime.text=fotmat.format(date)
                binding.result.setTextColor(Color.BLACK)
            }else if(viewModel.money.value!!.length != 0 &&
                    (viewModel.money.value!!.toInt() >=10000 ||
                            viewModel.money.value!!.toInt()<0) ) {
                binding.result.text = "송금액이 바르지 않습니다"
                binding.result.setTextColor(Color.RED)
            }
            else {
                binding.result.text = ""
            }
            var krw = data.quotes!!.uSDKRW.toString().split(".")

            var exchange = "${krw[0]}."
            for(i in 1..2){
                if(krw[1].length>=i){
                    exchange = "${exchange}${krw[0].toCharArray()[i-1]}"
                }
            }
            binding.ExchangeRateMoney.text = "${exchange} KRW / USD"
        } else if (check == 2) { // 일본
            if (viewModel.money.value!!.length != 0 &&
                    (viewModel.money.value!!.toInt() in 1..9999) ) {
                var sum = data.quotes!!.uSDJPY?.times(viewModel.money.value!!.toInt())
                var arr = sum.toString().split(".")
                var str = "수취금액은 ${arr[0]}."
                var carr = arr[1].toCharArray()

                for (i in 1..2) {
                    if (arr[1].length >= i) {
                        str = "${str}${carr[i - 1]}"
                        
                    } else {
                        str = "${str}0"
                    }
                }
                str = "${str} JPY 입니다"
                binding.result.text = str
                binding.searchTime.text=fotmat.format(date)
                binding.result.setTextColor(Color.BLACK)
            }else if(viewModel.money.value!!.length != 0 &&
                    (viewModel.money.value!!.toInt() >=10000 ||
                            viewModel.money.value!!.toInt()<0) ) {
                binding.result.text = "송금액이 바르지 않습니다"
                binding.result.setTextColor(Color.RED)
            } else {
                binding.result.text = ""
            }

            var jpy = data.quotes!!.uSDKRW.toString().split(".")

            var exchange = "${jpy[0]}."
            for(i in 1..2){
                if(jpy[1].length>=i){
                    exchange = "${exchange}${jpy[0].toCharArray()[i-1]}"
                }
            }
            binding.ExchangeRateMoney.text = "${exchange} JPY / USD"


        } else if (check == 3) { // 필리핀
            if (viewModel.money.value!!.length != 0 &&
                    (viewModel.money.value!!.toInt() in 1..9999) ) {
                var sum = data.quotes!!.uSDPHP?.times(viewModel.money.value!!.toInt())
                var arr = sum.toString().split(".")
                var str = "수취금액은 ${arr[0]}."
                var carr = arr[1].toCharArray()

                for (i in 1..2) {
                    if (arr[1].length >= i) {
                        str = "${str}${carr[i - 1]}"
                        
                    } else {
                        str = "${str}0"
                    }
                }
                str = "${str} PHP 입니다"

                binding.result.text = str
                binding.searchTime.text=fotmat.format(date)
                binding.result.setTextColor(Color.BLACK)
            }else if(viewModel.money.value!!.length != 0 &&
                    (viewModel.money.value!!.toInt() >=10000 ||
                            viewModel.money.value!!.toInt()<0) ) {
                binding.result.text = "송금액이 바르지 않습니다"
                binding.result.setTextColor(Color.RED)
            } else {
                binding.result.text = ""
            }

            var php = data.quotes!!.uSDKRW.toString().split(".")

            var exchange = "${php[0]}."
            for(i in 1..2){
                if(php[1].length>=i){
                    exchange = "${exchange}${php[0].toCharArray()[i-1]}"
                }
            }
            binding.ExchangeRateMoney.text = "${exchange} PHP / USD"
        } else {
            binding.result.text = ""
        }
    }


    fun setLoading() {
        binding.loop.startAnimation(AnimationUtils.loadAnimation(context,R.anim.rotate))
        setNotTouch()
    }

    fun setNotTouch() {
        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    fun setTouch(){
        binding.loop.animation.cancel()
        binding.loop.animation  = null
        binding.loop.visibility = View.GONE
        binding.loopText.visibility = View.GONE
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}