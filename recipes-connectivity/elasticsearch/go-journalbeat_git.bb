DESCRIPTION = "Journalbeat is a log shipper from systemd/journald to Logstash/Elasticsearch"
HOMEPAGE = "https://www.elastic.co/products/beats"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=5c969c520de466849d2984b24fbd8ee4"

GO_IMPORT = "github.com/mheese/journalbeat"
SRC_URI = "git://${GO_IMPORT} \
    file://journalbeat.service \
    file://journalbeat.yml \
    file://logstash-beats.crt \
"
PV = "1.1+git${SRCPV}"
SRCREV = "5ecb7ddc8c7dc1cdd6822c21b7a3d48804583ff3"

inherit go systemd

DEPENDS = "systemd"

do_install_append() {
    # systemd
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/journalbeat.service ${D}${systemd_unitdir}/system/

    # config
    install -d ${D}${sysconfdir}/journalbeat
    install -m 0644 ${WORKDIR}/journalbeat.yml ${D}${sysconfdir}/journalbeat/

    # CA cert
    install -d ${D}${sysconfdir}/ssl/certs
    install -m 644 ${WORKDIR}/logstash-beats.crt ${D}${sysconfdir}/ssl/certs/
}

SYSTEMD_SERVICE_${PN} = "journalbeat.service"

RDEPENDS_${PN}-dev += "bash"
