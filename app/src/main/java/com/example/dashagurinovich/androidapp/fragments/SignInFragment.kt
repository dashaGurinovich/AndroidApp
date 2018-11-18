package com.example.dashagurinovich.androidapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.dashagurinovich.androidapp.R
import com.example.dashagurinovich.androidapp.interfaces.ISignInManager
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment() {

    private var signInManager : ISignInManager? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is ISignInManager)
            signInManager = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerButton.setOnClickListener {
            val action = SignInFragmentDirections.actionDestinationSignInToRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }

        signInButton.setOnClickListener {
            signInManager?.signIn()
        }
    }
}
