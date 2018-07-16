package io.ipfs.multiformats.multihash

import io.ipfs.multiformats.multibase.BaseN
import io.ipfs.multiformats.multibase.MultiBase
import org.apache.commons.codec.binary.Base32
import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Hex
import java.math.BigInteger
import java.util.*

/**
 * changjiashuai@gmail.com.
 *
 * Created by CJS on 2018/7/14.
 */
class Multihash {

    var type: Type
    var hash: ByteArray  //hex base32 base58 base64

    constructor(multihash: Multihash) : this(multihash.type, multihash.hash)

    constructor(hash: ByteArray) : this(Type.lookup((hash[0].toInt() and 0xff)), Arrays.copyOfRange(hash, 2, hash.size))

    constructor(type: Type, hash: ByteArray) {
        if (type != Type.ID) {
            if (hash.size > 127) {
                throw IllegalStateException("Unsupported ${type.name} hash size: ${hash.size}")
            }
            if (hash.size != type.length) {
                throw IllegalStateException("Incorrect ${type.name} hash size: ${hash.size} != ${type.length}")
            }
        }
        this.type = type
        this.hash = hash
    }

    enum class Type(val code: Int, val length: Int) {

        ID(0x00, -1),
        SHA1(0x11, 20),
        SHA2_256(0x12, 32),
        SHA2_512(0x13, 64),
        SHA3_224(0x17, 28),
        SHA3_256(0x16, 32),
        SHA3_384(0x15, 48),
        SHA3_512(0x14, 64),
        SHA3(0x14, 64),
        KECCAK_224(0x1A, 28),
        KECCAK_256(0x1B, 32),
        KECCAK_384(0x1C, 48),
        KECCAK_512(0x1D, 64),

        SHAKE_128(0x18, 32),
        SHAKE_256(0x19, 64),

        DBL_SHA2_256(0x56, 32),
        MURMUR3(0x22, 4),

        BLAKE2B_8(0xb201, 1),
        BLAKE2B_16(0xb202, 2),
        BLAKE2B_24(0xb203, 3),
        BLAKE2B_32(0xb204, 4),
        BLAKE2B_40(0xb205, 5),
        BLAKE2B_48(0xb206, 6),
        BLAKE2B_56(0xb207, 7),
        BLAKE2B_64(0xb208, 8),
        BLAKE2B_72(0xb209, 9),
        BLAKE2B_80(0xb20a, 10),
        BLAKE2B_88(0xb20b, 11),
        BLAKE2B_96(0xb20c, 12),
        BLAKE2B_104(0xb20d, 13),
        BLAKE2B_112(0xb20e, 14),
        BLAKE2B_120(0xb20f, 15),
        BLAKE2B_128(0xb210, 16),
        BLAKE2B_136(0xb211, 17),
        BLAKE2B_144(0xb212, 18),
        BLAKE2B_152(0xb213, 19),
        BLAKE2B_160(0xb214, 20),
        BLAKE2B_168(0xb215, 21),
        BLAKE2B_176(0xb216, 22),
        BLAKE2B_184(0xb217, 23),
        BLAKE2B_192(0xb218, 24),
        BLAKE2B_200(0xb219, 25),
        BLAKE2B_208(0xb21a, 26),
        BLAKE2B_216(0xb21b, 27),
        BLAKE2B_224(0xb21c, 28),
        BLAKE2B_232(0xb21d, 29),
        BLAKE2B_240(0xb21e, 30),
        BLAKE2B_248(0xb21f, 31),
        BLAKE2B_256(0xb220, 32),
        BLAKE2B_264(0xb221, 33),
        BLAKE2B_272(0xb222, 34),
        BLAKE2B_280(0xb223, 35),
        BLAKE2B_288(0xb224, 36),
        BLAKE2B_296(0xb225, 37),
        BLAKE2B_304(0xb226, 38),
        BLAKE2B_312(0xb227, 39),
        BLAKE2B_320(0xb228, 40),
        BLAKE2B_328(0xb229, 41),
        BLAKE2B_336(0xb22a, 42),
        BLAKE2B_344(0xb22b, 43),
        BLAKE2B_352(0xb22c, 44),
        BLAKE2B_360(0xb22d, 45),
        BLAKE2B_368(0xb22e, 46),
        BLAKE2B_376(0xb22f, 47),
        BLAKE2B_384(0xb230, 48),
        BLAKE2B_392(0xb231, 49),
        BLAKE2B_400(0xb232, 50),
        BLAKE2B_408(0xb233, 51),
        BLAKE2B_416(0xb234, 52),
        BLAKE2B_424(0xb235, 53),
        BLAKE2B_432(0xb236, 54),
        BLAKE2B_440(0xb237, 55),
        BLAKE2B_448(0xb238, 56),
        BLAKE2B_456(0xb239, 57),
        BLAKE2B_464(0xb23a, 58),
        BLAKE2B_472(0xb23b, 59),
        BLAKE2B_480(0xb23c, 60),
        BLAKE2B_488(0xb23d, 61),
        BLAKE2B_496(0xb23e, 62),
        BLAKE2B_504(0xb23f, 63),
        BLAKE2B_512(0xb240, 64),

