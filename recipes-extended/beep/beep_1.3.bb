LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

SRC_URI = " \
    http://www.johnath.com/beep/beep-${PV}.tar.gz \
"

SRC_URI[md5sum] = "49c340ceb95dbda3f97b2daafac7892a"
SRC_URI[sha256sum] = "59acef7a987de5557cefd1a904666cc2691f132929af39e65450b182a581ec2d"

EXTRA_OEMAKE += 'CC="${CC}"'
EXTRA_OEMAKE += 'FLAGS="${CFLAGS} ${LDFLAGS}"'

do_install() {
	install -d "${D}${bindir}"
	install -m 0755 ${B}/beep "${D}${bindir}/beep"
}

