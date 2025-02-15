/*
 * Transport Operator MaaS Provider API
 * An API between MaaS providers and transport operators for booking trips and corresponding assets. <p>The documentation (examples, process flows and sequence diagrams) can be found at <a href=\"https://github.com/TOMP-WG/TOMP-API/\">github</a>.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package io.swagger.client

import com.squareup.okhttp.MediaType
import com.squareup.okhttp.ResponseBody
import okio.Buffer
import okio.BufferedSource
import okio.ForwardingSource
import okio.Okio
import okio.Source
import java.io.IOException

class ProgressResponseBody(private val responseBody: ResponseBody, private val progressListener: ProgressListener) : ResponseBody() {
    interface ProgressListener {
        fun update(bytesRead: Long, contentLength: Long, done: Boolean)
    }

    private var bufferedSource: BufferedSource? = null
    override fun contentType(): MediaType {
        return responseBody.contentType()
    }

    @Throws(IOException::class)
    override fun contentLength(): Long {
        return responseBody.contentLength()
    }

    @Throws(IOException::class)
    override fun source(): BufferedSource {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()))
        }
        return bufferedSource!!
    }

    private fun source(source: Source): Source {
        return object : ForwardingSource(source) {
            var totalBytesRead = 0L
            @Throws(IOException::class)
            override fun read(sink: Buffer, byteCount: Long): Long {
                val bytesRead = super.read(sink, byteCount)
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += if (bytesRead != -1L) bytesRead else 0
                progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1L)
                return bytesRead
            }
        }
    }
}
