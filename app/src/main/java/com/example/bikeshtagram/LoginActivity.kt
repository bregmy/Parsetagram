package com.example.bikeshtagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //check if the user is login or not
        if (ParseUser.getCurrentUser()!=null){
            goToMainActivity()

        }

        findViewById<Button>(R.id.button).setOnClickListener{
            val username= findViewById<EditText>(R.id.username).text.toString()
            val password= findViewById<EditText>(R.id.password).text.toString()
            loginUser(username, password)
        }


        findViewById<Button>(R.id.Signup).setOnClickListener{
            val username= findViewById<EditText>(R.id.username).text.toString()
            val password= findViewById<EditText>(R.id.password).text.toString()
            signUpUser(username, password)
        }
    }

    private fun signUpUser(username: String, password: String){
        // Create the ParseUser
        val user = ParseUser()

        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {

                goToMainActivity()
                // Hooray! Let them use the app now.


            } else {
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
                e.printStackTrace()
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username,password,({user, e->
            if (user!=null){
                Log.i(TAG,"Success")
                goToMainActivity()
            }else{
                e.printStackTrace()
                Toast.makeText(this, "error logging in",Toast.LENGTH_SHORT).show()
            }})

        )

    }

    private fun goToMainActivity(){
        val intent= Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    companion object{
        val TAG="LoginActivity"
    }


}