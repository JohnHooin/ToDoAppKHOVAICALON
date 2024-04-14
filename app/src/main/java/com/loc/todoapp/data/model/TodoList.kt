package com.loc.todoapp.data.model

import android.icu.text.SimpleDateFormat
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.util.Date
import java.util.Locale

class TodoList: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var title: String = "Tưqưqưqưqaưqư"
    var subTitle: String = "qưqưqưqưqưqưqư"
    var isDone: Boolean = true
    var addedTime: Long = System.currentTimeMillis()
}


val TodoList.addDate: String
    get() = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        .format(Date(addedTime))
