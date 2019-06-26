DESCRIPTION = "github.com/influxdata/influxdb"

GO_IMPORT = "github.com/influxdata/influxdb"

GO_LINKSHARED = ""
inherit go systemd

SRC_URI = "git://github.com/nefethael/influxdb;branch=1.7.6-with-vendors"
SRCREV = "a04344f9d95366bf2cd7b99bcb6c4f5a41cd5493"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=ba8146ad9cc2a128209983265136e06a"

FILES_${PN} += "${GOBIN_FINAL}/*"

RDEPENDS_${PN}-dev += "bash python-core"

SRC_URI += "file://influxdb.conf file://influxdb.service"

do_install_append() {
    # config
    install -d ${D}${sysconfdir}/influxdb/
    install -m 0644 ${WORKDIR}/influxdb.conf ${D}${sysconfdir}/influxdb/

    # systemd
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/influxdb.service ${D}${systemd_unitdir}/system/
}

SYSTEMD_SERVICE_${PN} = "influxdb.service"

