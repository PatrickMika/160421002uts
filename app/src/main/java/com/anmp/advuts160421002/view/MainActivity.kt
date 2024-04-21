package com.anmp.advuts160421002.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.anmp.advuts160421002.databinding.ActivityMainBinding
import com.anmp.advuts160421002.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        val KEY_USERNAME = "username"

    }

    fun cekLogin(username:String, password:String) {
        val q = Volley.newRequestQueue(this)
        val url = "https://192.168.53.61/anmp/users.php"
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                Log.d("apiresult", it)
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    if (data.length() > 0) {
                        val dataUser = data.getJSONObject(0)
                        val sType = object : TypeToken<User>() { }.type
                        val user = Gson().fromJson(dataUser.toString(), sType) as User
                        Toast.makeText(this,"Welcome ${user.username}", Toast.LENGTH_SHORT).show()

                        val i = Intent(this, HomeActivity::class.java)
                        i.putExtra(KEY_USERNAME, user.username)

                        startActivity(i)
                        finishAffinity()
                    } else {
                        Toast.makeText(this, "Username or Password is incorrect", Toast.LENGTH_LONG).show()
                    }
                }
            },
            {
                Log.e("apiresult", it.printStackTrace().toString())
            }
        ) {

        }
        q.add(stringRequest)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPass.text.toString()
            Log.d("apiresult", "Username: $username")
            Log.d("apiresult", "Password: $password")
            cekLogin(username, password)
        }
        binding.btnCreateAcc.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
    }
}