package io.ipfs.multiformats.multihash

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2018/7/19.
 */
fun Int.toOctets(): ByteArray {
    var s = Integer.toBinaryString(this)
    val r = s.length % 7
    var z = s.length / 7
    if (r > 0) {
        z++
        s = s.padStart(z * 7, '0')
    }
    s = Array(z) { "1" + s.slice(it * 7 until (it + 1) * 7) }.joinToString("")
    s = s.take(s.length - 8) + "0" + s.takeLast(7)
    return ByteArray(z) { Integer.parseInt(s.slice(it * 8 until (it + 1) * 8), 2).toByte() }
}

fun ByteArray.fromOctets(): Int {
    var s = ""
    for (b in this) s += Integer.toBinaryString(b.toInt()).padStart(7, '0').takeLast(7)
    return Integer.parseInt(s, 2)
}

fun Byte.unsignedInt():Int{
    return this.toInt() and 0xFF
}