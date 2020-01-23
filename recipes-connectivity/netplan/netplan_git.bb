SUMMARY = "Network configuration tool using YAML"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "git://github.com/CanonicalLtd/netplan;protocol=https \
    file://0001-Replace-hardcoded-python3-path.patch \
    file://0003-Change-default-generate-path.patch \
        file://10-default.yaml \
"

PV = "0.98+git${SRCPV}"
SRCREV = "9ea105bef2cea28916213aad93a37ea85d89fdbc"

S = "${WORKDIR}/git"

DEPENDS = "glib-2.0 libyaml"
RDEPENDS_${PN} += "python3 python3-pyyaml python3-netifaces"

inherit pkgconfig systemd python3-dir

#SYSTEMD_SERVICE_${PN} = "netplan-wpa@.service"

do_compile () {
    oe_runmake netplan/_features.py generate
}

do_install () {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/netplan/cli/commands
    install -m 0755 ${S}/generate ${D}${PYTHON_SITEPACKAGES_DIR}/netplan/generate
    install -m 0755 ${S}/src/netplan.script ${D}${PYTHON_SITEPACKAGES_DIR}/netplan/netplan.script
        
    cd ${S}
    find ./netplan/ -name '*.py' -exec install -Dm 644 "{}" "${D}${PYTHON_SITEPACKAGES_DIR}/netplan/{}" \;
    cd -
        
    install -d -m 0755 ${D}${bindir}
    install -d -m 0755 ${D}${libdir}/netplan
    ln -s ../../../${PYTHON_SITEPACKAGES_DIR}/netplan/netplan.script ${D}${bindir}/netplan

    install -d ${D}${systemd_unitdir}/system-generators
    ln -s ../../../${PYTHON_SITEPACKAGES_DIR}/netplan/generate ${D}${systemd_unitdir}/system-generators/netplan
    ln -s ../../../${PYTHON_SITEPACKAGES_DIR}/netplan/generate ${D}${libdir}/netplan/generate
  
    #install -d ${D}${systemd_unitdir}/system/
    #install -D -m 644 ${S}/src/netplan-wpa@.service ${D}${systemd_unitdir}/system/netplan-wpa@.service
}

do_install_append() {
    # config
    install -d ${D}${sysconfdir}/netplan
    install -m 0644 ${WORKDIR}/10-default.yaml ${D}${sysconfdir}/netplan
}

FILES_${PN} += "${PYTHON_SITEPACKAGES_DIR}/netplan ${systemd_unitdir}/system-generators/netplan"
