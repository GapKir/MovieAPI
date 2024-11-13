package com.dev.common

interface HasComponents {
    fun getComponentByViewModelClass(clazz: Class<*>): MavericksComponent
    fun onComponentBuilt(component: Any)
}
