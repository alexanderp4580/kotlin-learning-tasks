package learning.tasks.view

enum class Visibility {
    VISIBLE,
    GONE
}

interface OnClickLister {
    fun onClick()
}

open class View(var visibility: Visibility = Visibility.VISIBLE, var onClickLister: OnClickLister? = null){
    fun click() = onClickLister?.onClick()
}