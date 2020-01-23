DESCRIPTION = "github.com/influxdata/kapacitor"

GO_IMPORT = "github.com/influxdata/kapacitor"
GO_LINKSHARED = ""

inherit go systemd

SRC_URI = "git://${GO_IMPORT}"
SRCREV = "1f648f85772efe222a3853fe1a0d9ef88854a8c1"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=96cd9a86f733dbfec4107613b9b27c71"

DEPENDS_append = " go-dep-native"

do_compile_prepend() {
    ( cd ${WORKDIR}/build/src/${GO_IMPORT} && dep ensure -vendor-only )
}

FILES_${PN} += "${GOBIN_FINAL}/*"

RDEPENDS_${PN}-dev += "bash python-core"

SRC_URI += "file://kapacitor.service"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/bin/kapacitor ${D}${bindir}/
    install -m 0755 ${B}/bin/kapacitord ${D}${bindir}/
}

do_install_append() {
    # config
    install -d ${D}${sysconfdir}/kapacitor/
    install -m 0644 ${S}/src/${GO_IMPORT}/etc/kapacitor/kapacitor.conf ${D}${sysconfdir}/kapacitor/

    # systemd
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/kapacitor.service ${D}${systemd_unitdir}/system/
}

SYSTEMD_SERVICE_${PN} = "kapacitor.service"

