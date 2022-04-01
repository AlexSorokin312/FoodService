package com.example.foodservice

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class ChipGroupCustomAppBarBehaviour(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attrs){

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        var toolbar = dependency as AppBarLayout
        child.y = toolbar.layoutParams.height + toolbar.y
        child.requestLayout()
        return false
    }

}