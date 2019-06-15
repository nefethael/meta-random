DESCRIPTION = "Lua API wrapper with advanced features and top notch performance"
HOMEPAGE = "https://github.com/ThePhD/sol2"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=4a69ff64c8a5c7508c001d94edcaa6c6"

PR = "r0"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/ThePhD/sol2;branch=develop;protocol=git"
SRCREV = "902ceaf8a1d53ea1b70cf9f3f0302da87f5a7f8c"

inherit cmake

RDEPENDS_${PN}-dev = "lua-dev"
