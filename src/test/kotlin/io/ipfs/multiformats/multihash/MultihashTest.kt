package io.ipfs.multiformats.multihash

import org.apache.commons.codec.binary.Hex
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2018/7/15.
 */
class MultihashTest {

    private lateinit var testCases: Map<String, Multihash.Type>

    @Before
    fun setUp() {
        testCases = hashMapOf(
                "2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7aeff" to Multihash.Type.ID,
//                "" to Multihash.Type.ID,
                "0beec7b5ea3f0fdbc95d0dd47f3c5bc275da8a33" to Multihash.Type.SHA1,
//                "0beec7b5" to Multihash.Type.SHA1,
                "2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae" to Multihash.Type.SHA2_256,
//                "2c26b46b" to Multihash.Type.SHA2_256,
//                "2c26b46b68ffc68ff99b453c1d30413413" to Multihash.Type.BLAKE2B_256,
                "243ddb9e" to Multihash.Type.MURMUR3,
//                "f00ba4" to Multihash.Type.KECCAK_256,
                "f84e95cb5fbd2038863ab27d3cdeac295ad2d4ab96ad1f4b070c0bf36078ef08" to Multihash.Type.SHAKE_128,
                "1af97f7818a28edfdfce5ec66dbdc7e871813816d7d585fe1f12475ded5b6502b7723b74e2ee36f2651a10a8eaca72aa9148c3c761aaceac8f6d6cc64381ed39" to Multihash.Type.SHAKE_256,
                "4bca2b137edc580fe50a88983ef860ebaca36c857b1f492839d6d7392452a63c82cbebc68e3b70a2a1480b4bb5d437a7cba6ecf9d89f9ff3ccd14cd6146ea7e7" to Multihash.Type.SHA3_512
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun toHex() {
        testCases.forEach { hash, type ->
            val multihash = Multihash(type, hash = Hex.decodeHex(hash))
            val hex = multihash.toHex()
            println("${type.name}.hex=$hex")
        }
    }

    @Test
    fun toBase58() {
//        testCases.forEach { hash, type ->
//            val base58Hash = BaseN.encode(MultiBase.Base.BASE58_FLICKR.alphabet, BigInteger("58"), hash.toByteArray())
//            val multihash = Multihash.fromBase58(base58Hash)
//            println("type.code=multihash.code=(${type.code}=${multihash.type.code})")
//        }
//        val decode = BaseN.decode(MultiBase.Base.BASE58_BTC.alphabet, BigInteger("58"), "5dsgvJGnvAfiR3K6HCBc4hcokSfmjj")
//        val encode = BaseN.encode(MultiBase.Base.BASE58_BTC.alphabet, BigInteger("58"), toBytes())
//
        val mh = Multihash.fromBase58("5dsgvJGnvAfiR3K6HCBc4hcokSfmjj")
////        println("encode=$encode")
        println("mh.type=${mh.type}, mh.hash=${mh.hash.size}")
    }

    private fun toBytes(): ByteArray {
        val result = ByteArray("multihash".toByteArray().size  + 2)
        result[0] = 0x11
        result[1] = 20
        System.arraycopy("multihash".toByteArray(), 0, result, 2, "multihash".toByteArray().size)
        return result
    }

    @Test
    fun toBase64(){
        val hash = Multihash.fromBase64("EiCcvAfD+ZFyWDajqipYHKICkZiqQgudmbwOEx2fPiy+Rw==")
        println("hash.type=${hash.type}")
    }

    @Test
    fun fromHex() {
    }

    @Test
    fun fromBase58() {
    }
}