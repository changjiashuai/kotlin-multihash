package io.ipfs.multiformats.multihash;

enum class Type(val code: Long, val named: String, val length: Int) {

    ID(0x00, "id", -1),
    SHA1(0x11, "sha1", 20),
    SHA2_256(0x12, "sha2-256", 32),
    SHA2_512(0x13, "sha2-512", 64),
    SHA3_224(0x17, "sha3-224", 28),
    SHA3_256(0x16, "sha3-256", 32),
    SHA3_384(0x15, "sha3-384", 48),
    SHA3_512(0x14, "sha3-512", 64),
    SHA3(0x14, "sha3", 64),
    KECCAK_224(0x1A, "keccak-224", 28),
    KECCAK_256(0x1B, "keccak-256", 32),
    KECCAK_384(0x1C, "keccak-384", 48),
    KECCAK_512(0x1D, "keccak-512", 64),

    SHAKE_128(0x18, "shake-128", 32),
    SHAKE_256(0x19, "shake-256", 64),

    DBL_SHA2_256(0x56, "dbl-sha2-256", 32),
    MURMUR3(0x22, "murmur3", 4),

    BLAKE2B_8(0xb201, "blake2b-8", 1),
    BLAKE2B_16(0xb202, "blake2b-16", 2),
    BLAKE2B_24(0xb203, "blake2b-24", 3),
    BLAKE2B_32(0xb204, "blake2b-32", 4),
    BLAKE2B_40(0xb205, "blake2b-40", 5),
    BLAKE2B_48(0xb206, "blake2b-48", 6),
    BLAKE2B_56(0xb207, "blake2b-56", 7),
    BLAKE2B_64(0xb208, "blake2b-64", 8),
    BLAKE2B_72(0xb209, "blake2b-72", 9),
    BLAKE2B_80(0xb20a, "blake2b-80", 10),
    BLAKE2B_88(0xb20b, "blake2b-88", 11),
    BLAKE2B_96(0xb20c, "blake2b-96", 12),
    BLAKE2B_104(0xb20d, "blake2b-104", 13),
    BLAKE2B_112(0xb20e, "blake2b-112", 14),
    BLAKE2B_120(0xb20f, "blake2b-120", 15),
    BLAKE2B_128(0xb210, "blake2b-128", 16),
    BLAKE2B_136(0xb211, "blake2b-136", 17),
    BLAKE2B_144(0xb212, "blake2b-144", 18),
    BLAKE2B_152(0xb213, "blake2b-152", 19),
    BLAKE2B_160(0xb214, "blake2b-160", 20),
    BLAKE2B_168(0xb215, "blake2b-168", 21),
    BLAKE2B_176(0xb216, "blake2b-176", 22),
    BLAKE2B_184(0xb217, "blake2b-184", 23),
    BLAKE2B_192(0xb218, "blake2b-192", 24),
    BLAKE2B_200(0xb219, "blake2b-200", 25),
    BLAKE2B_208(0xb21a, "blake2b-208", 26),
    BLAKE2B_216(0xb21b, "blake2b-216", 27),
    BLAKE2B_224(0xb21c, "blake2b-224", 28),
    BLAKE2B_232(0xb21d, "blake2b-232", 29),
    BLAKE2B_240(0xb21e, "blake2b-240", 30),
    BLAKE2B_248(0xb21f, "blake2b-248", 31),
    BLAKE2B_256(0xb220, "blake2b-256", 32),
    BLAKE2B_264(0xb221, "blake2b-264", 33),
    BLAKE2B_272(0xb222, "blake2b-272", 34),
    BLAKE2B_280(0xb223, "blake2b-280", 35),
    BLAKE2B_288(0xb224, "blake2b-288", 36),
    BLAKE2B_296(0xb225, "blake2b-296", 37),
    BLAKE2B_304(0xb226, "blake2b-304", 38),
    BLAKE2B_312(0xb227, "blake2b-312", 39),
    BLAKE2B_320(0xb228, "blake2b-320", 40),
    BLAKE2B_328(0xb229, "blake2b-328", 41),
    BLAKE2B_336(0xb22a, "blake2b-336", 42),
    BLAKE2B_344(0xb22b, "blake2b-344", 43),
    BLAKE2B_352(0xb22c, "blake2b-352", 44),
    BLAKE2B_360(0xb22d, "blake2b-360", 45),
    BLAKE2B_368(0xb22e, "blake2b-368", 46),
    BLAKE2B_376(0xb22f, "blake2b-376", 47),
    BLAKE2B_384(0xb230, "blake2b-384", 48),
    BLAKE2B_392(0xb231, "blake2b-392", 49),
    BLAKE2B_400(0xb232, "blake2b-400", 50),
    BLAKE2B_408(0xb233, "blake2b-408", 51),
    BLAKE2B_416(0xb234, "blake2b-416", 52),
    BLAKE2B_424(0xb235, "blake2b-424", 53),
    BLAKE2B_432(0xb236, "blake2b-432", 54),
    BLAKE2B_440(0xb237, "blake2b-440", 55),
    BLAKE2B_448(0xb238, "blake2b-448", 56),
    BLAKE2B_456(0xb239, "blake2b-456", 57),
    BLAKE2B_464(0xb23a, "blake2b-464", 58),
    BLAKE2B_472(0xb23b, "blake2b-472", 59),
    BLAKE2B_480(0xb23c, "blake2b-480", 60),
    BLAKE2B_488(0xb23d, "blake2b-488", 61),
    BLAKE2B_496(0xb23e, "blake2b-496", 62),
    BLAKE2B_504(0xb23f, "blake2b-504", 63),
    BLAKE2B_512(0xb240, "blake2b-512", 64),

