package com.fengmi.factory_kt.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.WindowManager
import com.fengmi.factory_kt.globle.FactorySetting
import com.fengmi.factory_kt.globle.TVCommandDescription
import java.lang.ref.WeakReference

@SuppressLint("Registered")
open class BaseActivity : Activity() {
    companion object {
        private const val TAG = "FactoryTest"
        const val PASS = true
        const val FAIL = false
        const val RUN_TASK = 20000
        var DEBUG = true

        private class CommandHandler(activity: BaseActivity) : Handler() {
            private var reference: WeakReference<BaseActivity> = WeakReference(activity)
            override fun handleMessage(msg: Message?) {
                if (msg?.what == RUN_TASK) {
                    val act = reference.get()
                    act?.handleCommand(act.mCaseId, act.mCasePara)
                }
                super.handleMessage(msg)
            }
        }
    }

    private var mCaseName: String? = null
    private var mCaseId: String? = null
    private var mCasePara: String? = null

    private val mHandler: CommandHandler = CommandHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logDebug("BaseActivity onCreate")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        )
        var recAction: String?= null
        mCaseId = intent.getStringExtra(FactorySetting.EXTRA_CMD_ID)
        mCasePara = intent.getStringExtra(FactorySetting.EXTRA_CMD_PARA)
        mCaseName = localClassName
        logDebug("caseID=$mCaseId, param=$mCasePara, caseName=$mCaseName")
        if (mCaseId?.substring(0, 1) == FactorySetting.COMMAND_PRODUCT_TV) {
            recAction = TVCommandDescription.getFilterActionForCmd(mCaseId).toString()
        }else{
            logDebug("un-know product type")
        }
        var serviceIntent = Intent().also {
            it.action = recAction
            it.putExtra(FactorySetting.EXTRA_CMD_ID,this.mCaseId)
            it.putExtra(FactorySetting.EXTRA_CMD_PARA,this.mCasePara)
        }
    }

    open fun handleCommand(cmdID: String?, param: String?) {}

    open fun handleControlMsg(cmdType: Int?, cmdID: String?, cmdParam: String?) {}

    protected fun logDebug(info: String) {
        if (DEBUG) {
            Log.d(TAG + localClassName, info)
        }
    }

    inner class CommandReceiver : BroadcastReceiver() {
        private var filter: String? = null
        override fun onReceive(context: Context?, intent: Intent?) {

            if (mCaseId?.substring(0, 1) == FactorySetting.COMMAND_PRODUCT_TV) {
                filter = TVCommandDescription.getFilterActionForCmd(mCaseId).toString()
            } else {
                logDebug("un-know product type ")
            }
            if (intent!!.action != null && intent.action == filter) {
                logDebug("-----------------------------------")
                logDebug("----------CMD  receive-------------")
                logDebug("-----------------------------------")
                val param = intent.getStringExtra(FactorySetting.EXTRA_BROADCAST_CONTROL_PARA)
                val sameParam = if (param == null) mCasePara == null else param == mCasePara
                if (!sameParam)
                    return
                logDebug("-----------------------------------")
                logDebug("----------CMD  handle -------------")
                logDebug("-----------------------------------")
                val cmdType = intent.getIntExtra(
                    FactorySetting.EXTRA_BROADCAST_CONTROL_TYPE,
                    FactorySetting.COMMAND_TASK_BUSINESS
                )
                val cmdID = intent.getStringExtra(FactorySetting.EXTRA_BROADCAST_CONTROL_ID)
                val cmdPara = intent.getStringExtra(FactorySetting.EXTRA_BROADCAST_CONTROL_PARA)
                logDebug("CommandReceiver handleControlMsg cmdType=$cmdType, cmdId=$cmdID, cmdPara=$cmdPara")
                handleControlMsg(cmdType, cmdID, cmdPara)
            }
        }
    }
}