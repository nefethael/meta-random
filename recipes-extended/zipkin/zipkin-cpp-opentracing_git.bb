LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=20f54bda3d60a54fc36a3dcf4541ce04"

SRC_URI = " \
    git://github.com/rnburn/zipkin-cpp-opentracing;protocol=https \
    file://0001-Remove-system-cmake-flag.patch \
    file://0002-Export-config-cmake.patch \
    file://0003-Export-curl-without-absolute-path.patch \
"

PV = "1.1+git${SRCPV}"
SRCREV = "fee9468d6d1af86b0b67b97729674d2d356cbe80"

S = "${WORKDIR}/git"

DEPENDS = "curl opentracing-cpp embedfile-native"

inherit cmake

