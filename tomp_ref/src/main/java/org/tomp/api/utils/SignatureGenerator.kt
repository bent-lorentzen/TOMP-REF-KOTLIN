package org.tomp.api.utils

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.Key
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.PrivateKey
import java.security.Signature
import java.security.SignatureException
import java.security.UnrecoverableKeyException
import java.security.cert.CertificateException
import java.util.Base64
import java.util.Locale

class SignatureGenerator {
    @Throws(
        FileNotFoundException::class,
        KeyStoreException::class,
        CertificateException::class,
        UnrecoverableKeyException::class,
        UnsupportedEncodingException::class,
        NoSuchAlgorithmException::class,
        InvalidKeyException::class,
        SignatureException::class
    )
    fun generateSignature(
        httpMethod: String,
        url: String,
        sensorID: String,
        thumbprint: String?,
        msgBody: String,
        toID: String?,
        timestamp: String,
        pfx: String?,
        pw: String?
    ): String {
        var thumbprint = thumbprint
        val ks = loadKeyStore(pfx, pw)
        var maasHubKey: Key? = null
        var alias = ""
        try {
            val enumeration = ks.aliases()
            while (enumeration.hasMoreElements()) {
                alias = enumeration.nextElement()
                println("alias name: $alias")
                break
            }
            maasHubKey = ks.getKey(alias, pw!!.toCharArray())
        } catch (e: KeyStoreException) {
            throw UnrecoverableKeyException("Error loading key")
        } catch (e: NoSuchAlgorithmException) {
            throw UnrecoverableKeyException("Error loading key")
        } catch (e: UnrecoverableKeyException) {
            throw UnrecoverableKeyException("Error loading key")
        }
        thumbprint = ""
        val toEncode = (httpMethod + "|" + url.uppercase(Locale.getDefault()) + "|" + sensorID + "|" + toID + "|TO|" + timestamp + "|"
                + thumbprint.uppercase(Locale.getDefault()) + "|" + msgBody)
        println(toEncode)
        val byteConcString = toEncode.toByteArray(charset("UTF8"))
        val sig = Signature.getInstance("SHA256withRSA")
        sig.initSign(maasHubKey as PrivateKey?)
        sig.update(byteConcString)
        val sigInBytes = sig.sign()
        return Base64.getEncoder().encodeToString(sigInBytes)
    }

    @Throws(FileNotFoundException::class, KeyStoreException::class, CertificateException::class)
    private fun loadKeyStore(pfx: String?, pw: String?): KeyStore {
        val keyStore = KeyStore.getInstance("PKCS12")
        // String pfx =
        // getClass().getClassLoader().getResource("reference_environment.pfx").getFile();
        val ksFile = File(pfx)
        val keyStoreData: InputStream = FileInputStream(ksFile)
        try {
            keyStore.load(keyStoreData, pw!!.toCharArray())
        } catch (e: IOException) {
            throw CertificateException("Error loading certificate")
        } catch (e: NoSuchAlgorithmException) {
            throw CertificateException("Error loading certificate")
        } catch (e: CertificateException) {
            throw CertificateException("Error loading certificate")
        }
        return keyStore
    }
}
