package com.example.employeedirectoryapp.ui

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeedirectoryapp.databinding.ActivityMainBinding
import com.example.employeedirectoryapp.utils.NetworkChangeReceiver
import com.example.employeedirectoryapp.utils.NetworkUtils
import com.example.employeedirectoryapp.viewmodel.EmployeeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: EmployeeViewModel by viewModels()
    private lateinit var employeeAdapter: EmployeeAdapter
    private lateinit var networkChangeReceiver: NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        employeeAdapter = EmployeeAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = employeeAdapter
        }

        // Observe Employees List
        viewModel.employees.observe(this) { employees ->
            employeeAdapter = EmployeeAdapter(employees!!)
            binding.recyclerView.adapter = employeeAdapter

            binding.recyclerView.visibility = if (employees.isNotEmpty()) View.VISIBLE else View.GONE
            binding.tvEmptyState.visibility = if (employees.isEmpty()) View.VISIBLE else View.GONE
        }

        // Observe Errors
        viewModel.errorMessage.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }

        // Swipe to Refresh
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadEmployees()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        // Check internet and load employees
        if (NetworkUtils.isNetworkAvailable(this)) {
            viewModel.loadEmployees()
        } else {
            Toast.makeText(this, "No internet connection!", Toast.LENGTH_LONG).show()
        }

        // Register NetworkChangeReceiver
        networkChangeReceiver = NetworkChangeReceiver(viewModel)
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver) // Unregister receiver when activity is destroyed
    }
}