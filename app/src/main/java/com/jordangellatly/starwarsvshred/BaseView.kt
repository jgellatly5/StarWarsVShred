package com.jordangellatly.starwarsvshred

interface BaseView<T> {
    fun setPresenter(presenter: T)
}