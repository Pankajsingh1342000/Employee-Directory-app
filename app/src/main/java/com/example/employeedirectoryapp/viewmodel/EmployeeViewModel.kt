package com.example.employeedirectoryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeedirectoryapp.data.model.Employee
import com.example.employeedirectoryapp.data.repository.EmployeeRepository

class EmployeeViewModel : ViewModel() {
    private val repository = EmployeeRepository()

    private val _employees = MutableLiveData<List<Employee>?>()
    val employees: MutableLiveData<List<Employee>?> get() = _employees

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun loadEmployees() {
        repository.fetchEmployees { employeeList, errorMsg ->
            if (employeeList != null) {
                _employees.postValue(employeeList)
                _errorMessage.postValue(null)
            } else {
                _errorMessage.postValue(errorMsg)
            }
        }
    }
}
