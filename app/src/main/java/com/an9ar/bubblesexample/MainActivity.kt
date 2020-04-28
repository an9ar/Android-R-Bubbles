package com.an9ar.bubblesexample

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var builder1: Notification.Builder
    lateinit var builder2: Notification.Builder
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNotificationManager()
        setUpNotificationChannel()
        setUpBubbleNotification()

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btnSimpleBubble.setOnClickListener {
            notificationManager.notify(1, builder1.build())
        }

        btnExpandableBubble.setOnClickListener {
            notificationManager.notify(2, builder2.build())
        }
    }

    private fun setUpNotificationManager() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private fun setUpNotificationChannel() {
        notificationChannel = NotificationChannel("bubble_channel", "channel_name", NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.description = "channel description"
        notificationChannel.setAllowBubbles(true)
    }

    private fun setUpBubbleNotification() {

        // Create bubble intent
        val target = Intent(this, BubbleActivity::class.java)
        val bubbleIntent = PendingIntent.getActivity(this, 0, target, PendingIntent.FLAG_UPDATE_CURRENT /* flags */)

        // Create bubble metadata
        val simpleBubbleData = Notification.BubbleMetadata.Builder()
            .setDesiredHeight(500)
            .setIcon(Icon.createWithResource(this, R.mipmap.ic_bot_photo_round))
            .setIntent(bubbleIntent)
            //.setAutoExpandBubble(true)
            //.setSuppressNotification(true)
            .build()

        val expandableBubbleData = Notification.BubbleMetadata.Builder()
            .setDesiredHeight(1000)
            .setIcon(Icon.createWithResource(this, R.mipmap.ic_bot_photo_round))
            .setIntent(bubbleIntent)
            .setAutoExpandBubble(true)
            .setSuppressNotification(true)
            .build()

        // Create notification
        val chatBot = Person.Builder()
            .setBot(true)
            .setName("Person1")
            .setImportant(true)
            .build()

        val person2 = Person.Builder()
            .setBot(true)
            .setName("Person2")
            .setImportant(true)
            .build()

        val style: Notification.MessagingStyle = Notification.MessagingStyle(chatBot)
            /*.addMessage(
                "Hi Reto!", currentTimeMillis() - (1000*20), "Ian Lake"
            )
            .addMessage(
                "How's it going?", (currentTimeMillis() - (1000*20)) + (1000*10), "Ian Lake"
            )*/

        builder1 = Notification.Builder(this, notificationChannel.id)
            .setSmallIcon(R.drawable.ic_message)
            .setBubbleMetadata(simpleBubbleData)
            .addPerson(chatBot)
            .setStyle(style)

        builder2 = Notification.Builder(this, notificationChannel.id)
            .setSmallIcon(R.drawable.ic_message)
            .setBubbleMetadata(expandableBubbleData)
            .addPerson(person2)
            .setStyle(style)

        notificationManager.createNotificationChannel(notificationChannel)
    }
}
