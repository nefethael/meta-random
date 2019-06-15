LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=f8edd7ee5ca3e71470e9f73c285d79b8"

SRC_URI = "git://github.com/rbrito/usbmount;protocol=https"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "0ada45281dab978130d78fe8b5cca24b4e9590e0"

S = "${WORKDIR}/git"

inherit systemd

SRC_URI += "\
    file://usbmount-rules-fix.patch \
    file://usbmount-use-udev-environment-instead-of-blkid.patch \
"


do_install() {
    # udev rules
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${S}/90-usbmount.rules ${D}${sysconfdir}/udev/rules.d/90-usbmount.rules

    # usbmount conf
    install -d ${D}${sysconfdir}/usbmount/usbmount.d
    install -m 0755 ${S}/00_create_model_symlink ${D}${sysconfdir}/usbmount/usbmount.d/00_create_model_symlink
    install -m 0755 ${S}/00_remove_model_symlink ${D}${sysconfdir}/usbmount/usbmount.d/00_remove_model_symlink
    install -m 0644 ${S}/usbmount.conf ${D}${sysconfdir}/usbmount/usbmount.conf

    # usbmount script
    install -d ${D}${datadir}/usbmount
    install -m 0755 ${S}/usbmount ${D}${datadir}/usbmount/usbmount

    # systemd service
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/usbmount@.service ${D}${systemd_unitdir}/system

    # prepare media mount points
    install -d ${D}/media
    for i in 0 1 2 3 4 5 6 7
    do
        install -d ${D}/media/usb$i
    done
}

FILES_${PN} = "${sysconfdir}/udev ${sysconfdir}/usbmount ${datadir}/usbmount /media"
RDEPENDS_${PN} = "udev lockfile-progs"

# to replace udev-extra-rules from meta-oe
RPROVIDES_${PN} = "udev-extra-rules"
RREPLACES_${PN} = "udev-extra-rules"
RCONFLICTS_${PN} = "udev-extra-rules"

SYSTEMD_SERVICE_${PN} = "usbmount@.service"
