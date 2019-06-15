DESCRIPTION = "github.com/jessevdk/go-flags"

GO_IMPORT = "github.com/jessevdk/go-flags"

inherit go

SRC_URI = "git://github.com/jessevdk/go-flags;protocol=https;destsuffix=${PN}-${PV}/src/${GO_IMPORT}"
SRCREV = "460c7bb0abd6e927f2767cadc91aa6ef776a98b4" 
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=808b70f61299cac3989a0b3f6162c93d"

FILES_${PN} += "${GOBIN_FINAL}/*"

RDEPENDS_${PN}-dev += "bash"
