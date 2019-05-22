package com.fengmi.factory_kt.globle

class FactorySetting {
    companion object {
        const val EXTRA_CMD_ID = "commandid"
        const val EXTRA_CMD_PARA = "commandparas"
        /* start --- command service broadcast command to activity parameters definitions */
        const val EXTRA_BROADCAST_CMD_PARA = "commandparas"
        // type int, content: the command type from command service
        const val EXTRA_BROADCAST_CONTROL_TYPE = "controltype"
        // type String, content: the command id from command service
        const val EXTRA_BROADCAST_CONTROL_ID = "controlid"
        // type String, content: the command para from command service
        const val EXTRA_BROADCAST_CONTROL_PARA = "controlparas"
        // business command, check the parameters
        const val COMMAND_TASK_BUSINESS = 1
        // pause the task running
        const val COMMAND_TASK_PAUSE = 2
        // resume the task running
        const val COMMAND_TASK_RESUME = 3
        // finish the task running
        const val COMMAND_TASK_STOP = 4

        const val COMMAND_PRODUCT_TV = "1"
    }
}