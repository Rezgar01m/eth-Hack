package com.ride.bhack

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Surface
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ComponentActivity
import com.ride.bhack.ui.theme.BHackTheme
import org.w3c.dom.Text
import com.ride.bhack.databinding.FBinding
import io.github.novacrypto.bip39.wordlists.English

import io.github.novacrypto.bip39.MnemonicGenerator
import io.github.novacrypto.bip39.Words
import java.lang.StringBuilder
import java.security.SecureRandom
import java.util.*



class MainActivity : AppCompatActivity() {
private lateinit var s:FBinding
    @SuppressLint("CheckResult")
    var m=""
    var f=false
    var p=true
    var i=0;
    var t=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        s = FBinding.inflate(layoutInflater)
        setContentView(s.root)
     s.s.setOnClickListener {
         Thread(Runnable {
             var MonkeyPhone = MonkeyPhone(this);
             MonkeyPhone.connect("6505551212")
         })
      m()
         var phone =MonkeyPhone(this);
     }
        s.s.y=400f
        s.b1 .y=320f
    }
    fun m() {
        var handler = Handler()
        var handlerTask = Runnable { // do something
            e()
           // handler.postDelayed(handlerTask, 1000)
        }
        handlerTask.run()
    }
    fun u(){
        if (!f) {
            e()
            f = false

            s.s.setText("stop")
            s.s.visibility=View.GONE
        }
        else {
            p=false
            s.s.setText("Start")
        }
    }
    fun e(){
        val sb = StringBuilder()
        val entropy = ByteArray(Words.TWELVE.byteLength())
        SecureRandom().nextBytes(entropy)
        MnemonicGenerator(English.INSTANCE)
            .createMnemonic(entropy, sb::append)
        Arrays.fill(entropy, 0.toByte())
        s.t.setText(sb.toString()+":$m")
        s.t.textSize = 50F
        val g=GF(this)
//        g.s(sb.toString(),m)
        g.q(sb.toString(), S {
            s.b.text=it
            if(g.d){
                if (p) {
if (t)
                    e()
                }
                s.b1.text="I"+i++
                s.b1.textSize=40f
                Log.w("I",""+i)
            }
        })
    }
}

