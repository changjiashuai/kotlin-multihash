package io.ipfs.multiformats.multihash

import java.io.EOFException
import java.io.IOException
import java.io.InputStream

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2018/7/19.
 */
object VarInt {

    fun readVarint(inputStream: InputStream): Long {
        var x: Long = 0
        var s = 0
        for (i in 0..9) {
            val b = inputStream.read()
            if (b == -1)
                throw EOFException()
            if (b < 0x80) {
                if (i > 9 || i == 9 && b > 1) {
                    throw IllegalStateException("Overflow reading varint" + -(i + 1))
                }
                return x or (b.toLong() shl s)
            }
            x = x or (b.toLong() and 0x7f shl s)
            s += 7
        }
        throw IllegalStateException("Varint too long!")
    }

    fun putUvarint(buf: ByteArray, x: Long): ByteArray {
        var x = x
        var i = 0
        while (x >= 0x80) {
            buf[i] = (x or 0x80).toByte()
            x = x shr 7
            i++
        }
        buf[i] = x.toByte()
        return buf
    }

    fun encodeVarint(inp: Int): ByteArray {
        var value = inp
        val byteArrayList = ByteArray(10)
        var i = 0
        while (value and 0xFFFFFF80.toInt() != 0) {
            byteArrayList[i++] = ((value and 0x7F) or 0x80).toByte()
            value = value ushr 7
        }
        byteArrayList[i] = (value and 0x7F).toByte()
        val out = ByteArray(i + 1)
        while (i >= 0) {
            out[i] = byteArrayList[i]
            i--
        }
        return out.reversedArray()
    }

    fun encodeVarint(inp: Long): ByteArray {
        var value = inp
        val byteArrayList = ByteArray(10)
        var i = 0
        while (value and 0x7FL.inv() != 0L) {
            byteArrayList[i++] = ((value and 0x7F) or 0x80).toByte()
            value = value ushr 7
        }
        byteArrayList[i] = (value and 0x7F).toByte()
        val out = ByteArray(i + 1)
        while (i >= 0) {
            out[i] = byteArrayList[i]
            i--
        }
        return out.reversedArray()
    }

    fun decodeVarInt(src: ByteArray): Long {
        return decodeVarint(src.reversedArray().inputStream())
    }

    fun decodeVarint(inp: InputStream, bitLimit: Int = 32, eofOnStartAllowed: Boolean = false): Long {
        var result = 0L
        var shift = 0
        var b: Int
        do {
            if (shift >= bitLimit) {
                // Out of range
                throw IndexOutOfBoundsException("Varint too long")
            }
            // Get 7 bits from next byte
            b = inp.read()
            if (b == -1) {
                if (eofOnStartAllowed && shift == 0) return -1
                else throw IOException("Unexpected EOF")
            }
            result = result or (b.toLong() and 0x7FL shl shift)
            shift += 7
        } while (b and 0x80 != 0)
        return result
    }

    fun decodeSignedVarintInt(inp: InputStream): Int {
        val raw = decodeVarint(inp, 32).toInt()
        val temp = raw shl 31 shr 31 xor raw shr 1
        // This extra step lets us deal with the largest signed values by treating
        // negative results from read unsigned methods as like unsigned values.
        // Must re-flip the top bit if the original read value had it set.
        return temp xor (raw and (1 shl 31))
    }

    fun decodeSignedVarintLong(inp: InputStream): Long {
        val raw = decodeVarint(inp, 64)
        val temp = raw shl 63 shr 63 xor raw shr 1
        // This extra step lets us deal with the largest signed values by treating
        // negative results from read unsigned methods as like unsigned values
        // Must re-flip the top bit if the original read value had it set.
        return temp xor (raw and (1L shl 63))
    }
}