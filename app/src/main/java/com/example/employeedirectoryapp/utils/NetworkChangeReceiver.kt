package com.example.employeedirectoryapp.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.employeedirectoryapp.viewmodel.EmployeeViewModel


class NetworkChangeReceiver(private val viewModel: EmployeeViewModel) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            if (NetworkUtils.isNetworkAvailable(it)) {
                viewModel.loadEmployees() // Reload employees when internet is back
            }
        }
    }
}