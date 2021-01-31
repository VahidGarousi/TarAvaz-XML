package ir.vbile.app.taravaz.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.vbile.app.taravaz.R

interface TarAvazView {
    val rootView: CoordinatorLayout?
    val viewContext: Context?
    fun setProgressIndicator(mustShow: Boolean) {
        rootView?.let {
            viewContext?.let { context ->
                var loadingView = it.findViewById<View>(R.id.loadingView)
                if (loadingView == null && mustShow) {
                    loadingView =
                        LayoutInflater.from(context).inflate(R.layout.view_loading, it, false)
                    it.addView(loadingView)
                }
                loadingView?.visibility = if (mustShow) View.VISIBLE else View.GONE
            }
        }
    }
    fun setUpViews(view: View) = view.apply { }

    fun longToast(string: String) {
        Toast.makeText(viewContext, string, Toast.LENGTH_LONG).show()
    }
    fun toast(string: Int) {
        Toast.makeText(viewContext, string, Toast.LENGTH_SHORT).show()
    }
}

abstract class TarAvazFragment(
    @LayoutRes layoutId: Int
) : Fragment(layoutId), TarAvazView {
    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout?

    override val viewContext: Context?
        get() = context

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
    }
}

abstract class TarAvazActivity(
) : AppCompatActivity(), TarAvazView {
    override val rootView: CoordinatorLayout?
        get() {
            val viewGroup = window.decorView.findViewById<ViewGroup>(android.R.id.content)
            if (viewGroup !is CoordinatorLayout) {
                viewGroup.children.forEach {
                    if (it is CoordinatorLayout) {
                        return it
                    }
                }
            } else
                return viewGroup
            throw IllegalStateException("RooView must be instance of coordinator layout")
        }
    override val viewContext: Context?
        get() = this

}

abstract class TarAvazViewModel() : ViewModel(){
    var progressBarLiveData: MutableLiveData<Boolean> = MutableLiveData()
}
