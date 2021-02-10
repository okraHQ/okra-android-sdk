package com.okra.widget.services

import com.okra.widget.models.OkHiModels.Location
import com.okra.widget.models.OkHiModels.LocationException
import com.okra.widget.models.OkHiModels.User
import io.okhi.android_core.models.OkHiException
import io.okhi.android_core.models.OkHiLocation
import io.okhi.android_core.models.OkHiUser

interface AddressVerificationListener{
    fun onSuccessCollection(user: User, location: Location)
    fun onSuccessStartVerification(result:String)
    fun onErrorCollectionAction(locationException: LocationException)
    fun onErrorVerificationAction(locationException: LocationException)
    fun permissionRequestHandler(result:Boolean,type:PermissionType)
    fun permissionRequestHandlerError(locationException: LocationException, type:PermissionType)
}

enum class PermissionType{
    OK_COLLECT,OK_VERIFY
}
