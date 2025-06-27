package com.example.myapplication2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class ChangePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        val oldPasswordInput = findViewById<TextInputEditText>(R.id.oldPasswordInput)
        val newPasswordInput = findViewById<TextInputEditText>(R.id.newPasswordInput)
        val newPasswordRepeatInput = findViewById<TextInputEditText>(R.id.newPasswordRepeatInput)
        val confirmButton = findViewById<MaterialButton>(R.id.confirmChangePasswordButton)

        toolbar.setNavigationOnClickListener { finish() }

        confirmButton.setOnClickListener {
            val oldPassword = oldPasswordInput.text?.toString() ?: ""
            val newPassword = newPasswordInput.text?.toString() ?: ""
            val newPasswordRepeat = newPasswordRepeatInput.text?.toString() ?: ""

            if (oldPassword.isEmpty() || newPassword.isEmpty() || newPasswordRepeat.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (newPassword != newPasswordRepeat) {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // TODO: Реализуй смену пароля
            Toast.makeText(this, "Пароль изменён (заглушка)", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}