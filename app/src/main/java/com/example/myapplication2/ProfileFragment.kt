package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ProfileFragment", "onViewCreated called")
        val saveText = view.findViewById<TextView>(R.id.saveText)
        val changePasswordButton = view.findViewById<TextView>(R.id.changePassword)
        val logoutButton = view.findViewById<MaterialButton>(R.id.logoutButton)
        val loginInput = view.findViewById<TextInputEditText>(R.id.loginInput)
        val nicknameInput = view.findViewById<TextInputEditText>(R.id.nicknameInput)

        saveText.setOnClickListener {
            // Здесь будет логика сохранения профиля
            // Например, получить значения из loginInput и nicknameInput
            val login = loginInput.text?.toString() ?: ""
            val nickname = nicknameInput.text?.toString() ?: ""

            // TODO: Сохрани значения (например, в ViewModel, SharedPreferences или отправь на сервер)
        }

        changePasswordButton.setOnClickListener {
            val intent = Intent(requireContext(), ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        logoutButton.setOnClickListener {
            // Здесь логика выхода из аккаунта
            // Например, очистить SharedPreferences и перейти на экран входа
            // TODO: Реализуй выход из аккаунта
        }
    }
}