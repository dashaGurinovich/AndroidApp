package com.example.dashagurinovich.androidapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.dashagurinovich.androidapp.interfaces.IImeiManager
import com.example.dashagurinovich.androidapp.interfaces.IRegisterManager
import com.example.dashagurinovich.androidapp.interfaces.ISignInManager
import com.example.dashagurinovich.androidapp.services.ImeiService
import kotlinx.android.synthetic.main.activity_auth.*

class AuthenticationActivity : AppCompatActivity(), IImeiManager, IRegisterManager, ISignInManager {

    private val imeiService = ImeiService(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setSupportActionBar(auth_toolbar)

        val navController = Navigation.findNavController(this, R.id.auth_activity_fragment)
        setupActionBar(navController)
    }

    private fun setupActionBar(navController: NavController) {
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navController = Navigation.findNavController(this, R.id.auth_activity_fragment)

        val navigated = NavigationUI.onNavDestinationSelected(item!!, navController)
        return navigated || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.auth_activity_fragment).navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_menu_toolbar, menu)
        return true
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MainActivity.REQUEST_PERMISSION_PHONE_STATE -> {
                //Show the IMEI if the permission was granted
                if ((grantResults.isNotEmpty() &&
                                grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    imeiService.getImei()
                }

                //Show the explanation for the permission if it was denied
                else if ((grantResults.isNotEmpty() &&
                                grantResults[0] == PackageManager.PERMISSION_DENIED)) {
                    showPermissionExplanation(Manifest.permission.READ_PHONE_STATE,
                            getString(R.string.read_phone_state_permission_explanation),
                            MainActivity.REQUEST_PERMISSION_PHONE_STATE)
                }
                return
            }
        }
    }

    override fun getImei() : String {
        return imeiService.getImei()
    }

    override fun signIn() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun register(login: String, password: String) {

    }

}