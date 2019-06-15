DESCRIPTION = "github.com/influxdata/telegraf"

GO_IMPORT = "github.com/influxdata/telegraf"
GO_LINKSHARED = ""

inherit go systemd

SRC_URI = "git://github.com/nefethael/telegraf;branch=1.10-with-vendors"
SRCREV = "46aff5e426c9f2fd80988eff57d1b45c3d51c1c0"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=96cd9a86f733dbfec4107613b9b27c71"

FILES_${PN} += "${GOBIN_FINAL}/*"

RDEPENDS_${PN}-dev += "bash python-core"

SRC_URI += "file://telegraf.conf file://telegraf.service"

do_install_append() {
    # config
    install -d ${D}${sysconfdir}/telegraf/
    install -m 0644 ${WORKDIR}/telegraf.conf ${D}${sysconfdir}/telegraf/

    # systemd
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/telegraf.service ${D}${systemd_unitdir}/system/
}

SYSTEMD_SERVICE_${PN} = "telegraf.service"

INSANE_SKIP_${PN}-dev = "arch file-rdeps"
INSANE_SKIP_${PN}-dbg = "arch file-rdeps"
