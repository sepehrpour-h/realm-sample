package com.sepehrpour.realmsample.domain.viewmodels

import androidx.lifecycle.ViewModel
import com.sepehrpour.realmsample.data.model.Counter
import io.realm.kotlin.Realm

import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults


class CounterViewModel : ViewModel() {
    private var realm: Realm? = null

    init {
        val config = RealmConfiguration.create(schema = setOf(Counter::class))
        realm = Realm.open(config)

        realm!!.writeBlocking {
            if (this.query<Counter>().find().isEmpty()) {
                this.copyToRealm(Counter().apply {
                    id = 1
                    value = 0
                })
            }
        }
    }


    fun getCounter(): RealmResults<Counter> {
        return realm?.query<Counter>("id == $0", 1)?.find() ?: emptyList<Counter>() as RealmResults<Counter>
    }


    fun increaseCounter() {
        realm?.writeBlocking {
            val counter = this.query<Counter>("id == 1").find().first()
            counter.value += 1
        }
    }

    fun decreaseCounter() {
        realm?.writeBlocking {
            val counter = this.query<Counter>("id == 1").find().first()
            if (counter.value > 0) {
                counter.value -= 1
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        realm?.close()
    }
}