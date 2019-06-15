FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://mongod.conf file://mongodb.service"

inherit systemd

do_install_append() {
    # config
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/mongod.conf ${D}${sysconfdir}/

    # systemd
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/mongodb.service ${D}${systemd_unitdir}/system/
}

SYSTEMD_SERVICE_${PN} = "mongodb.service"
