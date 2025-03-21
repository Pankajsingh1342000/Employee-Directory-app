# Employee Directory App

## Overview
This is an **Android application** that fetches and displays a list of employees from a provided **JSON API**. The app is built using **MVVM architecture** and ensures efficient data handling, caching, and offline resilience.

---

## 📌 Features Implemented
✔️ Fetch Employee Data from API (Retrofit)  
✔️ Display Employee Name, Team, and Profile Photo (RecyclerView)  
✔️ Pull-to-Refresh (SwipeRefreshLayout)  
✔️ Loading, Empty & Error States  
✔️ Placeholder for Failed Image Loading (Glide)  
✔️ Prevent Unnecessary API Calls (ViewModel)  
✔️ Cache Images Locally (Glide Disk Caching)  
✔️ Detect Internet Connectivity & Auto-Reload on Reconnect  
✔️ Handle Malformed Employee Data by Invalidating Entire List  
✔️ Use Only Standard UI Components (No Jetpack Compose)  

---

## 📌 Technologies & Libraries Used
- **Kotlin** - Primary programming language  
- **MVVM Architecture** - For better separation of concerns  
- **Retrofit + Gson** - Fetching and parsing API responses  
- **RecyclerView** - Displaying the employee list efficiently  
- **Glide** - Loading and caching images  
- **SwipeRefreshLayout** - Enabling pull-to-refresh functionality  
- **LiveData + ViewModel** - Lifecycle-aware state management  
- **BroadcastReceiver + ConnectivityManager** - Detecting internet connection changes  
- **ConstraintLayout** - Modern and flexible UI layout  

---

## 📌 How It Works
1. **On app launch**, it checks the internet connection.  
2. If **online**, it fetches the **employee list** from the API.  
3. If **offline**, it shows a **"No internet connection"** message and waits for the network to reconnect.  
4. If the **network is restored**, the app automatically **reloads data**.  
5. Users can **pull down to refresh** the list.  
6. If **data is malformed**, the app does not display incomplete employees.  

---

## 📌 Key Implementations
### 1️⃣ Network Handling (Offline Support)
✔ Uses **BroadcastReceiver** to detect network changes and reload data automatically when internet is restored.  
✔ Uses **NetworkUtils** to check internet availability before making API requests.  

### 2️⃣ API Handling (Retrofit)
✔ Uses **Retrofit with GsonConverter** to fetch employee data from the API.  
✔ Implements proper **error handling** to detect failures, malformed data, or empty responses.  

### 3️⃣ RecyclerView for Employee List
✔ Employees are displayed in a **RecyclerView** with **smooth scrolling**.  
✔ **Glide handles image loading efficiently** with caching enabled.  

### 4️⃣ Handling Malformed Employee Data
✔ If **any employee** in the list is **missing required fields**, the entire list is discarded.  
✔ A **toast message alerts the user** about the issue.  

