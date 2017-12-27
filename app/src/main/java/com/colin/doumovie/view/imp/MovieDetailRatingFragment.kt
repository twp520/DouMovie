package com.colin.doumovie.view.imp

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.colin.doumovie.R
import com.colin.doumovie.bean.RatingBean
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.PercentFormatter

/**
 * Created by tianweiping on 2017/12/21.
 */
class MovieDetailRatingFragment : BottomSheetDialogFragment() {
    private var mChart: HorizontalBarChart? = null
    private var mPhoto: ImageView? = null
    private var mTitle: TextView? = null
    private var mRatingTv: TextView? = null
    private var mRatingBar: RatingBar? = null
    private var mRatingInfo: RatingBean? = null
    private var imageUrl: String? = ""
    private var title: String? = ""
    private var behavior: BottomSheetBehavior<View>? = null

   /* override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(context).inflate(R.layout.movie_detail_rating_fragment_layout, container, false)
        mChart = view.findViewById(R.id.movie_detail_rating_fragment_chart) as HorizontalBarChart?
        mPhoto = view.findViewById(R.id.movie_detail_rating_fragment_photo) as ImageView?
        mTitle = view.findViewById(R.id.movie_detail_rating_fragment_title) as TextView?
        mRatingTv = view.findViewById(R.id.movie_detail_rating_fragment_rating_tv) as TextView?
        mRatingBar = view.findViewById(R.id.movie_detail_rating_fragment_rating_rating) as RatingBar?
        Glide.with(context)
                .load(imageUrl)
                .into(mPhoto)
        mTitle?.text = title
        mRatingTv?.text = mRatingInfo?.average.toString()
        mRatingBar?.rating = (mRatingInfo?.average!! / 2).toFloat()
        initChart()
        dialog.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        Log.e("twp", "onCreateView")
        behavior= BottomSheetBehavior.from(view?.parent as View)
        return view
    }*/

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        var view = LayoutInflater.from(context).inflate(R.layout.movie_detail_rating_fragment_layout, null, false)
        mChart = view.findViewById(R.id.movie_detail_rating_fragment_chart) as HorizontalBarChart?
        mPhoto = view.findViewById(R.id.movie_detail_rating_fragment_photo) as ImageView?
        mTitle = view.findViewById(R.id.movie_detail_rating_fragment_title) as TextView?
        mRatingTv = view.findViewById(R.id.movie_detail_rating_fragment_rating_tv) as TextView?
        mRatingBar = view.findViewById(R.id.movie_detail_rating_fragment_rating_rating) as RatingBar?
        Glide.with(context)
                .load(imageUrl)
                .into(mPhoto)
        mTitle?.text = title
        mRatingTv?.text = mRatingInfo?.average.toString()
        mRatingBar?.rating = (mRatingInfo?.average!! / 2).toFloat()
        initChart()
        dialog.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        dialog.setContentView(view);
        behavior= BottomSheetBehavior.from(view?.parent as View)
        return dialog
    }
    override fun onStart() {
        super.onStart()
        behavior?.state = BottomSheetBehavior.STATE_EXPANDED//全屏展开
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)

        mRatingInfo = args?.getParcelable("rating")
        imageUrl = args?.getString("imageUrl")
        title = args?.getString("title")
    }

    private fun initChart() {
        mChart?.legend?.isEnabled = false
        mChart?.description?.isEnabled = false
        mChart?.setDrawValueAboveBar(true)
        mChart?.setDrawBarShadow(false)
        mChart?.setTouchEnabled(false)

        val lefY = mChart?.axisLeft
        lefY?.isEnabled = false
        val rightY = mChart?.axisRight
        rightY?.isEnabled = false

        val xAxis = mChart?.xAxis
        xAxis?.isEnabled = true
        xAxis?.position = XAxis.XAxisPosition.BOTTOM
        xAxis?.setLabelCount(5, false)
        xAxis?.setValueFormatter { value, _ ->
            "${value.toInt()} 星"
        }

        xAxis?.setDrawAxisLine(false)
        xAxis?.setDrawGridLines(false)
        xAxis?.setDrawLabels(true)
        initChartData()
    }

    private fun initChartData() {
        val count = (mRatingInfo?.details?.`_$1`!! + mRatingInfo?.details?.`_$2`!!
                + mRatingInfo?.details?.`_$3`!!
                + mRatingInfo?.details?.`_$4`!! + mRatingInfo?.details?.`_$5`!!)
        val one = getBaiFenBi(mRatingInfo?.details?.`_$1`!!, count)
        val two = getBaiFenBi(mRatingInfo?.details?.`_$2`!!, count)
        val three = getBaiFenBi(mRatingInfo?.details?.`_$3`!!, count)
        val four = getBaiFenBi(mRatingInfo?.details?.`_$4`!!, count)
        val five = getBaiFenBi(mRatingInfo?.details?.`_$5`!!, count)
        val be1 = BarEntry(1f, one)
        val be2 = BarEntry(2f, two)
        val be3 = BarEntry(3f, three)
        val be4 = BarEntry(4f, four)
        val be5 = BarEntry(5f, five)
        val barEnitys = arrayListOf<BarEntry>()
        barEnitys.add(be1)
        barEnitys.add(be2)
        barEnitys.add(be3)
        barEnitys.add(be4)
        barEnitys.add(be5)
        val barSet = BarDataSet(barEnitys, "ratingdata")
        barSet.color = ContextCompat.getColor(context, R.color.movie_detail_rating)
        val barData = BarData(barSet)
        barData.setValueFormatter(PercentFormatter())
        barData.setDrawValues(true)
        barData.setValueTextSize(10f)
        mChart?.setFitBars(true)
        mChart?.isAutoScaleMinMaxEnabled = true
        mChart?.data = barData
        mChart?.notifyDataSetChanged()
        mChart?.animateY(500)
    }

    private fun getBaiFenBi(x: Int, count: Int): Float {

        return x.toFloat() / count.toFloat() * 100f
    }

    override fun show(manager: FragmentManager?, tag: String?) {
        super.show(manager, tag)
        Log.e("twp", "show")
    }
}