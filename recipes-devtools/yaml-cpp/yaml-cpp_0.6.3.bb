DESCRIPTION = "yaml-cpp is a YAML parser and emitter in C++ matching the YAML 1.2 spec"
HOMEPAGE = "https://github.com/jbeder/yaml-cpp/"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6a8aaf0595c2efc1a9c2e0913e9c1a2c"

PR = "r0"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/jbeder/${PN}.git;branch=master;protocol=git"
SRCREV = "9a3624205e8774953ef18f57067b3426c1c5ada6"


EXTRA_OECMAKE = "-DYAML_BUILD_SHARED_LIBS=ON -DYAML_CPP_BUILD_TESTS=OFF"

inherit cmake
