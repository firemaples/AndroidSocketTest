package com.firemaples.androidsockettest.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.firemaples.androidsockettest.R

class MainFragment : Fragment(R.layout.main_fragment) {
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViews(view)
    }

    private fun setViews(view: View) {
        view.findViewById<View>(R.id.bt_setAsServer).setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionSetAsServer())
        }
    }
}
