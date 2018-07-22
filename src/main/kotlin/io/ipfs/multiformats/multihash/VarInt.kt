package io.ipfs.multiformats.multihash

import java.io.IOException
import java.io.InputStream

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2018/7/19.
 */
object VarInt {

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
        return out
    }

    fun encodeVarintLong(inp: Long): ByteArray {
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
        return out
    }

    fun decodeVarInt(src: ByteArray, bitLimit: Int = 64, eofOnStartAllowed: Boolean = false): Pair<Long, Int> {
        return decodeVarint(src.inputStream(), bitLimit, eofOnStartAllowed)
    }

    fun decodeVarint(inp: InputStream, bitLimit: Int = 64, eofOnStartAllowed: Boolean = false): Pair<Long, Int> {
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
                if (eofOnStartAllowed && shift == 0) return Pair(-1, shift)
                else throw IOException("Unexpected EOF")
            }
            result = result or (b.toLong() and 0x7FL shl shift)
            shift += 7
        } while (b and 0x80 != 0)
        return Pair(result, shift)
    }

    fun decodeSignedVarintInt(inp: InputStream): Int {
        val raw = decodeVarint(inp, 32).first.toInt()
        val temp = raw shl 31 shr 31 xor raw shr 1
        // This extra step lets us deal with the largest signed values by treating
        // negative results from read unsigned methods as like unsigned values.
        // Must re-flip the top bit if the original read value had it set.
        return temp xor (raw and (1 shl 31))
    }

    fun decodeSignedVarintLong(inp: InputStream): Long {
        val raw = decodeVarint(inp, 64).first
        val temp = raw shl 63 shr 63 xor raw shr 1
        // This extra step lets us deal with the largest signed values by treating
        // negative results from read unsigned methods as like unsigned values
        // Must re-flip the top bit if the original read value had it set.
        return temp xor (raw and (1L shl 63))
    }
}