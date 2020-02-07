package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Telephony
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.model.Contact
import com.example.myapplication.model.Sms
import java.text.SimpleDateFormat
import java.util.*

class ContactListActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_READ_CONTACTS = 79
    }

    private val contacts = mutableListOf<Contact>()
    private val sms = mutableListOf<Sms>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        getContacts()
        getSms()

        sms.map {
            if (it.dateSent > 0) {
                val calendar = Calendar.getInstance().apply {
                    timeInMillis = it.dateSent
                }
                val sdf = SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.getDefault())
                val date = sdf.format(calendar.time)

                Log.d("Test", "${it.address} ${it.body} ${sdf.format(calendar.time)}")
            } else {
                Log.d("Test", "${it.address} ${it.body} NUll")
            }
        }

//
//        contacts.map {
//            Log.d("Test", "${it.id} ${it.name} ${it.phoneNumber}")
//        }

    }

    fun getContacts() {
        val cr = contentResolver
        val cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        var contactName = ""
        var contactId = ""
        var contactPhoneNumber = ""

        if ((cur != null)) {
            if (cur.count > 0) {
                while (cur.moveToNext()) {
                    val id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID))
                    val name =
                        cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    contactName = name
                    contactId = id

                    if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        val pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            arrayOf(id),
                            null
                        )

                        if (pCur != null) {
                            while (pCur.moveToNext()) {
                                val phoneNo =
                                    pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                                contactPhoneNumber = phoneNo
                            }
                            pCur.close()
                        }
                    }

                    contacts.add(Contact(contactName, contactId, contactPhoneNumber))
                }
            }
            cur.close()
        }
    }

    fun getSms() {
        val uri = Telephony.Sms.Inbox.CONTENT_URI
        val cr = contentResolver
        val cur = cr.query(uri, null, null, null, null)

        if (cur != null) {
            while (cur.moveToNext()) {
                for (i in 0 until cur.columnCount) {
                    val field = cur.getColumnName(i)
                    Log.d("Tes", field)
                    var body = ""
                    var address = ""
                    var timestamp: Long = 0

                    when (field) {
                        "address" -> {
                            address = cur.getString(i)
                        }
                        "body" -> {
                            body = cur.getString(i)
                        }
                        "date_sent" -> {
                            timestamp = cur.getString(i).toLong()
                        }
                    }
                    sms.add(Sms(body, address, timestamp))

                }
            }
            cur.close()
        }

    }
}
