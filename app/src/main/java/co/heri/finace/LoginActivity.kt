package co.heri.finace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_btn.setOnClickListener { view ->
            val email = email_textedit.text.toString()
            val password = password_textedit.text.toString()

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("username", "heriagape")
                startActivity(intent)

        }
    }
}
