package io.ipfs.multiformats.multihash

import org.apache.commons.codec.binary.BinaryCodec
import org.junit.Test

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2018/7/19.
 */
class VarIntTest {

    private val tests = intArrayOf(1, 127, 128, 255, 300, 16384)

    @Test
    fun varInt() {
        val a = VarInt.encodeVarint(20)
        println("a=$a")
        val b = VarInt.decodeVarInt(a)
        println("b=$b")
    }

    @Test
    fun encodeVarint() {
        tests.forEach {
            val ba = VarInt.encodeVarint(it)
            println(BinaryCodec.toAsciiString(ba))
        }
    }

    @Test
    fun decodeVarint() {
        tests.forEach {
            val ba = VarInt.encodeVarint(it)
            val value = VarInt.decodeVarInt(ba)
            println("value=$value")
        }
    }
}