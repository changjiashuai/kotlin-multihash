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

    @Test
    fun putVarInt() {
        val length = 320
        val varint = ByteArray((32 - Integer.numberOfLeadingZeros(length) + 6) / 7)
        val a = VarInt.putUvarint(varint, length.toLong())
        println("a=$a")
    }

    @Test
    fun varIntExt() {
        for (test in tests) {
            val ba = test.toOctets()
            println(BinaryCodec.toAsciiString(ba))
//            print("${"0x%x".format(test).padEnd(8)} -> ")
//            var s = ""
//            ba.forEach { s += "0x%02x ".format(it) }
//            println("${s.padEnd(20)} <- ${"0x%x".format(ba.fromOctets())}")
        }
    }

    fun unsignedInt(value: Int): Long {
        return value.toLong() and 0xFFFFFFFF
    }

    fun unsignedByte(value: Byte): Int {
        return value.toInt() and 0xFF
    }
}