package com.coolcats.catsnotesexchange.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.coolcats.catsnotesexchange.R
import com.coolcats.catsnotesexchange.mod.User
import com.coolcats.catsnotesexchange.viewmodel.AppViewModel
import kotlinx.android.synthetic.main.user_info_fragment_layout.*

class UserInfoFragment : Fragment() {

    private val viewModel: AppViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.user_info_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userData.observe(viewLifecycleOwner, {
            user_name_txt.text = it.userName
        })
        viewModel.getCurrentUser()

        user_avatar_image.setImageResource(R.drawable.ic_no_avatar_img)


        viewModel.createStatusData.observe(viewLifecycleOwner, {
            if (it) {
                add_note_image.visibility = View.VISIBLE
                add_note_image.setOnClickListener {
                    val fragment = CreateNoteFragment()
                    requireActivity().supportFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            android.R.anim.fade_in,
                            android.R.anim.fade_out,
                            android.R.anim.fade_in,
                            android.R.anim.fade_out
                        )
                        .addToBackStack(fragment.tag)
                        .replace(R.id.new_note_frame, fragment)
                        .commit()
                }
            } else
                add_note_image.visibility = View.GONE
        })


    }

}