package com.decode.tumblr.ui.details

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.decode.tumblr.R
import kotlinx.android.synthetic.main.layout_fragment_details.*

class FragmentDetails : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_details, container, false)
    }

    override fun onStart() {
        super.onStart()
        getParcelableData()
    }

    private fun getParcelableData() {
        val args: FragmentDetailsArgs

        if (arguments != null) {
            args = FragmentDetailsArgs.fromBundle(arguments!!)
        } else {
            txtParcelableData!!.text = getString(R.string.no_data)
            return
        }

        val data = "<b> Blog name:</b> " + args.postObject.title

        txtParcelableData!!.text = Html.fromHtml(data)

        Glide.with(context!!)
                .load(args.postObject.photoObject?.url)
                .error(R.drawable.no_image_available)
                .into(imgPostLarge!!)
    }
}
