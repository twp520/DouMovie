package com.colin.doumovie.view.imp

import android.content.Context
import android.graphics.Matrix
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.colin.doumovie.R

/**
 * Created by tianweiping on 2017/12/22.
 */

class SensorFragment : Fragment() {

    private var mView: View? = null
    private var manager: SensorManager? = null
    private val TAG = "SensorFragment"
    private var sensor: Sensor? = null
    private var imageView: ImageView? = null
    private val scale = 5//阻尼系数

    private val mListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            //传感器值发生改变
            val values = event.values
            //            Log.e(TAG, "onSensorChanged:  Z轴 = "+values[0]);
            //            Log.e(TAG, "onSensorChanged:  X轴 = " + values[1]);
            //            Log.e(TAG, "onSensorChanged:  Y轴 = " + values[2]);
            val x = values[1]
            val y = values[2]
            //TODO 边界控制
//            val dtop = imageView!!.drawable.bounds.top
//            val itop = imageView!!.top
//            Log.e(TAG, "onSensorChanged: dtop = " + dtop)
//            Log.e(TAG, "onSensorChanged: itop = " + itop)

            val imageMatrix = Matrix()
            imageMatrix.preTranslate(-y * scale, -x * scale)
            imageView!!.imageMatrix = imageMatrix

        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            //传感器精度发生改变
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_scensor_layout, container, false)
        imageView = mView!!.findViewById(R.id.sensor_iv) as ImageView
        manager = activity.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = manager!!.getDefaultSensor(Sensor.TYPE_ORIENTATION) //方向传感器
        Log.e(TAG, "onCreateView: ")
        return mView
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
        manager!!.registerListener(mListener, sensor, SensorManager.SENSOR_DELAY_UI)
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
        manager!!.unregisterListener(mListener)
    }
}
