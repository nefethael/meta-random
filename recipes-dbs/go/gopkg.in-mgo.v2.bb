DESCRIPTION = "gopkg.in/mgo.v2"

GO_IMPORT = "gopkg.in/mgo.v2"

inherit go

SRC_URI = "git://gopkg.in/mgo.v2;protocol=https;destsuffix=${PN}-${PV}/src/${GO_IMPORT}"
SRCREV = "3f83fa5005286a7fe593b055f0d7771a7dce4655" 
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=566e96676859b5704130b80941bc9f1f"

FILES_${PN} += "${GOBIN_FINAL}/*"

DEPENDS+="\
    gopkg.in-tomb.v2 \
    cyrus-sasl \
"
