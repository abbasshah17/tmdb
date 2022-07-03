package com.starzplay.view.player

import android.app.Activity
import android.content.Context
import android.graphics.Insets
import android.os.Build
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Size
import android.view.SurfaceView
import android.view.WindowInsets


class VideoSurfaceView : SurfaceView {
	private var videoWidth = 0
	private var videoHeight = 0

	constructor(context: Context?) : super(context) {}
	constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
		context,
		attrs,
		defStyleAttr
	) {
	}

	constructor(
		context: Context?,
		attrs: AttributeSet?,
		defStyleAttr: Int,
		defStyleRes: Int
	) : super(context, attrs, defStyleAttr, defStyleRes) {
	}

	fun setVideoWidth(width: Int) {
		videoWidth = width
	}

	fun setVideoHeight(height: Int) {
		videoHeight = height
	}

	fun adjustDimensions(width: Int, height: Int) {
		setVideoWidth(width)
		setVideoHeight(height)
		if (videoWidth == 0 || videoHeight == 0) {
			return
		}

		//Get the width of the screen
		val screenWidth: Int = getDisplaySize(context).width

		//Get the SurfaceView layout parameters
		val lp = layoutParams

		//Set the width of the SurfaceView to the width of the screen
		lp.width = screenWidth

		//Set the height of the SurfaceView to match the aspect ratio of the video
		//be sure to cast these as floats otherwise the calculation will likely be 0
		lp.height = (videoHeight.toFloat() / videoWidth.toFloat() * screenWidth.toFloat()).toInt()

		//Commit the layout parameters
		layoutParams = lp
	}

	fun matchWidth() {
		if (videoWidth == 0 || videoHeight == 0) {
			return
		}

		//Get the width of the screen
		val screenWidth: Int = getDisplaySize(context).width

		//Get the SurfaceView layout parameters
		val lp = layoutParams

		//Set the width of the SurfaceView to the width of the screen
		lp.width = screenWidth

		//Set the height of the SurfaceView to match the aspect ratio of the video
		//be sure to cast these as floats otherwise the calculation will likely be 0
		lp.height = (videoHeight.toFloat() / videoWidth.toFloat() * screenWidth.toFloat()).toInt()

		//Commit the layout parameters
		layoutParams = lp
	}

	fun matchHeight() {
		if (videoWidth == 0 || videoHeight == 0) {
			return
		}

		//Get the width of the screen
		val screenHeight: Int = getDisplaySize(context).height

		//Get the SurfaceView layout parameters
		val lp = layoutParams

		//Set the width of the SurfaceView to the width of the screen
		lp.height = screenHeight

		//Set the height of the SurfaceView to match the aspect ratio of the video
		//be sure to cast these as floats otherwise the calculation will likely be 0
		lp.width = (videoWidth.toFloat() / videoHeight.toFloat() * screenHeight.toFloat()).toInt()

		//Commit the layout parameters
		layoutParams = lp
	}

	private fun getDisplaySize(context: Context): Size {
		if (context !is Activity) {
			return Size(0, 0)
		}

		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
			val windowMetrics = context.windowManager.currentWindowMetrics
			val insets: Insets = windowMetrics.windowInsets
				.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())

			Size(
				windowMetrics.bounds.width() - insets.left - insets.right,
				windowMetrics.bounds.height() - insets.top - insets.bottom
			)
		} else {
			val displayMetrics = DisplayMetrics()
			context.windowManager.defaultDisplay.getMetrics(displayMetrics)

			Size(
				displayMetrics.widthPixels,
				displayMetrics.heightPixels
			)
		}
	}
}
