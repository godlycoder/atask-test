package com.banidevv.mpassigmenttest

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import com.banidevv.mpassignmenttest.R

abstract class BaseActivity : AppCompatActivity() {

    private var loadingDialog: Dialog? = null

    fun showLoading() {
        loadingDialog = Dialog(this)
        loadingDialog?.setContentView(R.layout.view_loading)

        loadingDialog?.show()
    }

    fun hideLoading() {
        loadingDialog?.hide()
    }

}