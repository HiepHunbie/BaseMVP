
package com.hiepnt.basemvp.utils

import android.content.Context
import android.content.SharedPreferences
import jp.takuji31.koreference.KoreferenceModel
import jp.takuji31.koreference.booleanPreference
import jp.takuji31.koreference.stringPreference

class AppPreferences(sharedPreferences: SharedPreferences): KoreferenceModel(sharedPreferences) {
    constructor(context: Context): this(context.applicationContext.getSharedPreferences(context
        .packageName, Context.MODE_PRIVATE))

    var accessToken: String by stringPreference("")
}