        BLAKE2S_8(0xb241, 1),
        BLAKE2S_16(0xb242, 2),
        BLAKE2S_24(0xb243, 3),
        BLAKE2S_32(0xb244, 4),
        BLAKE2S_40(0xb245, 5),
        BLAKE2S_48(0xb246, 6),
        BLAKE2S_56(0xb247, 7),
        BLAKE2S_64(0xb248, 8),
        BLAKE2S_72(0xb249, 9),
        BLAKE2S_80(0xb24a, 10),
        BLAKE2S_88(0xb24b, 11),
        BLAKE2S_96(0xb24c, 12),
        BLAKE2S_104(0xb24d, 13),
        BLAKE2S_112(0xb24e, 14),
        BLAKE2S_120(0xb24f, 15),
        BLAKE2S_128(0xb250, 16),
        BLAKE2S_136(0xb251, 17),
        BLAKE2S_144(0xb252, 18),
        BLAKE2S_152(0xb253, 19),
        BLAKE2S_160(0xb254, 20),
        BLAKE2S_168(0xb255, 21),
        BLAKE2S_176(0xb256, 22),
        BLAKE2S_184(0xb257, 23),
        BLAKE2S_192(0xb258, 24),
        BLAKE2S_200(0xb259, 25),
        BLAKE2S_208(0xb25a, 26),
        BLAKE2S_216(0xb25b, 27),
        BLAKE2S_224(0xb25c, 28),
        BLAKE2S_232(0xb25d, 29),
        BLAKE2S_240(0xb25e, 30),
        BLAKE2S_248(0xb25f, 31),
        BLAKE2S_256(0xb260, 32);

        companion object {
            private val codeTypeMap = TreeMap<Int, Type>()

            init {
                Type.values().forEach {
                    codeTypeMap[it.code] = it
                }
            }

            fun lookup(code: Int): Type {
                return codeTypeMap[code]
                        ?: throw IllegalStateException("Unknown Multihash type:$code")
            }
        }
    }

    private fun toBytes(): ByteArray {
        val result = ByteArray(hash.size + 2)
        result[0] = type.code.toByte()
        result[1] = hash.size.toByte()
        System.arraycopy(hash, 0, result, 2, hash.size)
        return result
    }

    fun toHex(): String {
        return Hex.encodeHexString(toBytes())
    }

    fun toBase32(): String {
        return Base32().encodeToString(toBytes())
    }

    fun toBase58(): String {
        return BaseN.encode(MultiBase.Base.BASE58_BTC.alphabet, BigInteger("58"), toBytes())
    }

    fun toBase64(): String {
        return Base64.encodeBase64String(toBytes())
    }

    companion object {

        fun fromHex(hex: String): Multihash {
            return Multihash(Hex.decodeHex(hex))
        }

        fun fromBase32(base32: String): Multihash {
            return Multihash(Base32().decode(base32))
        }

        fun fromBase58(base58: String): Multihash {
            return Multihash(BaseN.decode(MultiBase.Base.BASE58_BTC.alphabet, BigInteger("58"), base58))
        }

        fun fromBase64(base64: String): Multihash {
            return Multihash(Base64.decodeBase64(base64))
        }
    }
}