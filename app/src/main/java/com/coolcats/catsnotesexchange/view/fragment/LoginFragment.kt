package com.coolcats.catsnotesexchange.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.coolcats.catsnotesexchange.R
import com.coolcats.catsnotesexchange.util.Util.Companion.showError
import com.coolcats.catsnotesexchange.viewmodel.AppViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_fragment_layout.*

class LoginFragment : Fragment() {

    private val viewModel: AppViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.login_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        new_user_txt.setOnClickListener {
            val fragment = SignUpFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out,
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
                .replace(R.id.sign_up_frame, fragment)
                .addToBackStack(fragment.tag)
                .commit()
        }

        login_btn.setOnClickListener { v ->
            val emailInput = login_email_input_txt.text.toString().trim()
            val pwdInput = login_pwd_input_txt.text.toString()
            if (emailInput.isEmpty() || pwdInput.isEmpty()) {
                showError(v, "Email/Password must not be empty")
            } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailInput, pwdInput)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true) {
                                viewModel.loginStatus.value = true
                                childFragmentManager.beginTransaction()
                                    .replace(R.id.sign_up_frame, HomeFragment())
                                    .commit()
                            } else
                                NotesDialogFragment.newInstance(
                                    "E-mail Verification",
                                    "Please check e-mail address $emailInput for a link to verify your account"
                                ).show(childFragmentManager, NotesDialogFragment.tag)
                        } else
                            showError(v, "Error: ${task.exception?.localizedMessage}")
                    }
            }
        }

    }

}