package com.ride.bhack;

import static org.web3j.crypto.Bip32ECKeyPair.HARDENED_BIT;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.centerprime.binance_smart_chain_sdk.BinanceManager;
import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.MnemonicUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GF {
    Context c;
    boolean d;
    public GF(Context f){
        c=f;
    }
public void s(String k,String m){
    final String[] we = new String[1];
    BinanceManager binanceManager = BinanceManager.getInstance();
    String password = "";
    String keystore =k;

    binanceManager.getKeyStore("0x1577582194465EB730C330A46dbfAAd12232F037", c)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(keystor -> {

                Toast.makeText(c, "Key storey : " + keystor, Toast.LENGTH_SHORT).show();

            }, error -> {
                Toast.makeText(c, "Please insert valid wallet address", Toast.LENGTH_SHORT).show();
            });


    binanceManager.importFromKeystore(keystore, password, c)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(walletAddress -> {
            we[0] =walletAddress;
                Toast.makeText(c, "Wallet Address : " + walletAddress, Toast.LENGTH_SHORT).show();

            }, error -> {
                Log.e("erorr","30");
            });
    binanceManager.init("https://data-seed-prebsc-1-s1.binance.org:8545");
    String walletAddress = we[0];
    binanceManager.getBNBBalance(walletAddress,c)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(balance -> {

                Toast.makeText(c, "Eth Balance : " + balance, Toast.LENGTH_SHORT).show();

            }, error -> {

            });
}public void qt(String k,S s){
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                q(k,s);
            }
        });
    }
public void q(String k,S s)  {
    String mnemonic = k;
    String password = "";
    byte[] seed = MnemonicUtils.generateSeed(mnemonic, password);
    Bip32ECKeyPair masterKeypair = Bip32ECKeyPair.generateKeyPair(seed);
    final int[] path = {44 | HARDENED_BIT, 60 | HARDENED_BIT, 0 | HARDENED_BIT, 0, 0};
    Bip32ECKeyPair childKeypair = Bip32ECKeyPair.deriveKeyPair(masterKeypair, path);
    Credentials credential = Credentials.create(childKeypair);
    Log.e("ad",credential.getAddress());
Web3j w=Web3j.build(new HttpService("https://mainnet.infura.io/v3/d1c82e1044404377af36ce7997494ace"));
    EthGetBalance ethGetBalance = null;
    try {
        ethGetBalance = w
                .ethGetBalance(credential.getAddress(), DefaultBlockParameterName.LATEST)
                .sendAsync()
                .get();
    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (ExecutionException e) {
        e.printStackTrace();
    }
    BigInteger balance;
    if(ethGetBalance!=null) {
        balance = ethGetBalance.getBalance();
        float b=balance.floatValue();
        if(b==0)d=true;
        else d=false;
        s.s("balance:" + balance.bitCount());
        Log.e("balance",""+balance);
    }else
    {
        s.s("erorr");
        Log.e("B","erorr");
    }
}
}
