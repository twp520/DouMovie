package com.colin.doumovie.util

import java.text.DecimalFormat

/**
 * Created by tianweiping on 2017/12/19.
 */

object Util {

    fun formatLargeNumber(number: Int): String {
        val ns = number.toString()
        if (ns.length < 5) {
            return ns
        }
        val a = (number / 10000f).toDouble()
        val df = DecimalFormat(".0")
        return df.format(a) + "万"
    }
}
