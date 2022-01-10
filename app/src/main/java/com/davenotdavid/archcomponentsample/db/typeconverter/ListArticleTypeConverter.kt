package com.davenotdavid.archcomponentsample.db.typeconverter

import androidx.room.TypeConverter
import com.davenotdavid.archcomponentsample.model.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * TODO
 */
class ListArticleTypeConverter {

    @TypeConverter
    fun fromListJson(json: String?): List<Article>? {
        json?.let { jsn ->
            val listType = object : TypeToken<List<Article>>() {}.type
            return Gson().fromJson(jsn, listType)
        }

        return null
    }

    @TypeConverter
    fun listToJson(list: List<Article>?): String? {
        list?.let { lst ->
            return Gson().toJson(lst)
        }

        return null
    }

}
