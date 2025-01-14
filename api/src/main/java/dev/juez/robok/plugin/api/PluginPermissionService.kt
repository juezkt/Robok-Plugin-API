package dev.juez.robok.plugin.api

import android.app.Service
import android.content.Intent
import android.os.IBinder

class PluginPermissionService : Service() {

  private val binder = PermissionBinder()

  override fun onBind(intent: Intent?): IBinder? {
    return binder
  }

  inner class PermissionBinder : IPluginPermissionService.Stub() {

    override fun requestPermission(callerPackage: String): Boolean {
      return hasPermission(callerPackage)
    }

    private fun hasPermission(callerPackage: String): Boolean {
      return callerPackage == "com.example.trustedapp"
    }
  }
}
