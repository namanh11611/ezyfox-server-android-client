package com.tvd12.ezyfoxserver.client.example.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tvd12.ezyfoxserver.client.example.R
import com.tvd12.ezyfoxserver.client.example.extension.afterTextChanged
import com.tvd12.ezyfoxserver.client.example.socket.SocketClientProxy
import com.tvd12.ezyfoxserver.client.example.view.LoggedInUserView
import com.tvd12.ezyfoxserver.client.example.view.LoginViewModel
import com.tvd12.ezyfoxserver.client.example.view.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)
    }
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var loading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        initView()
        initObserver()
        initAction()
    }

    private fun initView() {
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        login = findViewById(R.id.login)
        loading = findViewById(R.id.loading)
    }

    private fun initObserver() {
        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            loginState.usernameError?.let { errorId ->
                username.error = getString(errorId)
            }
            loginState.passwordError?.let { errorId ->
                password.error = getString(errorId)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            loginResult.error?.let { error ->
                showLoginFailed(error)
            }
            loginResult.success?.let { success ->
                updateUiWithUser(success)
            }
            setResult(Activity.RESULT_OK)
        })
    }

    private fun initAction() {
        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        doLogin()
                }
                false
            }

            login.setOnClickListener {
                doLogin()
            }
        }

        SocketClientProxy.apply {
            onDisconnectedCallback {
                loading.visibility = View.GONE
                showToast("reconnecting ...")
            }

            onConnectionFailedCallback {
                loading.visibility = View.GONE
                showToast("can not connect to server, please check")
            }

            onAuthenticated {
                val intent = Intent(this@LoginActivity, MessageListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun doLogin() {
        loading.visibility = View.VISIBLE
        SocketClientProxy.connectToServer(
            username = username.text.toString(),
            password = password.text.toString()
        )
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        showToast("$welcome $displayName")
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(this, errorString, Toast.LENGTH_SHORT).show()
    }
}
