DESCRIPTION = "github.com/influxdata/telegraf"

GO_IMPORT = "github.com/influxdata/telegraf"
GO_LINKSHARED = ""

inherit go systemd

SRC_URI = "git://${GO_IMPORT};branch=release-1.13"
SRCREV = "6dad859d74c29c677b525be7333c482f946c2b5e"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=4c87a94f9ef84eb3e8c5f0424fef3b9e"

DEPENDS_append = " go-dep-native"

do_compile_prepend() {
    ( cd ${WORKDIR}/build/src/${GO_IMPORT} && dep ensure -vendor-only )
}

FILES_${PN} += "${GOBIN_FINAL}/*"

RDEPENDS_${PN}-dev += "bash python-core"

SRC_URI += "file://telegraf.conf file://telegraf.service"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/bin/telegraf ${D}${bindir}/
}

do_install_append() {
    # config
    install -d ${D}${sysconfdir}/telegraf/
    install -m 0644 ${WORKDIR}/telegraf.conf ${D}${sysconfdir}/telegraf/

    # systemd
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/telegraf.service ${D}${systemd_unitdir}/system/
}

SYSTEMD_SERVICE_${PN} = "telegraf.service"

