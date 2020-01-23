SUMMARY = "AdoptOpenJDK's official binary release"
HOMEPAGE = "https://adoptopenjdk.net/index.html"
LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3e0b59f8fac05c3c03d4a26bbda13f8f"

SRC_URI = "https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u242-b08/OpenJDK8U-jre_x64_linux_hotspot_${PV}.tar.gz"
SRC_URI[md5sum] = "0845bed731b881cd21ecd3d0137ef517"
SRC_URI[sha256sum] = "5edfaefdbb0469d8b24d61c8aef80c076611053b1738029c0232b9a632fe2708"

DEPENDS = "patchelf-native zlib alsa-lib"

libdir_jvm ?= "${libdir}/jvm"
JRE_HOME = "${libdir_jvm}/openjre-8"
PATCHELF_DIR = "${STAGING_DIR_NATIVE}/usr/bin"

S = "${WORKDIR}/jdk8u242-b08-jre"

do_install () {
    install -d -m 0755 ${D}${JRE_HOME}
    cp -R --no-dereference --preserve=mode,links -v ${S}/* ${D}${JRE_HOME}

    rm -rf ${D}${JRE_HOME}/bin/.debug
    rm -rf ${D}${JRE_HOME}/lib/.debug
    rm -rf ${D}${JRE_HOME}/lib/amd64/.debug
    rm -rf ${D}${JRE_HOME}/lib/amd64/jli/.debug
    rm -rf ${D}${JRE_HOME}/lib/amd64/server/.debug
    rm -rf ${D}${JRE_HOME}/man

    ${PATCHELF_DIR}/patchelf --remove-needed libX11.so.6     ${D}${JRE_HOME}/bin/policytool
    ${PATCHELF_DIR}/patchelf --remove-needed libXrender.so.1 \
                             --remove-needed libXtst.so.6 \
                             --remove-needed libXi.so.6 \
                             --remove-needed libXext.so.6 \
                             --remove-needed libX11.so.6     ${D}${JRE_HOME}/lib/amd64/libawt_xawt.so
    ${PATCHELF_DIR}/patchelf --remove-needed libXext.so.6 \
                             --remove-needed libX11.so.6     ${D}${JRE_HOME}/lib/amd64/libsplashscreen.so

    ln -sf lib ${D}/lib64
}

do_configure[noexec] = "1"

do_compile[noexec] = "1"

INSANE_SKIP_${PN} = "debug-files dev-so" 

PACKAGES = "${PN}"
FILES_${PN} = "/lib64 ${libdir}/jvm ${bindir}/*"

RDEPENDS_${PN} += "libasound zlib"

COMPATIBLE_MACHINE = "(intel-corei7-64|qemux86|qemux86-64|genericx86|genericx86-64)"

RPROVIDES_${PN} = "java2-runtime"

inherit update-alternatives

ALTERNATIVE_${PN} = "java"
ALTERNATIVE_LINK_NAME[java] = "${bindir}/java"
ALTERNATIVE_TARGET[java] = "${JRE_HOME}/bin/java"
ALTERNATIVE_PRIORITY[java] = "100"

