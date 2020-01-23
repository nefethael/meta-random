DESCRIPTION = "github.com/influxdata/influxdb"

GO_IMPORT = "github.com/influxdata/influxdb"
GO_LINKSHARED = ""

inherit go systemd

SRC_URI = "git://${GO_IMPORT};branch=1.7"
SRCREV = "23bc63d43a8dc05f53afa46e3526ebb5578f3d88"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=ba8146ad9cc2a128209983265136e06a"

DEPENDS_append = " go-dep-native"

do_compile_prepend() {
    ( cd ${WORKDIR}/build/src/${GO_IMPORT} && dep ensure -vendor-only )
}

FILES_${PN} += "${GOBIN_FINAL}/*"

RDEPENDS_${PN}-dev += "bash python-core"

SRC_URI += "file://influxdb.conf file://influxdb.service"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/bin/influx ${D}${bindir}/
    install -m 0755 ${B}/bin/influxd ${D}${bindir}/
}

do_install_append() {
    # config
    install -d ${D}${sysconfdir}/influxdb/
    install -m 0644 ${WORKDIR}/influxdb.conf ${D}${sysconfdir}/influxdb/

    # systemd
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/influxdb.service ${D}${systemd_unitdir}/system/
}

SYSTEMD_SERVICE_${PN} = "influxdb.service"
