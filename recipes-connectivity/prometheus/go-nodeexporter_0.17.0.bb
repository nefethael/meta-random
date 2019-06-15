DESCRIPTION = "This is an agent for prometheus to collect and transmit system information."
HOMEPAGE = "https://prometheus.io/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

GO_LINKSHARED = ""
GO_IMPORT = "github.com/prometheus/node_exporter"
SRC_URI = "git://${GO_IMPORT} \
	   file://node_exporter.service \
           file://node_exporter.sysconfig \
"

SRCREV = "f9dd8e9b8c29f6c9da676036d8a8c587326bb710"
PR = "r1"

inherit go systemd

RDEPENDS_${PN} += "gawk bash"

do_install_append() {
    # systemd
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/node_exporter.service ${D}${systemd_unitdir}/system/

    # sysconfig
    install -d ${D}${sysconfdir}/sysconfig/
    install -m 0644 ${WORKDIR}/node_exporter.sysconfig ${D}${sysconfdir}/sysconfig/node_exporter

}

SYSTEMD_SERVICE_${PN} = "node_exporter.service"
