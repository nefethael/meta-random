SUMMARY = "Simple Library application  on Yocto"
DESCRIPTION = "The peakusb package implements can managment daemon."
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI =  "file://peakusb.service"
          
PV = "1.0"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
    # systemd
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/peakusb.service ${D}${systemd_unitdir}/system/
}

inherit systemd allarch

SYSTEMD_SERVICE_${PN} = "peakusb.service"
