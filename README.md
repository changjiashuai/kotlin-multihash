# kotlin-multihash

[![](https://img.shields.io/badge/made%20by-Protocol%20Labs-blue.svg?style=flat-square)](http://ipn.io)
[![](https://img.shields.io/badge/project-multiformats-blue.svg?style=flat-square)](https://github.com/multiformats/multiformats)
[![](https://img.shields.io/badge/freenode-%23ipfs-blue.svg?style=flat-square)](https://webchat.freenode.net/?channels=%23ipfs)
[![](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)

> Implementation of [multihash](https://github.com/multiformats/multihash) -self identifying hashes in Kotlin.


## Install

Add the relevant dependency to your project:

#### Maven

```maven
<dependency>
  <groupId>io.ipfs.multiformats</groupId>
  <artifactId>kotlin-multihash</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

#### Gradle

```gradle
compile 'io.ipfs.multiformats:kotlin-multihash:1.0.1'
```


## Usage


```kotlin
  val mh = Multihash.fromBase58(hash)
  val hex = mh.toHex()
  val base32 = mh.toBase32()
  val base58 = mh.toBase58()
  val base64 = mh.toBase64()

  val b = Multihash.encodeByName("sha1", "0beec7b5ea3f0fdbc95d0dd47f3c5bc275da8a33")
  println("encode=" + b.contentToString())
  val s = Multihash.decode(b)
  println("decode=$s")
```


## Maintainers

Captain: [@changjiashuai](https://github.com/changjiashuai).

## Contribute

Contributions welcome. Please check out [the issues](https://github.com/changjiashuai/kotlin-multihash/issues).

Check out our [contributing document](https://github.com/multiformats/multiformats/blob/master/contributing.md) for more information on how we work, and about contributing in general. Please be aware that all interactions related to multiformats are subject to the IPFS [Code of Conduct](https://github.com/ipfs/community/blob/master/code-of-conduct.md).

Small note: If editing the README, please conform to the [standard-readme](https://github.com/RichardLitt/standard-readme) specification.

## License

[MIT](LICENSE) © 2016 Protocol Labs Inc.
