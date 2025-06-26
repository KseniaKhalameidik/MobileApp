package com.example.myapplication2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class Registration: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        val termsTextView = findViewById<TextView>(R.id.termsTextView)
        val text = "Нажимая на кнопку, вы соглашаетесь с политикой конфиденциальности и обработкой персональных данных, а также принимаете пользовательское соглашение"
        val spannable = SpannableString(text)

        val privacyStart = text.indexOf("политикой конфиденциальности")
        val privacyEnd = privacyStart + "политикой конфиденциальности".length

        val agreementStart = text.indexOf("пользовательское соглашение")
        val agreementEnd = agreementStart + "пользовательское соглашение".length

        val linkColor = ContextCompat.getColor(this, R.color.primary)


        class ClickPartOfText(val onClickAction: () -> Unit) : ClickableSpan() {
            override fun onClick(widget: View) = onClickAction()
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = linkColor
                ds.isUnderlineText = false
            }
        }

        spannable.setSpan(
            ClickPartOfText { /* открыть политику */ },
            privacyStart, privacyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            ClickPartOfText { /* открыть соглашение */ },
            agreementStart, agreementEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        termsTextView.text = spannable
        termsTextView.movementMethod = LinkMovementMethod.getInstance()
        termsTextView.highlightColor = Color.TRANSPARENT

        findViewById<MaterialButton>(R.id.registerButton).setOnClickListener {
            val login = findViewById<TextInputEditText>(R.id.login).text.toString()
            val nickname = findViewById<TextInputEditText>(R.id.nickname).text.toString()
            val password = findViewById<TextInputEditText>(R.id.password).text.toString()
            val repeatPassword = findViewById<TextInputEditText>(R.id.repeatPassword).text.toString()
            val intent = Intent(this, Activity::class.java)
            startActivity(intent)
            finish()
        }

        val genderGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
        genderGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadio = findViewById<RadioButton>(checkedId)
            val selectedGender = selectedRadio.text.toString()
        }
    }
}