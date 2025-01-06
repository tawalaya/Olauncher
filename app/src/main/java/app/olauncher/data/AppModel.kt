package app.olauncher.data

import android.os.UserHandle
import java.text.CollationKey
import com.google.gson.Gson

data class AppModel(
    val appLabel: String,
    val key: CollationKey?,
    val appPackage: String,
    val activityClassName: String?,
    val isNew: Boolean? = false,
    val user:UserHandle?,
) : Comparable<AppModel> {
    override fun compareTo(other: AppModel): Int = when {
        key != null && other.key != null -> key.compareTo(other.key)
        else -> appLabel.compareTo(other.appLabel, true)
    }

    companion object Factory {
        fun fromJson(data: String): AppModel {
            return Gson().fromJson(data, AppModel::class.java)
        }
    }

    fun toJson(): String {
        val serilizable_model = AppModel(
            appLabel,
            null,
            appPackage,
            activityClassName,
            false,
            null
        )
        return Gson().toJson(serilizable_model)
    }
}