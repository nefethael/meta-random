LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "git://github.com/rnburn/zipkin-cpp-opentracing;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "e0cd6fd3527c7a5cabfb25d1f5ccbfdb7c52496c"

S = "${WORKDIR}/git/3rd_party/${BPN}/src"

do_compile () {
    ${CC} embedfile.c -o embedfile
}

do_install() {
	install -d ${D}${bindir}
	install embedfile ${D}${bindir}/
}

inherit native
