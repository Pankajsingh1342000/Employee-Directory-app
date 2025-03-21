package com.example.employeedirectoryapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.employeedirectoryapp.R
import com.example.employeedirectoryapp.data.model.Employee

class EmployeeAdapter(private var employees: List<Employee>) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    class EmployeeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvEmployeeName)
        val team: TextView = view.findViewById(R.id.tvEmployeeTeam)
        val photo: ImageView = view.findViewById(R.id.ivEmployeePhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees[position]
        holder.name.text = employee.full_name
        holder.team.text = employee.team

        Glide.with(holder.itemView.context)
            .load(employee.photo_url_small)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.photo)
    }

    override fun getItemCount() = employees.size
}