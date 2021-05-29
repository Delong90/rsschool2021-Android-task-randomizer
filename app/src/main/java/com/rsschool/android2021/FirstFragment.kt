package com.rsschool.android2021

import android.os.Bundle
import android.util.Log
import android.util.LogPrinter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var min: EditText? = null
    private var max: EditText? = null
    private var list: OpenSecondFragmentList? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        min = view.findViewById(R.id.min_value)
        max = view.findViewById(R.id.max_value)

        list = context as OpenSecondFragmentList

        generateButton?.setOnClickListener{
            if (checkToast(min?.text.toString(),max?.text.toString())) {
                list?.openSecondFragment( min?.text.toString().toInt(), max?.text.toString().toInt())
            } else {
                Toast.makeText(context, "Invalid input!", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun checkToast(min: String, max: String): Boolean{
        if (min == "") return false
        if (max == "") return false
        if (min.toLong() > 2147483647) return false
        if (max.toLong() > 2147483647) return false

        if (min.toInt() >= max.toInt()) return false
        return true
    }



    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }

    interface OpenSecondFragmentList{
        fun openSecondFragment(min: Int, max: Int)
    }

}