package com.fengmi.factory_kt.activity

import android.os.Bundle
import com.fengmi.factory_kt.R
import com.fengmi.factory_kt.base.BaseActivity

class PicTest : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pic_test)
        logDebug("Pic Test onCreate")
    }

    override fun handleCommand(cmdID: String?, param: String?) {
        super.handleCommand(cmdID, param)
    }

    override fun handleControlMsg(cmdType: Int?, cmdID: String?, cmdParam: String?) {
        super.handleControlMsg(cmdType, cmdID, cmdParam)
    }
}
