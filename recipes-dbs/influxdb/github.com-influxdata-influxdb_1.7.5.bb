DESCRIPTION = "github.com/influxdata/influxdb"

GO_IMPORT = "github.com/influxdata/influxdb"

GO_LINKSHARED = ""
inherit go systemd

SRC_URI = "git://github.com/nefethael/influxdb;branch=1.7-with-vendors"
SRCREV = "54fa3b7df19092b38a4dd7de134cb78bae53d245"

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

