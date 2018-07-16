package io.ipfs.multiformats.multihash

import io.ipfs.multiformats.multibase.BaseN
import io.ipfs.multiformats.multibase.MultiBase
import org.apache.commons.codec.binary.Base32
import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Hex
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.math.BigInteger

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2018/7/15.
 */
class MultihashTest {

    private lateinit var fromHexHashMap: Map<String, Multihash.Type>
    private lateinit var fromBase32Hash: Map<String, Multihash.Type>
    private lateinit var fromBase58Hash: Map<String, Multihash.Type>
    private lateinit var fromBase64Hash: Map<String, Multihash.Type>
    private lateinit var toHexHashMap: Map<Multihash.Type, String>
    private lateinit var toBase32HashMap: Map<Multihash.Type, String>
    private lateinit var toBase58HashMap: Map<Multihash.Type, String>
    private lateinit var toBase64HashMap: Map<Multihash.Type, String>

    @Before
    fun setUp() {
        fromHexHashMap = hashMapOf(
                "111488c2f11fb2ce392acb5b2986e640211c4690073e" to Multihash.Type.SHA1,
                "12209cbc07c3f991725836a3aa2a581ca2029198aa420b9d99bc0e131d9f3e2cbe47" to Multihash.Type.SHA2_256
        )
        toHexHashMap = hashMapOf(
                Multihash.Type.ID to "2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7aeff",
                Multihash.Type.SHA1 to "0beec7b5ea3f0fdbc95d0dd47f3c5bc275da8a33",
//                "0beec7b5" to Multihash.Type.SHA1,
                Multihash.Type.SHA2_256 to "2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae",
//                "2c26b46b" to Multihash.Type.SHA2_256,
//                "2c26b46b68ffc68ff99b453c1d30413413" to Multihash.Type.BLAKE2B_256,
                Multihash.Type.MURMUR3 to "243ddb9e",
//                "f00ba4" to Multihash.Type.KECCAK_256,
                Multihash.Type.SHAKE_128 to "f84e95cb5fbd2038863ab27d3cdeac295ad2d4ab96ad1f4b070c0bf36078ef08",
                Multihash.Type.SHAKE_256 to "1af97f7818a28edfdfce5ec66dbdc7e871813816d7d585fe1f12475ded5b6502b7723b74e2ee36f2651a10a8eaca72aa9148c3c761aaceac8f6d6cc64381ed39",
                Multihash.Type.SHA3_512 to "4bca2b137edc580fe50a88983ef860ebaca36c857b1f492839d6d7392452a63c82cbebc68e3b70a2a1480b4bb5d437a7cba6ecf9d89f9ff3ccd14cd6146ea7e7"
        )
        fromBase32Hash = hashMapOf(
                "CEKIRQXRD6ZM4OJKZNNSTBXGIAQRYRUQA47A====" to Multihash.Type.SHA1,
                "CIQJZPAHYP4ZC4SYG2R2UKSYDSRAFEMYVJBAXHMZXQHBGHM7HYWL4RY=" to Multihash.Type.SHA2_256
        )
        toBase32HashMap = hashMapOf(
                Multihash.Type.SHA1 to "0beec7b5ea3f0fdbc95d0dd47f3c5bc275da8a",
                Multihash.Type.SHA2_256 to "2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7aebba"
        )
        fromBase58Hash = hashMapOf(
                "5dsgvJGnvAfiR3K6HCBc4hcokSfmjj" to Multihash.Type.SHA1,
                "QmYtUc4iTCbbfVSDNKvtQqrfyezPPnFvE33wFmutw9PBBk" to Multihash.Type.SHA2_256
        )
        toBase58HashMap = hashMapOf(
                Multihash.Type.SHA1 to "abeec7b5ea3fafdbc95dadd47f3",
                Multihash.Type.SHA2_256 to "126b46b68ffc68ff99b453c1d3a413413422d7a6483"
        )
        fromBase64Hash = hashMapOf(
                "ERSIwvEfss45KstbKYbmQCEcRpAHPg==" to Multihash.Type.SHA1,
                "EiCcvAfD+ZFyWDajqipYHKICkZiqQgudmbwOEx2fPiy+Rw==" to Multihash.Type.SHA2_256
        )
        toBase64HashMap = hashMapOf(
                Multihash.Type.SHA1 to "abeec7b5ea3fafdbc95dadd47f3",
                Multihash.Type.SHA2_256 to "126b46b68ffc68ff99b453c1d3a413413422d7a6483"
        )
    }

    @Test
    fun fromHex() {
        fromHexHashMap.forEach { hash, type ->
            val mh = Multihash.fromHex(hash)
            println("mh.type=${mh.type}, mh.type.length=${mh.type.length}")
            assertEquals(type.code, mh.type.code)
            assertEquals(type.length, mh.type.length)
        }
    }

    @Test
    fun toHex() {
        toHexHashMap.forEach { type, hash ->
            val mh = Multihash(type, Hex.decodeHex(hash))
            val hex = mh.toHex()
            println("$type.hex=$hex")
            println("mh.type=${mh.type}, mh.type.length=${mh.type.length}")
            assertEquals(type.code, mh.type.code)
            assertEquals(type.length, mh.type.length)
        }
    }

    @Test
    fun fromBase32() {
        fromBase32Hash.forEach { hash, type ->
            val mh = Multihash.fromBase32(hash)
            println("mh.type=${mh.type}, mh.type.length=${mh.type.length}")
            assertEquals(type.code, mh.type.code)
            assertEquals(type.length, mh.type.length)
        }
    }

    @Test
    fun toBase32() {
        toBase32HashMap.forEach { type, hash ->
            val mh = Multihash(type, Base32().decode(hash))
            val base32 = mh.toBase32()
            println("$type.base32=$base32")
            println("mh.type=${mh.type}, mh.type.length=${mh.type.length}")
            assertEquals(type.code, mh.type.code)
            assertEquals(type.length, mh.type.length)
        }
    }

    @Test
    fun fromBase58() {
        fromBase58Hash.forEach { hash, type ->
            val mh = Multihash.fromBase58(hash)
            println("mh.type=${mh.type}, mh.hash.size=${mh.hash.size}")
            assertEquals(type.code, mh.type.code)
            assertEquals(type.length, mh.type.length)
        }
    }

    @Test
    fun toBase58() {
        toBase58HashMap.forEach { type, hash ->
            val mh = Multihash(type, BaseN.decode(MultiBase.Base.BASE58_BTC.alphabet, BigInteger("58"), hash))
            val base58 = mh.toBase58()
            println("$type.base58=$base58")
            println("mh.type=${mh.type}, mh.type.length=${mh.type.length}")
            assertEquals(type.code, mh.type.code)
            assertEquals(type.length, mh.type.length)
        }
    }

    @Test
    fun fromBase64() {
        fromBase64Hash.forEach { hash, type ->
            val mh = Multihash.fromBase64(hash)
            println("mh.type=${mh.type}, mh.hash.size=${mh.hash.size}")
            assertEquals(type.code, mh.type.code)
            assertEquals(type.length, mh.type.length)
        }
    }

    @Test
    fun toBase64() {
        toBase64HashMap.forEach { type, hash ->
            val mh = Multihash(type, Base64.decodeBase64(hash))
            val base64 = mh.toBase64()
            println("$type.base64=$base64")
            println("mh.type=${mh.type}, mh.type.length=${mh.type.length}")
            assertEquals(type.code, mh.type.code)
            assertEquals(type.length, mh.type.length)
        }
    }
}