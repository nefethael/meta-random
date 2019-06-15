LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8e3fef3a1d3ee2272541ad184ba4d7c2"

SRC_URI = "git://github.com/opentracing/opentracing-cpp;protocol=https"

SRCREV = "ac50154a7713877f877981c33c3375003b6ebfe1"

S = "${WORKDIR}/git"

inherit cmake


