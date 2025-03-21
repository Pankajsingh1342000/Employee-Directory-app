package com.example.employeedirectoryapp.data.repository

import com.example.employeedirectoryapp.data.model.Employee
import com.example.employeedirectoryapp.data.model.EmployeeResponse
import com.example.employeedirectoryapp.data.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeRepository {
    fun fetchEmployees(callback: (List<Employee>?, String?) -> Unit) {
        val call = RetrofitClient.apiService.getEmployees()
        call.enqueue(object : Callback<EmployeeResponse> {
            override fun onResponse(call: Call<EmployeeResponse>, response: Response<EmployeeResponse>) {
                if (response.isSuccessful) {
                    val employees = response.body()?.employees

                    // Validate employees list
                    if (employees != null && isValidEmployeeList(employees)) {
                        callback(employees, null)
                    } else {
                        callback(null, "Malformed data received. Cannot display employees.")
                    }
                } else {
                    callback(null, "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<EmployeeResponse>, t: Throwable) {
                callback(null, "Network Error: ${t.localizedMessage}")
            }
        })
    }

    // Check if all employees have required fields
    private fun isValidEmployeeList(employees: List<Employee>): Boolean {
        for (employee in employees) {
            if (employee.uuid.isNullOrBlank() || employee.full_name.isNullOrBlank() ||
                employee.email_address.isNullOrBlank() || employee.team.isNullOrBlank() ||
                employee.employee_type.isNullOrBlank()
            ) {
                return false // Invalidate the entire list if any employee is malformed
            }
        }
        return true
    }
}