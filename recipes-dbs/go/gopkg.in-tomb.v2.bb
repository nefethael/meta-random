DESCRIPTION = "gopkg.in/tomb.v2"

GO_IMPORT = "gopkg.in/tomb.v2"

inherit go

SRC_URI = "git://gopkg.in/tomb.v2;protocol=https;destsuffix=${PN}-${PV}/src/${GO_IMPORT}"
SRCREV = "d5d1b5820637886def9eef33e03a27a9f166942c" 
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=95d4102f39f26da9b66fee5d05ac597b"

FILES_${PN} += "${GOBIN_FINAL}/*"
