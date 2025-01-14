package dev.juez.robok.plugin.api;

interface IPluginPermissionService {
  boolean requestPermission(String callerPackage);
}