DESCRIPTION = "This is an agent for prometheus to collect and transmit mongodb information."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

GO_LINKSHARED = ""
GO_IMPORT = "github.com/percona/mongodb_exporter"
SRC_URI = "git://${GO_IMPORT} \
    file://mongodb_exporter.service \
"

SRCREV = "bf683745093a9210ebacbeb235bb792e21d17389"
PV = "0.10.0"

inherit go systemd

RDEPENDS_${PN}-staticdev += "bash"

do_install_append() {
    # systemd
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/mongodb_exporter.service ${D}${systemd_unitdir}/system/
}

SYSTEMD_SERVICE_${PN} = "mongodb_exporter.service"
