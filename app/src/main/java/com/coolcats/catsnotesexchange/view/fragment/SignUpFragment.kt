package com.coolcats.catsnotesexchange.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coolcats.catsnotesexchange.R
import com.coolcats.catsnotesexchange.mod.User
import com.coolcats.catsnotesexchange.util.Util.Companion.getUserDB
import com.coolcats.catsnotesexchange.util.Util.Companion.showError
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.new_user_fragment_layout.*

class SignUpFragment : Fragment() {

    private val userReference = getUserDB()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.new_user_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submit_btn.setOnClickListener { v ->
            val emailInput = new_email_input_txt.text.toString()
            val usernameInput = new_username_txt.text.toString()
            val pwdInput = new_pwd_input_txt.text.toString()
            val pwdConfirm = confirm_pwd_input_txt.text.toString()

            if (pwdInput != pwdConfirm)
                showError(v, "Passwords do not match")
            else if (emailInput.isEmpty() || usernameInput.isEmpty() || pwdInput.isEmpty() || pwdConfirm.isEmpty())
                showError(v, "All fields must not be empty")
            else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailInput, pwdInput)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val key = userReference.push().key ?: ""
                            val user = User(key, usernameInput)
                            userReference.child(key).setValue(user)
                            NotesDialogFragment.newInstance(
                                "E-mail Verification",
                                "Please check e-mail address $emailInput for verification link."
                            ).show(childFragmentManager, NotesDialogFragment.tag)
                            childFragmentManager.popBackStack()
                        }
                    }
            }
        }
    }
}