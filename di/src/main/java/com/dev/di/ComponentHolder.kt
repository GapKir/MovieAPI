package com.dev.di

import com.dev.common.HasComponents
import com.dev.common.MavericksComponent

class ComponentHolder: HasComponents {
    private var dependencyComponents: HashSet<Any> = HashSet()

    override fun getComponentByViewModelClass(clazz: Class<*>): MavericksComponent {
        return dependencyComponents.firstNotNullOfOrNull { component ->
            (component as? MavericksComponent)?.takeIf {
                component.dependentViewModels().contains(clazz)
            }
        } ?: error("Component is not found $clazz")
    }

    override fun onComponentBuilt(component: Any) {
        dependencyComponents.add(component)
    }
}