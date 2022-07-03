package com.starzplay.view.player

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aaa.timers.TimeTicker
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player.*
import com.starzplay.R
import com.starzplay.base.views.BaseFragment
import com.starzplay.databinding.VideoPlayerLayoutBinding
import com.starzplay.ext.beGone
import com.starzplay.ext.beVisible
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.TimeUnit


class PlayerFragment: BaseFragment<VideoPlayerLayoutBinding>() {

    override val layout: Int
        get() = R.layout.video_player_layout


    private val player: ExoPlayer by lazy {
        ExoPlayer.Builder(
            requireContext()
        ).build()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachPlayerWithView()

        setupPlayerView()

        setupPlayerCallbacks()

        loadMedia()
    }

    private fun attachPlayerWithView() {
        player.setVideoSurfaceView(binding.playerSurfaceView)
    }

    private fun setupPlayerView() {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE

        attachPlayerWithControls()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun attachPlayerWithControls() = binding.apply {
        playPauseBtn.setOnClickListener {
            if (player.isPlaying) {
                player.pause()
            } else {
                player.play()
            }

            timerRunner.restart()
        }

        root.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (playerControls.isVisible) {
                    hideControls()
                } else {
                    showControls()
                }
            }

            true
        }
    }

    private val timerRunner = object : TimeTicker(
        dispatcher = Dispatchers.Main,
        tickMillis = TimeUnit.SECONDS.toMillis(1),
        maxMillis = TimeUnit.SECONDS.toMillis(10)
    ) {
        override fun tick() {
            binding.playerSeekBar.progress = player.currentPosition.toInt()
        }

        override fun complete() {
            hideControls()
        }
    }

    private fun setupPlayerCallbacks() {
        player.addListener(playerCallbacks)
    }

    private val playerCallbacks: Listener = object: Listener {

        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)

            when (playbackState) {
                STATE_IDLE -> {
                    binding.playerBuffering.beGone()
                }

                STATE_BUFFERING -> {
                    binding.playerBuffering.beVisible()
                }

                STATE_READY -> {
                    onPlayerReady()
                }

                STATE_ENDED -> {
                    onPlaybackEnded()
                }
            }
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            if (isPlaying) {
                binding.playPauseBtn.setImageResource(R.drawable.ic_pause)
            } else {
                binding.playPauseBtn.setImageResource(R.drawable.ic_play)
            }
        }

        override fun onSeekBackIncrementChanged(seekBackIncrementMs: Long) {
            Log.i(TAG, "onSeekBackIncrementChanged=$seekBackIncrementMs")
        }

        override fun onSeekForwardIncrementChanged(seekForwardIncrementMs: Long) {
            Log.i(TAG, "onSeekBackIncrementChanged=$seekForwardIncrementMs")
        }

        override fun onPlayerError(error: PlaybackException) {
            //  TODO: An error occurred perhaps show a dialog to exit player?
            Log.e(TAG, error.message, error)
        }
    }

    private fun onPlayerReady() {
        binding.playerBuffering.beGone()
        setupSeekbar()
        player.play()
        timerRunner.restart()
    }

    private fun onPlaybackEnded() {
        binding.playerBuffering.beGone()
        findNavController().popBackStack()
    }

    private fun setupSeekbar() = binding.apply {
        playerSeekBar.max = player.duration.toInt()

        playerSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                player.seekTo(playerSeekBar.progress.toLong())
                timerRunner.restart()
            }
        })
    }

    private val args: PlayerFragmentArgs by navArgs()
    private fun loadMedia() {
        val url = args.url

        val mediaItem: MediaItem = MediaItem.fromUri(Uri.parse(url))
        player.setMediaItem(mediaItem)
        player.prepare()
        player.playWhenReady = true
    }

    private fun hideControls() {
        binding.playerControls.beGone()
    }

    private fun showControls() {
        binding.playerControls.beVisible()

        timerRunner.restart()
    }

    override fun onPause() {
        player.pause()

        super.onPause()
    }

    override fun onDestroyView() {
        player.stop()

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER

        super.onDestroyView()
    }

    companion object {
        const val TAG = "PlayerFragment"
    }
}