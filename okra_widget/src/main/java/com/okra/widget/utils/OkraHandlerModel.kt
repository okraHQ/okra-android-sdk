package com.okra.widget.utils

import com.okra.widget.handlers.OkraHandler

class OkraHandlerModel private constructor() {
    interface OnOkraResponseReceived {
        fun onOkraResponseReceived()
    }

    private var mListener: OnOkraResponseReceived? = null

    var okraHandler:OkraHandler? = null


    fun setListener(listener: OnOkraResponseReceived) {
        mListener = listener
    }


    fun onOkraResponseReceived(okraHandler: OkraHandler) {
        if (mListener != null) {
            this.okraHandler = okraHandler
            notifyStateChange()
        }
    }

    private fun notifyStateChange() {
        mListener?.onOkraResponseReceived()
    }

    companion object {
        private var mInstance: OkraHandlerModel? = null
        val instance: OkraHandlerModel?
            get() {
                if (mInstance == null) {
                    mInstance = OkraHandlerModel()
                }
                return mInstance
            }
    }
}