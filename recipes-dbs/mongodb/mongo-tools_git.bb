DESCRIPTION = "MongoDB Tools"
HOMEPAGE = "https://docs.mongodb.com/tools/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE.md;md5=afa58684f5cd257af11dc6dfe10a2458"

inherit go
GO_IMPORT = "github.com/mongodb/mongo-tools"

SRC_URI = "git://${GO_IMPORT}"

PV = "4.1.5"
SRCREV = "d6c691f3ed621e087db9f064c5ca872fbb7096ca"

GO_INSTALL = "\
    ${GO_IMPORT}/common \
    ${GO_IMPORT}/mongodump/... \
    ${GO_IMPORT}/mongoimport/... \
"

DEPENDS = " \
    github.com-howeyc-gopass \
    github.com-jessevdk-go-flags \
    golang.org-x-net \
    gopkg.in-mgo.v2 \
    gopkg.in-tomb.v2 \
"

do_compile(){
        export TMPDIR="${GOTMPDIR}"
	for i in mongodump mongoimport; do
		${GO} build -o "${B}/bin/$i" ${GOBUILDFLAGS} "${B}/src/${GO_IMPORT}/$i/main/$i.go"
	done
	${GO} install ${GO_LINKSHARED} ${GOBUILDFLAGS} `go_list_packages`
}

RDEPENDS_${PN}-dev += "bash python"

