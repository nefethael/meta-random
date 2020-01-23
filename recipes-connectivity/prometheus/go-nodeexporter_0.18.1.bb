DESCRIPTION = "This is an agent for prometheus to collect and transmit system information."
HOMEPAGE = "https://prometheus.io/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

GO_LINKSHARED = ""
GO_IMPORT = "github.com/prometheus/node_exporter"
SRC_URI = "git://${GO_IMPORT};branch=release-0.18 \
	   file://node_exporter.service \
           file://node_exporter.sysconfig \
"

SRCREV = "3db77732e925c08f675d7404a8c46466b2ece83e"

inherit go systemd

RDEPENDS_${PN} += "gawk bash"
RDEPENDS_${PN}-dev += "gawk bash"

do_install_append() {
    # systemd
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/node_exporter.service ${D}${systemd_unitdir}/system/

    # sysconfig
    install -d ${D}${sysconfdir}/sysconfig/
    install -m 0644 ${WORKDIR}/node_exporter.sysconfig ${D}${sysconfdir}/sysconfig/node_exporter

    # clean examples
    rm -rf ${D}${libdir}/go/src/${GO_IMPORT}/text_collector_examples
    rm -rf ${D}${libdir}/go/src/${GO_IMPORT}/examples
}

SYSTEMD_SERVICE_${PN} = "node_exporter.service"
