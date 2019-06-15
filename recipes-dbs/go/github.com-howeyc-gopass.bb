DESCRIPTION = "github.com/howeyc/gopass"

GO_IMPORT = "github.com/howeyc/gopass"

inherit go

SRC_URI = "git://${GO_IMPORT}"
SRCREV = "bf9dde6d0d2c004a008c27aaee91170c786f6db8"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE.txt;md5=b24abd09a925eaf2b6de5a9b4c9bea07"

FILES_${PN} += "${GOBIN_FINAL}/*"

DEPENDS = "golang.org-x-net"

RDEPENDS_${PN}-staticdev += "bash"
