SUMMARY = "The high-level C binding for 0MQ"
HOMEPAGE = "https://github.com/zeromq/czmq"

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9741c346eef56131163e13b9db1241b3"

DEPENDS = "zeromq"

SRC_URI = "git://github.com/zeromq/czmq"

SRCREV = "e305dc2136635cfb38a05e090580a833da54de28"
S = "${WORKDIR}/git"

inherit cmake


