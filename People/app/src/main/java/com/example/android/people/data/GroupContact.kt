package com.example.android.people.data

import android.net.Uri
import androidx.core.net.toUri
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.random.nextUInt

/**
 * Created by ninhhk on 1/14/22.
 */
class GroupContact(
    id: Long,
    groupName: String,
    icon: String
) : Contact(id, groupName, icon) {
    private val contacts = listOf(
        object : Contact(1L, "Cat", "cat.jpg") {
            override fun reply(text: String) = buildReply().apply { this.text = "Meow" }
        },
        object : Contact(2L, "Dog", "dog.jpg") {
            override fun reply(text: String) = buildReply().apply { this.text = "Woof woof!!" }
        },
        object : Contact(3L, "Parrot", "parrot.jpg") {
            override fun reply(text: String) = buildReply().apply { this.text = text }
        }
    )

    private val random = Random(System.currentTimeMillis())

    private var index = 0

    override fun reply(text: String): Message.Builder {
        index = random.nextInt(0, contacts.size)
        return contacts[index].reply(text)
    }

    override val replyName: String
        get() = contacts[index].name

    override val iconUri: Uri
        get() = contacts[index].iconUri

    val groupIconUri: Uri
        get() = "content://com.example.android.people/icon/$id".toUri()
}