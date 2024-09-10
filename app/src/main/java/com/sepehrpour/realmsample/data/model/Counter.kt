package com.sepehrpour.realmsample.data.model

import io.realm.kotlin.types.RealmObject


class Counter : RealmObject {
    var id: Int = 0
    var value: Int = 0
//    var name: String? = null
}