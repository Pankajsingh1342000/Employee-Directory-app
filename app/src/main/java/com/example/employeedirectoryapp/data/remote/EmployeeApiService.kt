package com.example.employeedirectoryapp.data.remote

import com.example.employeedirectoryapp.data.model.EmployeeResponse
import com.example.employeedirectoryapp.utils.Constants
import retrofit2.Call
import retrofit2.http.GET

interface EmployeeApiService {
    @GET(Constants.EMPLOYEES_URL)
    fun getEmployees(): Call<EmployeeResponse>
}