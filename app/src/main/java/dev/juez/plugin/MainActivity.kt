package dev.juez.plugin

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import dev.juez.plugin.databinding.ActivityMainBinding
import dev.juez.robok.plugin.api.Constants
import dev.juez.robok.plugin.api.IPluginPermissionService

class MainActivity : Activity() {
  private var _binding: ActivityMainBinding? = null
  private val binding: ActivityMainBinding
    get() = checkNotNull(_binding) { "Activity has been destroyed" }

  private var permissionService: IPluginPermissionService? = null

  private val serviceConnection =
    object : ServiceConnection {
      override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as IPluginPermissionService.Stub
        permissionService = binder

        val hasPermission = permissionService?.requestPermission(Constants.PACKAGE) ?: false
        if (hasPermission) {
          Toast.makeText(this@MainActivity, "Permission granted!", Toast.LENGTH_SHORT).show()
        } else {
          Toast.makeText(this@MainActivity, "Permission denied.", Toast.LENGTH_SHORT).show()
        }
      }

      override fun onServiceDisconnected(name: ComponentName?) {
        permissionService = null
      }
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    _binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val intent = Intent("dev.juez.robok.plugin.api.PluginPermissionService")
    intent.setPackage("dev.juez.robok.plugin.api")
    bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
  }

  override fun onDestroy() {
    super.onDestroy()
    unbindService(serviceConnection)
    _binding = null
  }
}
