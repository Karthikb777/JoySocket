package com.karthik.socketcontroller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class ControllerNew : AppCompatActivity() {

    private lateinit var socket: Socket

    private var buttonQuit = 0

    private var buttonLeft = 0
    private var buttonRight = 0
    private var buttonUp = 0
    private var buttonDown = 0

    private var buttonX = 0
    private var buttonY = 0
    private var buttonA = 0
    private var buttonB = 0

    private var buttonLb = 0
    private var buttonRb = 0
    private var buttonView = 0
    private var buttonXbox = 0
    private var buttonMenu = 0

    private var leftHorizontal = 50
    private var rightHorizontal = 50
    private var lt = 0
    private var rt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_controller_new)

        val ipIntent = intent.getStringExtra("com.karthik.socketontroller.extra_message").toString()

        val leftHorizontalSeekBar = findViewById<SeekBar>(R.id.leftHorizontal)
        val rightHorizontalSeekBar = findViewById<SeekBar>(R.id.rightHorizontal)
        val ltSeekBar = findViewById<SeekBar>(R.id.lt)
        val rtSeekBar = findViewById<SeekBar>(R.id.rt)

        socket = IO.socket(ipIntent)
        socket.connect()

        leftHorizontalSeekBar.progress = 50
        rightHorizontalSeekBar.progress = 50

        leftHorizontalSeekBar?.setOnSeekBarChangeListener(object :
        SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                leftHorizontal = progress
                emit()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d("leftHorizontalVals", seekBar?.progress.toString())
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.progress = 50
                Log.d("leftHorizontalVals", seekBar?.progress.toString())
            }

        })

        rightHorizontalSeekBar?.setOnSeekBarChangeListener(object :
        SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rightHorizontal = progress
                emit()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d("rightHorizontalVals", seekBar?.progress.toString())
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.progress = 50
                Log.d("rightHorizontalVals", seekBar?.progress.toString())
            }

        })

        ltSeekBar?.setOnSeekBarChangeListener(object :
        SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                lt = progress
                emit()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d("ltVals", seekBar?.progress.toString())
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d("ltVals", seekBar?.progress.toString())
            }

        })

        rtSeekBar?.setOnSeekBarChangeListener(object :
        SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rt = progress
                emit()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d("rtVals", seekBar?.progress.toString())
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d("rtVals", seekBar?.progress.toString())
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        socket.disconnect()
    }

    private fun resetValues() {

        buttonQuit = 0

        buttonLeft = 0
        buttonRight = 0
        buttonUp = 0
        buttonDown = 0

        buttonX = 0
        buttonY = 0
        buttonA = 0
        buttonB = 0

        buttonLb = 0
        buttonRb = 0
        buttonView = 0
        buttonXbox = 0
        buttonMenu = 0

//        leftHorizontal = 50
//        rightHorizontal = 50
//        lt = 0
//        rt = 0
    }

    private fun emit() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.MILLISECONDS.toMillis(200))
            withContext(Dispatchers.Main) {
//                        do work here
                socket.emit(
                        "values",
                  "${buttonQuit.toString()},${buttonLeft.toString()},${buttonRight.toString()},${buttonUp.toString()},${buttonDown.toString()},${buttonX.toString()},${buttonY.toString()},${buttonA.toString()},${buttonB.toString()},${buttonLb.toString()},${buttonRb.toString()},${buttonView.toString()},${buttonXbox.toString()},${buttonMenu.toString()},${leftHorizontal.toString()},${rightHorizontal.toString()},${lt.toString()},${rt.toString()}"
                )
                resetValues()
            }
        }
    }

    fun onButtonUpClicked(view: View) {
        buttonUp = 1
        emit()
    }
    fun onButtonRightClicked(view: View) {
        buttonRight = 1
        emit()
    }
    fun onButtonDownClicked(view: View) {
        buttonDown = 1
        emit()
    }
    fun onButtonLeftClicked(view: View) {
        buttonLeft = 1
        emit()
    }
    fun onButtonBClicked(view: View) {
        buttonB = 1
        emit()
    }
    fun onButtonAClicked(view: View) {
        buttonA = 1
        emit()
    }
    fun onButtonYClicked(view: View) {
        buttonY = 1
        emit()
    }
    fun onButtonXClicked(view: View) {
        buttonX = 1
        emit()
    }
    fun onLbBtnClicked(view: View) {
        buttonLb = 1
        emit()
    }
    fun onViewBtnClicked(view: View) {
        buttonView = 1
        emit()
    }
    fun onMenuBtnClicked(view: View) {
        buttonMenu = 1
        emit()
    }
    fun onRbBtnClicked(view: View) {
        buttonRb = 1
        emit()
    }
    fun onXboxBtnClicked(view: View) {
        buttonXbox = 1
        emit()
    }

    fun onQuitBtnClicked(view: View) {
        buttonQuit = 1
        emit()
        val backIntent = Intent(this, MainActivity::class.java).apply {
//            putExtra(EXTRA_MESSAGE, ipInput.text.toString())
        }
        startActivity(backIntent)
    }
}