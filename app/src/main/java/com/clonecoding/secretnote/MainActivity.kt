package com.clonecoding.secretnote

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    /**
     * NumberPicker min value
     */
    private val MIN_VALUE = 0

    /**
     * NumberPicker max value
     */
    private val MAX_VALUE = 9

    /**
     * NumberPick 1
     */
    private lateinit var numberPicker1: NumberPicker

    /**
     * NumberPick 2
     */
    private lateinit var numberPicker2: NumberPicker

    /**
     * NumberPick 3
     */
    private lateinit var numberPicker3: NumberPicker

    /**
     * Open button
     */
    private val openButton: AppCompatButton by lazy {
        findViewById(R.id.openButton)
    }

    /**
     * Change password button
     */
    private val changePassWordButton: AppCompatButton by lazy {
        findViewById(R.id.changePasswordButton)
    }

    /**
     * is change password Mode
     */
    private var changePasswordMode: Boolean = false

    /**
     * onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initNumberPicker()

        this.openButton.setOnClickListener {

            this.onClickOpenButton()
        }

        this.changePassWordButton.setOnClickListener {

            this.onClickChangePasswordButton()
        }
    }

    /**
     * open button event
     */
    private fun onClickOpenButton() {

        if (this.changePasswordMode) {
            Toast.makeText(this, "비밀번호 변경 중입니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)
        val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

        if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {

            startActivity(Intent(this, DiaryActivity::class.java))
        } else {

            this.showPasswordAlertDialog()

        }
    }

    /**
     * Init numberPicker
     */
    private fun initNumberPicker() {

        this.numberPicker1 = findViewById<NumberPicker>(R.id.numberPicker1)
            .apply {
                minValue = MIN_VALUE
                maxValue = MAX_VALUE
            }
        this.numberPicker2 = findViewById<NumberPicker>(R.id.numberPicker2)
            .apply {
                minValue = MIN_VALUE
                maxValue = MAX_VALUE
            }
        this.numberPicker3 = findViewById<NumberPicker>(R.id.numberPicker3)
            .apply {
                minValue = MIN_VALUE
                maxValue = MAX_VALUE
            }
    }

    /**
     * change password button event
     */
    private fun onClickChangePasswordButton() {

        val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)
        val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

        if (this.changePasswordMode) {

            passwordPreference.edit(true) {

                putString("password", passwordFromUser)
            }

            this.changePasswordMode = false
            this.changePassWordButton.setBackgroundColor(Color.BLACK)

        } else {

            if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {

                this.changePasswordMode = true
                Toast.makeText(this, "변경할 패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()

                this.changePassWordButton.setBackgroundColor(Color.RED)
            } else {

                this.showPasswordAlertDialog()

            }
        }
    }

    /**
     * Show password alert dialog
     */
    private fun showPasswordAlertDialog() {

        AlertDialog.Builder(this)
            .setTitle("실패!!")
            .setMessage("비밀번호가 잘못되었습니다.")
            .setPositiveButton("확인", ) { _, _ -> }
            .create()
            .show()
    }
}