    BLAKE2S_8(0xb241, "blake2s-8", 1),
    BLAKE2S_16(0xb242, "blake2s-16", 2),
    BLAKE2S_24(0xb243, "blake2s-24", 3),
    BLAKE2S_32(0xb244, "blake2s-32", 4),
    BLAKE2S_40(0xb245, "blake2s-40", 5),
    BLAKE2S_48(0xb246, "blake2s-48", 6),
    BLAKE2S_56(0xb247, "blake2s-56", 7),
    BLAKE2S_64(0xb248, "blake2s-64", 8),
    BLAKE2S_72(0xb249, "blake2s-72", 9),
    BLAKE2S_80(0xb24a, "blake2s-80", 10),
    BLAKE2S_88(0xb24b, "blake2s-88", 11),
    BLAKE2S_96(0xb24c, "blake2s-96", 12),
    BLAKE2S_104(0xb24d, "blake2s-104", 13),
    BLAKE2S_112(0xb24e, "blake2s-112", 14),
    BLAKE2S_120(0xb24f, "blake2s-120", 15),
    BLAKE2S_128(0xb250, "blake2s-128", 16),
    BLAKE2S_136(0xb251, "blake2s-136", 17),
    BLAKE2S_144(0xb252, "blake2s-144", 18),
    BLAKE2S_152(0xb253, "blake2s-152", 19),
    BLAKE2S_160(0xb254, "blake2s-160", 20),
    BLAKE2S_168(0xb255, "blake2s-168", 21),
    BLAKE2S_176(0xb256, "blake2s-176", 22),
    BLAKE2S_184(0xb257, "blake2s-184", 23),
    BLAKE2S_192(0xb258, "blake2s-192", 24),
    BLAKE2S_200(0xb259, "blake2s-200", 25),
    BLAKE2S_208(0xb25a, "blake2s-208", 26),
    BLAKE2S_216(0xb25b, "blake2s-216", 27),
    BLAKE2S_224(0xb25c, "blake2s-224", 28),
    BLAKE2S_232(0xb25d, "blake2s-232", 29),
    BLAKE2S_240(0xb25e, "blake2s-240", 30),
    BLAKE2S_248(0xb25f, "blake2s-248", 31),
    BLAKE2S_256(0xb260, "blake2s-256", 32);

    companion object {
        /**
         * maps the name of a hash to the code
         */
        val names = hashMapOf<String, Long>()
        /**
         * maps a hash code to it's name
         */
        val codes = hashMapOf<Long, String>()
        /**
         * maps a hash code to it's default length
         */
        val defaultLengths = hashMapOf<Long, Int>()

        init {
            Type.values().forEach {
                names[it.named] = it.code
                codes[it.code] = it.named
                defaultLengths[it.code] = it.length
            }
        }

        fun hasCode(code: Long): Boolean {
            return codes.containsKey(code)
        }
    }
}