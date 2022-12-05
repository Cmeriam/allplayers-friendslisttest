package edu.farmingdale.alrajab.allplayers_friendslist.data

import com.google.firebase.database.Exclude

data class Contact(
    @get:Exclude
    var id: String? = null,
    var username: String? = null,
    @get:Exclude
    var isDeleted: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return if(other is Contact){
            other.id == id
        }else false
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (username?.hashCode() ?: 0)
        result = 31 * result + isDeleted.hashCode()
        return result
    }
}