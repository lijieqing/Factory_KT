package com.fengmi.factory_kt.globle

import com.fengmi.factory_kt.globle.CommandID.Companion.CMDID_CAMERA_TEST_ON
import com.fengmi.factory_kt.globle.CommandID.Companion.CMDID_MEDIA_PLAY
import com.fengmi.factory_kt.globle.CommandID.Companion.CMDID_N_TEST_PATTERN_START
import com.fengmi.factory_kt.globle.CommandID.Companion.CMDID_N_TVVIEW_SOUR_START
import com.fengmi.factory_kt.globle.CommandID.Companion.CMDID_SOUR_START
import com.fengmi.factory_kt.globle.CommandID.Companion.CMDID_START_AUTO_FOCUS
import com.fengmi.factory_kt.globle.CommandID.Companion.CMDID_XPR_PIC_OPEN


class TVCommandDescription {
    companion object {
        /**************4. define CommandType */
        const val CMD_TYPE_COMMON = "1"
        const val CMD_TYPE_ACTIVITY_ON = "2"
        const val CMD_TYPE_ACTIVITY_OFF = "3"
        const val CMD_TYPE_INNACTIVITY = "4"

        const val APPLICATION_PACKAGENAME = "com.fm.factorytest"
        const val ACTIVITY_PACKAGENAME = "com.fm.factorytest.activity"
        const val ACTION_FILTER_PREFIX = "com.duokan.action."

        private val Cmd_Activity = arrayOf(
            arrayOf(Integer.toHexString(CMDID_SOUR_START).toUpperCase(), "InputSource"),
            arrayOf(Integer.toHexString(CMDID_N_TEST_PATTERN_START).toUpperCase(), "TestPattern"),
            arrayOf(Integer.toHexString(CMDID_MEDIA_PLAY).toUpperCase(), "LocalMedia"),
            arrayOf(Integer.toHexString(CMDID_N_TVVIEW_SOUR_START).toUpperCase(), "InputSourceForTvView"),
            arrayOf(Integer.toHexString(CMDID_CAMERA_TEST_ON).toUpperCase(), "UVCCamera"),
            arrayOf(Integer.toHexString(CMDID_XPR_PIC_OPEN).toUpperCase(), "PicTest"),
            arrayOf(Integer.toHexString(CMDID_START_AUTO_FOCUS).toUpperCase(), "AutoFocus")
        )
        fun getFilterActionForCmd(cmdid: String?): String? {
            if (cmdid == null) return null
            for (i in Cmd_Activity.indices) {
                if (cmdid == Cmd_Activity[i][0]) {
                    return ACTION_FILTER_PREFIX + Cmd_Activity[i][1]
                }
            }
            return null
        }
    }
}

class CommandID {
    companion object {
        const val CMDID_SOUR_START = 0x1104
        const val CMDID_SOUR_SWITCH = 0x1105
        const val CMDID_SOUR_STOP = 0x1106
        const val CMDID_N_TEST_PATTERN_START = 0x1490
        const val CMDID_N_TEST_PATTERN_STOP = 0x1491
        const val CMDID_MEDIA_PLAY = 0x1113
        const val CMDID_MEDIA_STOP = 0x111a
        const val CMDID_N_TVVIEW_SOUR_START = 0x147C
        const val CMDID_N_TVVIEW_SOUR_STOP = 0x147D
        const val CMDID_CAMERA_TEST_ON = 0x14C0
        const val CMDID_CAMERA_TEST_OFF = 0x14C1
        const val CMDID_XPR_PIC_OPEN = 0x14CB
        const val CMDID_XPR_PIC_OFF = 0x14CC
        const val CMDID_START_AUTO_FOCUS = 0x14D3
        const val CMDID_STOP_AUTO_FOCUS = 0x14D4
    }
}
