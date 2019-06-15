SUMMARY = "Fast NTLM authentication proxy with tunneling"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = " \
    git://github.com/Evengard/cntlm \
    file://cntlm.service \
    file://cntlm.conf \
"

# Modify these as desired
PV = "0.93beta5+git${SRCPV}"
SRCREV = "ff89e6394191adc814e65eea7b7baa507296ff8a"

S = "${WORKDIR}/git"

do_configure () {
    sh ${S}/configure
}

LDFLAGS_append = " -pthread"
EXTRA_OEMAKE = "\
    'CC=${CC}' \
    'CFLAGS=${CFLAGS} -DVERSION=\"0.93beta5\"' \
    'LDFLAGS=${LDFLAGS}' \
"

do_compile () {
	oe_runmake cntlm
}

do_install () {
    # config
    install -d ${D}${sysconfdir}/
    install -m 0600 ${WORKDIR}/cntlm.conf ${D}${sysconfdir}/cntlm.conf

    # systemd 
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/cntlm.service ${D}${systemd_unitdir}/system/

    # bin
    install -d -m 0755 ${D}${bindir}
    install -m 0755 ${B}/cntlm ${D}${bindir}/cntlm
}

inherit systemd

SYSTEMD_SERVICE_${PN} = " \
   cntlm.service \
"
